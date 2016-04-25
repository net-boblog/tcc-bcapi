package com.tinet.api.boss2.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.EnterpriseDao;
import com.tinet.api.boss2.dao.EnterpriseNumberDao;
import com.tinet.api.boss2.dao.EnterpriseSettingDao;
import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.EnterpriseAll;
import com.tinet.api.boss2.model.EnterpriseNumber;
import com.tinet.api.boss2.model.EnterpriseSettingAll;
import com.tinet.common.util.StringUtil;
import com.tinet.core.jdbc.DatabaseContextHolder;
/**
 * BOSS2定时任务业务逻辑处理层
 * <p>
 *  FileName： CrontabService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("crontabService")
public class CrontabService {
	
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private EnterpriseSettingDao enterpriseSettingDao;
	@Autowired
	private EnterpriseNumberDao enterpriseNumberDao;
	
	/**
	 * 获取所有企业基本信息、业务信息和套餐信息，测试账户获取使用状态和到期时间。
	 * @param ccicList 所有激活状态分平台对象集合
	 * @param entTestStatusList BOSS2中测试账户状态集合
	 * @param entTestExpiryDateList BOSS2中测试账户有效期集合
	 * @return
	 */
	public Map<String, Object> getEntInfo(List<Ccic> ccicList, List<EnterpriseSettingAll> entTestStatusList,
			List<EnterpriseSettingAll> entTestExpiryDateList) {
		// 返回值定义
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		// 返回的两个同步信息列表
		List<EnterpriseAll> entAllList = new ArrayList<EnterpriseAll>();
		List<EnterpriseSettingAll> entSettingAllList = new ArrayList<EnterpriseSettingAll>();
		String ccicCantCon = "";
		for (Ccic ccic : ccicList) {
			Integer ccicId = ccic.getId();
			// 切换数据源id
			DatabaseContextHolder.setDBType(ccicId.toString()); 
			try {
				entAllList.addAll(enterpriseDao.getEntSyncInfo(ccicId));
				entSettingAllList.addAll(enterpriseSettingDao.getEntSettingSync(entTestStatusList, entTestExpiryDateList));
			} catch (Exception e) {
				ccicCantCon += ccic.getCcicName()+"、";
				e.printStackTrace();
			}
		}
		rtnMap.put("entAllList", entAllList);
		rtnMap.put("entSettingAllList", entSettingAllList);
		if (!"".equals(ccicCantCon)) {
			rtnMap.put("error", "平台"+StringUtil.substringBeforeLast(ccicCantCon, "、")+"连接失败，其他平台同步企业信息成功，请联系管理员！");
		}
		return rtnMap;
	}

	/**
	 * 获取企业及其对应的下月固定费用总和
	 * @param ccicList 所有平台信息对象集合 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getEntTotalCost(List<Ccic> ccicList) {
		// 返回结果定义
		Map rtnMap = new HashMap();
		String ccicCantCon = "";
		
		// 循环各平台
		for (Ccic ccic : ccicList) {
			// 切换数据源id
			DatabaseContextHolder.setDBType(ccic.getId().toString()); 
			try {
				rtnMap.putAll(enterpriseDao.getEntTotalCost());
			} catch (Exception e) {
				ccicCantCon += ccic.getCcicName()+"、";
				e.printStackTrace();
			}
		}
		if (!"".equals(ccicCantCon)) {
			rtnMap.put("error", "平台"+StringUtil.substringBeforeLast(ccicCantCon, "、")+"连接失败，其他平台下月固定费用成功，请联系管理员！");
		}
		return rtnMap;
	}
	
	/**
	 * 查询全平台所有企业热线号码，目的号码和自备号码。
	 * @param ccicList 所有平台信息对象集合
	 * @return
	 */
	public Map<String, Object> getEntNumbers(List<Ccic> ccicList) {
		// 返回值定义
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		// 分平台所有企业号码
		List<EnterpriseNumber> entNumberList = new ArrayList<EnterpriseNumber>();
		// 全平台所有客户的热线号码，中继号码，手机虚拟号码和自备号码
		Set<String> numberSet  = new HashSet<String>(); 
		
		String ccicCantCon = "";
		for (Ccic ccic : ccicList) {
			Integer ccicId = ccic.getId();
			// 切换数据源id
			DatabaseContextHolder.setDBType(ccicId.toString()); 
			try {
				entNumberList.addAll(enterpriseNumberDao.getAllNumbers(numberSet));
			} catch (Exception e) {
				ccicCantCon += ccic.getCcicName()+"、";
				e.printStackTrace();
			}
		}
		if (!"".equals(ccicCantCon)) {
			rtnMap.put("error", "平台"+StringUtil.substringBeforeLast(ccicCantCon, "、")+"连接失败，其他平台同步号码信息成功，请联系管理员！");
		}
		rtnMap.put("entNumberList", entNumberList);
		return rtnMap;
		
	}
}
