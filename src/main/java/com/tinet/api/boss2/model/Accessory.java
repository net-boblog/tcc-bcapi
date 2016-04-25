package com.tinet.api.boss2.model;

import java.util.Date;

import com.tinet.common.inc.Const;


/**
 * 企业附件类
 * <p>
 * 	FileName：Accessory.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Accessory extends BaseStandardEntity implements java.io.Serializable {

	// Fields    
	private Integer entityId; // 对应entity->id，或分平台的entity->enterprise_id
	private Integer accessoryType; // 附件类型：1--合同  2--运营商受理单  3--营业执照复印件  4--经办人身份证复印件  5--天润受理单  6--法人身份证复印件  7--特号申请表
	private Integer operatorType; // 上传者角色：1--一级代理商，2--客服
	private String fileName; // 附件文件名称
	private String filePath; // 附件实际地址
	private String comment; // 附件备注
	private String accessoryTypeDesc; // 附件类型描述
	private Date createTime; // 记录创建时间

	// Constructors

	/** default constructor */
	public Accessory() {
		this.setCreateTime(new Date());
	}

	// Property accessors

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}
	
	public Integer getAccessoryType() {
		return this.accessoryType;
	}

	public void setAccessoryType(Integer accessoryType) {
		this.accessoryType = accessoryType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getAccessoryTypeDesc() {
		switch (accessoryType) {
		case Const.ACCESSORY_ACCESORY_TYPE_CONTRACT:
			accessoryTypeDesc = "合同";
			break;
		case Const.ACCESSORY_ACCESORY_TYPE_MSO_ACCEPTANCE_FORM:
			accessoryTypeDesc = "运营商受理单";
			break;
		case Const.ACCESSORY_ACCESORY_TYPE_BUSINESS_LICENSE:
			accessoryTypeDesc = "营业执照复印件";
			break;
		case Const.ACCESSORY_ACCESORY_TYPE_OPERATOR_ID_CARD:
			accessoryTypeDesc = "经办人身份证复印件";
			break;
		case Const.ACCESSORY_ACCESORY_TYPE_TINET_ACCEPTANCE_FORM:
			accessoryTypeDesc = "天润受理单";
			break;
		case Const.ACCESSORY_ACCESORY_TYPE_LEGAL_PERSON_ID_CARD:
			accessoryTypeDesc = "法人身份证复印件";
			break;
		case Const.ACCESSORY_ACCESORY_TYPE_SPECIAL_NUMBER_APPLICATION_FORM:
			accessoryTypeDesc = "特号申请表";
			break;
		default:
			break;
		}
		return accessoryTypeDesc;
	}

	public void setAccessoryTypeDesc(String accessoryTypeDesc) {
		this.accessoryTypeDesc = accessoryTypeDesc;
	}

}