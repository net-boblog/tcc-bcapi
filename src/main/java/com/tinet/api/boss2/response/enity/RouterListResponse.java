package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Router;

public class RouterListResponse extends CommonResponse {
	private static final long serialVersionUID = 73338458692684031L;
	private List<Router> RouterList;

	public List<Router> getRouterList() {
		return RouterList;
	}
	
	public void setRouterList(List<Router> RouterList) {
		this.RouterList = RouterList;
	}
	
}
