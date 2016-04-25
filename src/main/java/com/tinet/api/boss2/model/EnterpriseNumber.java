package com.tinet.api.boss2.model;

import java.util.Date;

import com.tinet.common.inc.Const;


/**
 * 企业外显号码数据对象类
 * <p>
 * 	FileName：EnterpriseNumber.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
public class EnterpriseNumber extends BaseStandardEntity implements java.io.Serializable {

	private static final long serialVersionUID = -3148187575363477972L;
	
	// Fields    
	private Integer enterpriseId;
	private String areaCode;
	private String numberInuse;
	private Integer type;
	private Integer status;
	private String carrier; // 运营商信息
	private Integer flag;
	private Date exportTime;
	private Date createTime;
	private String flagDesc; // 导出标记描述
	private String typeDesc; // 类型描述
	private String statusDesc; // 类型描述
	private String fullName; // 客户名称
	private String masterHotline; // 主热线号码
	private Integer entityParentId;// 所属销售id
	private String entityParentName; // 所属销售
	private String directSectionName; // 所属部门
	private String trade; // 所属行业
	private String entireNumber; // 带区号的号码
	private Integer clientCount; // 座席数量
	private String clientArea; // 座席所在地
	private Integer ccicId; // 所属平台ID
	private String ccicName;	// 	所属平台名称
	private String legalPerson; // 法人代表
	private String address; // 联系地址
	private String principalInfo; // 负责人信息
	private String principal;	// 负责人姓名
	private String mobile;		// 负责人手机
	private String email;		// 负责人邮箱
	private String payerInfo; // 付款人信息
	private String payerName; // 付款人姓名
	private String payerMobile; // 付款人手机
	private String payerEmail; // 付款人邮箱

	// Constructors

	/** default constructor */
	public EnterpriseNumber() {
		this.status = Const.ENTERPRISE_NUMBER_STATUS_INUSE;
		this.flag = Const.ENTERPRISE_NUMBER_FLAG_NO;
		this.carrier = "";
		this.areaCode = "";
		this.createTime = new Date();
	}


	// Property accessors

	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumberInuse() {
		return this.numberInuse;
	}

	public void setNumberInuse(String numberInuse) {
		this.numberInuse = numberInuse;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getExportTime() {
		return this.exportTime;
	}

	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFlagDesc() {
		this.flagDesc = "";
		if(this.getFlag()!=null){
			switch (this.getFlag()) {
				case Const.ENTERPRISE_NUMBER_FLAG_NO:
					this.flagDesc = "未导出";
					break;
				case Const.ENTERPRISE_NUMBER_FLAG_YES:
					this.flagDesc = "已导出";
					break;
				default:
					this.flagDesc = "";
					break;
			}
		}
		return flagDesc;
	}

	public void setFlagDesc(String flagDesc) {
		this.flagDesc = flagDesc;
	}

	public String getTypeDesc() {
		this.typeDesc = "";
		if (this.getType()!=null) {
			switch (this.getType()) {
				case Const.ENTERPRISE_NUMBER_TYPE_400_NUMBER:
					this.typeDesc = "400/1010号码";
					break;
				case Const.ENTERPRISE_NUMBER_TYPE_TRUNK:
					this.typeDesc = "中继号码";
					break;
				case Const.ENTERPRISE_NUMBER_TYPE_MOBILE_VIRTUAL:
					this.typeDesc = "手机虚拟号码";
					break;
				case Const.ENTERPRISE_NUMBER_TYPE_ENTERPRISE_OWNED:
					this.typeDesc = "客户自备号码";
					break;
				default:
					this.typeDesc = "";
					break;
			}
		}
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
	public String getStatusDesc() {
		this.statusDesc = "";
		if (this.getStatus()!=null) {
			switch (this.getStatus()) {
				case Const.ENTERPRISE_NUMBER_STATUS_UNUSE:
					this.statusDesc = "曾使用";
					break;
				case Const.ENTERPRISE_NUMBER_STATUS_INUSE:
					this.statusDesc = "使用中";
					break;
				default:
					this.statusDesc = "";
					break;
			}
		}
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMasterHotline() {
		return masterHotline;
	}

	public void setMasterHotline(String masterHotline) {
		this.masterHotline = masterHotline;
	}

	public Integer getEntityParentId() {
		return entityParentId;
	}
	
	public void setEntityParentId(Integer entityParentId) {
		this.entityParentId = entityParentId;
	}

	public String getEntityParentName() {
		return entityParentName;
	}

	public void setEntityParentName(String entityParentName) {
		this.entityParentName = entityParentName;
	}

	public String getDirectSectionName() {
		return directSectionName;
	}

	public void setDirectSectionName(String directSectionName) {
		this.directSectionName = directSectionName;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getEntireNumber() {
		if (this.getType() == Const.ENTERPRISE_NUMBER_TYPE_TRUNK) {
			if (this.getAreaCode()!=null && this.getNumberInuse()!=null) {
				this.entireNumber = this.getAreaCode()+this.getNumberInuse();
			}
		} else {
			this.entireNumber = this.getNumberInuse();
		}
		return entireNumber;
	}

	public void setEntireNumber(String entireNumber) {
		this.entireNumber = entireNumber;
	}

	public Integer getClientCount() {
		return clientCount;
	}

	public void setClientCount(Integer clientCount) {
		this.clientCount = clientCount;
	}

	public String getClientArea() {
		return clientArea;
	}

	public void setClientArea(String clientArea) {
		this.clientArea = clientArea;
	}

	public Integer getCcicId() {
		return ccicId;
	}

	public void setCcicId(Integer ccicId) {
		this.ccicId = ccicId;
	}
	
	public String getCcicName() {
		return ccicName;
	}

	public void setCcicName(String ccicName) {
		this.ccicName = ccicName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrincipalInfo() {
		String name = this.getPrincipal()==null ? "": this.getPrincipal();
		String phone = this.getMobile()==null ? "": this.getMobile();
		String mail = this.getEmail()==null ? "": this.getEmail();
		principalInfo = name + "/" + phone + "/" + mail;
		return principalInfo;
	}

	public void setPrincipalInfo(String principalInfo) {
		this.principalInfo = principalInfo;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayerInfo() {
		String name = this.getPayerName()==null ? "": this.getPayerName();
		String phone = this.getPayerMobile()==null ? "": this.getPayerMobile();
		String mail = this.getPayerEmail()==null ? "": this.getPayerEmail();
		payerInfo = name + "/" + phone + "/" + mail;
		return payerInfo;
	}

	public void setPayerInfo(String payerInfo) {
		this.payerInfo = payerInfo;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerMobile() {
		return payerMobile;
	}

	public void setPayerMobile(String payerMobile) {
		this.payerMobile = payerMobile;
	}

	public String getPayerEmail() {
		return payerEmail;
	}

	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}
	
}