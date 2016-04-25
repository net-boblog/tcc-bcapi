package com.tinet.api.boss2.model;

import java.util.Date;


/**
 * product产品实体类
 * <p>
 * 	FileName：Product.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 阎向清
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Product extends BaseStandardEntity
		implements java.io.Serializable {

	/**
	 * 
	 */
	// Fields
	private String productName; // 产品名称
	private String description; // 产品描述
	private Date createTime; // 记录创建时间

	// Constructors

	/** default constructor */
	public Product() {
		this.setCreateTime(new Date());
	}

	public Product(Integer id,String productName,String description) {
		this.setId(id);
		this.productName=productName;
		this.description=description;
	
	}
	// Property accessors

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}