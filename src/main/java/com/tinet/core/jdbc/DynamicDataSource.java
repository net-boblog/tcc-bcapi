package com.tinet.core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @author wangguiyu
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private Map<Object, Object> _targetDataSources;

	/**
	 * @describe 数据源为空或者为0时，自动切换至默认数据源，即在配置文件中定义的dataSource数据
	 */
	@Override
	protected Object determineCurrentLookupKey() {
//		return DatabaseContextHolder.getDBType();
		String dataSourceName = DatabaseContextHolder.getDBType();
		if (dataSourceName == null || dataSourceName.equals("0")) {
			dataSourceName = "dataSource";
		} 
		else {
			this.selectDataSource(dataSourceName);
		}
//		log.debug("--------> use datasource " + dataSourceName); 
		return dataSourceName;
	}

	/** 
	 * @param serverId 
	 * @describe 数据源存在时不做处理，不存在时创建新的数据源链接，并将新数据链接添加至缓存 
	 */
	public void selectDataSource(String serverId) {
		String sid = DatabaseContextHolder.getDBType();
		if ("0".equals(serverId)) {
			DatabaseContextHolder.setDBType("0");
			return;
		}

		Object obj = this._targetDataSources.get(serverId);
		if (obj != null && sid!=null && sid.equals(serverId)) {
			return;
		} else {
			BasicDataSource dataSource = this.getDataSource(serverId);
			if (null != dataSource)
				this.setDataSource(serverId, dataSource);
		}
	}

	/** 
	 * @describe 查询serverId对应的数据源记录 
	 * @param serverId 
	 *  @return 
	 */
	public BasicDataSource getDataSource(String serverId) {
		this.selectDataSource("0");
		this.determineCurrentLookupKey();
		Connection conn = null;
		HashMap<String, Object> map = null;
		try {
			conn = this.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ccic WHERE id = ?");
			ps.setInt(1, Integer.parseInt(serverId));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map = new HashMap<String, Object>();
				map.put("DBS_DriverClassName", "org.postgresql.Driver");
				map.put("DBS_ID", rs.getInt("id"));
				map.put("DBS_IP", rs.getString("ccic_ip"));
				map.put("DBS_PORT", rs.getString("ccic_port"));
				map.put("DBS_DB", rs.getString("ccic_db"));
				map.put("DBS_USERNAME", rs.getString("ccic_user"));
				map.put("DBS_PASSWORD", rs.getString("ccic_pwd"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			log.error(e.toString());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.toString());
			}
		}
		if (null != map) {
			String url = "";
			String driverClassName = map.get("DBS_DriverClassName").toString();
			String ip = map.get("DBS_IP").toString();
			String port = map.get("DBS_PORT").toString();
			String db = map.get("DBS_DB").toString();
			String userName = map.get("DBS_USERNAME").toString();
			String password = map.get("DBS_PASSWORD").toString();
			url = "jdbc:postgresql://" + ip + ":" + port + "/" + db;
			DataBaseConfig dbc = new DataBaseConfig();
			dbc.setUrl(url);
			dbc.setDriverClassName(driverClassName);
			dbc.setPassword(password);
			dbc.setUsername(userName);

			BasicDataSource dataSource = this.createDataSource(dbc);
			return dataSource;
		}
		return null;
	}

	/**
	 * 创建数据源
	 * @param driverClassName
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public BasicDataSource createDataSource(DataBaseConfig dbc) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbc.getDriverClassName());
		dataSource.setUrl(dbc.getUrl());
		dataSource.setUsername(dbc.getUsername());
		dataSource.setPassword(dbc.getPassword());

		dataSource.setInitialSize(dbc.getInitialSize());
		dataSource.setMaxActive(dbc.getMaxActive());
		dataSource.setMaxIdle(dbc.getMaxIdle());
		dataSource.setMinIdle(dbc.getMinIdle());
		dataSource.setMaxWait(dbc.getMaxWait());
		/*        dataSource.setRemoveAbandoned(dbc.isRemoveAbandoned());
		        dataSource.setLogAbandoned(dbc.isLogAbandoned());
		        dataSource.setRemoveAbandonedTimeout(dbc.getRemoveAbandonedTimeout());*/
		dataSource.setTestWhileIdle(true);
		return dataSource;
	}

	/** 
	 * @param serverId 
	 * @param dataSource 
	 */
	public void setDataSource(String serverId, BasicDataSource dataSource) {
		this.addTargetDataSource(serverId, dataSource);
		DatabaseContextHolder.setDBType(serverId);
	}

	public void addTargetDataSource(String key, BasicDataSource dataSource) {
		this._targetDataSources.put(key, dataSource);
		this.setTargetDataSources(this._targetDataSources);
	}

	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		this._targetDataSources = targetDataSources;
		super.setTargetDataSources(this._targetDataSources);
		afterPropertiesSet();
	}

}