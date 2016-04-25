package com.tinet.api.boss2.response.enity;

import com.tinet.api.boss2.model.Account;

public class AccountResponse extends CommonResponse {
	private static final long serialVersionUID = -5691434808242791736L;
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
