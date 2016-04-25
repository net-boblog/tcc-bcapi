package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;
import com.tinet.common.util.StringUtil;

/**
 * 客户开户信息临时存储实体类
 * <p>
 * 	文件名： EnterpriseTemp.java
 * <p>
 * Copyright (c) 2006-2011 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */

public class EnterpriseTemp  {

	// Fields    

	/** 实体id，对应entity */
	private Entity entity;
	
	/** 实体id，对应分平台entity-->enterprise_id */
	private Integer enterpriseId;
	
	/**开户操作人类型，对应entity-->entity_type*/
	private Integer operatorType;
	
	/** 平台id，对应ccic-->ccic_id */
	private Ccic ccic;
	
	/** cti服务器id，对应cti-->id */
	private Integer ctiId;
	private String ctiIp;
	
	/** 产品id，对应product-->product_id */
	private Product product;
	
	/** 合同号 */
	private String bargainNo;
	
	/** 所属销售或客服 */
	private String owner;
	
	/** 企业logo标识 */
	private String logo;
	
	/** 企业状态， 0:未开通 1:正常 2:欠费 3:停机 4:注销 */
	private Integer status;
	
	/** 是否测试用户，0:非测试用户 1：测试用户 */
	private Integer isTest;
	
	/** 直销经理填写热线号码 按呼入计算 */
	private String userField1;
	/** 直销经理填写热线号码 最低消费*/
	private String userField2;
	/** 直销经理填写最低消费计费模式*/
	private String lowestCostMode;
	/** 直销经理填写费率组成字符串*/
	private String userField3;
	/** 直销经理填写计费周期*/
	private String userField4;
	/**  使用接口*/
	private Integer isUseItf;
	/**  客户电话接入方式*/
	private Integer accessMode;
	/** 是否全称录音*/
	private Integer isFullRecord;
	/**  企业录音试听方式*/
	private Integer isAwsRecord;
	/**  S3目录*/
	private String awsRecordDirectory;
	
	private String outCallSwitch;
	
	private String outCallTimes;
	
	private String outCallBridgeTime;
	
	private String clearDays;
	
	private String outCallPropertyJson;

	/** 呼入客户侧费率套餐id */
	private Integer rateIbLeft;
	private String rateIbLeftName;
	private String rateIbLeftPrice;
	private BigDecimal ibLeft;
	
	/** 呼入座席侧费率套餐id */
	private Integer rateIbRight;
	private String rateIbRightName;
	private String rateIbRightPrice;
	private BigDecimal ibRight;
	
	/** 呼出客户侧费率套餐id */
	private Integer rateObLeft;
	private String rateObLeftName;
	private String rateObLeftPrice;
	private BigDecimal obLeft;
	
	/** 呼出座席侧费率套餐id */
	private Integer rateObRight;
	private String rateObRightName;
	private String rateObRightPrice;
	private BigDecimal obRight;
	
	/** 短信费率 */
	private BigDecimal rateSms;
	
	/** 所有计费号码最低消费 */
	private BigDecimal lowestCost;
	/** 所有计费号码最低消费英文逗号组成字符串 */
	private String lowestCostEach;
	
	/** 电话座席最大个数 */
	private Integer clientTel;
	
	/** 电脑座席最大个数 */
	private Integer clientWeb;
	
	/** 座席月租费 */
	private BigDecimal rent;
	
	/** 座席月租费有效期 */
	private Date rentEnd;
	
	/** 话费充值金额 */
	private BigDecimal money;
	
	/** 功能费充值金额 */
	private BigDecimal fmoney;
	
	/** 外呼话费充值金额 */
	private BigDecimal omoney;
	
	/** 短信账户充值金额 */
	private BigDecimal smoney;
	
	/** 呼入路由:呼叫座席 */
	private Integer ibRouterRight;
	private String ibRouterRightName;
	
	/** 预览外呼路由:呼叫客户（网上400呼叫客户，主叫外呼呼叫客户） */
	private Integer obPreviewRouterLeft;
	private String obPreviewRouterLeftName;
	
	/** 预测外呼路由:呼叫客户 */
	private Integer obPredictiveRouterLeft;
	private String obPredictiveRouterLeftName;
	
	/** 呼入转座席透传号码 1:中继 2:客户 3:固定 */
	private Integer ibClidRightType;
	
	/** ib_clid_right_type=3时的号码 */
	private String ibClidRightNumber;
	
	/** 预览外呼客户侧透传号码类型 1:中继 2:客户 3:固定 */
	private Integer obPreviewClidLeftType;
	
	/** ob_preview_clid_left_type=3时的号码 */
	private String obPreviewClidLeftNumber;
	
	/** 预览外呼座席侧透传号码类型 1:中继 2:客户 3:固定 */
	private Integer obPreviewClidRightType;
	
	/** ob_preview_clid_right_type=3时的号码 */
	private String obPreviewClidRightNumber;
	
	/** 预测外呼客户侧透传号码类型 1:中继 2:客户 3:固定 */
	private Integer obPredictiveClidLeftType;
	
	/** ob_predictive_clid_left_type=3时的号码 多个号码以逗号分隔呼叫时随机使用 */
	private String obPredictiveClidLeftNumber;
	
	/** 预测外呼座席侧透传号码类型 1:中继 2:客户 3:固定 */
	private Integer obPredictiveClidRightType;
	
	/** ob_predictive_clid_right_type=3时的号码 */
	private String obPredictiveClidRightNumber;
	
	/** 座席端风格 smoothness,redmond */
	private String clientCss;
	
	/** CRM配置 0:网上托管 1:CRM对接 */
	private Integer crmType;
	
	/** CRM地址 crm_type=0时为内部地址 crm_type=1时为外部地址 */
	private String crmUrl;
	
	/** 弹屏配置 0:默认 1:iframe弹屏 */
	private Integer popscreenType;
	
	/** 弹屏地址 popscreen_type=0时为内部地址 popscreen_type=1时为外部地址 */
	private String popscreenUrl;
	
	/** 是否支持API开发，字符串存储比如 cdr,queue,agent,ivr等以逗号分隔字符串 */
	private String apiSupport;
	
	/** 呼入并发限制 0:不限制 配置时可以按座席比例，也可以直接写绝对值 */
	private Integer inboundCallLimit;
	
	/** 合同开始时间戳 */
	private Date serviceStart;
	
	/** 合同截止时间戳 */
	private Date serviceEnd;
	
	/** 审核状态，1:未提交 2:未审核 3:审核中 4:打回修改 */
	private Integer auditStatus;
	
	/** 审核备注 */
	private String comment;
	
	/** 提交审核时间 */
	private Date submitTime;
	
	/** 记录修改时间 */
	private Date updateTime;
	
	/** 记录创建时间 */
	private Date createTime;
	
	/** 二级代理商名称 */
	private String agent2Name;
	
	/** 一级代理商名称 */
	private String agent1Name;
	
	/** 直销经理名称 */
	private String managerName;
	
	/** 直销部门名称 */
	private String directName;
	
	/** 企业状态描述 */
	private String statusDesc;
	
	/** 开户企业审核状态描述 */
	private String auditStatusDesc;
	
	/** 测试账户使用有效期 */
	private String expiryDate;
	
	/** 操作标识：0-账户初始化 1-新增账户 */
	private Integer operateFlag;
	
	/** 仅仅是一个标识 */
	private Integer xiangQing;
	
	private Set<EnterpriseTrunk> enterpriseTrunks;
	private Set<EnterpriseHotline> enterpriseHotlines;
	private Set<FunctionSelect> functionSelects;
	
	private String allNumber;
	private String masterHotline;
	private String allTrunk;
	private String billingTrunk;
	
	private String path;
	/** 外呼套餐id */
	private Integer comboId;
	
	private String[] functionId;
	
	private Integer clientFree;
	
	private Integer productType;
	private String clientArea;
	private Integer isIdd;
	
	/**
	 * 开户写enterprise_combo使用
	 * 
	*/
	private BigDecimal deuctFee;
	private BigDecimal threshold;
	
	private Integer ccicId;
	// Constructors

	/** default constructor */
	public EnterpriseTemp() {
		this.logo = "";
		this.status = Const.ENTERPRISE_TEMP_STATUS_NO_SERVICE;
		this.isTest = Const.ENTERPRISE_TEMP_IS_TEST_NO;
		this.lowestCost = new BigDecimal(0);
		this.clientTel = 0;
		this.clientWeb = 0;
		this.rent = new BigDecimal(0.0);
		this.rentEnd = new Date();
		this.money = new BigDecimal(0.0);
		this.fmoney = new BigDecimal(0.0);
		this.omoney = new BigDecimal(0.0);
		this.smoney = new BigDecimal(0.0);
		// 外呼路由默认为死路由
		this.ibRouterRight = 0;
		this.obPredictiveRouterLeft = 0;
		this.obPreviewRouterLeft = 0; 
		this.ibClidRightNumber = "";
		// 外呼透传号码默认为透传中继号码
		this.obPreviewClidLeftType = Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK; 
		this.obPreviewClidLeftNumber = "";
		this.obPreviewClidRightType = Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK;
		this.obPreviewClidRightNumber = "";
		this.obPredictiveClidLeftType = Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK;
		this.obPredictiveClidLeftNumber = "";
		this.obPredictiveClidRightType = Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK;
		this.obPredictiveClidRightNumber = "";
		this.inboundCallLimit = 200;
		this.crmType = Const.ENTERPRISE_TEMP_CRM_TYPE_HOSTED;
		this.crmUrl = "";
		this.popscreenType = Const.ENTERPRISE_TEMP_POPSCREEN_TYPE_DEFAULT;
		this.popscreenUrl = "";
		this.apiSupport = "";
		this.auditStatus = Const.ENTERPRISE_TEMP_AUDIT_STATUS_UNCOMMITTED;
		this.updateTime = new Date();
		this.allNumber = "";
		this.allTrunk = "";
		this.masterHotline = "";
		this.billingTrunk = "";
		// 设置默认短信费率
		this.rateSms = new BigDecimal("0.1");
		this.setCreateTime(new Date());
		this.isIdd = Const.ENTERPRISE_TEMP_IS_IDD_OFF;
	}
	//直销经理填写开户申请使用的构造方法
	public EnterpriseTemp(int i) {
		this.logo = "";
		this.status = Const.ENTERPRISE_TEMP_STATUS_NO_SERVICE;
		this.isTest = Const.ENTERPRISE_TEMP_IS_TEST_NO;
		this.lowestCost = new BigDecimal(0);
		this.clientTel = 0;
		this.clientWeb = 0;
		this.rent = new BigDecimal(0.0);
		this.rentEnd = new Date();
		this.money = new BigDecimal(0.0);
		this.fmoney = new BigDecimal(0.0);
		this.omoney = new BigDecimal(0.0);
		this.smoney = new BigDecimal(0.0);
		/*// 外呼路由默认为死路由
		this.ibRouterRight = 0;
		this.obPredictiveRouterLeft = 0;
		this.obPreviewRouterLeft = 0; 
		this.ibClidRightNumber = "";*/
		// 外呼透传号码默认为透传中继号码
		this.obPreviewClidLeftType = Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK; 
		this.obPreviewClidLeftNumber = "";
		this.obPreviewClidRightType = Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK;
		this.obPreviewClidRightNumber = "";
		this.obPredictiveClidLeftType = Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK;
		this.obPredictiveClidLeftNumber = "";
		this.obPredictiveClidRightType = Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK;
		this.obPredictiveClidRightNumber = "";
		this.inboundCallLimit = 200;
		this.crmType = Const.ENTERPRISE_TEMP_CRM_TYPE_HOSTED;
		this.crmUrl = "";
		this.popscreenType = Const.ENTERPRISE_TEMP_POPSCREEN_TYPE_DEFAULT;
		this.popscreenUrl = "";
		this.apiSupport = "";
		this.auditStatus = Const.ENTERPRISE_TEMP_AUDIT_STATUS_UNCOMMITTED;
		this.updateTime = new Date();
		this.allNumber = "";
		this.allTrunk = "";
		this.masterHotline = "";
		this.billingTrunk = "";
		// 设置默认短信费率
		this.rateSms = new BigDecimal("0.1");
		this.setCreateTime(new Date());
		this.isIdd = Const.ENTERPRISE_TEMP_IS_IDD_OFF;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public Ccic getCcic() {
		return this.ccic;
	}

	public void setCcic(Ccic ccic) {
		this.ccic = ccic;
	}

	public Integer getCtiId() {
		return this.ctiId;
	}

	public void setCtiId(Integer ctiId) {
		this.ctiId = ctiId;
	}
	
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBargainNo() {
		return this.bargainNo;
	}

	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsTest() {
		return this.isTest;
	}

	public void setIsTest(Integer isTest) {
		this.isTest = isTest;
	}

	public Integer getRateIbLeft() {
		return this.rateIbLeft;
	}

	public void setRateIbLeft(Integer rateIbLeft) {
		this.rateIbLeft = rateIbLeft;
	}

	public Integer getRateIbRight() {
		return this.rateIbRight;
	}

	public void setRateIbRight(Integer rateIbRight) {
		this.rateIbRight = rateIbRight;
	}

	public Integer getRateObLeft() {
		return this.rateObLeft;
	}

	public void setRateObLeft(Integer rateObLeft) {
		this.rateObLeft = rateObLeft;
	}

	public Integer getRateObRight() {
		return this.rateObRight;
	}

	public void setRateObRight(Integer rateObRight) {
		this.rateObRight = rateObRight;
	}

	public String getUserField1() {
		return userField1;
	}
	
	public void setUserField1(String userField1) {
		this.userField1 = userField1;
	}
	
	public String getUserField2() {
		return userField2;
	}
	
	public void setUserField2(String userField2) {
		this.userField2 = userField2;
	}

	public void getUserField3Parse(){
		if(StringUtil.isNotEmpty(this.userField3)){
			String[] rate = this.userField3.split(",");
			this.ibLeft = new BigDecimal(rate[0]);
			this.ibRight = new BigDecimal(rate[1]);
			this.obLeft = new BigDecimal(rate[2]);
			this.obRight = new BigDecimal(rate[3]);
		}
	}
	public void setUserField3Format(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d){
		this.userField3 = a.toString() + "," + b.toString() + "," + c.toString() + "," + d.toString();
	}
	
	public void setUserField3(String userField3) {
		this.userField3 = userField3;
	}

	public String getUserField3() {
		return userField3;
	}
	
	public BigDecimal getRateSms() {
		return rateSms;
	}

	public void setRateSms(BigDecimal rateSms) {
		this.rateSms = rateSms;
	}

	public BigDecimal getLowestCost() {
		lowestCost = new BigDecimal(0);
		if (enterpriseHotlines!=null && enterpriseHotlines.size()>0) {
			for (Iterator<EnterpriseHotline> iterator = enterpriseHotlines.iterator(); iterator.hasNext();) {
				EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
				lowestCost = lowestCost.add(entHotline.getLowestCost());
			}
		}
		return lowestCost;
	}

	public void setLowestCost(BigDecimal lowestCost) {
		this.lowestCost = lowestCost;
	}
	
	public BigDecimal getMHLowestCost() {
		BigDecimal MHLowestCost = new BigDecimal(0);
		if (enterpriseHotlines!=null && enterpriseHotlines.size()>0) {
			for (Iterator<EnterpriseHotline> iterator = enterpriseHotlines.iterator(); iterator.hasNext();) {
				EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
				if(entHotline.getIsMaster() == Const.ENTERPRISE_HOTLINE_IS_MASTER_YES){
					MHLowestCost = entHotline.getLowestCost();
				}
			}
		}
		return MHLowestCost;
	}
	
	public void setLowestCostEach(String lowestCostEach) {
		this.lowestCostEach = lowestCostEach;
	}

	public String getLowestCostEach() {
		lowestCostEach = "";
		if (enterpriseHotlines!=null && enterpriseHotlines.size()>0) {
			for (Iterator<EnterpriseHotline> iterator = enterpriseHotlines.iterator(); iterator.hasNext();) {
				EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
				if(entHotline.getPeriod().equals(Const.ENTERPRISE_HOTLINE_PERIOD_MONTH)){
					lowestCostEach += entHotline.getLowestCost().multiply(new BigDecimal(12)).toString();
					lowestCostEach += ",";
				}else{
					lowestCostEach += entHotline.getLowestCost().toString();
					lowestCostEach += ",";
				}
			}
		}
		if(lowestCostEach != null && lowestCostEach.length() > 0){
			lowestCostEach = lowestCostEach.substring(0,lowestCostEach.length()-1);
		}
		return lowestCostEach;
	}

	public String getLowestCostMode() {
		String lowestCostMode = "";
		if (enterpriseHotlines!=null && enterpriseHotlines.size()>0) {
			for (Iterator<EnterpriseHotline> iterator = enterpriseHotlines.iterator(); iterator.hasNext();) {
				EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
				if(entHotline.getLowestCostMode() != null){
					lowestCostMode += entHotline.getLowestCostMode().toString();
					lowestCostMode += ",";
				}
			}
		}
		if(lowestCostMode != null && lowestCostMode.length() > 0){
			lowestCostMode = lowestCostMode.substring(0,lowestCostMode.length()-1);
		}
		if(StringUtil.isNotEmpty(lowestCostMode)){
			return lowestCostMode;
		}else{
			return this.lowestCostMode;
		}
	}
	
	public Integer getPeriod() {
		int period = 1;
		if (enterpriseHotlines!=null && enterpriseHotlines.size()>0) {
			for (Iterator<EnterpriseHotline> iterator = enterpriseHotlines.iterator(); iterator.hasNext();) {
				EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
				period = entHotline.getPeriod();
				break;
			}
		}
		return period;
	}

	public Integer getClientTel() {
		return this.clientTel;
	}

	public void setClientTel(Integer clientTel) {
		this.clientTel = clientTel;
	}

	public Integer getClientWeb() {
		return this.clientWeb;
	}

	public void setClientWeb(Integer clientWeb) {
		this.clientWeb = clientWeb;
	}

	public BigDecimal getRent() {
		return this.rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public Date getRentEnd() {
		return this.rentEnd;
	}

	public void setRentEnd(Date rentEnd) {
		this.rentEnd = rentEnd;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getFmoney() {
		return this.fmoney;
	}

	public void setFmoney(BigDecimal fmoney) {
		this.fmoney = fmoney;
	}

	public BigDecimal getOmoney() {
		return this.omoney;
	}

	public void setOmoney(BigDecimal omoney) {
		this.omoney = omoney;
	}

	public BigDecimal getSmoney() {
		return this.smoney;
	}

	public void setSmoney(BigDecimal smoney) {
		this.smoney = smoney;
	}

	public Integer getIbRouterRight() {
		return this.ibRouterRight;
	}

	public void setIbRouterRight(Integer ibRouterRight) {
		this.ibRouterRight = ibRouterRight;
	}

	public Integer getObPreviewRouterLeft() {
		return this.obPreviewRouterLeft;
	}

	public void setObPreviewRouterLeft(Integer obPreviewRouterLeft) {
		this.obPreviewRouterLeft = obPreviewRouterLeft;
	}

	public Integer getObPredictiveRouterLeft() {
		return this.obPredictiveRouterLeft;
	}

	public void setObPredictiveRouterLeft(Integer obPredictiveRouterLeft) {
		this.obPredictiveRouterLeft = obPredictiveRouterLeft;
	}

	public Integer getIbClidRightType() {
		return this.ibClidRightType;
	}

	public void setIbClidRightType(Integer ibClidRightType) {
		this.ibClidRightType = ibClidRightType;
	}

	public String getIbClidRightNumber() {
		return this.ibClidRightNumber;
	}

	public void setIbClidRightNumber(String ibClidRightNumber) {
		this.ibClidRightNumber = ibClidRightNumber;
	}

	public Integer getObPreviewClidLeftType() {
		return this.obPreviewClidLeftType;
	}

	public void setObPreviewClidLeftType(Integer obPreviewClidLeftType) {
		this.obPreviewClidLeftType = obPreviewClidLeftType;
	}

	public String getObPreviewClidLeftNumber() {
		return this.obPreviewClidLeftNumber;
	}

	public void setObPreviewClidLeftNumber(String obPreviewClidLeftNumber) {
		this.obPreviewClidLeftNumber = obPreviewClidLeftNumber;
	}

	public Integer getObPreviewClidRightType() {
		return this.obPreviewClidRightType;
	}

	public void setObPreviewClidRightType(Integer obPreviewClidRightType) {
		this.obPreviewClidRightType = obPreviewClidRightType;
	}

	public String getObPreviewClidRightNumber() {
		return this.obPreviewClidRightNumber;
	}

	public void setObPreviewClidRightNumber(String obPreviewClidRightNumber) {
		this.obPreviewClidRightNumber = obPreviewClidRightNumber;
	}

	public Integer getObPredictiveClidLeftType() {
		return this.obPredictiveClidLeftType;
	}

	public void setObPredictiveClidLeftType(Integer obPredictiveClidLeftType) {
		this.obPredictiveClidLeftType = obPredictiveClidLeftType;
	}

	public String getObPredictiveClidLeftNumber() {
		return this.obPredictiveClidLeftNumber;
	}

	public void setObPredictiveClidLeftNumber(String obPredictiveClidLeftNumber) {
		this.obPredictiveClidLeftNumber = obPredictiveClidLeftNumber;
	}

	public Integer getObPredictiveClidRightType() {
		return this.obPredictiveClidRightType;
	}

	public void setObPredictiveClidRightType(Integer obPredictiveClidRightType) {
		this.obPredictiveClidRightType = obPredictiveClidRightType;
	}

	public String getObPredictiveClidRightNumber() {
		return this.obPredictiveClidRightNumber;
	}

	public void setObPredictiveClidRightNumber(String obPredictiveClidRightNumber) {
		this.obPredictiveClidRightNumber = obPredictiveClidRightNumber;
	}

	public String getClientCss() {
		return this.clientCss;
	}

	public void setClientCss(String clientCss) {
		this.clientCss = clientCss;
	}

	public Integer getCrmType() {
		return this.crmType;
	}

	public void setCrmType(Integer crmType) {
		this.crmType = crmType;
	}

	public String getCrmUrl() {
		return this.crmUrl;
	}

	public void setCrmUrl(String crmUrl) {
		this.crmUrl = crmUrl;
	}

	public Integer getPopscreenType() {
		return this.popscreenType;
	}

	public void setPopscreenType(Integer popscreenType) {
		this.popscreenType = popscreenType;
	}

	public String getPopscreenUrl() {
		return this.popscreenUrl;
	}

	public void setPopscreenUrl(String popscreenUrl) {
		this.popscreenUrl = popscreenUrl;
	}

	public String getApiSupport() {
		return this.apiSupport;
	}

	public void setApiSupport(String apiSupport) {
		this.apiSupport = apiSupport;
	}

	public Integer getInboundCallLimit() {
		return this.inboundCallLimit;
	}

	public void setInboundCallLimit(Integer inboundCallLimit) {
		this.inboundCallLimit = inboundCallLimit;
	}

	public Date getServiceStart() {
		return this.serviceStart;
	}
	
	public void setServiceStart(Date serviceStart) {
		this.serviceStart = serviceStart;
	}

	public void setServiceStart(String serviceStart) {
		try {
			this.serviceStart = DateUtil.parse(serviceStart);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getServiceEnd() {
		return this.serviceEnd;
	}
	
	public void setServiceEnd(Date serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	public void setServiceEnd(String serviceEnd) {
		try {
			this.serviceEnd = DateUtil.parse(serviceEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Integer getAuditStatus() {
		return this.auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getAgent2Name() {
		return agent2Name;
	}

	public void setAgent2Name(String agent2Name) {
		this.agent2Name = agent2Name;
	}

	public String getAgent1Name() {
		return agent1Name;
	}

	public void setAgent1Name(String agent1Name) {
		this.agent1Name = agent1Name;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getDirectName() {
		return directName;
	}

	public void setDirectName(String directName) {
		this.directName = directName;
	}

	public String getAuditStatusDesc() {
		switch (this.getAuditStatus()) {
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_UNCOMMITTED:
			this.auditStatusDesc = "未提交";
			break;
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_UNAUDITED:
			this.auditStatusDesc = "未审核";
			break;
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_ONAUDIT:
			this.auditStatusDesc = "审核中";
			break;
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_FAIL_AUDIT:
			this.auditStatusDesc = "审核失败";
			break;
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_AUDIT_PASS:
			this.auditStatusDesc = "审核通过";
			break;
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_UNAPPROVED:
			this.auditStatusDesc = "未审批";
			break;
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_APPROVEING:
			this.auditStatusDesc = "审批中";
			break;
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_APPROVEFALL:
			this.auditStatusDesc = "审批失败";
			break;
		case Const.ENTERPRISE_TEMP_AUDIT_STATUS_APPROVEPASS:
			this.auditStatusDesc = "审批通过";
			break;
		default:
			break;
		}
		return auditStatusDesc;
	}

	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}

	public String getStatusDesc() {
		switch(getStatus()){
		case Const.ENTERPRISE_BUSINESS_STATUS_UNTUTORED:
			this.statusDesc="未开通";
			break;
		case Const.ENTERPRISE_BUSINESS_STATUS_NORMAL:
			this.statusDesc="正常";
			break;
		case Const.ENTERPRISE_BUSINESS_STATUS_SUSPEND:
			this.statusDesc="欠费";
			break;
		case Const.ENTERPRISE_BUSINESS_STATUS_STOP:
			this.statusDesc="停机";
			break;
		default:this.statusDesc="未知状态";
	}
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/** operateFlag：操作标识，0-账户初始化 1-新增账户 */
	public Integer getOperateFlag() {
		return operateFlag;
	}

	public void setOperateFlag(Integer operateFlag) {
		this.operateFlag = operateFlag;
	}

	public Set<EnterpriseTrunk> getEnterpriseTrunks() {
		return enterpriseTrunks;
	}

	public void setEnterpriseTrunks(Set<EnterpriseTrunk> enterpriseTrunks) {
		this.enterpriseTrunks = enterpriseTrunks;
	}

	public Set<EnterpriseHotline> getEnterpriseHotlines() {
		return enterpriseHotlines;
	}

	public void setEnterpriseHotlines(Set<EnterpriseHotline> enterpriseHotlines) {
		this.enterpriseHotlines = enterpriseHotlines;
	}

	public Set<FunctionSelect> getFunctionSelects() {
		return functionSelects;
	}

	public void setFunctionSelects(Set<FunctionSelect> functionSelects) {
		this.functionSelects = functionSelects;
	}

	public String getAllNumber() {
		allNumber = "";
		if (getEnterpriseHotlines()!=null && getEnterpriseHotlines().size()>0) {
			for (Iterator<EnterpriseHotline> iterator = getEnterpriseHotlines().iterator(); iterator.hasNext();) {
				EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
				allNumber += entHotline.getHotline()+",";
			}
		}
		if (allNumber.length()>0) {
			allNumber = allNumber.substring(0, allNumber.length()-1);
		}
		return allNumber;
	}

	public void setAllNumber(String allNumber) {
		this.allNumber = allNumber;
	}

	public String getMasterHotline() {
		if (enterpriseHotlines!=null && enterpriseHotlines.size()>0) {
			for (Iterator<EnterpriseHotline> iterator = enterpriseHotlines.iterator(); iterator.hasNext();) {
				EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
				if (entHotline.getIsMaster() == Const.ENTERPRISE_HOTLINE_IS_MASTER_YES) {
					masterHotline = entHotline.getHotline();
					break; // 一个企业只有一个主号码
				}
			}
		}
		return masterHotline;
	}

	public void setMasterHotline(String masterHotline) {
		this.masterHotline = masterHotline;
	}

	public String getAllTrunk() {
		allTrunk = "";
		if (enterpriseTrunks!=null && enterpriseTrunks.size()>0) {
			for (Iterator<EnterpriseTrunk> iterator = enterpriseTrunks.iterator(); iterator.hasNext();) {
				EnterpriseTrunk entTrunk = (EnterpriseTrunk) iterator.next();
				allTrunk += entTrunk.getNumberTrunk()+",";
			}
		}
		if (allTrunk.length()>0) {
			allTrunk = allTrunk.substring(0,allTrunk.length()-1);
		}
		return allTrunk;
	}
	
	public String getAreaCode() {
		String areaCode = "";
		if (enterpriseTrunks!=null && enterpriseTrunks.size()>0) {
			for (Iterator<EnterpriseTrunk> iterator = enterpriseTrunks.iterator(); iterator.hasNext();) {
				EnterpriseTrunk entTrunk = (EnterpriseTrunk) iterator.next();
				areaCode += entTrunk.getAreaCode()+",";
			}
		}
		if (areaCode.length()>0) {
			areaCode = areaCode.substring(0,areaCode.length()-1);
		}
		return areaCode;
	}
	
	

	public void setAllTrunk(String allTrunk) {
		this.allTrunk = allTrunk;
	}

	public String getBillingTrunk() {
		billingTrunk = "";
		if (enterpriseTrunks!=null && enterpriseTrunks.size()>0) {
			for (Iterator<EnterpriseTrunk> iterator = enterpriseTrunks.iterator(); iterator.hasNext();) {
				EnterpriseTrunk entTrunk = (EnterpriseTrunk) iterator.next();
				if (entTrunk.getIsBilling() == Const.ENTERPRISE_TRUNK_IS_BILLING_YES) {
					billingTrunk += entTrunk.getNumberTrunk()+",";
				}
			}
		}
		if (billingTrunk.length()>0) {
			billingTrunk = billingTrunk.substring(0,billingTrunk.length()-1);
		}
		return billingTrunk;
	}

	public void setBillingTrunk(String billingTrunk) {
		this.billingTrunk = billingTrunk;
	}
	
	public Integer getXiangQing() {
		return xiangQing;
	}

	public void setXiangQing(Integer xiangQing) {
		this.xiangQing = xiangQing;
	}
	
	public String getRateIbLeftName() {
		return rateIbLeftName;
	}

	public void setRateIbLeftName(String rateIbLeftName) {
		this.rateIbLeftName = rateIbLeftName;
	}

	public String getRateIbRightName() {
		return rateIbRightName;
	}

	public void setRateIbRightName(String rateIbRightName) {
		this.rateIbRightName = rateIbRightName;
	}

	public String getRateObLeftName() {
		return rateObLeftName;
	}

	public void setRateObLeftName(String rateObLeftName) {
		this.rateObLeftName = rateObLeftName;
	}

	public String getRateObRightName() {
		return rateObRightName;
	}

	public void setRateObRightName(String rateObRightName) {
		this.rateObRightName = rateObRightName;
	}

	public String getIbRouterRightName() {
		return ibRouterRightName;
	}

	public void setIbRouterRightName(String ibRouterRightName) {
		this.ibRouterRightName = ibRouterRightName;
	}

	public String getObPreviewRouterLeftName() {
		return obPreviewRouterLeftName;
	}

	public void setObPreviewRouterLeftName(String obPreviewRouterLeftName) {
		this.obPreviewRouterLeftName = obPreviewRouterLeftName;
	}

	public String getObPredictiveRouterLeftName() {
		return obPredictiveRouterLeftName;
	}

	public void setObPredictiveRouterLeftName(String obPredictiveRouterLeftName) {
		this.obPredictiveRouterLeftName = obPredictiveRouterLeftName;
	}
	public void setIbRight(BigDecimal ibRight) {
		this.ibRight = ibRight;
	}

	public BigDecimal getIbRight() {
		return ibRight;
	}

	public BigDecimal getIbLeft() {
		return ibLeft;
	}
	public void setIbLeft(BigDecimal ibLeft) {
		this.ibLeft = ibLeft;
	}

	public BigDecimal getObLeft() {
		return obLeft;
	}
	public void setObLeft(BigDecimal obLeft) {
		this.obLeft = obLeft;
	}

	public BigDecimal getObRight() {
		return obRight;
	}
	public void setObRight(BigDecimal obRight) {
		this.obRight = obRight;
	}

	public String getRateIbLeftPrice() {
		return rateIbLeftPrice;
	}
	public void setRateIbLeftPrice(String rateIbLeftPrice) {
		this.rateIbLeftPrice = rateIbLeftPrice;
	}

	public String getRateIbRightPrice() {
		return rateIbRightPrice;
	}
	public void setRateIbRightPrice(String rateIbRightPrice) {
		this.rateIbRightPrice = rateIbRightPrice;
	}

	public String getRateObLeftPrice() {
		return rateObLeftPrice;
	}
	public void setRateObLeftPrice(String rateObLeftPrice) {
		this.rateObLeftPrice = rateObLeftPrice;
	}

	public String getRateObRightPrice() {
		return rateObRightPrice;
	}
	
	public void setRateObRightPrice(String rateObRightPrice) {
		this.rateObRightPrice = rateObRightPrice;
	}
	
	public void setUserField4(String userField4) {
		this.userField4 = userField4;
	}

	public String getUserField4() {
		return userField4;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	public void setCtiIp(String ctiIp) {
		this.ctiIp = ctiIp;
	}

	public String getCtiIp() {
		return ctiIp;
	}
	public void setIsUseItf(Integer isUseItf) {
		this.isUseItf = isUseItf;
	}

	public Integer getIsUseItf() {
		return isUseItf;
	}
	public void setAccessMode(Integer accessMode) {
		this.accessMode = accessMode;
	}

	public Integer getAccessMode() {
		return accessMode;
	}
	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}

	public Integer getComboId() {
		return comboId;
	}
	public void setFunctionId(String[] functionId) {
		this.functionId = functionId;
	}

	public String[] getFunctionId() {
		return functionId;
	}
	public void setDeuctFee(BigDecimal deuctFee) {
		this.deuctFee = deuctFee;
	}

	public BigDecimal getDeuctFee() {
		return deuctFee;
	}
	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}

	public BigDecimal getThreshold() {
		return threshold;
	}
	
	public void setLowestCostMode(String lowestCostMode) {
		this.lowestCostMode = lowestCostMode;
	}

	public void setClientFree(Integer clientFree) {
		this.clientFree = clientFree;
	}

	public Integer getClientFree() {
		return clientFree;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getProductType() {
		return productType;
	}
	public void setClientArea(String clientArea) {
		this.clientArea = clientArea;
	}

	public String getClientArea() {
		return clientArea;
	}

	public void setCcicId(Integer ccicId) {
		if(this.getCcic() != null){
			this.ccicId = this.getCcic().getId();
		}else{
			this.ccicId = ccicId;
		}
	}

	public Integer getCcicId() {
		return ccicId;
	}
	
	public Integer getIsIdd() {
		return isIdd;
	}
	
	public void setIsIdd(Integer isIdd) {
		this.isIdd = isIdd;
	}
	
	public Integer getIsFullRecord() {
		return isFullRecord;
	}
	
	public void setIsFullRecord(Integer isFullRecord) {
		this.isFullRecord = isFullRecord;
	}
	
	public Integer getIsAwsRecord() {
		return isAwsRecord;
	}
	
	public void setIsAwsRecord(Integer isAwsRecord) {
		this.isAwsRecord = isAwsRecord;
	}
	
	public String getAwsRecordDirectory() {
		return awsRecordDirectory;
	}
	
	public void setAwsRecordDirectory(String awsRecordDirectory) {
		this.awsRecordDirectory = awsRecordDirectory;
	}
	
	public String getOutCallSwitch() {
		return outCallSwitch;
	}

	public void setOutCallSwitch(String outCallSwitch) {
		this.outCallSwitch = outCallSwitch;
	}

	public String getOutCallTimes() {
		return outCallTimes;
	}

	public void setOutCallTimes(String outCallTimes) {
		this.outCallTimes = outCallTimes;
	}

	public String getOutCallBridgeTime() {
		return outCallBridgeTime;
	}

	public void setOutCallBridgeTime(String outCallBridgeTime) {
		this.outCallBridgeTime = outCallBridgeTime;
	}

	public String getClearDays() {
		return clearDays;
	}

	public void setClearDays(String clearDays) {
		this.clearDays = clearDays;
	}

	public String getOutCallPropertyJson() {
		return outCallPropertyJson;
	}

	public void setOutCallPropertyJson(String outCallPropertyJson) {
		this.outCallPropertyJson = outCallPropertyJson;
	}
}