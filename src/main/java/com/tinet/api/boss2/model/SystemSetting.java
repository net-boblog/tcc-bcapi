package com.tinet.api.boss2.model;

import java.util.Date;

/**
 * SystemSetting系统设置实体类
 * <p>
 * 	FileName： SystemSetting.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 王云龙
 * @author 娄雪
 * @author 战春雨
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SystemSetting extends com.tinet.api.boss2.model.BaseStandardEntity 
	implements java.io.Serializable {

	// Fields    

	private String name; // name-value对
	private String value; // name-value对
	private String property; // 属性
	private Date createTime; // 创建时间
	// Constructors
	
	/** default constructor */
	
	public SystemSetting() {
		this.setCreateTime(new Date());
	}
	
	
	public SystemSetting(Integer id,String name,String value,String property) {
		this.setId(id);
		this.name=name;
		this.value=value;
		this.property=property;
	}
	
	public Integer getId() {
		return super.getId();
	}

	public void setId(Integer id) {
		super.setId(id);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}