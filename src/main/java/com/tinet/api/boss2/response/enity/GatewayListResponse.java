package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Gateway;

public class GatewayListResponse extends CommonResponse {
	private static final long serialVersionUID = -4785167584590711660L;
	private List<Gateway> gatewayList;

	public List<Gateway> getGatewayList() {
		return gatewayList;
	}

	public void setGatewayList(List<Gateway> gatewayList) {
		this.gatewayList = gatewayList;
	}
	
}
