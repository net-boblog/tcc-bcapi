package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.SystemSettingDao;
import com.tinet.api.boss2.model.SystemSetting;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 平台管理涉资业务逻辑处理层
 * <p>
 *  FileName： SystemSettingService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("systemSettingService")
public class SystemSettingService {
	
	@Autowired
	SystemSettingDao systemSettingDao;
	
	/**
	 * 查询平台系统设置
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @return
	 */
	public List<SystemSetting> getAllSysSetting(String ccicId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		// 获取设置信息
		List<SystemSetting> list = systemSettingDao.getAllSysSetting();
		return list;
	}
	
	/**
	 * 修改企业system_setting某id的value值
	 * @author zhancy
	 * @param id 企业id
	 * @param value 企业配置值
	 * @return
	 */
	public void updateSysSetting(String ccicId, Integer id, String value, String property){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		systemSettingDao.updateSysSetting(id, value, property);
	}

	/**
	 * 修改企业system_setting某name的value值
	 * @author zhancy
	 * @param name
	 * @param value 企业配置值
	 * @return
	 */
	public void updateSysSettingByName(String ccicId, String name, String value){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		systemSettingDao.updateSysSettingByName(name, value);
	}
	
	/**
	 * 获取分平台接口用户名及密码
	 * @param ccicId 平台ID
	 */
	public SystemSetting getUsrPwd(String ccicId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		SystemSetting systemSetting = systemSettingDao.getUsrPwd();
		return systemSetting;
	}
}
