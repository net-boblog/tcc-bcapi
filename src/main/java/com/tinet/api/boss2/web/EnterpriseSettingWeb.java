package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseSettingResponse;
/**
 * 企业配置业务接口
 * <p>
 *  FileName： EnterpriseSettingWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.EnterpriseSettingWebImp
 */
/**
 * 企业配置业务接口
 * <p>
 *  FileName： EnterpriseSettingWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.EnterpriseSettingWebImp
 */
@WebService 
public interface EnterpriseSettingWeb {
	
	/**
	 * 获取企业其他配置
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名
	 * @return
	 */
	public EnterpriseSettingResponse getOtherConf(String ccicId, Integer enterpriseId, CommonRequest creq);
	
	/**
	 * 更新企业配置
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param entSetting 企业配置对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse instOrUpdateEntSetting(String ccicId, EnterpriseSetting entSetting, CommonRequest creq);
	
	
} 
