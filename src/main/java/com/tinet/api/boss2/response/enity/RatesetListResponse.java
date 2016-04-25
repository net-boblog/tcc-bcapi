package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Rateset;

public class RatesetListResponse extends CommonResponse {
	private static final long serialVersionUID = 8439493371744325857L;
	private List<Rateset> list;

	public List<Rateset> getList() {
		return list;
	}
	public void setList(List<Rateset> list) {
		this.list = list;
	}

}
