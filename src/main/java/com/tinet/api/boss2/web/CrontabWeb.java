package com.tinet.api.boss2.web;

import java.util.List;

import javax.jws.WebService;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.EnterpriseSettingAll;
import com.tinet.api.boss2.response.enity.EntInfoSyncResponse;
import com.tinet.api.boss2.response.enity.EnterpriseNumberListResponse;
import com.tinet.api.boss2.response.enity.MapDataResponse;
/**
 * BOSS2定时任务所需业务接口
 * <p>
 *  FileName： CrontabWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.CrontabWebImp
 */
@WebService 
public interface CrontabWeb {
	
	/**
	 * 同步所有企业信息到BOSS2
	 * @param ccicList 所有平台列表
	 * @param entTestStatusList 测试账户使用状态
	 * @param entTestExpiryDateList 测试账户过期时间
	 * @param cRequest 数据签名
	 * @return
	 */
	public EntInfoSyncResponse getEnterpriseInfo(List<Ccic> ccicList, List<EnterpriseSettingAll> entTestStatusList, 
			List<EnterpriseSettingAll> entTestExpiryDateList, CommonRequest cRequest);
	
	/**
	 * 获取全平台所有企业下月固定费用
	 * @param ccicList 所有平台列表
	 * @param cRequest 数据签名
	 * @return
	 */
	public MapDataResponse getEntTotalCost(List<Ccic> ccicList, CommonRequest cRequest);
	
	/**
	 * 获取全平台所有企业号码：热线号码，中继号码，手机虚拟号码，客户自备号码
	 * @param ccicList 所有平台列表
	 * @param cRequest 数据签名
	 * @return
	 */
	public EnterpriseNumberListResponse getEnterpriseNumbers(List<Ccic> ccicList, CommonRequest cRequest);
} 
