package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Combo;
import com.tinet.api.boss2.model.EnterpriseHotline;

public class EnterpriseHotlineResponse extends CommonResponse {
	private static final long serialVersionUID = -1881393671745587299L;
	private List<EnterpriseHotline> entHotlineList;
	private Combo combo;

	public List<EnterpriseHotline> getEntHotlineList() {
		return entHotlineList;
	}

	public void setEntHotlineList(List<EnterpriseHotline> entHotlineList) {
		this.entHotlineList = entHotlineList;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}
	
}
