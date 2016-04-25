package com.tinet.api.boss2.model;

import java.util.Date;

import com.tinet.common.inc.Const;

/**
 * function功能实体类
 * <p>
 * 	FileName： Function.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 阎向清
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
public class Function extends BaseStandardEntity
		implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private String functionName;// 功能名称
	private Double rent;// 功能费
	private Double rentNext;// 下一计费周期功能费
	private Integer type;// 功能类别 1:基本功能 2:增值功能
	private Integer interval;// 功能费间隔 1-按月2-按年
	private String description;// 功能描述
	private String typeDesc;// 功能类别描述
	private String rentDesc;// 功能费单位
	private Date createTime; // 记录创建时间
	
	// 全部功能id定义
	public static final int IB_RECORD = 1;
	public static final int OB_RECORD = 2;
	public static final int REMEMBER_CALL = 3;
	public static final int OB = 4;
	public static final int PREVIEW_OB = 5;
	public static final int PREDICTIVE_OB = 6;
	public static final int DIRECT_OB = 7;
	public static final int VOICE_MAIL = 8;
	public static final int WEB400 = 9;
	public static final int FAX_RECEIVE = 10;
	public static final int SMS_SEND = 11;
	public static final int WEB_CHAT = 12;
	public static final int RESTRICT_TEL = 13;
	public static final int IVR_RECORD = 14;
	public static final int ADMIN_ACCESS_CONTROL = 15;
	public static final int SMS_RECEIVE = 16;
	public static final int QUEUE_OBSERVER = 17;
	
	public static final int[] ALLFUNCTION = new int[]{Function.IB_RECORD,Function.OB_RECORD,Function.REMEMBER_CALL,Function.OB,Function.PREVIEW_OB
		,Function.PREDICTIVE_OB,Function.DIRECT_OB,Function.VOICE_MAIL,Function.WEB400,Function.FAX_RECEIVE,Function.SMS_SEND,Function.WEB_CHAT
		,Function.RESTRICT_TEL,Function.IVR_RECORD,Function.ADMIN_ACCESS_CONTROL,Function.SMS_RECEIVE,Function.QUEUE_OBSERVER};

	// Constructors

	/** default constructor */
	public Function() {
		this.setCreateTime(new Date());
	}
	
	/** minimal constructor */
	public Function(Integer id, String functionName, Double rent, Integer type,  String description) {
		this.setId(id);
		this.functionName = functionName;
		this.rent = rent;
		this.type = type;
		this.description = description;
	}

	/** full constructor */
	public Function(Integer id, String functionName, Double rent, Integer type, Integer interval, String description) {
		this.setId(id);
		this.functionName = functionName;
		this.rent = rent;
		this.type = type;
		this.interval = interval;
		this.description = description;
		
	}

	// Property accessors
	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Double getRent() {
		return this.rent;
	}

	public void setRent(Double rent) {
		this.rent = rent;
	}

	public Double getRentNext() {
		return this.rentNext;
	}

	public void setRentNext(Double rentNext) {
		this.rentNext = rentNext;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getInterval() {
		return this.interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
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
	
	public String getTypeDesc() {
		switch (this.getType()) {
		case Const.FUNCTION_TYPE_BASE:
			typeDesc = "基本功能";
			break;
		case Const.FUNCTION_TYPE_EXTRA:
			typeDesc = "增值功能";
			break;
		default:
			break;
		}
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getRentDesc() {
		switch (this.getInterval()) {
		case Const.FUNCTION_INTERVAL_MONTH:
			rentDesc = this.getRent()+"元/月";
			break;
		case Const.FUNCTION_INTERVAL_YEAR:
			rentDesc = this.getRent()+"元/年";
			break;
		default:
			break;
		}
		return rentDesc;
	}

	public void setRentDesc(String rentDesc) {
		this.rentDesc = rentDesc;
	}

}