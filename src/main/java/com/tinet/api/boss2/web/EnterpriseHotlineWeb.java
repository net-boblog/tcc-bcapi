package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseHotlineResponse;
/**
 * 热线号码业务接口
 * <p>
 *  FileName： EnterpriseHotlineWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.EnterpriseHotlineWebImp
 */
@WebService 
public interface EnterpriseHotlineWeb {
	
	/**
	 * 查询一个企业的所有热线号码信息（对于400/1010号码，直线号码他们对应的目的码含区号。）及其外呼套餐信息。
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名
	 * @return
	 */
	public EnterpriseHotlineResponse getAllHotline(String ccicId, Integer enterpriseId, CommonRequest creq); 
	
	/**
	 * 修改热线号码的最低消费信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param idChange enterprise_hotline表id串，多个用英文逗号分隔
	 * @param lowestCostChange 当前计费周期最低消费字符串，多个用英文逗号分隔
	 * @param lowestCostNextChange 下个计费周期最低消费字符串，多个用英文逗号分隔
	 * @param lowestCostModeChange 当前计费周期计费模式字符串，多个用英文逗号分隔
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveLowestCost(String ccicId, Integer enterpriseId, String idChange, String lowestCostChange, 
			String lowestCostNextChange, String lowestCostModeChange, CommonRequest creq);
} 
