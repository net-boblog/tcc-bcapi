package com.tinet.api.boss2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.common.util.MD5Encoder;
import com.tinet.common.util.RandomPwd;

/**
 * enterprise_user表读写
 * <p>
 *  FileName： EnterpriseUserDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseUserDao")
public class EnterpriseUserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	/**
	 * 重置企业后台admin用户密码为8为长度密码
	 * @param enterpriseId
	 * @return 返回重置后的密码
	 */
	@Transactional
	public String resetEntAdminPwd(Integer enterpriseId) {
		String pwd = RandomPwd.randomString(8);
		this.saveEntUserPwd(enterpriseId, "admin", MD5Encoder.encode(pwd));
		return pwd;
	}
	
	/**
	 * 修改企业后台登录某用户的密码
	 * @param enterpriseId 企业ID
	 * @param name 企业后台登录用户
	 * @param pwd md5加密过的密码
	 */
	@Transactional
	public void saveEntUserPwd(Integer enterpriseId, String userName, String pwd){
		String sql = "update enterprise_user set pwd = ? where enterprise_id = ? and name=? ;";
		jdbcTemplate.update(sql, pwd, enterpriseId, userName);
	}
}
