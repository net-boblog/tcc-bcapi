package com.tinet.api.boss2.model;


import java.util.Date;

import com.tinet.common.inc.Const;


/**
 * 基本路由网关表实体类
 * <p>
 * 	文件名： Gateway.java
 * <p>
 * Copyright (c) 2006-2011 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Gateway extends BaseStandardEntity implements java.io.Serializable {

	// Fields    
	private String name;
	private String prefix;
	private String postfix;
	private String ipAddr;
	private String areaCode;
	private Integer port ;
	private Integer callLimit;
	private String disallow;
	private String allow;
	private String dtmf;
	private Integer register;
	private Date createTime;

	// Constructors
	
	/** default constructor */
	public Gateway() {
		this.postfix = "";
		this.callLimit =Const.GATEWAY_CALLLIMIT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
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

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getCallLimit() {
		return callLimit;
	}

	public void setCallLimit(Integer callLimit) {
		this.callLimit = callLimit;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setDisallow(String disallow) {
		this.disallow = disallow;
	}

	public String getDisallow() {
		return disallow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public String getAllow() {
		return allow;
	}

	public void setDtmf(String dtmf) {
		this.dtmf = dtmf;
	}

	public String getDtmf() {
		return dtmf;
	}

	public void setRegister(Integer register) {
		this.register = register;
	}

	public Integer getRegister() {
		return register;
	}
}