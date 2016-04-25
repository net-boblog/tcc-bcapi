package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.PublicVoice;

public class PublicVoiceListResponse extends CommonResponse {
	private List<PublicVoice> list;

	public List<PublicVoice> getList() {
		return list;
	}
	public void setList(List<PublicVoice> list) {
		this.list = list;
	}
}
