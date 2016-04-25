package com.tinet.api.boss2.model;

import java.util.Date;

import com.tinet.common.inc.Const;



/**
 * 语音文件审核视图实体类
 * <p>
 * 	FileName： VoiceAudit.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */

@SuppressWarnings("serial")

public class VoiceAudit extends BaseStandardEntity implements java.io.Serializable {
	private Integer enterpriseId;
	private Integer ccicId;
	private String enterpriseName;
	private String masterHotline;
	private Integer voiceId;
	private String voiceName;
	private String description;
	private Integer auditStatus;
	private String auditStatusDesc;
	private String auditComment;
	private String path;
	private String url;
	private Date createTime;
	
	
	public VoiceAudit() {
		super();
	}

	public VoiceAudit(Integer enterpriseId, Integer ccicId, String enterpriseName, String masterHotline, Integer voiceId, 
			String voiceName, String description, Integer auditStatus,String auditComment, String path, String url, Date createTime) {
		this.enterpriseId = enterpriseId;
		this.ccicId=ccicId;
		this.enterpriseName = enterpriseName;
		this.masterHotline = masterHotline;
		this.voiceId = voiceId;
		this.voiceName = voiceName;
		this.description = description;
		this.auditStatus = auditStatus;
		this.auditComment = auditComment;
		this.path = path;
		this.url = url;
		this.createTime = createTime;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}


	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}


	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}


	public String getMasterHotline() {
		return masterHotline;
	}


	public void setMasterHotline(String masterHotline) {
		this.masterHotline = masterHotline;
	}


	public Integer getVoiceId() {
		return voiceId;
	}


	public void setVoiceId(Integer voiceId) {
		this.voiceId = voiceId;
	}


	public String getVoiceName() {
		return voiceName;
	}


	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getAuditStatus() {
		return auditStatus;
	}


	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}


	public Integer getCcicId() {
		return ccicId;
	}


	public void setCcicId(Integer ccicId) {
		this.ccicId = ccicId;
	}
	
	
	public String getAuditStatusDesc() {
		if (getAuditStatus()!=null) {
			switch(getAuditStatus()){
				case Const.ENTERPRISE_VOICE_AUDIT_STATUS_UNAUDITED:
					this.auditStatusDesc = "未审核";
					break;
				case Const.ENTERPRISE_VOICE_AUDIT_STATUS_AUDITING:
					this.auditStatusDesc = "审核中";
					break;
				case Const.ENTERPRISE_VOICE_AUDIT_STATUS_AUDITPASS:
					this.auditStatusDesc = "审核通过";
					break;
				case Const.ENTERPRISE_VOICE_AUDIT_STATUS_AUDITFAIL:
					this.auditStatusDesc = "审核失败";
					break;
				default:this.auditStatusDesc="未知状态";
			}
		}
		return auditStatusDesc;
	}

	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
