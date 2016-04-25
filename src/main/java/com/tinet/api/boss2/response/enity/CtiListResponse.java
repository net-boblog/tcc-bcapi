package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Cti;

public class CtiListResponse extends CommonResponse {
	private static final long serialVersionUID = 4575431436598268701L;
	private List<Cti> list;

	public List<Cti> getList() {
		return list;
	}
	public void setList(List<Cti> list) {
		this.list = list;
	}
	
}
