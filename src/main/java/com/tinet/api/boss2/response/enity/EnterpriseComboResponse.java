package com.tinet.api.boss2.response.enity;

import com.tinet.api.boss2.model.EnterpriseCombo;

public class EnterpriseComboResponse extends CommonResponse {
	private static final long serialVersionUID = -9000243477556986288L;
	private EnterpriseCombo enterpriseCombo;

	public EnterpriseCombo getEnterpriseCombo() {
		return enterpriseCombo;
	}
	public void setEnterpriseCombo(EnterpriseCombo enterpriseCombo) {
		this.enterpriseCombo = enterpriseCombo;
	}
}
