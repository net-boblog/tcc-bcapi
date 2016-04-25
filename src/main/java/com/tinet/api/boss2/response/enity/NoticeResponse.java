package com.tinet.api.boss2.response.enity;

import com.tinet.api.boss2.model.Notice;

@SuppressWarnings("serial")
public class NoticeResponse extends CommonResponse {
	private Notice notice;

	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
}
