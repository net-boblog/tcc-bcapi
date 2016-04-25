package com.tinet.api.boss2.model;

public class SystemMonitor {
	
	private String ccicName;
	private Integer enterpriseNum;
	private Integer callTogetherNum;
	private Integer queueNum;
	private Integer clientNum;
	
	public SystemMonitor(){
		
	}
	
	public String getCcicName() {
		return ccicName;
	}
	public void setCcicName(String ccicName) {
		this.ccicName = ccicName;
	}
	public Integer getEnterpriseNum() {
		return enterpriseNum;
	}
	public void setEnterpriseNum(Integer enterpriseNum) {
		this.enterpriseNum = enterpriseNum;
	}
	public Integer getCallTogetherNum() {
		return callTogetherNum;
	}
	public void setCallTogetherNum(Integer callTogetherNum) {
		this.callTogetherNum = callTogetherNum;
	}
	public Integer getQueueNum() {
		return queueNum;
	}
	public void setQueueNum(Integer queueNum) {
		this.queueNum = queueNum;
	}
	public Integer getClientNum() {
		return clientNum;
	}
	public void setClientNum(Integer clientNum) {
		this.clientNum = clientNum;
	}
}
