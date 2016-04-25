package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业套餐实体类
 * <p>
 *	FileName：EnterpriseCombo.java
 * <p>
 * Copyright (c) 2006-2011 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author fengwei
 * @since 1.0
 * @version 1.0
*/
@SuppressWarnings("serial")
public class EnterpriseCombo extends BaseStandardEntity implements java.io.Serializable {

	// Fields    
	private Entity entity;
	private Combo combo;
	private Integer clientCount;
	private BigDecimal deductFee;
	private BigDecimal threshold;
	private long startTime;
	private long endTime;
	private Integer comboOrderId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public EnterpriseCombo() {
		this.setCreateTime(new Date());
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public Integer getClientCount() {
		return clientCount;
	}

	public void setClientCount(Integer clientCount) {
		this.clientCount = clientCount;
	}

	public BigDecimal getDeductFee() {
		return deductFee;
	}

	public void setDeductFee(BigDecimal deductFee) {
		this.deductFee = deductFee;
	}

	public BigDecimal getThreshold() {
		return threshold;
	}

	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public Integer getComboOrderId() {
		return comboOrderId;
	}

	public void setComboOrderId(Integer comboOrderId) {
		this.comboOrderId = comboOrderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}