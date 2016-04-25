package com.tinet.api.boss2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.EnterpriseComboDao;
import com.tinet.api.boss2.model.EnterpriseCombo;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * <p>
 *  FileName： EnterpriseComboService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseComboService")
public class EnterpriseComboService {
	@Autowired
	private EnterpriseComboDao enterpriseComboDao;
	
	/**
	 * 根据enterpriseId查询企业当前月套餐使用明细
	 * @return
	 */
	public EnterpriseCombo getEntComboDetail(String ccicId, String enterpriseId){
		// 切换数据源ID
		DatabaseContextHolder.setDBType(ccicId);
		return enterpriseComboDao.getEntComboDetail(enterpriseId);
	}
	
}
