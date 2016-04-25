package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.LogCharge;
import com.tinet.api.boss2.response.enity.StatusResponse;
/**
 * 客户充值业务接口
 * <p>
 *  FileName： ChargeWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.ChargeWebImp
 */
@WebService 
public interface ChargeWeb {
	
	/**
	 * 给企业账户充值，冲正
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId	企业ID
	 * @param logCharge 充值对象
	 * @param creq 数据签名
	 * @return
	 */
	public StatusResponse charge(String ccicId, Integer enterpriseId, LogCharge logCharge, CommonRequest creq);
	
} 
