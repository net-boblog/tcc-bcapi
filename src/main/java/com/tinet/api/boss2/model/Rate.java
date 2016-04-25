package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费率详细信息实体类
 * <p>
 * 	FileName：Rate.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @author 战春雨
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Rate extends BaseStandardEntity implements java.io.Serializable {

	// Fields    

	private Integer ratesetId;
	private String prefix;
	private Integer firstTime;
	private BigDecimal firstRate;
	private Integer secondTime;
	private BigDecimal secondRate;
	private String comment;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Rate() {
		this.setCreateTime(new Date());
	}
	public Rate(Integer ratesetId, String prefix, Integer firstTime, 
			BigDecimal firstRate, Integer secondTime,BigDecimal secondRate, String comment){
		this.ratesetId = ratesetId;
		this.prefix = prefix;
		this.firstTime = firstTime;
		this.firstRate = firstRate;
		this.secondTime = secondTime;
		this.secondRate = secondRate;
		this.comment = comment;
	}


	// Property accessors
	public Integer getRatesetId() {
		return ratesetId;
	}

	public void setRatesetId(Integer ratesetId) {
		this.ratesetId = ratesetId;
	}
	
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getFirstTime() {
		return this.firstTime;
	}

	public void setFirstTime(Integer firstTime) {
		this.firstTime = firstTime;
	}

	public BigDecimal getFirstRate() {
		return this.firstRate;
	}

	public void setFirstRate(BigDecimal firstRate) {
		this.firstRate = firstRate;
	}

	public Integer getSecondTime() {
		return this.secondTime;
	}

	public void setSecondTime(Integer secondTime) {
		this.secondTime = secondTime;
	}

	public BigDecimal getSecondRate() {
		return this.secondRate;
	}

	public void setSecondRate(BigDecimal secondRate) {
		this.secondRate = secondRate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}