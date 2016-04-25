package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.TrunkResponse;
/**
 * 目的码业务接口
 * <p>
 *  FileName： TrunkWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.TrunkWebImp
 */
@WebService 
public interface TrunkWeb {
	
	/**
	 * 查询一个企业的所有直线号码、手机虚拟号码及其月租信息。
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名
	 * @return
	 */
	public TrunkResponse getTrunkRent(String ccicId, Integer enterpriseId, CommonRequest creq); 
	
	/**
	 * 修改号码的月功能费
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param idChange enterprise_hotline表id串，多个用英文逗号分隔
	 * @param rentChange 当前计费周期最低消费字符串，多个用英文逗号分隔
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveTrunkRent(String ccicId, Integer enterpriseId, String idChange, String rentChange, CommonRequest creq);
} 
