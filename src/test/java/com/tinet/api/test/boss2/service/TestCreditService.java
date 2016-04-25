package com.tinet.api.test.boss2.service;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.service.AccountService;
import com.tinet.api.boss2.service.CreditService;

public class TestCreditService extends BaseServiceTest {
	@Autowired
	private CreditService creditService;
	@Autowired
	private AccountService accountService;
	
	/**
	 * 测试更新企业临时信用额度
	 */
	@Ignore
	public void testSetCreditTemp() {
		Integer bizStatus = creditService.setCredit("1", 3000162, new BigDecimal("1000"), 7, 0);
		Account account = accountService.getAccountInfo("1", 3000162);
		logger.error("客户业务状态::" + bizStatus);
		logger.error("临时信用额度:" + account.getCreditTemp());
		logger.error("临时信用有效期:" + account.getCreditTempDays());
		logger.error("固定信用额度:" + account.getCredit());
		logger.error("固定信用额度有效期:" + account.getCreditDays());
	}
	
	/**
	 * 测试更新企业固定信用额度
	 */
	@Test
	public void testSetCredit() {
		Integer bizStatus = creditService.setCredit("1", 3000162, new BigDecimal("3000"), 7, 1);
		Account account = accountService.getAccountInfo("1", 3000162);
		logger.error("客户业务状态::" + bizStatus);
		logger.error("临时信用额度:" + account.getCreditTemp());
		logger.error("临时信用有效期:" + account.getCreditTempDays());
		logger.error("固定信用额度:" + account.getCredit());
		logger.error("固定信用额度有效期:" + account.getCreditDays());
	}
	
}
