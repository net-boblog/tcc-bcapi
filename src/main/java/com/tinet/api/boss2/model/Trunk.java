package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tinet.common.inc.Const;
/**
 * 目的码实体类
 * <p>
 * 	FileName：Trunk.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 阎向清
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
public class Trunk extends BaseStandardEntity
		implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String trunk; // 中继号码
	private String number; // 绑定的400号码
	private Integer trunkType; // 目的码类型 1-普通目的码 2-传真目的码
	private Integer bureauId; // 所属端局,对应bureau-->id
	private Integer ccicId; // 所属CCIC平台,对应ccic-->ccic_id
	private Integer enterpriseId; // 企业id
	private Integer status; // 号码状态，1-未使用 2-使用中
	private String comment; // 备注
	private Date createTime; // 记录创建时间
	private String trunkTypeDesc; // 目的码类型描述
	private String statusDesc; // 状态描述
	private String areaCode; // 目的码所属端局区号
	private Integer ctiId;
	private BigDecimal rent;

	// Constructors

	/** default constructor */
	public Trunk() {
		status = Const.TRUNK_STATUS_UNUSED;
		this.setCreateTime(new Date());
	}

	public Trunk(Integer id, String trunk, Integer trunkType, Integer bureauId, 
			Integer ccicId, Integer status, String comment) {
		super.setId(id);
		this.trunk = trunk;
		this.trunkType = trunkType;
		this.bureauId = bureauId;
		this.ccicId = ccicId;
		this.status = status;
		this.comment = comment;
	}
	
	public Trunk(Integer id, String trunk, Integer trunkType, BigDecimal rent, String areaCode, Date createTime) {
		super.setId(id);
		this.trunk = trunk;
		this.trunkType = trunkType;
		this.rent = rent;
		this.areaCode = areaCode;
		this.createTime = createTime;
	}
	
	// Property accessors
	public Integer getId() {
		return super.getId();
	}
	
	public String getTrunk() {
		return this.trunk;
	}

	public void setTrunk(String trunk) {
		this.trunk = trunk;
	}

	public Integer getBureauId() {
		return this.bureauId;
	}

	public void setBureauId(Integer bureauId) {
		this.bureauId = bureauId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getTrunkType() {
		return this.trunkType;
	}

	public void setTrunkType(Integer trunkType) {
		this.trunkType = trunkType;
	}

	public Integer getCcicId() {
		return this.ccicId;
	}

	public void setCcicId(Integer ccicId) {
		this.ccicId = ccicId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTrunkTypeDesc() {
		if (getTrunkType()!=null) {
			switch(getTrunkType()){
				case Const.ENTERPRISE_TRUNK_TYPE_NOT_BIND:
					this.trunkTypeDesc="直线号码";
					break;
				case Const.ENTERPRISE_TRUNK_TYPE_BIND:
					this.trunkTypeDesc="绑定热线的中继";
					break;
				case Const.ENTERPRISE_TRUNK_TYPE_MOBILE_VIRTUAL:
					this.trunkTypeDesc="手机虚拟号码";
					break;
					
			}
		}
		return this.trunkTypeDesc;
	}

	public void setTrunkTypeDesc(String trunkTypeDesc) {
		this.trunkTypeDesc = trunkTypeDesc;
	}

	public String getStatusDesc() {
		if (getStatus()!=null) {
			switch(getStatus()){
			case Const.TRUNK_STATUS_UNUSED:
				this.statusDesc="未使用";
				break;
			case Const.TRUNK_STATUS_INUSE:
				this.statusDesc="使用中";
				break;
			}
		}
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setCtiId(Integer ctiId) {
		this.ctiId = ctiId;
	}

	public Integer getCtiId() {
		return ctiId;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getRent() {
		return rent;
	}
}