package com.tinet.api.boss2.web.imp;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.AccountResponse;
import com.tinet.api.boss2.response.enity.IntegerDataResponse;
import com.tinet.api.boss2.service.AccountService;
import com.tinet.api.boss2.web.AccountWeb;

/**
 * 企业账户信息业务接口实现
 * <p>
 *  FileName： AccountWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.AccountWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.AccountWeb")
public class AccountWebImp implements AccountWeb {
	
	@Autowired
	private AccountService accountService;

	/**
	 * 查询企业账户信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名
	 * @return
	 */
	public AccountResponse getAccountInfo(String ccicId, Integer enterpriseId, CommonRequest creq){
		AccountResponse accountResponse = new AccountResponse();
		accountResponse.setReturnCode("0");
		accountResponse.setMsg("success");
		try {
			Account account = accountService.getAccountInfo(ccicId, enterpriseId);
			accountResponse.setAccount(account);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			accountResponse.setReturnCode("101");
			accountResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			accountResponse.setReturnCode("999");
			accountResponse.setMsg("其他异常，请联系管理员！");
		}
		return accountResponse;
	}
	
	/**
	 * 获取account表的下一个序列ID
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public IntegerDataResponse getAccountNextSeq(String ccicId, CommonRequest creq) {
		IntegerDataResponse intResponse = new IntegerDataResponse();
		intResponse.setReturnCode("0");
		intResponse.setMsg("success");
		try {
			intResponse.setData(accountService.getNextSeq(ccicId));
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			intResponse.setReturnCode("101");
			intResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			intResponse.setReturnCode("999");
			intResponse.setMsg("其他异常，请联系管理员！");
		}
		return intResponse;
	}
	
}
