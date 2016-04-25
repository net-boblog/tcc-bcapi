package com.tinet.api.boss2.model;



/**
 * 企业路由设置表实体类
 * <p>
 * 	文件名： Router.java
 * <p>
 * Copyright (c) 2006-2011 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Router extends BaseStandardEntity implements java.io.Serializable {

	// Fields    
	private Integer routersetId;
	private String prefix;
	private Gateway gateway;
	private Integer priority;
	private String description;
	private Integer gatewayId;

	// Constructors
	
	/** default constructor */
	public Router() {
	}
	
	public Router(Integer id, Integer routersetId){
		super.setId(id);
		this.routersetId = routersetId;
	}
	
	public Integer getRoutersetId() {
		return routersetId;
	}
	public void setRoutersetId(Integer routersetId) {
		this.routersetId = routersetId;
	}
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(Integer gatewayId) {
		this.gatewayId = gatewayId;
	}
}