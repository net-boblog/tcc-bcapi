package com.tinet.api.boss2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Gateway;

/**
 * gateway表读写
 * <p>
 *  FileName： GatewayDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("gatewayDao")
public class GatewayDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 查询平台基本路由信息
	 * @author louxue
	 * @return
	 */
	@Transactional
	public List<Gateway> getAllGateway()  {
		List<Gateway> gatewayList = new  ArrayList<Gateway>();
		String qrySql = "select id, prefix, postfix, ip_addr, port, area_code, description, call_limit,"
				+ "disallow, allow, dtmf_mode, register from gateway order by prefix,ip_addr,description";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(qrySql);
		while(rs.next()){
			Gateway gateway = new Gateway();
			gateway.setId(rs.getInt("id"));
			gateway.setName(rs.getString("description"));
			gateway.setPrefix(rs.getString("prefix"));
			gateway.setPostfix(rs.getString("postfix"));
			gateway.setIpAddr(rs.getString("ip_addr"));
			gateway.setPort(rs.getInt("port"));
			gateway.setAreaCode(rs.getString("area_code"));
			gateway.setCallLimit(rs.getInt("call_limit"));
			gateway.setDisallow(rs.getString("disallow"));
			gateway.setAllow(rs.getString("allow"));
			gateway.setDtmf(rs.getString("dtmf_mode"));
			gateway.setRegister(rs.getInt("register"));
			gatewayList.add(gateway);
		}
		
		return gatewayList;
	}
	
	/**
	 * 新增一条网关配置
	 * @param gateway
	 */
	@Transactional
	public void insert(Gateway gateway) {
		if (gateway.getId()==null) {
			String insertSql = "insert into gateway(prefix,ip_addr,port,area_code,call_limit,description,disallow,allow,dtmf_mode,register) values(?,?,?,?,?,?,?,?,?,?)";
			jdbcTemplate.update(insertSql, gateway.getPrefix(), gateway.getIpAddr(), gateway.getPort(), gateway.getAreaCode(), 
					gateway.getCallLimit(), gateway.getName(), gateway.getDisallow(), gateway.getAllow(), gateway.getDtmf(), gateway.getRegister());
		} else {
			String insertSql = "insert into gateway(id,prefix,ip_addr,port,area_code,call_limit,description,disallow,allow,dtmf_mode,register) values(?,?,?,?,?,?,?,?,?,?,?)";
			jdbcTemplate.update(insertSql, gateway.getId(), gateway.getPrefix(), gateway.getIpAddr(), gateway.getPort(), gateway.getAreaCode(), 
					gateway.getCallLimit(), gateway.getName(), gateway.getDisallow(), gateway.getAllow(), gateway.getDtmf(), gateway.getRegister());
		}
	}
	
	/**
	 * 删除一条网关设备
	 * @param gatewayId 网关id
	 * @return
	 */
	@Transactional
	public String delete(Integer gatewayId) {
		// 查询该网关设备是否在号码绑定规则中被使用
		String qurySql = "select count(*) as num from router where gateway_id =?";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qurySql, gatewayId);
		if (rSet.next()) {
			int existNum = rSet.getInt("num");
			if (existNum!=0) {
				return "存在号码绑定规则使用该网关的路由组，不能删除！";
			}
		}
		
		// 删除
		String delSql = "delete from gateway where id =?" ;
		jdbcTemplate.update(delSql, gatewayId);
		return null;
	}
	
	/**
	 * 修改一条网关配置信息
	 * @param gateway
	 */
	@Transactional
	public void update(Gateway gateway) {
		String updateSql = "update gateway set prefix=?,area_code=?,ip_addr=?,description=?,port=?,call_limit=?,disallow=?,allow=?,dtmf_mode=?,register=? where id=?";
		jdbcTemplate.update(updateSql, gateway.getPrefix(), gateway.getAreaCode(), gateway.getIpAddr(), gateway.getName(), gateway.getPort(),
				gateway.getCallLimit(), gateway.getDisallow(), gateway.getAllow(), gateway.getDtmf(), gateway.getRegister(), gateway.getId());
	}
	
	/**
	 * 获取gateway表下一个序列值
	 * @return
	 */
	public Integer getNextSeq() {
		String qrySql = "select nextval('gateway_id_seq') as id;";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qrySql);
		if (rSet.next()) {
			return rSet.getInt("id");
		} 
		return -1;
	}
}
