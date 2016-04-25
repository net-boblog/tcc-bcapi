package com.tinet.api.boss2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.SystemSetting;
import com.tinet.common.inc.Const;

/**
 * <p>
 *  FileName： SystemSettingDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("systemSettingDao")
public class SystemSettingDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 获取平台系统设置
	 * @author zhancy
	 * @return
	 */
	public List<SystemSetting> getAllSysSetting() {
		List<SystemSetting> list = new ArrayList<SystemSetting>();
		String sql = "select id, name, value, property, create_time from system_setting order by id";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while(rs.next()) {
			SystemSetting systemSetting = new SystemSetting();
			systemSetting.setId(rs.getInt("id"));
			systemSetting.setName(rs.getString("name"));
			systemSetting.setValue(rs.getString("value"));
			systemSetting.setProperty(rs.getString("property"));
			systemSetting.setCreateTime(rs.getDate("create_time"));
			list.add(systemSetting);
		}
		return list;
	}
	
	/**
	 * 修改企业system_setting某id的value值
	 * @author zhancy
	 * @param id 企业id
	 * @param value 企业配置值
	 * @return
	 */
	@Transactional
	public Integer updateSysSetting(Integer id, String value, String property){
		String sql = "";
		// 是否更新property字段，依据参数property是否为null
		if (property!=null) {
			sql = "update system_setting set value = ?,property = ? where id =? ; ";
			return jdbcTemplate.update(sql, value, property, id);
		} else {
			sql = "update system_setting set value =? where id =? ;";
			return jdbcTemplate.update(sql, value, id);
		}
	}
	
	/**
	 * 修改企业system_setting某name的value值
	 * @author zhancy
	 * @param name
	 * @param value 企业配置值
	 * @return
	 */
	@Transactional
	public Integer updateSysSettingByName(String name, String value){
		String sql = "update system_setting set value = ? where name =? ; ";
		return jdbcTemplate.update(sql, value, name);
	}

	/**
	 * 获取分平台接口用户名及密码
	 */
	public SystemSetting getUsrPwd(){
		SystemSetting systemSetting = new SystemSetting();
		String sql = "select value,property from system_setting where name= ?";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, Const.CCIC_SYSTEM_SETTING_NAME_INTERFACE_AUTH);
		if(rs.next()){
			systemSetting.setValue(rs.getString("value"));			//用户名
			systemSetting.setProperty(rs.getString("property"));	//密码
		}
		return systemSetting;
	}
	
}
