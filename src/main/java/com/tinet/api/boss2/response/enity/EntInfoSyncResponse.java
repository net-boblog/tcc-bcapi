package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.EnterpriseAll;
import com.tinet.api.boss2.model.EnterpriseSettingAll;

public class EntInfoSyncResponse extends CommonResponse {

	private static final long serialVersionUID = -3284711806467152334L;
	
	private List<EnterpriseAll> entAllList;
	private List<EnterpriseSettingAll> entSettingAllList;
	
	public List<EnterpriseAll> getEntAllList() {
		return entAllList;
	}
	
	public void setEntAllList(List<EnterpriseAll> entAllList) {
		this.entAllList = entAllList;
	}
	
	public List<EnterpriseSettingAll> getEntSettingAllList() {
		return entSettingAllList;
	}
	
	public void setEntSettingAllList(List<EnterpriseSettingAll> entSettingAllList) {
		this.entSettingAllList = entSettingAllList;
	}
	
}
