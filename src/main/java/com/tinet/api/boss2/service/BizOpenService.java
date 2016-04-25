package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.BizOpenDao;
import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.model.EnterpriseTemp;
import com.tinet.api.boss2.model.LogCharge;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 客户开户业务逻辑处理层
 * <p>
 *  FileName： BizOpenService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("bizOpenService")
public class BizOpenService {
	@Autowired
	private BizOpenDao bizOpenDao;

	/**
	 * 开户开通业务客户账户，企业配置等信息初始化。
	 * @author louxue
	 * @param enterpriseTemp 开户所需客户基本信息，业务信息，企业配置
	 * @param logChargeList 充值日志
	 * @param logDeductionList 扣费日志
	 * @param account 企业账户信息
	 * @return
	 */
	public void executeOpenBiz(EnterpriseTemp enterpriseTemp, List<LogCharge> logChargeList,
			List<LogDeduction> logDeductionList, Account account){
		String ccicId = enterpriseTemp.getCcic().getId().toString();
		DatabaseContextHolder.setDBType(ccicId);
		bizOpenDao.executeOpenBiz(enterpriseTemp, logChargeList, logDeductionList, account);
	}
}
