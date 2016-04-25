package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Combo;

public class ComboListResponse extends CommonResponse {
	private static final long serialVersionUID = 4900076848587175898L;
	private List<Combo> list;

	public List<Combo> getList() {
		return list;
	}
	public void setList(List<Combo> list) {
		this.list = list;
	}
	
}
