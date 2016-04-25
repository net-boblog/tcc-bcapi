package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.EnterpriseSetting;

public class EnterpriseSettingResponse extends CommonResponse {
	private static final long serialVersionUID = 5700322774602341373L;
	private List<EnterpriseSetting> entSettingList;

	public List<EnterpriseSetting> getEntSettingList() {
		return entSettingList;
	}

	public void setEntSettingList(List<EnterpriseSetting> entSettingList) {
		this.entSettingList = entSettingList;
	}

}
