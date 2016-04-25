package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.EnterpriseHotlineDao;
import com.tinet.api.boss2.model.EnterpriseHotline;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 客户热线号码业务逻辑处理层
 * <p>
 *  FileName： EnterpriseHotlineSevice.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseHotlineService")
public class EnterpriseHotlineService {
	@Autowired
	private EnterpriseHotlineDao enterpriseHotlineDao;
	
	/**
	 * 查询一个企业的所有热线号码，400/1010号码和直线号码对应的目的码含区号。
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public List<EnterpriseHotline> getAllHotline(String ccicId, Integer enterpriseId){
		DatabaseContextHolder.setDBType(ccicId); // 切换数据源id
		return enterpriseHotlineDao.getAllHotlineList(enterpriseId);
	}
	
	/**
	 * 修改企业的热线号码最低消费信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param idChange enterprise_hotline表id串，多个用英文逗号分隔
	 * @param lowestCostChange 当前计费周期最低消费字符串，多个用英文逗号分隔
	 * @param lowestCostNextChange 下个计费周期最低消费字符串，多个用英文逗号分隔
	 * @param lowestCostModeChange 当前计费周期计费模式字符串，多个用英文逗号分隔
	 */
	public void saveLowestCost(String ccicId, Integer enterpriseId, String idChange, String lowestCostChange, 
			String lowestCostNextChange, String lowestCostModeChange) {
		DatabaseContextHolder.setDBType(ccicId); // 切换数据源id
		enterpriseHotlineDao.saveLowestCost(enterpriseId, idChange, lowestCostChange, lowestCostNextChange, lowestCostModeChange);
	}

}
