package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.EnterpriseComboResponse;
/**
 * <p>
 *  FileName： EnterpriseComboWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.EnterpriseComboWebImp
 */
@WebService 
public interface EnterpriseComboWeb {
	
	/**
	 * 根据enterpriseId查询企业当前月套餐使用明细
	 * @return
	 */
	public EnterpriseComboResponse getEntComboDetail(String ccicId, String enterpriseId, CommonRequest creq);
} 
