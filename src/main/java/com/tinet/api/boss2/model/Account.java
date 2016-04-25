package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;

/**
 * 资金账户实体类
 * <p>
 * 	FileName：Account.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 王云龙
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Account extends com.tinet.api.boss2.model.BaseStandardEntity
		implements java.io.Serializable {

	// Fields

	private Entity entity; // 对应entity->id流水号
	private BigDecimal money; // 现金账号余额
	private BigDecimal credit; // 账号信用额度，可透支的金额
	private Integer accountType; // 账号类型，用来标识代理商的功能账户对应关系 1:账户
	private Integer payType; // 付费类型： 1:预付费 2:后付费(目前只支持预付费)
	private Integer active; // 有效性：1为正常,2为冻结,3为注销
	private Date createTime; // 记录创建时间
	
	private Integer enterpriseId;//企业ID
	private BigDecimal creditTemp;//临时信用额度
	private Date arrearsTime;//arrears_time＝0，表示信用额度未起作用，arrears_time >0， 表示信用额度起作用...
	private int creditDays;//信用有效天数：信用额度生效后，其可生效天数，每天检查信用额度是否有效。...
	private int creditTempDays;//临时信用有效天数
	private int comboCount;//套餐数...
	
	private int entityStatus;
	private int bizStatus;
	
	private Date creditValidTime; // 信用额度指定生效时间
	private Date creditExpiredTime; // 信用额度失效时间

	// Constructors

	/** default constructor */
	public Account() {
		this.setCreateTime(new Date());
	}

	/** full constructor */
	public Account(Entity entity, BigDecimal money, BigDecimal credit,
			Integer accountType, Integer payType, Integer active) {
		this.entity = entity;
		this.money = money;
		this.credit = credit;
		this.accountType = accountType;
		this.payType = payType;
		this.active = active;
	}


	public Account(Integer id, Integer enterpriseId, BigDecimal money,
			BigDecimal credit, int accountType,
			int payType, int active, Date createTime,
			BigDecimal creditTemp, Date arrearsTime, int creditDays, int creditTempDays, int comboCount) {
		
		this.setId(id);
		this.setEnterpriseId(enterpriseId);
		this.setMoney(money);
		this.setCredit(credit);
		this.setAccountType(accountType);
		this.setPayType(payType);
		this.setActive(active);
		this.setCreateTime(createTime);
		this.setCreditTemp(creditTemp);
		this.setArrearsTime(arrearsTime);
		this.setCreditDays(creditDays);
		this.setCreditTempDays(creditTempDays);
		this.setComboCount(comboCount);
	}

	// Property accessors
	public Integer getId() {
		return super.getId();
	}

	public Entity getEntity() {
		return this.entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getCredit() {
		return this.credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public Integer getAccountType() {
		return this.accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setCreditTemp(BigDecimal creditTemp) {
		this.creditTemp = creditTemp;
	}

	public BigDecimal getCreditTemp() {
		return creditTemp;
	}

	public void setCreditDays(int creditDays) {
		this.creditDays = creditDays;
	}

	public int getCreditDays() {
		return creditDays;
	}

	public void setCreditTempDays(int creditTempDays) {
		this.creditTempDays = creditTempDays;
	}

	public int getCreditTempDays() {
		return creditTempDays;
	}

	public void setComboCount(int comboCount) {
		this.comboCount = comboCount;
	}

	public int getComboCount() {
		return comboCount;
	}

	public void setArrearsTime(Date arrearsTime) {
		this.arrearsTime = arrearsTime;
	}

	public Date getArrearsTime() {
		return arrearsTime;
	}

	public void setEntityStatus(int entityStatus) {
		this.entityStatus = entityStatus;
	}

	public int getEntityStatus() {
		return entityStatus;
	}

	public void setBizStatus(int bizStatus) {
		this.bizStatus = bizStatus;
	}

	public int getBizStatus() {
		return bizStatus;
	}
	
	public String getEntityStatusDesc() {
		String statusDesc = "";
		switch(getEntityStatus()){
			case Const.ENTITY_STATUS_OK:
				statusDesc="正常";
				break;
			case Const.ENTITY_STATUS_FORBIDDEN:
				statusDesc="暂停";
				break;
			case Const.ENTITY_STATUS_LOCK:
				statusDesc="锁定";
				break;
			case Const.ENTITY_STATUS_CLOSE:
				statusDesc="注销";
				break;
			default:
				statusDesc="未知状态";
		}
		return statusDesc;
	}
	
	public String getBizStatusDesc(){
		String bizStatusDesc = "";
		switch (this.getBizStatus()) {
		case 0:
			bizStatusDesc = "未开通";
			break;
		case 1:
			bizStatusDesc = "正常";
			break;
		case 2:
			bizStatusDesc = "欠费";
			break;
		case 3:
			bizStatusDesc = "停机";
			break;
		case 4:
			bizStatusDesc = "注销";
			break;
		default:
			bizStatusDesc = "未知";
			break;
		}
		return bizStatusDesc;
	}

	public Date getCreditValidTime() {
		return creditValidTime;
	}

	public void setCreditValidTime(Date creditValidTime) {
		this.creditValidTime = creditValidTime;
	}

	public Date getCreditExpiredTime() {
		if (this.getCreditValidTime()!=null) {
			try {
				// C2定时任务每天01:20:00执行信用额度到期清零
				creditExpiredTime =  DateUtil.parse(DateUtil.format(DateUtil.addDay(this.getCreditValidTime(), 7), DateUtil.FMT_DATE_YYYY_MM_DD)+" 01:20:00", DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return creditExpiredTime;
	}

	public void setCreditExpiredTime(Date creditExpiredTime) {
		this.creditExpiredTime = creditExpiredTime;
	}
	
	
	
}