package com.tinet.api.boss2.response.enity;

import java.util.List;

import com.tinet.api.boss2.model.LogDeduction;

public class LogDeductionListResponse extends CommonResponse {
	private static final long serialVersionUID = -7916337495021604906L;
	private List<LogDeduction> logDeductionList;
	private Integer status; // 当前企业业务状态

	public List<LogDeduction> getLogDeductionList() {
		return logDeductionList;
	}

	public void setLogDeductionList(List<LogDeduction> logDeductionList) {
		this.logDeductionList = logDeductionList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
