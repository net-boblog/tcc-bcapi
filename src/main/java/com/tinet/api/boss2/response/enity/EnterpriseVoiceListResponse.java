package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.VoiceAudit;

public class EnterpriseVoiceListResponse extends CommonResponse {

	private static final long serialVersionUID = 8173326615420531917L;
	private List<VoiceAudit> voiceList;

	public List<VoiceAudit> getVoiceList() {
		return voiceList;
	}

	public void setVoiceList(List<VoiceAudit> voiceList) {
		this.voiceList = voiceList;
	}
	
	
}
