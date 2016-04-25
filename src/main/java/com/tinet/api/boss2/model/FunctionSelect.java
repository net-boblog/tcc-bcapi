package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 开户：企业功能选择实体
 * <p>
 * 	FileName：FunctionSelect.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
*/
@SuppressWarnings("serial")
public class FunctionSelect extends BaseStandardEntity implements java.io.Serializable {

	// Fields    
	private Integer enterpriseId; // 企业id,对应分平台entity->enterprise_id流水号
	private Integer functionId; // 功能id,对应function->id流水号
	private Integer isOpen;    //功能是否开启，0=关闭 1=开启
	private BigDecimal discount; // 功能折扣
	private Date functionEnd; // 记录功能费到期时间
	private String functionProperty; // 选择的功能配置属性，比如短信功能的短信小号与签名配置，多字段用逗号分隔
	private Date createTime; // 记录创建时间

	// Constructors

	/** default constructor */
	public FunctionSelect() {
		this.discount = new BigDecimal(1);
		this.functionProperty = "";
		this.functionEnd = new Date();
		this.setCreateTime(new Date());
	}


	// Property accessors

	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Date getFunctionEnd() {
		return this.functionEnd;
	}

	public void setFunctionEnd(Date functionEnd) {
		this.functionEnd = functionEnd;
	}

	public String getFunctionProperty() {
		return this.functionProperty;
	}

	public void setFunctionProperty(String functionProperty) {
		this.functionProperty = functionProperty;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
}