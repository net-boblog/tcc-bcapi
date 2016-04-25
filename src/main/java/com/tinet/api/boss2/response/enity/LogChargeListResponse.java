package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.LogCharge;

public class LogChargeListResponse extends CommonResponse {
	private static final long serialVersionUID = 3303916709162728454L;
	private List<LogCharge> logChargeList;
	private Integer status; // 当前企业业务状态

	public List<LogCharge> getLogChargeList() {
		return logChargeList;
	}

	public void setLogChargeList(List<LogCharge> logChargeList) {
		this.logChargeList = logChargeList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
