package com.tinet.api.boss2.response.enity;

import com.tinet.api.boss2.model.Enterprise;

public class EnterpriseResponse extends CommonResponse{
	private static final long serialVersionUID = 8593227230022452633L;
	private Enterprise enterprise;

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	
}
