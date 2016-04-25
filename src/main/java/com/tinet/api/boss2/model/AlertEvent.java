package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tinet.common.inc.Const;

/**
 * 提醒事件实体类
 * <p>
 * 	FileName: AlertEvent.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AlertEvent extends BaseStandardEntity implements java.io.Serializable {

	// Fields

	private Integer entityId; // 事件相关实体id
	private String entityName; // 事件相关实体名称
	private Integer entityType; // 事件相关实体角色:1--一代 2--二代 3--客户 4--直销部门，5--直销经理，同entity的entity_type
	private String number400; // 企业400号码
	private Integer productId; // 产品类型
	private BigDecimal price; // 最低消费
	
	private BigDecimal cash; // 单账户,现金账户余额
	private BigDecimal money; // 话费余额
	private BigDecimal fmoney; // 座席/功能费余额
	private BigDecimal omoney; // 外呼话费余额
	private BigDecimal smoney; // 短信费余额
	private String agent1Name; // 一级代理商名称
	private String agent2Name; // 二级代理商名称
	private Integer entityStatus; // 事件相关实体状态：1--正常，2--禁止，3--锁定
	private Integer businessStatus; // 企业业务状态：0--未开通 1--正常 2--欠费 3--停机 4--注销
	private Integer eventType; // 提醒事件类别 具体见：const事件类别表
	private Integer sendFlag; // 发送标志: 0--未发送,1--已发送
	private Integer sendTimeType; // 发送时间类型:0--实时发送 1--定时发送
	private Integer sendMethod; // 发送方式: 0--无,1--邮件,2--短信,3--邮件/短信
	private Integer tagType; // 标记: 1--未处理，2--待后续，3--已处理
	private String description; // 简单描述
	private String comment; // 备注和详细描述
	private Date createTime; // 创建时间
	private String sendFlagDesc; 
	private String entityStatusDesc; 
	private String businessStatusDesc;
	private String tagTypeDesc;
	private String eventTypeDesc;
	private BigDecimal rechargeMoney; // 充值单金额
	private String failReason; // 充值单金额
	private Integer period;//计费周期
	private String changeType;
	private String oldValue;
	private String newValue;
	private String creditType;



	/** default constructor */
	public AlertEvent() {
		this.setCreateTime(new Date());
	}

	/** full constructor */
	public AlertEvent(Integer entityId, String entityName, Integer entityType, String number400,
			Integer productId, BigDecimal price, BigDecimal money, BigDecimal fmoney,
			BigDecimal omoney, BigDecimal smoney, String agent1Name, String agent2Name,
			Integer entityStatus, Integer businessStatus, Integer eventType,
			Integer sendFlag, Integer sendTimeType, Integer sendMethod,
			Integer tagType, String description) {
		this.entityId = entityId;
		this.entityName = entityName;
		this.entityType = entityType;
		this.number400 = number400;
		this.productId = productId;
		this.price = price;
		this.money = money;
		this.fmoney = fmoney;
		this.omoney = omoney;
		this.smoney = smoney;
		this.agent1Name = agent1Name;
		this.agent2Name = agent2Name;
		this.entityStatus = entityStatus;
		this.businessStatus = businessStatus;
		this.eventType = eventType;
		this.sendFlag = sendFlag;
		this.sendTimeType = sendTimeType;
		this.sendMethod = sendMethod;
		this.tagType = tagType; 
		this.description = description;
		this.createTime = new Date();
	}
	
	/** new constructor for alert*/
	public AlertEvent(Integer entityId, String entityName, Integer entityType, String number400,
			Integer productId, BigDecimal price, BigDecimal money, BigDecimal fmoney,
			BigDecimal omoney, BigDecimal smoney, String agent1Name, String agent2Name,
			Integer entityStatus, Integer businessStatus, Integer eventType,
			Integer sendFlag, Integer sendTimeType, Integer sendMethod,
			Integer tagType, String description,BigDecimal rechargeMoney,String failReason) {
		this.entityId = entityId;
		this.entityName = entityName;
		this.entityType = entityType;
		this.number400 = number400;
		this.productId = productId;
		this.price = price;
		this.money = money;
		this.fmoney = fmoney;
		this.omoney = omoney;
		this.smoney = smoney;
		this.agent1Name = agent1Name;
		this.agent2Name = agent2Name;
		this.entityStatus = entityStatus;
		this.businessStatus = businessStatus;
		this.eventType = eventType;
		this.sendFlag = sendFlag;
		this.sendTimeType = sendTimeType;
		this.sendMethod = sendMethod;
		this.tagType = tagType; 
		this.description = description;
		this.createTime = new Date();
		this.rechargeMoney = rechargeMoney;
		this.failReason = failReason;
	}

	// Property accessors
	public Integer getId() {
		return super.getId();
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

	public String getNumber400() {
		return this.number400;
	}

	public void setNumber400(String number400) {
		this.number400 = number400;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getFmoney() {
		return fmoney;
	}
	
	public void setFmoney(BigDecimal fmoney) {
		this.fmoney = fmoney;
	}

	
	public BigDecimal getOmoney() {
		return omoney;
	}
	
	public void setOmoney(BigDecimal omoney) {
		this.omoney = omoney;
	}
	
	public BigDecimal getSmoney() {
		return smoney;
	}

	public void setSmoney(BigDecimal smoney) {
		this.smoney = smoney;
	}

	public String getAgent1Name() {
		return this.agent1Name;
	}

	public void setAgent1Name(String agent1Name) {
		this.agent1Name = agent1Name;
	}

	public String getAgent2Name() {
		return this.agent2Name;
	}

	public void setAgent2Name(String agent2Name) {
		this.agent2Name = agent2Name;
	}

	public Integer getEntityStatus() {
		return this.entityStatus;
	}

	public void setEntityStatus(Integer entityStatus) {
		this.entityStatus = entityStatus;
	}

	public Integer getBusinessStatus() {
		return this.businessStatus;
	}

	public void setBusinessStatus(Integer businessStatus) {
		this.businessStatus = businessStatus;
	}

	public Integer getEventType() {
		return this.eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Integer getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
	}

	public Integer getSendTimeType() {
		return this.sendTimeType;
	}

	public void setSendTimeType(Integer sendTimeType) {
		this.sendTimeType = sendTimeType;
	}

	public Integer getSendMethod() {
		return this.sendMethod;
	}

	public void setSendMethod(Integer sendMethod) {
		this.sendMethod = sendMethod;
	}

	public Integer getTagType() {
		return this.tagType;
	}

	public void setTagType(Integer tagType) {
		this.tagType = tagType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSendFlagDesc() {
		switch(this.getSendFlag()){
			case Const.ALERT_EVENT_SEND_FLAG_UNSENT:sendFlagDesc="未发送"; break;
			case Const.ALERT_EVENT_SEND_FLAG_SENT:sendFlagDesc="已发送"; break;
		}
		return this.sendFlagDesc;
	}

	public void setSendFlagDesc(String sendFlagDesc) {
		this.sendFlagDesc = sendFlagDesc;
	}

	public String getEntityStatusDesc() {
		switch(this.getEntityStatus()){
		case Const.ENTITY_STATUS_OK:entityStatusDesc="正常"; break;
		case Const.ENTITY_STATUS_FORBIDDEN:entityStatusDesc="禁止"; break;
		case Const.ENTITY_STATUS_LOCK:entityStatusDesc="锁定"; break;
		}
		return this.entityStatusDesc;
	}

	public void setEntityStatusDesc(String entityStatusDesc) {
		this.entityStatusDesc = entityStatusDesc;
	}

	public String getBusinessStatusDesc() {
		switch(this.getBusinessStatus()){
		case Const.ENTERPRISE_TEMP_STATUS_NO_SERVICE:businessStatusDesc="未开通"; break;
		case Const.ENTERPRISE_TEMP_STATUS_OK:businessStatusDesc="正常"; break;
		case Const.ENTERPRISE_TEMP_STATUS_PAUSED:businessStatusDesc="欠费"; break;
		case Const.ENTERPRISE_TEMP_STATUS_STOP:businessStatusDesc="停机"; break;
		case Const.ENTERPRISE_TEMP_STATUS_CLOSE:businessStatusDesc="注销"; break;
		}
		return this.businessStatusDesc;
	}
	
	public void setBusinessStatusDesc(String businessStatusDesc) {
		this.businessStatusDesc = businessStatusDesc;
	}
	
	public String getTagTypeDesc() {
		switch(this.getTagType()){
		case Const.ALERT_EVENT_TAG_TYPE_UNDISPOSED:tagTypeDesc="未处理"; break;
		case Const.ALERT_EVENT_TAG_TYPE_WAITING:tagTypeDesc="待后续"; break;
		case Const.ALERT_EVENT_TAG_TYPE_HANDLED:tagTypeDesc="已处理"; break;
		}
		return this.tagTypeDesc;
	}
	
	public void setTagTypeDesc(String tagTypeDesc) {
		this.tagTypeDesc = tagTypeDesc;
	}

	public String getEventTypeDesc() {
		eventTypeDesc=Const.ALERT_EVENT_TYPE.get(getEventType());	
		return eventTypeDesc;
	}

	public void setEventTypeDesc(String eventTypeDesc) {
		this.eventTypeDesc = eventTypeDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public Integer changeEventType(Integer eventType){
		if (eventType > 1000 && eventType < 1116) {
			eventType = 1111;
		} else if (eventType > 2000 && eventType < 2113) {
			eventType = 2111;
		} else if (eventType > 4000 && eventType < 4200) {
			eventType = 4100;
		} else if (eventType > 4200 && eventType < 5000) {
			eventType = 4200;
		}else if (eventType == 1122 || eventType == 3115) {
			eventType = 1120;
		}else if (eventType == 1123 || eventType == 3117) {
			eventType = 1121;
		}else if (eventType == 6111 || eventType == 6113) {
			eventType = 6111;
		}
		return eventType;
	}	
	
	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	
	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getCreditType() {
		return creditType;
	}
}