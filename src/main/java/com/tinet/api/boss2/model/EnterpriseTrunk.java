package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tinet.common.inc.Const;
/**
 * 开户：企业目的码实体
 * <p>
 * 	FileName：EnterpriseTrunk.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
*/
@SuppressWarnings("serial")
public class EnterpriseTrunk extends  BaseStandardEntity implements java.io.Serializable,java.lang.Cloneable {

	// Fields    
	private Integer enterpriseId; // 企业id
	private String numberTrunk; // 中继号码
	private String areaCode; // 目的码所在地区区号
	private Integer isBilling; // 是否计费 1:计费 0:不计费
	private Integer type; // 是否分配给400号码 1--已分配 0--未分配
	private String comment; // 备注
	private Date createTime; // 记录创建时间
	private Integer status; // 中继号码状态	1.未绑定 2.绑定中，未使用 3.使用中
	private BigDecimal rent;
	private String carrier; // 所属运营商
	private String carrierDesc; // 所属运营商

	// Constructors
	/** default constructor */
	public EnterpriseTrunk() {
		this.isBilling = Const.ENTERPRISE_TRUNK_IS_BILLING_NO;
		this.comment = "";
		this.setCreateTime(new Date());
	}


	// Property accessors
	
	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getNumberTrunk() {
		return this.numberTrunk;
	}

	public void setNumberTrunk(String numberTrunk) {
		this.numberTrunk = numberTrunk;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getIsBilling() {
		return this.isBilling;
	}

	public void setIsBilling(Integer isBilling) {
		this.isBilling = isBilling;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public EnterpriseTrunk clone() {
		EnterpriseTrunk enterpriseTrunk = null;
		try {
			enterpriseTrunk = (EnterpriseTrunk) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return enterpriseTrunk;
	}


	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCarrierDesc() {
		if (this.getCarrier()!=null && !"".equals(this.getCarrier())) {
			switch (Integer.parseInt(this.getCarrier())) {
				case Const.TRUNK_CARRIER_CUCC:
					carrierDesc = "联通";
					break;
				case Const.TRUNK_CARRIER_CTTC:
					carrierDesc = "铁通";
					break;
				case Const.TRUNK_CARRIER_CTCC:
					carrierDesc = "电信";
					break;
				case Const.TRUNK_CARRIER_CMCC:
					carrierDesc = "移动";
					break;
				case Const.TRUNK_CARRIER_OTHER:
					carrierDesc = "其他";
					break;
				default:
					break;
			}
		}
		return carrierDesc;
	}

	public void setCarrierDesc(String carrierDesc) {
		this.carrierDesc = carrierDesc;
	}

}