package com.tinet.api.boss2.response.enity;

import java.io.Serializable;

public class CommonResponse implements Serializable {	
	private static final long serialVersionUID = 7977303993180548393L;
	private String returnCode;
	private String msg;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "CommonResponse [returnCode=" + returnCode + ", msg=" + msg
				+ "]";
	}

}
