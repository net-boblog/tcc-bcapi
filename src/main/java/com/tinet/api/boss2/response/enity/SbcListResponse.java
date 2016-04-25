package com.tinet.api.boss2.response.enity;

import java.util.List;
import com.tinet.api.boss2.model.Sbc;

public class SbcListResponse extends CommonResponse {
	private static final long serialVersionUID = -1986656926280607112L;
	private List<Sbc> list;

	public List<Sbc> getList() {
		return list;
	}
	public void setList(List<Sbc> list) {
		this.list = list;
	}
}
