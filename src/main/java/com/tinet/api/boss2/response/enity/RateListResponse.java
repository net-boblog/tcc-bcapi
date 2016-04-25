package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Rate;

public class RateListResponse extends CommonResponse {
	private static final long serialVersionUID = -1665626101463355580L;
	private List<Rate> list;

	public List<Rate> getList() {
		return list;
	}
	public void setList(List<Rate> list) {
		this.list = list;
	}
	
}
