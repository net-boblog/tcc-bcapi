package com.tinet.api.boss2.web;

import java.math.BigDecimal;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.StatusResponse;
/**
 * 客户信用管理业务接口
 * <p>
 *  FileName： CreditWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.CreditWebImp
 */
@WebService 
public interface CreditWeb {
	
	/**
	 * 信用申请审批通过,更新企业信用额度
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param credit 信用额度
	 * @param creditDays 信用有效期,自然天数
	 * @param creditType 信用类型,0-临时信用额度 1-固定信用额度
	 * @param creq 数据签名
	 * @return 返回企业当前业务状态
	 */
	public StatusResponse setCredit(String ccicId, Integer enterpriseId, BigDecimal credit, Integer creditDays, Integer creditType, CommonRequest creq);
} 
