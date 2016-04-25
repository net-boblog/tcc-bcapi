package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.AccountResponse;
import com.tinet.api.boss2.response.enity.IntegerDataResponse;
/**
 * 企业账户信息业务接口
 * <p>
 *  FileName： AccountWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.AccountWebImp
 */
@WebService 
public interface AccountWeb {
	
	/**
	 * 查询企业账户信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名
	 * @return
	 */
	public AccountResponse getAccountInfo(String ccicId, Integer enterpriseId, CommonRequest creq); 
	
	/**
	 * 获取account表的下一个序列ID
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public IntegerDataResponse getAccountNextSeq(String ccicId, CommonRequest creq);
	
} 
