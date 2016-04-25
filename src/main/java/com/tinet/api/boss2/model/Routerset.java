package com.tinet.api.boss2.model;


/**
 * 企业路由组表实体类
 * <p>
 * 	文件名： Routerset.java
 * <p>
 * Copyright (c) 2006-2011 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Routerset extends BaseStandardEntity implements java.io.Serializable {

	// Fields    
	private String name;
	private String description;
	private Boolean defaultRouter;

	// Constructors
	
	/** default constructor */
	public Routerset() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDefaultRouter() {
		return defaultRouter;
	}

	public void setDefaultRouter(Boolean defaultRouter) {
		this.defaultRouter = defaultRouter;
	}

}