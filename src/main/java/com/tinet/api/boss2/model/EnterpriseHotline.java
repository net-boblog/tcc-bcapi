package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;

/**
 * 开户：企业热线号码实体
 * <p>
 * 	FileName：EnterpriseHotline.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author  娄雪
 * @since 1.0
 * @version 1.0
*/
@SuppressWarnings("serial")
public class EnterpriseHotline extends BaseStandardEntity implements java.io.Serializable,java.lang.Cloneable {

	// Fields    
	private Integer enterpriseId; // 企业id
	private String hotline; // 热线号码，一个企业的多个热线号码都可以登录
	private Integer isMaster; // 主热线号码 一个企业有且只有一个主热线号码
	private BigDecimal lowestCost; // 号码最低消费
	private BigDecimal lowestCostNext; // 号码下一计费周期最低消费
	private Integer period; // 号码最低消费周期 1:月最低消费 2:年最低消费
	private String numberTrunk; // 目的码
	private Integer type; // 热线号码类型：1-400号码，2-直线号码
	private Date createTime; // 记录创建时间
	private Date feeDeductTime;//最近一次扣费时间
	private String areaCode; // 区号
	private Boolean isNewAdd; // 是否新增的，在修改企业的号码信息时有用
	
	private Integer status;//记录号码中心查询结果
	
	private Integer lowestCostMode;//号码最低消费计费模式
	private BigDecimal rent;//对应trunk.rent
	private String typeDesc;
	

	// Constructors

	/** default constructor */
	public EnterpriseHotline() {
		this.period = Const.ENTERPRISE_HOTLINE_PERIOD_MONTH;
		this.isMaster = Const.ENTERPRISE_HOTLINE_IS_MASTER_NO;
		this.isNewAdd = true;
		this.setCreateTime(new Date());
	}

	// Property accessors
	
	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getHotline() {
		return this.hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public Integer getIsMaster() {
		return this.isMaster;
	}

	public void setIsMaster(Integer isMaster) {
		this.isMaster = isMaster;
	}

	public BigDecimal getLowestCost() {
		return this.lowestCost;
	}

	public void setLowestCost(BigDecimal lowestCost) {
		this.lowestCost = lowestCost;
	}
	
	public BigDecimal getLowestCostNext() {
		return this.lowestCostNext;
	}

	public void setLowestCostNext(BigDecimal lowestCostNext) {
		this.lowestCostNext = lowestCostNext;
	}
	
	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getNumberTrunk() {
		return this.numberTrunk;
	}

	public void setNumberTrunk(String numberTrunk) {
		this.numberTrunk = numberTrunk;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Boolean getIsNewAdd() {
		return isNewAdd;
	}

	public void setIsNewAdd(Boolean isNewAdd) {
		this.isNewAdd = isNewAdd;
	}

	@Override
	public EnterpriseHotline clone() {
		EnterpriseHotline enterpriseHotline = null;
		try {
			enterpriseHotline = (EnterpriseHotline) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return enterpriseHotline;
	}

	public void setFeeDeductTime(Date feeDeductTime) {
		this.feeDeductTime = feeDeductTime;
	}

	public Date getFeeDeductTime() {
		return feeDeductTime;
	}

	public String getFeeDeductStart() {
		if(this.getPeriod() == Const.ENTERPRISE_HOTLINE_PERIOD_MONTH){
			return DateUtil.getDayInMonth(DateUtil.addMonth(this.getFeeDeductTime(), -1), true);
		}else{
			return DateUtil.format(DateUtil.addMonth(this.getFeeDeductTime(), -12), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
		}
	}

	public String getFeeDeductend() {
		if(this.getPeriod() == Const.ENTERPRISE_HOTLINE_PERIOD_MONTH){
			return DateUtil.getDayInMonth(DateUtil.addMonth(this.getFeeDeductTime(), -1), false);
		}else{
			return DateUtil.format(this.getFeeDeductTime(), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
		}
		
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public String getPeriodDesc() {
		if(this.getPeriod() == Const.ENTERPRISE_HOTLINE_PERIOD_MONTH){
			return "月";
		}else{
			return "年";
		}
	}

	public void setLowestCostMode(Integer lowestCostMode) {
		this.lowestCostMode = lowestCostMode;
	}

	public Integer getLowestCostMode() {
		return lowestCostMode;
	}

	public String getLowestCostModeDesc(){
		String lowestCostModeDesc = "";
		if(this.getLowestCostMode() != null){
			switch (this.getLowestCostMode()) {
			case 0:
				lowestCostModeDesc = "呼入";
				break;
				
			case 1:
				lowestCostModeDesc = "呼入+呼出";
				break;
			default:
				lowestCostModeDesc = "";
				break;
			}
		}
		return lowestCostModeDesc;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getRent() {
		return rent;
	}
	
	public String getTypeDesc() {
		if (this.getType()!=null) {
			switch (this.getType()) {
			case Const.ENTERPRISE_HOTLINE_TYPE_400_NUMBER:
				this.typeDesc = "400/1010号码";
				break;
			case Const.ENTERPRISE_HOTLINE_TYPE_DIRECT_NUMBER:
				this.typeDesc = "直线号码";
				break;
			case Const.ENTERPRISE_HOTLINE_TYPE_MOBILE_VIRTUAL:
				this.typeDesc = "手机虚拟号码";
				break;
			default:
				break;
			}
		}
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
}