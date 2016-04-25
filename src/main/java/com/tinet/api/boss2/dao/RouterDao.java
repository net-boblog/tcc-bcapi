package com.tinet.api.boss2.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Gateway;
import com.tinet.api.boss2.model.Router;
import com.tinet.api.boss2.model.Routerset;
import com.tinet.common.util.StringUtil;

/**
 * router表读写
 * <p>
 *  FileName： RouterDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("routerDao")
public class RouterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RoutersetDao routersetDao;
	@Autowired
	private GatewayDao gatewayDao;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 获取某企业路由组的详细规则配置
	 * @author louxue
	 * @return
	 */
	public List<Router> getAllRouter(Integer routersetId){
		List<Router> routerList = new ArrayList<Router>();
		StringBuffer getRouerSql = new StringBuffer("");
		getRouerSql.append("select r.id as id, r.prefix as prefix,r.priority as priority,r.description as description, ")
			.append(" gw.ip_addr as ipAddr,gw.id as gwId")
			.append(" from router r,gateway gw where r.routerset_id = ? and r.gateway_id = gw.id order by prefix,priority");
		
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(getRouerSql.toString(), routersetId);
		while (rSet.next()) {
			Router router = new Router();
			router.setId(rSet.getInt("id"));
			router.setPrefix(rSet.getString("prefix"));
			router.setPriority(rSet.getInt("priority"));
			router.setDescription(rSet.getString("description"));
			Gateway gw = new Gateway();
			gw.setId(rSet.getInt("gwId"));
			gw.setIpAddr(rSet.getString("ipAddr"));
			router.setGateway(gw);
			router.setRoutersetId(routersetId);
			routerList.add(router);
		}
		
		return routerList;
	}
	
	/**
	 * 获取某平台所有企业路由组的所有详细规则配置
	 * @author louxue
	 * @return
	 */
	public List<Router> getAllRouter(){
		List<Router> routerList = new ArrayList<Router>();
		StringBuffer getRouerSql = new StringBuffer("select id,routerset_id,prefix,gateway_id,priority,description from router order by id asc;");
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(getRouerSql.toString());
		while (rowSet.next()) {
			Router router = new Router();
			router.setId(rowSet.getInt("id"));
			router.setRoutersetId(rowSet.getInt("routerset_id"));
			router.setPrefix(rowSet.getString("prefix"));
			router.setGatewayId(rowSet.getInt("gateway_id"));
			router.setPriority(rowSet.getInt("priority"));
			router.setDescription(rowSet.getString("description"));
			routerList.add(router);
		}
		
		return routerList;
	}
	
	/**
	 * router表新增一条数据
	 * @author louxue
	 * @param router router对象
	 */
	@Transactional
	public void insert(Router router) {
		String insterSql = "insert into router(routerset_id,prefix,gateway_id,priority,description) values(?,?,?,?,?)";
		jdbcTemplate.update(insterSql, router.getRoutersetId(), router.getPrefix(), router.getGatewayId(), router.getPriority(), router.getDescription());
	}
	
	/**
	 * 修改一条router数据
	 * @author louxue
	 * @param router router对象
	 */
	@Transactional
	public void update(Router router) {
		String updateSql = "update router set prefix=?,gateway_id=?,priority=?,description=? where id=?" ;
		jdbcTemplate.update(updateSql, router.getPrefix(), router.getGatewayId(), router.getPriority(), router.getDescription(), router.getId());
	}
	
	/**
	 * router表删除一条数据
	 * @param id 路由组一条详细配置id
	 */
	@Transactional
	public void delete(Integer id) {
		// 删除
		String delSql = "delete from router where id =?" ;
		jdbcTemplate.update(delSql, id);
	}
	
	/**
	 * 批量新增路由组详细规则配置
	 * @author louxue
	 * @param routerList router对象集合
	 */
	@Transactional
	public void batchInsert(List<Router> routerList) {
		if (routerList!=null && routerList.size()>0) {
			for (Router router : routerList) {
				this.insert(router);
			}
		}
	}
	
	/**
	 * 批量更新路由组详细规则配置
	 * @author louxue
	 * @param routerList
	 */
	@Transactional
	public void batchUpdate(List<Router> routerList) {
		if (routerList!=null && routerList.size() > 0) {
			// 构造批量修改参数
			List<Object[]> argsList = new ArrayList<Object[]>();
			for (Router router : routerList) {
				argsList.add(new Object[]{router.getPrefix(), router.getGatewayId(), router.getPriority(), router.getDescription(), router.getId()});
			}
			
			// 批量更新
			String updateSql = "update router set prefix=?,gateway_id=?,priority=?,description=? where id=?" ;
			int[] argsTypes = {Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.INTEGER};
			jdbcTemplate.batchUpdate(updateSql, argsList, argsTypes);
		}
	}
	
	/**
	 * 批量删除路由组详细规则配置
	 * @author louxue
	 * @param routerList
	 */
	@Transactional
	public void batchDelete(List<Integer> ids) {
		if (ids!=null && ids.size()>0) {
			// 构造批量删除sql
			List<Object[]> argsList = new ArrayList<Object[]>();
			for (Integer id : ids) {
				argsList.add(new Object[]{id});
			}
			
			// 执行删除
			String delSql = "delete from router where id =?" ;
			int[] argsTypes = {Types.INTEGER};
			jdbcTemplate.batchUpdate(delSql, argsList, argsTypes);
		}
		
	}
	
	/**
	 * 查询某使用某网关设备的所有路由组id，多个之间使用英文逗号分隔。
	 * @param gatewayId
	 * @return
	 */
	public String getDifRoutersetIds(Integer gatewayId) {
		String routersetIds = "";
		String quryRouterSql = "select distinct routerset_id as id from router where gateway_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(quryRouterSql, gatewayId);
		while (rowSet.next()) {
			routersetIds += rowSet.getObject("id")+",";
		}
		if (routersetIds!=null && !routersetIds.isEmpty()) {
			routersetIds = StringUtil.substringBeforeLast(routersetIds, ",");
		}
		return routersetIds;
	}
	
	/**
	 * 批量新增gateway，routerset和router
	 * @param gatewayListAdd
	 * @param routersetListAdd
	 * @param routerListAdd
	 */
	@Transactional
	public void batchSave(List<Gateway> gatewayListAdd, List<Routerset> routersetListAdd, List<Router> routerListAdd) {
		// gateway新增
		for (Gateway gateway : gatewayListAdd) {
			gatewayDao.insert(gateway);
		}
		
		// routerset新增
		for (Routerset routerset : routersetListAdd) {
			routersetDao.insert(routerset.getId(), routerset.getName(), routerset.getDescription());
		}
		
		// router新增
		this.batchInsert(routerListAdd);
	}
}
