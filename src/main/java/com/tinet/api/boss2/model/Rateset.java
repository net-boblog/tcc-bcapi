package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 费率套餐组实体类
 * <p>
 * 	FileName：Rateset.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Rateset extends BaseStandardEntity implements java.io.Serializable {


    // Fields    
	
     private String ratesetName;
     private String description;
     private BigDecimal price;
     private Date createTime;
     private Integer ratesetId;

    // Constructors

    /** default constructor */
    public Rateset() {
    	this.setCreateTime(new Date());
    }
    public Rateset(String ratesetName, String description, BigDecimal price, Integer ratesetId){
    	this.ratesetId = ratesetId;
    	this.ratesetName = ratesetName;
    	this.description = description;
    	this.price = price;
    }

   
    // Property accessors
    public String getRatesetName() {
        return this.ratesetName;
    }
    
    public void setRatesetName(String ratesetName) {
        this.ratesetName = ratesetName;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setRatesetId(Integer ratesetId) {
		this.ratesetId = ratesetId;
	}
	public Integer getRatesetId() {
		return ratesetId;
	}
    
}