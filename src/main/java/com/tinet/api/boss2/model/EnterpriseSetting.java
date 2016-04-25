package com.tinet.api.boss2.model;

import java.util.Date;

import com.tinet.common.inc.Const;
import com.tinet.common.util.StringUtil;

public class EnterpriseSetting {
	private Integer id;
	private Integer enterpriseId;
	private String name; // name-value对
	private String value; // name-value对
	private String property; // 属性或描述
	private Date createTime; // 记录创建时间
	private String nameDesc;
	
	public EnterpriseSetting(){
		
	}
	public EnterpriseSetting(int enterpriseId, String name, String value,
			String property) {
		this.enterpriseId = enterpriseId;
		this.name = name;
		this.value = value;
		this.property = property;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getNameDesc() {
		if(this.getName().equals(Const.ENTERPRISE_SETTING_NAME_TAOBAO_NICK)){
			this.nameDesc = "淘宝账号";
		}else if(this.getName().equals(Const.ENTERPRISE_SETTING_TEL_STATUS_IDENTIFICATION)){
			this.nameDesc = "号码状态识别选项";
		}else if(this.getName().equals(Const.ENTERPRISE_SETTING_PREDICTIVE_CALL_LIMIT)){
			this.nameDesc = "预测外呼企业并发限制";
		}else if(this.getName().equals(Const.ENTGERPRISE_SETTING_IS_IVR_LOCK)){
			this.nameDesc = "ivr锁定";
		}else if(this.getName().equals(Const.ENTGERPRISE_SETTING_CURL_LEVEL)){
			this.nameDesc = "curl级别";
		}else if(this.getName().equals(Const.ENTGERPRISE_SETTING_IS_CRBT)){
			this.nameDesc = "呼入彩铃功能";
		}else if(this.getName().equals(Const.ENTERPRISE_SETTING_IS_RECORD_TEL)){
			this.nameDesc = "呼叫手机录音";
		}else if(this.getName().equals(Const.ENTERPRISE_SETTING_NAME_OB_PREDICTIVE_WORK_TIME)){
			this.nameDesc = "预测外呼功能启用时间";
		}else {
			this.nameDesc = this.getName();
		}
		return this.nameDesc;
	}
	
	public String getStartTime() {
		if(this.getName().equals(Const.ENTERPRISE_SETTING_NAME_OB_PREDICTIVE_WORK_TIME)
				&& StringUtil.isNotEmpty(this.getValue())){
			return this.getValue().split(",")[0];
		}else{
			return "";
		}
	}
	public String getEndTime() {
		if(this.getName().equals(Const.ENTERPRISE_SETTING_NAME_OB_PREDICTIVE_WORK_TIME)
				&& StringUtil.isNotEmpty(this.getValue())){
			return this.getValue().split(",")[1];
		}else{
			return "";
		}
	}
	
	
}
