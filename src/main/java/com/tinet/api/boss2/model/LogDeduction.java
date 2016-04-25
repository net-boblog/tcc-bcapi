package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户扣费日志实体类
 * <p>
 * 	FileName: LogDeduction.java
 * <p>
 * Copyright (c) 2006-2011 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LogDeduction extends BaseStandardEntity implements java.io.Serializable {

	// Fields    
	private Integer transactionId; // 事务id
	private Integer accountId; // 源资金帐户ID
	private Integer enterpriseId; // 客户ID
	private String enterpriseName; // 客户名称
	private BigDecimal totalLowestCost; // 企业所有400号码的最低消费总额（相同计费周期的）
	private BigDecimal money; // 流动资金
	private BigDecimal previousMoney; // 扣费前余额
	private BigDecimal currentMoney; // 当前余额
	private Integer feeType; // 扣费业务类型:  1=最低消费，2=功能费,3=座席费,4=一次性费用,11=客服给客户话费账户手工扣费
	private Date feeStartTime; // 费用开始时间 格式YYYY-MM-DD HH:MM:SS 2009-03-01 00:00:00 对于月扣费类型开始时间为月初00:00:00点，结束时间为月末23:59:59 对于一次性费用此字段无意义
	private Date feeEndTime; // 费用结束时间 格式YYYY-MM-DD HH:MM:SS 2009-03-31 23:59:59 对于年扣费类型为开通当天00:00:00 结束时间为365天后23:59:59 对于一次性费用此字段无意义
	private String deductComment; // 备注
	private Date createTime; // 操作时间

	// Constructors

	/** default constructor */
	public LogDeduction() {
		this.setCreateTime(new Date());
		this.setTotalLowestCost(new BigDecimal(0));
	}
	
	public LogDeduction(Integer accountId, Integer enterpriseId, String enterpriseName,	BigDecimal money, BigDecimal previousMoney, 
			BigDecimal currentMoney, Integer feeType, Date feeStartTime, Date feeEndTime, String deductComment) {
		this.accountId = accountId;
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.money = money;
		this.previousMoney = previousMoney;
		this.currentMoney = currentMoney;
		this.feeType = feeType;
		this.feeStartTime = feeStartTime;
		this.feeEndTime = feeEndTime;
		this.deductComment = deductComment;
		this.setCreateTime(new Date());
		this.setTotalLowestCost(new BigDecimal(0));
	}
	
	/** full constructor */
	public LogDeduction(Integer transactionId, Integer accountId, Integer enterpriseId, String enterpriseName,
			BigDecimal money, BigDecimal previousMoney, BigDecimal currentMoney, Integer feeType, Date feeStartTime,
			Date feeEndTime, String deductComment) {
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.money = money;
		this.previousMoney = previousMoney;
		this.currentMoney = currentMoney;
		this.feeType = feeType;
		this.feeStartTime = feeStartTime;
		this.feeEndTime = feeEndTime;
		this.deductComment = deductComment;
		this.setCreateTime(new Date());
		this.setTotalLowestCost(new BigDecimal(0));
	}

	// Property accessors
	public Integer getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public BigDecimal getTotalLowestCost() {
		return totalLowestCost;
	}

	public void setTotalLowestCost(BigDecimal totalLowestCost) {
		this.totalLowestCost = totalLowestCost;
	}
	
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getPreviousMoney() {
		return this.previousMoney;
	}

	public void setPreviousMoney(BigDecimal previousMoney) {
		this.previousMoney = previousMoney;
	}

	public BigDecimal getCurrentMoney() {
		return this.currentMoney;
	}

	public void setCurrentMoney(BigDecimal currentMoney) {
		this.currentMoney = currentMoney;
	}

	public Integer getFeeType() {
		return this.feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public Date getFeeStartTime() {
		return this.feeStartTime;
	}

	public void setFeeStartTime(Date feeStartTime) {
		this.feeStartTime = feeStartTime;
	}

	public Date getFeeEndTime() {
		return this.feeEndTime;
	}

	public void setFeeEndTime(Date feeEndTime) {
		this.feeEndTime = feeEndTime;
	}

	public String getDeductComment() {
		return this.deductComment;
	}

	public void setDeductComment(String deductComment) {
		this.deductComment = deductComment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}