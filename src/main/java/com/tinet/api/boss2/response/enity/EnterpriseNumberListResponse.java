package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.EnterpriseNumber;

public class EnterpriseNumberListResponse extends CommonResponse {

	private static final long serialVersionUID = -5792477985064494126L;
	
	private List<EnterpriseNumber> entNumberList;
	public List<EnterpriseNumber> getEntNumberList() {
		return entNumberList;
	}
	public void setEntNumberList(List<EnterpriseNumber> entNumberList) {
		this.entNumberList = entNumberList;
	}

	
}
