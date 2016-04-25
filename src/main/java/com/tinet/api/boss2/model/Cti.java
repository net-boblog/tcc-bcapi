package com.tinet.api.boss2.model;

/**
* Cti表实体类
*<p>
* 文件名： Cti.java
*<p>
* Copyright (c) 2006-2010 T&I Net Communication CO.,LTD.  All rights reserved.
* @author MyEclipse Persistence Tools
* @since 1.0
* @version 1.0
*/

@SuppressWarnings("serial")
public class Cti extends BaseStandardEntity implements java.io.Serializable  {

	// Fields    
	private String ipAddr;
	private String areaCode;
	private String radiusPwd;
	private String amiServer;
	private Integer amiServerPort;
	private String managerUser;
	private String managerPwd;
	private String actionUser;
	private String actionPwd;
	private Integer active;
	private Integer type;
	private String mac;
	private Integer webcallCaps;
	private String realAddr;
	// Constructors

	/** default constructor */
	public Cti() {
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getRadiusPwd() {
		return radiusPwd;
	}

	public void setRadiusPwd(String radiusPwd) {
		this.radiusPwd = radiusPwd;
	}

	public String getAmiServer() {
		return amiServer;
	}

	public void setAmiServer(String amiServer) {
		this.amiServer = amiServer;
	}

	public Integer getAmiServerPort() {
		return amiServerPort;
	}

	public void setAmiServerPort(Integer amiServerPort) {
		this.amiServerPort = amiServerPort;
	}

	public String getManagerUser() {
		return managerUser;
	}

	public void setManagerUser(String managerUser) {
		this.managerUser = managerUser;
	}

	public String getManagerPwd() {
		return managerPwd;
	}

	public void setManagerPwd(String managerPwd) {
		this.managerPwd = managerPwd;
	}

	public String getActionUser() {
		return actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}

	public String getActionPwd() {
		return actionPwd;
	}

	public void setActionPwd(String actionPwd) {
		this.actionPwd = actionPwd;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public Integer getWebcallCaps() {
		return webcallCaps;
	}

	public void setWebcallCaps(Integer webcallCaps) {
		this.webcallCaps = webcallCaps;
	}

	public String getRealAddr() {
		return realAddr;
	}

	public void setRealAddr(String realAddr) {
		this.realAddr = realAddr;
	}
	
}