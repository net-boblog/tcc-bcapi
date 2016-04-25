package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tinet.common.inc.Const;

/**
 * 充值日志实体类
 * <p>
 * 	FileName：LogCharge.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LogCharge extends BaseStandardEntity
		implements java.io.Serializable {

	// Fields

	private Integer transactionId; // 事务id
	private Integer entityId; // 操作人id
	private String entityName; // 操作人名称
	private Integer entityType; // 操作人类型：系统、出纳、客服、经理
	private Integer chargeType; // 充值类型：参考操作类型表
	private Integer accountId; // 资金账户ID
	private Integer accountType; // 账号类型，用来标识客户的功能账户对应关系 1:话费账户 2:功能费账户 11:默认账户(用于代理商)
	private String chargedEntityName; // 充值对象 企业/代理商名称
	private Integer chargedEntityId; // 对应企业entity->enterprise_id或代理商entity_entity_id
	private BigDecimal money; // 流动资金
	private BigDecimal previousMoney; // 充值前余额
	private BigDecimal currentMoney; // 当前金额，即充值后余额
	private BigDecimal currentSumMoney; // 对账余额
	private String chargeComment; // 充值备注
	private Date createTime; // 操作时间
	
	private String chargeTypeDesc; // 充值类型描述
	private String moneyFlowDesc; // 资金流向描述
	private String accountTypeDesc; // 账户类型描述
	private Integer transactionGroupId; // 事务组id
	private Integer isCancel = 0; //是否为取消交易记录

	// Constructors

	/** default constructor */
	public LogCharge() {
		this.setCreateTime(new Date());
		this.setIsCancel(Const.TRADE_IS_NOT_CANCEL);
	}

	
	public LogCharge(Integer transactionId,Integer entityId,
			String entityName, Integer entityType, Integer chargeType,
			Integer accountId, Integer accountType, String chargedEntityName,
			Integer chargedEntityId, BigDecimal money, BigDecimal previousMoney,
			BigDecimal currentMoney, BigDecimal currentSumMoney, String chargeComment) {
		
		this.transactionId = transactionId;
		this.entityId = entityId;
		this.entityName = entityName;
		this.entityType = entityType;
		this.chargeType = chargeType;
		this.accountId = accountId;
		this.accountType = accountType;
		this.chargedEntityName = chargedEntityName;
		this.chargedEntityId = chargedEntityId;
		this.money = money;
		this.previousMoney = previousMoney;
		this.currentMoney = currentMoney;
		this.currentSumMoney = currentSumMoney;
		this.chargeComment = chargeComment;
		this.setCreateTime(new Date());
		this.setIsCancel(Const.TRADE_IS_NOT_CANCEL);
	}

	// Property accessors

	public Integer getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	
	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getEntityType() {
		return this.entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public Integer getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getChargedEntityName() {
		return this.chargedEntityName;
	}

	public void setChargedEntityName(String chargedEntityName) {
		this.chargedEntityName = chargedEntityName;
	}

	public Integer getChargedEntityId() {
		return this.chargedEntityId;
	}

	public void setChargedEntityId(Integer chargedEntityId) {
		this.chargedEntityId = chargedEntityId;
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

	public BigDecimal getCurrentSumMoney() {
		return currentSumMoney;
	}

	public void setCurrentSumMoney(BigDecimal currentSumMoney) {
		this.currentSumMoney = currentSumMoney;
	}

	public String getChargeComment() {
		return this.chargeComment;
	}

	public void setChargeComment(String chargeComment) {
		this.chargeComment = chargeComment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getTransactionGroupId() {
		return transactionGroupId;
	}
	
	public void setTransactionGroupId(Integer transactionGroupId) {
		this.transactionGroupId = transactionGroupId;
	}
	
	public String getAccountTypeDesc() {
		switch(getAccountType()){
		case Const.LOG_CHARGE_ACCOUNT_TYPE_CALL_IB:
			this.accountTypeDesc="呼入话费账户";
			break;
		case Const.LOG_CHARGE_ACCOUNT_TYPE_FUNCTION:
			this.accountTypeDesc="功能费账户";
			break;
		case Const.LOG_CHARGE_ACCOUNT_TYPE_CALL_OB:
			this.accountTypeDesc="外呼话费账户";
			break;
		case Const.LOG_CHARGE_ACCOUNT_TYPE_SMS:
			this.accountTypeDesc="短信费账户";
			break;
		case Const.LOG_CHARGE_ACCOUNT_TYPE_AGENT:
			this.accountTypeDesc="默认账户";
			break;
		case Const.LOG_CHARGE_ACCOUNT_TYPE_CASH:
			this.accountTypeDesc="现金账户";
			break;
		default:this.accountTypeDesc="未知账户";
	}
		return accountTypeDesc;
	}

	public void setAccountTypeDesc(String accountTypeDesc) {
		this.accountTypeDesc = accountTypeDesc;
	}
	
	public String getChargeTypeDesc() {
		chargeTypeDesc=Const.JSP_CHARGE_TYPE.get(getChargeType());
		return chargeTypeDesc;
	}

	public void setChargeTypeDesc(String chargeTypeDesc) {
		this.chargeTypeDesc = chargeTypeDesc;
	}

	public String getMoneyFlowDesc() {
		moneyFlowDesc=Const.MONEY_FLOW.get(getChargeType());
		return moneyFlowDesc;
	}

	public void setMoneyFlowDesc(String moneyFlowDesc) {
		this.moneyFlowDesc = moneyFlowDesc;
	}
	
	public Integer getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}

}