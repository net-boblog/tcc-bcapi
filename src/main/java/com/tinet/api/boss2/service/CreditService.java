package com.tinet.api.boss2.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.AccountDao;
import com.tinet.api.boss2.dao.BusinessDao;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 企业信用变更业务逻辑处理层
 * <p>
 *  FileName： CreditService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("creditService")
public class CreditService {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private BusinessDao businessDao;
	
	/**
	 * 信用申请审批通过,更新企业信用额度
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param credit 信用额度
	 * @param creditDays 信用有效期,自然天数
	 * @param creditType 信用类型,0-临时信用额度 1-固定信用额度
	 * @return
	 */
	public Integer setCredit(String ccicId, Integer enterpriseId, BigDecimal credit, Integer creditDays, Integer creditType){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		accountDao.updateCredit(enterpriseId, credit, creditDays, creditType);
		
		// 查询当前企业状态
		Integer bizStatus = businessDao.getEntBizStatus(enterpriseId);
		
		return bizStatus;
	}
}
