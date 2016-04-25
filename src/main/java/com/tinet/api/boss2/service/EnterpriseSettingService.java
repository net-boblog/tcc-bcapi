package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.EnterpriseSettingDao;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 企业配置业务逻辑处理层
 * <p>
 *  FileName： EnterpriseSettingService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseSettingService")
public class EnterpriseSettingService {
	@Autowired
	private EnterpriseSettingDao enterpriseSettingDao;
	
	/**
	 * 获取企业其他配置
	 * @param ccicId
	 * @param enterpriseId
	 * @return
	 */
	public List<EnterpriseSetting> getOtherConf(String ccicId, Integer enterpriseId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		return enterpriseSettingDao.getOtherConf(enterpriseId);
	}
	
	/**
	 * 更新企业配置
	 * @param ccicId 平台ID
	 * @param entSetting 企业配置对象
	 */
	public void instOrUpdateEntSetting(String ccicId, EnterpriseSetting entSetting){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		enterpriseSettingDao.instOrUpdateEntSetting(entSetting);
	}

}
