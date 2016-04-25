package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.Notice;

@SuppressWarnings("serial")
public class NoticeListResponse extends CommonResponse {
	private List<Notice> list;

	public List<Notice> getList() {
		return list;
	}
	public void setList(List<Notice> list) {
		this.list = list;
	}
}
