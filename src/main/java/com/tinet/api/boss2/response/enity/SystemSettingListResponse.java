package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.SystemSetting;

public class SystemSettingListResponse extends CommonResponse {
	private static final long serialVersionUID = -8436558269120326297L;
	private List<SystemSetting> list;

	public List<SystemSetting> getList() {
		return list;
	}
	public void setList(List<SystemSetting> list) {
		this.list = list;
	}

}
