package com.tinet.api.boss2.model;


public class CommonRequest {
	private String version;
	private String clientId;
	private String sign;
	private Long timeStamp;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "CommonRequest [version=" + version + ", clientId=" + clientId
				+ ", sign=" + sign+ ", timeStamp=" + timeStamp + "]";
	}
	

}
