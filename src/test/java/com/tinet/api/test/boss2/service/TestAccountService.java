package com.tinet.api.test.boss2.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.istack.Interned;
import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.service.AccountService;

public class TestAccountService extends BaseServiceTest {

	@Autowired
	private AccountService accountService;
	
	@Ignore
	public void testGetAccountInfo() {
		Account account = accountService.getAccountInfo("20", 3000128);
		Assert.assertNotNull(account);
		Assert.assertEquals(new BigDecimal("12979.354"), account.getMoney());
	}
	
	@Ignore
	public void testMonty() {
		accountService.testMoney("1",3000128, new BigDecimal("1"));
	}
	
	@Ignore
	public void testGetNextSeq() {
		System.out.println(accountService.getNextSeq("1"));
	}
}
