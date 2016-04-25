package com.tinet.api.boss2.model;

import java.util.Date;

import com.tinet.common.inc.Const;
/**
 * 企业扩展设置实体类
 * <p>
 * 	FileName：EnterpriseSettingAll.java
 * <p>
 * @author chenhui
 * @since 
 * @version 
 */
public class EnterpriseSettingAll extends BaseStandardEntity implements java.io.Serializable {

	private static final long serialVersionUID = -3806356258281253377L;
	
	// Fields    
	private Integer enterpriseId;
	private String name;
	private String value;
	private String property;
	private Date createTime;
	
	private String nameDesc;

	// Constructors

	/** default constructor */
	public EnterpriseSettingAll() {
		this.setCreateTime(new Date());
	}
	
	public EnterpriseSettingAll(Integer enterpriseId,String name, String value,String property) {
//		super.setId(id);
		this.setEnterpriseId(enterpriseId);
		this.setName(name);
		this.setValue(value);
		this.setProperty(property);
		this.setCreateTime(new Date());
	}


	// Property accessors
	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getName() {
		return this.name;
	}
	
	public String getNameDesc() {
		if (this.getName()!=null) {
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
			}else {
				this.nameDesc = this.getName();
			}
		}
		return this.nameDesc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}