package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Trunk;

public class TrunkResponse extends CommonResponse {
	private static final long serialVersionUID = -751800979265808248L;
	private List<Trunk> trunkList;

	public List<Trunk> getTrunkList() {
		return trunkList;
	}

	public void setTrunkList(List<Trunk> trunkList) {
		this.trunkList = trunkList;
	}
	
}
