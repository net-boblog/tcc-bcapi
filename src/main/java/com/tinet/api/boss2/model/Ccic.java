package com.tinet.api.boss2.model;

import java.util.Date;

import com.tinet.common.inc.Const;
/**
 * ccic平台实体类
 * <p>
 * 	FileName： Ccic.java
 * <p>
 * Copyright (c) 2006-2012 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Ccic extends BaseStandardEntity
		implements java.io.Serializable {

	// Fields

	private String ccicName; // ccic_name
	private String ccicUrl; // ccic平台域名，跳转用
	private String ccicIp; // ccic平台IP
	private Integer ccicPort; // ccic平台数据库端口
	private String ccicDb; // ccic平台默认数据库名
	private String ccicUser; // ccic平台数据库用户名
	private String ccicPwd; // ccic平台数据库密码
	private String areaCode; // 区号
	private Integer systemId; // 系统id
	private Integer nodeId; // 节点id
	private Integer active; // 平台状态，是否激活,0-未激活 1-激活
	private Date createTime; // 创建时间
	private String nodeDesc; // 节点描述
	private String codeIp;//代码服务器IP
	private String version;//分平台代码版本号

	// Constructors

	/** default constructor */
	public Ccic() {
		this.systemId = Const.CCIC_SYSTEM_ID_HCC;
		this.setCreateTime(new Date());
	}

	public Ccic(Integer id,String ccicName,String ccicUrl,String ccicIp,String areaCode,Integer ccicPort,String ccicDb,
			String ccicUser,String ccicPwd,Integer active){
		this.setId(id);
		this.ccicName = ccicName;
		this.ccicUrl = ccicUrl;
		this.ccicIp = ccicIp;
		this.areaCode = areaCode;
		this.ccicPort = ccicPort;
		this.ccicDb = ccicDb;
		this.ccicUser = ccicUser;
		this.ccicPwd = ccicPwd;
		this.active = active;
	}
	
	// Property accessors

	public String getCcicName() {
		return this.ccicName;
	}

	public void setCcicName(String ccicName) {
		this.ccicName = ccicName;
	}

	public String getCcicUrl() {
		return this.ccicUrl;
	}

	public void setCcicUrl(String ccicUrl) {
		this.ccicUrl = ccicUrl;
	}

	public String getCcicIp() {
		return this.ccicIp;
	}

	public void setCcicIp(String ccicIp) {
		this.ccicIp = ccicIp;
	}

	public Integer getCcicPort() {
		return this.ccicPort;
	}

	public void setCcicPort(Integer ccicPort) {
		this.ccicPort = ccicPort;
	}

	public String getCcicDb() {
		return this.ccicDb;
	}

	public void setCcicDb(String ccicDb) {
		this.ccicDb = ccicDb;
	}

	public String getCcicUser() {
		return this.ccicUser;
	}

	public void setCcicUser(String ccicUser) {
		this.ccicUser = ccicUser;
	}

	public String getCcicPwd() {
		return this.ccicPwd;
	}

	public void setCcicPwd(String ccicPwd) {
		this.ccicPwd = ccicPwd;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getSystemId() {
		return this.systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
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
	
	public String getNodeDesc() {
		switch (this.getNodeId()) {
		case Const.CCIC_NODE_ID_BEIJING:
			nodeDesc = "北京";
			break;
		case Const.CCIC_NODE_ID_SHANGHAI:
			nodeDesc = "上海";
			break;
		case Const.CCIC_NODE_ID_XIAN:
			nodeDesc = "西安";
			break;
		case Const.CCIC_NODE_ID_WUHAN:
			nodeDesc = "武汉";
			break;
		case Const.CCIC_NODE_ID_LANGFANG:
			nodeDesc = "廊坊";
			break;
		case Const.CCIC_NODE_ID_CHONGQING:
			nodeDesc = "重庆";
			break;
		case Const.CCIC_NODE_ID_NINGBO:
			nodeDesc = "宁波";
			break;
		case Const.CCIC_NODE_ID_YINCHUAN:
			nodeDesc = "银川";
			break;
		case Const.CCIC_NODE_ID_GUANGZHOU:
			nodeDesc = "广州";
			break;
		case Const.CCIC_NODE_ID_SHENZHEN:
			nodeDesc = "深圳";
			break;
		default:
			nodeDesc = "未知落地";
			break;
		}
		return nodeDesc;
	}

	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}

	public void setCodeIp(String codeIp) {
		this.codeIp = codeIp;
	}

	public String getCodeIp() {
		return codeIp;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return version;
	}

}