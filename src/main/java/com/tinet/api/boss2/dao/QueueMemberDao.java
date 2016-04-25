package com.tinet.api.boss2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

/**
 * queue_member表读写
 * <p>
 *  FileName： QueueMemberDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("queueMemberDao")
public class QueueMemberDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 查询企业当前在线座席数
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param type 座席类型
	 * @return
	 */
	public Integer getClientCount(Integer enterpriseId, Integer type) {
		String onlineClientSql = "select count(distinct(member_name)) as count from queue_member where queue_name like ? and online=1 " +
				" and client_id in (select id from client where client_type = ? and enterprise_id = ?)";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(onlineClientSql, enterpriseId+"%", type, enterpriseId);
		if (rSet.next()) {
			return rSet.getInt("count");
		} else {
			return 0;
		}
	}
	
	/**
	 * 检查企业在线电脑座席数和电话座席数是否大于当前要修改为的座席数
	 * @param enterpriseId 企业ID
	 * @param newClientWeb 变更后付费电脑座席数
	 * @param newClientFree 变更后赠送电脑座席数
	 * @param newClientTel 变更后电话座席数
	 * @param oldClientWeb 原付费电脑座席数
	 * @param oldClientFree 原赠送电脑座席数
	 * @param oldClientTel 原电话座席数
	 * @return
	 */
	public String checkOnlineClientCount(Integer enterpriseId, Integer newClientWeb, Integer newClientFree, Integer newClientTel, 
			Integer oldClientWeb, Integer oldClientFree, Integer oldClientTel) {
		String error = null;
		// 当总电脑座席数减少时
		if ((oldClientWeb + oldClientFree ) > (newClientWeb + newClientFree)){ 
			// 检查当前在线电脑座席数
			Integer onlineClientWeb = this.getClientCount(enterpriseId, 2);
			if (onlineClientWeb > 0 && onlineClientWeb > (newClientWeb + newClientFree)) {
				error =  "当前在线电脑座席总数" + onlineClientWeb + "个 >要变更为的（“付费电脑座席数”" + newClientWeb + " + “赠送电脑座席数”" + newClientFree 
						+ "）个。 请下线部分电脑座席后提交变更！";
				return error;
			}
			
		}
		
		// 当电话座席数减少时
		if (oldClientTel > newClientTel) {
			// 检查当前在线电话座席数
			Integer onlineClientTel = this.getClientCount(enterpriseId, 1);
			if ( onlineClientTel > 0 &&  onlineClientTel > newClientTel) {
				error = "当前在线电话座席总数" + onlineClientTel + "个 > 要变更为的“电话座席数”" + newClientTel + "个。 请座席下线部分电话座席后提交变更！" ;
				return error;
			}
		}
		return error;
	}
}
