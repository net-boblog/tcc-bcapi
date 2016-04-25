package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Routerset;

public class RoutersetListResponse extends CommonResponse {
	private static final long serialVersionUID = -4772365891701515819L;
	private List<Routerset> routersetList;

	public List<Routerset> getRoutersetList() {
		return routersetList;
	}
	
	public void setRoutersetList(List<Routerset> routersetList) {
		this.routersetList = routersetList;
	}
	
}
