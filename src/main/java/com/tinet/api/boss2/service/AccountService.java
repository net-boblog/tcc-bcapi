package com.tinet.api.boss2.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.AccountDao;
import com.tinet.api.boss2.dao.EnterpriseDao;
import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 企业账户信息业务逻辑处理层
 * <p>
 *  FileName： AccountService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("accountService")
public class AccountService {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private EnterpriseDao enterpriseDao;
	
	/**
	 * 查询企业账户信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public Account getAccountInfo(String ccicId, Integer enterpriseId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		// 获取账户信息
		Account account = accountDao.getAccountInfo(enterpriseId);
		// 获取企业两个状态
		Enterprise ent = enterpriseDao.getEntBaseInfo(enterpriseId);
		account.setEntityStatus(ent.getEntityStatus());
		account.setBizStatus(ent.getStatus());
		return account;
	}
	
	/**
	 * 获取account表的下一个序列值
	 * @param ccicId
	 * @return
	 */
	public Integer getNextSeq(String ccicId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		return accountDao.getNextSeq();
	}
	
	public void testMoney(String ccicId, Integer enterpriseId, BigDecimal money){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		accountDao.testMoney(enterpriseId, money);
	}

}
