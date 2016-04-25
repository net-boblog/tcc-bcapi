package com.tinet.api.boss2.dao;

import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Sbc;
import com.tinet.common.util.DateUtil;

/**
 * sbc表读写
 * <p>
 *  FileName： SbcDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("sbcDao")
public class SbcDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 获取某平台所有Sbc
	 * @author zhancy
	 * @return
	 */
	public List<Sbc> getAllSbc(){
		List<Sbc> sbcList = new ArrayList<Sbc>();
		String sql = "select id,host,port,password,timeout,area_code,call_limit,active,type,mac,create_time from sbc order by create_time desc";
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql.toString());
		while (rs.next()) {
			Sbc sbc = new Sbc();
			sbc.setId(rs.getInt("id"));
			sbc.setHost(rs.getString("host"));
			sbc.setPort(rs.getInt("port"));
			sbc.setPassword(rs.getString("password"));
			sbc.setTimeout(rs.getInt("timeout"));
			sbc.setAreaCode(rs.getString("area_code"));
			sbc.setCallLimit(rs.getInt("call_limit"));
			sbc.setActive(rs.getInt("active"));
			sbc.setMac(rs.getString("mac"));
			try {
				sbc.setCreateTime(DateUtil.parse(rs.getString("create_time"), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
			} catch (InvalidResultSetAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sbcList.add(sbc);
		}
		return sbcList;
	}
	
	/**
	 * 获取某平台所有Sbc
	 * @author zhancy
	 * @return
	 */
	@Transactional
	public void insert(String host, String port, String password, String timeout, 
			String areaCode, String callLimit, String active, String mac){
		String sql = "insert into sbc (host,port,password,timeout,area_code,call_limit,active,type,mac) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, host, Integer.parseInt(port), password, Integer.parseInt(timeout), 
			areaCode, Integer.parseInt(callLimit), Integer.parseInt(active), 1, mac);
	}
	
	/**
	 * 批量更新
	 * @author zhancy
	 * @param sbcList
	 */
	@Transactional
	public void batchUpdateSbc(List<Sbc> sbcList){
		if (sbcList != null && sbcList.size() > 0) {
			// 构造批量修改参数
			List<Object[]> argsList = new ArrayList<Object[]>();
			for (Sbc sbc : sbcList) {
				argsList.add(new Object[]{sbc.getHost(), sbc.getPort(), sbc.getTimeout(),
					sbc.getAreaCode(), sbc.getCallLimit(), sbc.getActive(), sbc.getMac(), sbc.getId()});
			}
			String sql = "update sbc set host=?,port=?,timeout=?,area_code=?,call_limit=?,active=?,mac=? where id=?";
			int[] argsTypes = {Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.INTEGER};
			jdbcTemplate.batchUpdate(sql, argsList, argsTypes);
		}
	}
	
	/**
	 * 批量更新密码
	 * @author zhancy
	 */
	@Transactional
	public void batchUpdatePwd(List<Sbc> sbcList){
		if (sbcList != null && sbcList.size() > 0) {
			// 构造批量修改参数
			List<Object[]> argsList = new ArrayList<Object[]>();
			for (Sbc sbc : sbcList) {
				argsList.add(new Object[]{sbc.getPassword(), sbc.getId()});
			}
			String sql = "update sbc set password = ? where id = ?";
			int[] argsTypes = {Types.VARCHAR, Types.INTEGER};
			jdbcTemplate.batchUpdate(sql, argsList, argsTypes);
		}
	}
	
	/**
	 * 批量更新多有：包括数据和密码
	 * @author zhancy
	 */
	@Transactional
	public void bathcUpdateSbcAndPwd(List<Sbc> sbcList){
		this.batchUpdateSbc(sbcList);
		this.batchUpdatePwd(sbcList);
	}
	
	/**
	 * 批量删除
	 * @author zhancy
	 * @param ids
	 */
	@Transactional
	public void batchDeleteSbc(List<Integer> ids){
		if (ids != null && ids.size()>0) {
			// 构造批量删除sql
			List<Object[]> argsList = new ArrayList<Object[]>();
			for (Integer id : ids) {
				argsList.add(new Object[]{id});
			}
			String sql = "delete from sbc where id =?";
			int[] argsTypes = {Types.INTEGER};
			jdbcTemplate.batchUpdate(sql, argsList, argsTypes);
		}
	}
}
