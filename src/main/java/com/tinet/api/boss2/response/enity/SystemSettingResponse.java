package com.tinet.api.boss2.response.enity;

import com.tinet.api.boss2.model.SystemSetting;

public class SystemSettingResponse extends CommonResponse {
	private static final long serialVersionUID = -2924046334446115443L;
	private SystemSetting systemSetting;

	public SystemSetting getSystemSetting() {
		return systemSetting;
	}
	public void setSystemSetting(SystemSetting systemSetting) {
		this.systemSetting = systemSetting;
	}

}
