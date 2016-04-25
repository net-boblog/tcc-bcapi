package com.tinet.api.boss2.web;

import java.util.List;

import javax.jws.WebService;

import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.EnterpriseTemp;
import com.tinet.api.boss2.model.LogCharge;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.api.boss2.response.enity.CommonResponse;
/**
 * 客户开户业务接口
 * <p>
 *  FileName： BizOpenWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.BizOpenWebImp
 */
@WebService 
public interface BizOpenWeb {
	
	/**
	 * 开户开通业务客户账户，企业配置等信息初始化。
	 * @author louxue
	 * @param enterpriseTemp 开户所需客户基本信息，业务信息，企业配置
	 * @param logChargeList 充值日志
	 * @param logDeductionList 扣费日志
	 * @param account 企业账户信息
	 * @return
	 */
	public CommonResponse openBiz(EnterpriseTemp enterpriseTemp, List<LogCharge> logChargeList, 
			List<LogDeduction> logDeductionList, Account account, CommonRequest creq);
} 
