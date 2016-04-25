package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.tinet.common.inc.Const;

/**
 * Enterprise 企业基本信息,业务信息,现金账号,号码视图实体类
 * <p>
 * 	文件名： Enterprise.java
 * <p>
 * Copyright (c) 2006-2011 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 王云龙
 * @author 阎向清
 * @author 娄雪
 * @since 1.0
 * @version 1.0
 */
public class Enterprise {

	// Fields

	private Integer enterpriseId;
	private String enterpriseName;
	private String entityPwd;
	private Integer entityType;
	private Integer entityParent;
	private Integer entityParentType;
	private String fullName;
	private String areaCode;
	private String trade;
	private String legalPerson;
	private String comment;
	private String payerName;
	private String payerMobile;
	private String payerEmail;
	private String principal;
	private Date createTime;
	private String mobile;
	private String tel;
	private String email;
	private String fax;
	private String address;
	private String post;
	private Integer entityStatus;
	private String businessNo;
	private String question;
	private String answer;
	private String website;
	private Integer businessId;
	private Integer systemId;
	private Integer nodeId;
	private Integer ctiId;
	private String bargainNo;
	private String logo;
	private String owner;
	private String functionId;
	private Integer productId;
	private String masterHotline; // 主热线号码
	private String hotline; // 所有热线号码
	private String directNumber;//直线号码
	private String directNumberRent;//直线号码对应月功能费
	private Integer status;
	private String statusDesc;
	private Integer isTest;
	private Integer inboundCallLimit;
	private Integer isFullRecord;
	private Integer isAwsRecord;
	private String awsRecordDirectory;
	
	private String outCallSwitch;
	private String outCallTimes;
	private String outCallBridgeTime;
	private String clearDays; 
	private String outCallPropertyJson;
	
	/** 呼入客户侧费率套餐id */
	private Integer rateIbLeft;
	private String rateIbLeftName;
	private BigDecimal rateIbLeftValue;
	
	/** 呼入座席侧费率套餐id */
	private Integer rateIbRight;
	private String rateIbRightName;
	private BigDecimal rateIbRightValue;
	
	/** 呼出客户侧费率套餐id */
	private Integer rateObLeft;
	private String rateObLeftName;
	private BigDecimal rateObLeftValue;
	
	/** 呼出座席侧费率套餐id */
	private Integer rateObRight;
	private String rateObRightName;
	private BigDecimal rateObRightValue;
	private Integer isPredictiveOpen;
	//业务变更类型
	private Integer changeType;
	/** 使用接口：0=不使用接口 1=使用接口 */
	private Integer isUseItf;
	/** 客户电话接入方式：1=直线 2=IAD  3=无线话机   4=软电话*/
	private Integer accessMode;
	private Integer isIdd; // 是否开通国际长途
	private Integer productType; // 产品类型
	private String clientArea;	// 	座席所在地
	
	private String mobileVirtual;
	private String mobileVirtualRent;
	
	private Integer predictiveCallLimitRecord;
	private String predictiveCallLimit;
	private Integer predictiveCallFeeRecord;
	private String predictiveCallFee;
	
	private List<EnterpriseHotline> enterpriseHotlineList;
	private List<Rateset> rateSetList;
	
	/** 短信费率 */
	private BigDecimal rateSms;
	
	private String lowestCostModeFlag; // 低消计费模式标识，客户有一个号码是呼入+外呼计低消，则lowestCostModeFlag=1，否则=0。
	private String lowestCostMode; // 所有400号码的低消计费模式
	private BigDecimal totalLowestCost; // 所有400号码的最低消费总额
	private BigDecimal lowestCost; // 主热线号码最低消费
	private Integer period; // 主热线号码计费周期
	private BigDecimal lowestCostNext;
	private BigDecimal rent;
	private BigDecimal comboFee; // 查询当时当月套餐总资费
	private BigDecimal numberRent; // 查询当时当月号码总月功能费
	private Date rentEnd;
	private Date serviceStart;
	private Date serviceEnd;
	private Integer clientTel;
	private Integer clientWeb;
	private Integer clientFree;
	private Integer newClient;
	
	private Integer accountId; // 现金账户id
	private BigDecimal money; // 现金账户余额
	private BigDecimal creditTemp; // 临时信用额度
	private BigDecimal credit; // 固定信用额度
	private Date arrearsTime; // 现金账户欠费时间点
	private Date creditValidTime; // 信用额度指定生效时间点
	
	private String numberTrunk;
	private String trunkAreaCode;
	private String billingTrunk;
	
	//所属二级代理商名称,一级代理商Id,名称
	private String agent2Name;
	private Integer agent1Id;
	private String agent1Name;
	/** 产品名称 */
	private String productName;
	
	/** 所在平台Id */
	private Integer ccicId;

	/** 企业开户证件信息 */
	private List<Accessory> accessoryList;
	
	/** 测试账户使用有效期 **/
	private String expiryDate;
	/** 测试账户使用状态 **/
	private String testStatus;
	public String getTestStatus() {
		return testStatus;
	}


	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

	/** 短信签名 **/
	private String smsCell;
	/** 短信小号 **/
	private String smsSign;
	
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
	
	/** 呼入路由:呼叫座席 */
	private Integer ibRouterRight;
	private String ibRouterRightName;
	
	/** 预览外呼路由:呼叫客户（网上400呼叫客户，主叫外呼呼叫客户） */
	private Integer obPreviewRouterLeft;
	private String obPreviewRouterLeftName;
	
	/** 预览外呼路由:呼叫座席 */
	private Integer obPreviewRouterRight;
	private String obPreviewRouterRightName;
	
	/** 预测外呼路由:呼叫客户 */
	private Integer obPredictiveRouterLeft;
	private String obPredictiveRouterLeftName;
	
	/** 预测外呼路由:呼叫座席 */
	private Integer obPredictiveRouterRight;
	private String obPredictiveRouterRightName;
	
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
	
	/** 修改企业号码信息时使用下面两个set */
	private Set<EnterpriseTrunk> enterpriseTrunks;  // 企业直线号码
	private Set<EnterpriseHotline> enterpriseHotlines;  // 企业热线号码
	private Set<EnterpriseSetting> enterpriseSettings;
	//企业欠款金额
	private BigDecimal moneyArrears;
	private Integer isSmsSend;//是否开启短信发送功能
	private Integer isFaxReceive;//是否开启传真接收功能
	
	private String hotlineTrunk;
	
	private Integer comboId;
	private Integer newComboId;
	// Constructors
	/** default constructor */
	public Enterprise() {
		this.isIdd = Const.ENTERPRISE_TEMP_IS_IDD_OFF;
	}
	
	
	public Enterprise(Integer enterpriseId, String enterpriseName, Integer status, Integer clientWeb,
			BigDecimal rent, Date rentEnd) {
		this.enterpriseId  = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.status = status;
		this.clientWeb = clientWeb;
		this.rent = rent;
		this.rentEnd = rentEnd;
	}
	
	public Enterprise(Integer enterpriseId, String enterpriseName, String entityPwd, Integer entityParent, String email, String mobile){
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.entityPwd = entityPwd;
		this.entityParent = entityParent;
		this.email = email;
		this.mobile = mobile;
	}
	
	public Enterprise(Integer enterpriseId, String enterpriseName,Integer entityParent, Integer entityParentType, String fullName, Date createTime,  
			Integer entityStatus, Integer productId, String masterHotline,Integer status, BigDecimal lowestCost, Integer period, 
			Integer isTest,String agent2Name, Integer agent1Id, String agent1Name, String productName,Integer ccicId) {
		super();
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.entityParent = entityParent;
		this.entityParentType = entityParentType;
		this.fullName = fullName;
		this.createTime = createTime;
		this.entityStatus = entityStatus;
		this.productId = productId;
		this.masterHotline = masterHotline;
		this.status = status;
		this.lowestCost = lowestCost;
		this.period = period;
		this.isTest = isTest;
		this.agent2Name = agent2Name;
		this.agent1Id = agent1Id;
		this.agent1Name = agent1Name;
		this.productName = productName;
		this.ccicId = ccicId;
	}
	public Enterprise(Integer enterpriseId, String enterpriseName,Integer entityParent, Date createTime, Integer entityStatus,
			Integer productId, String masterHotline, Integer status, BigDecimal lowestCost, Integer period, 
			String agent2Name,Integer agent1Id,String agent1Name,String productName,Integer ccicId) {
		super();
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.entityParent = entityParent;
		this.createTime = createTime;
		this.entityStatus = entityStatus;
		this.productId = productId;
		this.masterHotline = masterHotline;
		this.status = status;
		this.lowestCost = lowestCost;
		this.period=period;
		this.agent2Name=agent2Name;
		this.agent1Id=agent1Id;
		this.agent1Name=agent1Name;
		this.productName=productName;
		this.ccicId=ccicId;
	}

	public Enterprise(Integer enterpriseId, String enterpriseName,String fullName,Integer entityParent, Date createTime, Integer entityStatus,
			Integer productId, String masterHotline, Integer status, BigDecimal lowestCost, Integer period, 
			String agent2Name,Integer agent1Id,String agent1Name,String productName,Integer ccicId) {
		super();
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.fullName = fullName;
		this.entityParent = entityParent;
		this.createTime = createTime;
		this.entityStatus = entityStatus;
		this.productId = productId;
		this.masterHotline = masterHotline;
		this.status = status;
		this.lowestCost = lowestCost;
		this.period=period;
		this.agent2Name=agent2Name;
		this.agent1Id=agent1Id;
		this.agent1Name=agent1Name;
		this.productName=productName;
		this.ccicId=ccicId;
	}
	
	// Property accessors

	public Integer getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getEntityPwd() {
		return this.entityPwd;
	}

	public void setEntityPwd(String entityPwd) {
		this.entityPwd = entityPwd;
	}

	public Integer getEntityType() {
		return this.entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public Integer getEntityParent() {
		return this.entityParent;
	}

	public void setEntityParent(Integer entityParent) {
		this.entityParent = entityParent;
	}

	public Integer getEntityParentType() {
		return entityParentType;
	}

	public void setEntityParentType(Integer entityParentType) {
		this.entityParentType = entityParentType;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getEntityStatus() {
		return this.entityStatus;
	}
	public String getEntityStatusDesc() {
		if (this.getEntityStatus()!=null) {
			switch (this.getEntityStatus()) {
				case Const.ENTITY_STATUS_OK:
					this.statusDesc = "正常";
					break;
				case Const.ENTITY_STATUS_LOCK:
					this.statusDesc = "锁定";
					break;
				case Const.ENTITY_STATUS_CLOSE:
					this.statusDesc = "注销";
					break;
				case Const.ENTITY_STATUS_FORBIDDEN:
					this.statusDesc = "暂停";
					break;
				default:
					break;
			}
		}
		return statusDesc;
	}

	public void setEntityStatus(Integer entityStatus) {
		this.entityStatus = entityStatus;
	}

	public String getBusinessNo() {
		return this.businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
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

	public Integer getCtiId() {
		return this.ctiId;
	}

	public void setCtiId(Integer ctiId) {
		this.ctiId = ctiId;
	}

	public String getBargainNo() {
		return this.bargainNo;
	}

	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getMasterHotline() {
		return this.masterHotline;
	}

	public void setMasterHotline(String masterHotline) {
		this.masterHotline = masterHotline;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusDesc() {
		statusDesc = "";
		if (this.getStatus()!=null) {
			switch (this.getStatus()) {
				case Const.BUSINESS_STATUS_OK:
					this.statusDesc = "正常";
					break;
				case Const.BUSINESS_STATUS_NO_SERVICE:
					this.statusDesc = "未开通";
					break;
				case Const.BUSINESS_STATUS_PAUSED:
					this.statusDesc = "欠费";
					break;
				case Const.BUSINESS_STATUS_STOP:
					this.statusDesc = "停机";
					break;
				case Const.BUSINESS_STATUS_CLOSE:
					this.statusDesc = "注销";
					break;
				default:
					break;
			}
		}
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Integer getIsTest() {
		return this.isTest;
	}

	public void setIsTest(Integer isTest) {
		this.isTest = isTest;
	}

	public Integer getInboundCallLimit() {
		return inboundCallLimit;
	}

	public void setInboundCallLimit(Integer inboundCallLimit) {
		this.inboundCallLimit = inboundCallLimit;
	}

	public Integer getRateIbLeft() {
		return rateIbLeft;
	}

	public void setRateIbLeft(Integer rateIbLeft) {
		this.rateIbLeft = rateIbLeft;
	}

	public Integer getRateIbRight() {
		return rateIbRight;
	}

	public void setRateIbRight(Integer rateIbRight) {
		this.rateIbRight = rateIbRight;
	}

	public Integer getRateObLeft() {
		return rateObLeft;
	}

	public void setRateObLeft(Integer rateObLeft) {
		this.rateObLeft = rateObLeft;
	}

	public Integer getRateObRight() {
		return rateObRight;
	}

	public void setRateObRight(Integer rateObRight) {
		this.rateObRight = rateObRight;
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

	public BigDecimal getRateSms() {
		return rateSms;
	}

	public void setRateSms(BigDecimal rateSms) {
		this.rateSms = rateSms;
	}

	public BigDecimal getTotalLowestCost() {
		return totalLowestCost;
	}

	public void setTotalLowestCost(BigDecimal totalLowestCost) {
		this.totalLowestCost = totalLowestCost;
	}

	public BigDecimal getLowestCost() {
		return this.lowestCost;
	}

	public void setLowestCost(BigDecimal lowestCost) {
		this.lowestCost = lowestCost;
	}
	
	public Integer getPeriod() {
		return this.period;
	}
	
	public void setPeriod(Integer period) {
		this.period = period;
	}

	public BigDecimal getLowestCostNext() {
		return this.lowestCostNext;
	}

	public void setLowestCostNext(BigDecimal lowestCostNext) {
		this.lowestCostNext = lowestCostNext;
	}

	public BigDecimal getRent() {
		return this.rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public Date getRentEnd() {
		return rentEnd;
	}

	public void setRentEnd(Date rentEnd) {
		this.rentEnd = rentEnd;
	}

	public Date getServiceStart() {
		return this.serviceStart;
	}

	public void setServiceStart(Date serviceStart) {
		this.serviceStart = serviceStart;
	}

	public Date getServiceEnd() {
		return this.serviceEnd;
	}

	public void setServiceEnd(Date serviceEnd) {
		this.serviceEnd = serviceEnd;
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
	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getArrearsTime() {
		return arrearsTime;
	}
	
	public void setArrearsTime(Date arrearsTime) {
		this.arrearsTime = arrearsTime;
	}
	
	public Date getCreditValidTime() {
		return creditValidTime;
	}

	public void setCreditValidTime(Date creditValidTime) {
		this.creditValidTime = creditValidTime;
	}

	public BigDecimal getMoneyArrears() {
		return moneyArrears;
	}

	public void setMoneyArrears(BigDecimal moneyArrears) {
		this.moneyArrears = moneyArrears;
	}

	public String getNumberTrunk() {
		return this.numberTrunk;
	}

	public void setNumberTrunk(String numberTrunk) {
		this.numberTrunk = numberTrunk;
	}

	public String getBillingTrunk() {
		return billingTrunk;
	}

	public void setBillingTrunk(String billingTrunk) {
		this.billingTrunk = billingTrunk;
	}

	public Integer getCcicId() {
		return ccicId;
	}
	
	public void setCcicId(Integer ccicId) {
		this.ccicId = ccicId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAgent2Name() {
		return agent2Name;
	}

	public void setAgent2Name(String agent2Name) {
		this.agent2Name = agent2Name;
	}

	public Integer getAgent1Id() {
		return agent1Id;
	}
	
	public void setAgent1Id(Integer agent1Id) {
		this.agent1Id = agent1Id;
	}
	
	public String getAgent1Name() {
		return agent1Name;
	}
	
	public void setAgent1Name(String agent1Name) {
		this.agent1Name = agent1Name;
	}
	
	public String getTrade() {
		return trade;
	}
	
	public void setTrade(String trade) {
		this.trade = trade;
	}
	
	public String getLegalPerson() {
		return legalPerson;
	}
	
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getPrincipal() {
		return principal;
	}
	
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getFunctionId() {
		return functionId;
	}
	
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	
	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public String getSmsCell() {
		return smsCell;
	}

	public void setSmsCell(String smsCell) {
		this.smsCell = smsCell;
	}

	public String getSmsSign() {
		return smsSign;
	}

	public void setSmsSign(String smsSign) {
		this.smsSign = smsSign;
	}

	public String getClientCss() {
		return clientCss;
	}

	public void setClientCss(String clientCss) {
		this.clientCss = clientCss;
	}

	public Integer getCrmType() {
		return crmType;
	}

	public void setCrmType(Integer crmType) {
		this.crmType = crmType;
	}

	public String getCrmUrl() {
		return crmUrl;
	}

	public void setCrmUrl(String crmUrl) {
		this.crmUrl = crmUrl;
	}

	public Integer getPopscreenType() {
		return popscreenType;
	}

	public void setPopscreenType(Integer popscreenType) {
		this.popscreenType = popscreenType;
	}

	public String getPopscreenUrl() {
		return popscreenUrl;
	}

	public void setPopscreenUrl(String popscreenUrl) {
		this.popscreenUrl = popscreenUrl;
	}

	public String getApiSupport() {
		return apiSupport;
	}

	public void setApiSupport(String apiSupport) {
		this.apiSupport = apiSupport;
	}

	public Integer getIbRouterRight() {
		return ibRouterRight;
	}

	public void setIbRouterRight(Integer ibRouterRight) {
		this.ibRouterRight = ibRouterRight;
	}

	public String getIbRouterRightName() {
		return ibRouterRightName;
	}

	public void setIbRouterRightName(String ibRouterRightName) {
		this.ibRouterRightName = ibRouterRightName;
	}

	public Integer getObPreviewRouterLeft() {
		return obPreviewRouterLeft;
	}

	public void setObPreviewRouterLeft(Integer obPreviewRouterLeft) {
		this.obPreviewRouterLeft = obPreviewRouterLeft;
	}

	public String getObPreviewRouterLeftName() {
		return obPreviewRouterLeftName;
	}

	public void setObPreviewRouterLeftName(String obPreviewRouterLeftName) {
		this.obPreviewRouterLeftName = obPreviewRouterLeftName;
	}

	public Integer getObPreviewRouterRight() {
		return obPreviewRouterRight;
	}

	public void setObPreviewRouterRight(Integer obPreviewRouterRight) {
		this.obPreviewRouterRight = obPreviewRouterRight;
	}

	public String getObPreviewRouterRightName() {
		return obPreviewRouterRightName;
	}

	public void setObPreviewRouterRightName(String obPreviewRouterRightName) {
		this.obPreviewRouterRightName = obPreviewRouterRightName;
	}

	public Integer getObPredictiveRouterLeft() {
		return obPredictiveRouterLeft;
	}

	public void setObPredictiveRouterLeft(Integer obPredictiveRouterLeft) {
		this.obPredictiveRouterLeft = obPredictiveRouterLeft;
	}

	public String getObPredictiveRouterLeftName() {
		return obPredictiveRouterLeftName;
	}

	public void setObPredictiveRouterLeftName(String obPredictiveRouterLeftName) {
		this.obPredictiveRouterLeftName = obPredictiveRouterLeftName;
	}

	public Integer getObPredictiveRouterRight() {
		return obPredictiveRouterRight;
	}

	public void setObPredictiveRouterRight(Integer obPredictiveRouterRight) {
		this.obPredictiveRouterRight = obPredictiveRouterRight;
	}

	public Integer getIbClidRightType() {
		return ibClidRightType;
	}

	public void setIbClidRightType(Integer ibClidRightType) {
		this.ibClidRightType = ibClidRightType;
	}

	public String getIbClidRightNumber() {
		return ibClidRightNumber;
	}

	public void setIbClidRightNumber(String ibClidRightNumber) {
		this.ibClidRightNumber = ibClidRightNumber;
	}

	public Integer getObPreviewClidLeftType() {
		return obPreviewClidLeftType;
	}

	public void setObPreviewClidLeftType(Integer obPreviewClidLeftType) {
		this.obPreviewClidLeftType = obPreviewClidLeftType;
	}

	public String getObPreviewClidLeftNumber() {
		return obPreviewClidLeftNumber;
	}

	public void setObPreviewClidLeftNumber(String obPreviewClidLeftNumber) {
		this.obPreviewClidLeftNumber = obPreviewClidLeftNumber;
	}

	public Integer getObPreviewClidRightType() {
		return obPreviewClidRightType;
	}

	public void setObPreviewClidRightType(Integer obPreviewClidRightType) {
		this.obPreviewClidRightType = obPreviewClidRightType;
	}

	public String getObPreviewClidRightNumber() {
		return obPreviewClidRightNumber;
	}

	public void setObPreviewClidRightNumber(String obPreviewClidRightNumber) {
		this.obPreviewClidRightNumber = obPreviewClidRightNumber;
	}

	public Integer getObPredictiveClidLeftType() {
		return obPredictiveClidLeftType;
	}

	public void setObPredictiveClidLeftType(Integer obPredictiveClidLeftType) {
		this.obPredictiveClidLeftType = obPredictiveClidLeftType;
	}

	public String getObPredictiveClidLeftNumber() {
		return obPredictiveClidLeftNumber;
	}

	public void setObPredictiveClidLeftNumber(String obPredictiveClidLeftNumber) {
		this.obPredictiveClidLeftNumber = obPredictiveClidLeftNumber;
	}

	public Integer getObPredictiveClidRightType() {
		return obPredictiveClidRightType;
	}

	public String getObPredictiveRouterRightName() {
		return obPredictiveRouterRightName;
	}

	public void setObPredictiveRouterRightName(String obPredictiveRouterRightName) {
		this.obPredictiveRouterRightName = obPredictiveRouterRightName;
	}

	public void setObPredictiveClidRightType(Integer obPredictiveClidRightType) {
		this.obPredictiveClidRightType = obPredictiveClidRightType;
	}

	public String getObPredictiveClidRightNumber() {
		return obPredictiveClidRightNumber;
	}

	public void setObPredictiveClidRightNumber(String obPredictiveClidRightNumber) {
		this.obPredictiveClidRightNumber = obPredictiveClidRightNumber;
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

	public Set<EnterpriseSetting> getEnterpriseSettings() {
		return enterpriseSettings;
	}


	public void setEnterpriseSettings(Set<EnterpriseSetting> enterpriseSettings) {
		this.enterpriseSettings = enterpriseSettings;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Enterprise))
			return false;
		Enterprise castOther = (Enterprise) other;

		return ((this.getEnterpriseId() == castOther.getEnterpriseId()) || (this
				.getEnterpriseId() != null
				&& castOther.getEnterpriseId() != null && this
				.getEnterpriseId().equals(castOther.getEnterpriseId())))
				&& ((this.getEnterpriseName() == castOther.getEnterpriseName()) || (this
						.getEnterpriseName() != null
						&& castOther.getEnterpriseName() != null && this
						.getEnterpriseName().equals(
								castOther.getEnterpriseName())))
				&& ((this.getEntityPwd() == castOther.getEntityPwd()) || (this
						.getEntityPwd() != null
						&& castOther.getEntityPwd() != null && this
						.getEntityPwd().equals(castOther.getEntityPwd())))
				&& ((this.getEntityType() == castOther.getEntityType()) || (this
						.getEntityType() != null
						&& castOther.getEntityType() != null && this
						.getEntityType().equals(castOther.getEntityType())))
				&& ((this.getEntityParent() == castOther.getEntityParent()) || (this
						.getEntityParent() != null
						&& castOther.getEntityParent() != null && this
						.getEntityParent().equals(castOther.getEntityParent())))
				&& ((this.getFullName() == castOther.getFullName()) || (this
						.getFullName() != null
						&& castOther.getFullName() != null && this
						.getFullName().equals(castOther.getFullName())))
				&& ((this.getAreaCode() == castOther.getAreaCode()) || (this
						.getAreaCode() != null
						&& castOther.getAreaCode() != null && this
						.getAreaCode().equals(castOther.getAreaCode())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getMobile() == castOther.getMobile()) || (this
						.getMobile() != null
						&& castOther.getMobile() != null && this.getMobile()
						.equals(castOther.getMobile())))
				&& ((this.getTel() == castOther.getTel()) || (this.getTel() != null
						&& castOther.getTel() != null && this.getTel().equals(
						castOther.getTel())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null
						&& castOther.getEmail() != null && this.getEmail()
						.equals(castOther.getEmail())))
				&& ((this.getFax() == castOther.getFax()) || (this.getFax() != null
						&& castOther.getFax() != null && this.getFax().equals(
						castOther.getFax())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null
						&& castOther.getAddress() != null && this.getAddress()
						.equals(castOther.getAddress())))
				&& ((this.getPost() == castOther.getPost()) || (this.getPost() != null
						&& castOther.getPost() != null && this.getPost()
						.equals(castOther.getPost())))
				&& ((this.getEntityStatus() == castOther.getEntityStatus()) || (this
						.getEntityStatus() != null
						&& castOther.getEntityStatus() != null && this
						.getEntityStatus().equals(castOther.getEntityStatus())))
				&& ((this.getBusinessNo() == castOther.getBusinessNo()) || (this
						.getBusinessNo() != null
						&& castOther.getBusinessNo() != null && this
						.getBusinessNo().equals(castOther.getBusinessNo())))
				&& ((this.getQuestion() == castOther.getQuestion()) || (this
						.getQuestion() != null
						&& castOther.getQuestion() != null && this
						.getQuestion().equals(castOther.getQuestion())))
				&& ((this.getAnswer() == castOther.getAnswer()) || (this
						.getAnswer() != null
						&& castOther.getAnswer() != null && this.getAnswer()
						.equals(castOther.getAnswer())))
				&& ((this.getWebsite() == castOther.getWebsite()) || (this
						.getWebsite() != null
						&& castOther.getWebsite() != null && this.getWebsite()
						.equals(castOther.getWebsite())))
				&& ((this.getBusinessId() == castOther.getBusinessId()) || (this
						.getBusinessId() != null
						&& castOther.getBusinessId() != null && this
						.getBusinessId().equals(castOther.getBusinessId())))
				&& ((this.getSystemId() == castOther.getSystemId()) || (this
						.getSystemId() != null
						&& castOther.getSystemId() != null && this
						.getSystemId().equals(castOther.getSystemId())))
				&& ((this.getNodeId() == castOther.getNodeId()) || (this
						.getNodeId() != null
						&& castOther.getNodeId() != null && this.getNodeId()
						.equals(castOther.getNodeId())))
				&& ((this.getCtiId() == castOther.getCtiId()) || (this
						.getCtiId() != null
						&& castOther.getCtiId() != null && this.getCtiId()
						.equals(castOther.getCtiId())))
				&& ((this.getBargainNo() == castOther.getBargainNo()) || (this
						.getBargainNo() != null
						&& castOther.getBargainNo() != null && this
						.getBargainNo().equals(castOther.getBargainNo())))
				&& ((this.getLogo() == castOther.getLogo()) || (this.getLogo() != null
						&& castOther.getLogo() != null && this.getLogo()
						.equals(castOther.getLogo())))
				&& ((this.getProductId() == castOther.getProductId()) || (this
						.getProductId() != null
						&& castOther.getProductId() != null && this
						.getProductId().equals(castOther.getProductId())))
				&& ((this.getMasterHotline() == castOther.getMasterHotline()) || (this
						.getMasterHotline() != null
						&& castOther.getMasterHotline() != null && this
						.getMasterHotline().equals(castOther.getMasterHotline())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null
						&& castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus())))
				&& ((this.getIsTest() == castOther.getIsTest()) || (this
						.getIsTest() != null
						&& castOther.getIsTest() != null && this.getIsTest()
						.equals(castOther.getIsTest())))
				&& ((this.getLowestCost() == castOther.getLowestCost()) || (this
						.getLowestCost() != null
						&& castOther.getLowestCost() != null && this
						.getLowestCost().equals(castOther.getLowestCost())))
				&& ((this.getPeriod() == castOther.getPeriod()) || (this
						.getPeriod() != null
						&& castOther.getPeriod() != null && this.getPeriod()
						.equals(castOther.getPeriod())))
				&& ((this.getRent() == castOther.getRent()) || (this.getRent() != null
						&& castOther.getRent() != null && this.getRent()
						.equals(castOther.getRent())))
				&& ((this.getServiceStart() == castOther.getServiceStart()) || (this
						.getServiceStart() != null
						&& castOther.getServiceStart() != null && this
						.getServiceStart().equals(castOther.getServiceStart())))
				&& ((this.getServiceEnd() == castOther.getServiceEnd()) || (this
						.getServiceEnd() != null
						&& castOther.getServiceEnd() != null && this
						.getServiceEnd().equals(castOther.getServiceEnd())))
				&& ((this.getClientTel() == castOther.getClientTel()) || (this
						.getClientTel() != null
						&& castOther.getClientTel() != null && this
						.getClientTel().equals(castOther.getClientTel())))
				&& ((this.getClientWeb() == castOther.getClientWeb()) || (this
						.getClientWeb() != null
						&& castOther.getClientWeb() != null && this
						.getClientWeb().equals(castOther.getClientWeb())))
				&& ((this.getNumberTrunk() == castOther.getNumberTrunk()) || (this
						.getNumberTrunk() != null
						&& castOther.getNumberTrunk() != null && this
						.getNumberTrunk().equals(castOther.getNumberTrunk())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseId() == null ? 0 : this.getEnterpriseId()
						.hashCode());
		result = 37
				* result
				+ (getEnterpriseName() == null ? 0 : this.getEnterpriseName()
						.hashCode());
		result = 37 * result
				+ (getEntityPwd() == null ? 0 : this.getEntityPwd().hashCode());
		result = 37
				* result
				+ (getEntityType() == null ? 0 : this.getEntityType()
						.hashCode());
		result = 37
				* result
				+ (getEntityParent() == null ? 0 : this.getEntityParent()
						.hashCode());
		result = 37 * result
				+ (getFullName() == null ? 0 : this.getFullName().hashCode());
		result = 37 * result
				+ (getAreaCode() == null ? 0 : this.getAreaCode().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result
				+ (getTel() == null ? 0 : this.getTel().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result
				+ (getFax() == null ? 0 : this.getFax().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getPost() == null ? 0 : this.getPost().hashCode());
		result = 37
				* result
				+ (getEntityStatus() == null ? 0 : this.getEntityStatus()
						.hashCode());
		result = 37
				* result
				+ (getBusinessNo() == null ? 0 : this.getBusinessNo()
						.hashCode());
		result = 37 * result
				+ (getQuestion() == null ? 0 : this.getQuestion().hashCode());
		result = 37 * result
				+ (getAnswer() == null ? 0 : this.getAnswer().hashCode());
		result = 37 * result
				+ (getWebsite() == null ? 0 : this.getWebsite().hashCode());
		result = 37
				* result
				+ (getBusinessId() == null ? 0 : this.getBusinessId()
						.hashCode());
		result = 37 * result
				+ (getSystemId() == null ? 0 : this.getSystemId().hashCode());
		result = 37 * result
				+ (getNodeId() == null ? 0 : this.getNodeId().hashCode());
		result = 37 * result
				+ (getCtiId() == null ? 0 : this.getCtiId().hashCode());
		result = 37 * result
				+ (getBargainNo() == null ? 0 : this.getBargainNo().hashCode());
		result = 37 * result
				+ (getLogo() == null ? 0 : this.getLogo().hashCode());
		result = 37 * result
				+ (getProductId() == null ? 0 : this.getProductId().hashCode());
		result = 37 * result
				+ (getMasterHotline() == null ? 0 : this.getMasterHotline().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getIsTest() == null ? 0 : this.getIsTest().hashCode());
		result = 37
				* result
				+ (getLowestCost() == null ? 0 : this.getLowestCost()
						.hashCode());
		result = 37 * result
				+ (getPeriod() == null ? 0 : this.getPeriod().hashCode());
		result = 37 * result
				+ (getRent() == null ? 0 : this.getRent().hashCode());
		result = 37
				* result
				+ (getServiceStart() == null ? 0 : this.getServiceStart()
						.hashCode());
		result = 37
				* result
				+ (getServiceEnd() == null ? 0 : this.getServiceEnd()
						.hashCode());
		result = 37 * result
				+ (getClientTel() == null ? 0 : this.getClientTel().hashCode());
		result = 37 * result
				+ (getClientWeb() == null ? 0 : this.getClientWeb().hashCode());
		result = 37
				* result
				+ (getNumberTrunk() == null ? 0 : this.getNumberTrunk()
						.hashCode());
		return result;
	}


	public void setIsSmsSend(Integer isSmsSend) {
		this.isSmsSend = isSmsSend;
	}


	public Integer getIsSmsSend() {
		return isSmsSend;
	}


	public void setIsFaxReceive(Integer isFaxReceive) {
		this.isFaxReceive = isFaxReceive;
	}


	public Integer getIsFaxReceive() {
		return isFaxReceive;
	}


	public void setIsPredictiveOpen(Integer isPredictiveOpen) {
		this.isPredictiveOpen = isPredictiveOpen;
	}


	public Integer getIsPredictiveOpen() {
		return isPredictiveOpen;
	}

	public void setNewClient(Integer newClient) {
		this.newClient = newClient;
	}

	public Integer getNewClient() {
		return newClient;
	}


	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}


	public Integer getChangeType() {
		return changeType;
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


	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}


	public String getPayerName() {
		return payerName;
	}


	public void setPayerMobile(String payerMobile) {
		this.payerMobile = payerMobile;
	}


	public String getPayerMobile() {
		return payerMobile;
	}


	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}


	public String getPayerEmail() {
		return payerEmail;
	}


	public void setEnterpriseHotlineList(List<EnterpriseHotline> enterpriseHotlineList) {
		this.enterpriseHotlineList = enterpriseHotlineList;
	}

	public List<EnterpriseHotline> getEnterpriseHotlineList() {
		return enterpriseHotlineList;
	}


	public void setRateSetList(List<Rateset> rateSetList) {
		this.rateSetList = rateSetList;
	}


	public List<Rateset> getRateSetList() {
		return rateSetList;
	}


	public void setLowestCostMode(String lowestCostMode) {
		this.lowestCostMode = lowestCostMode;
	}


	public String getLowestCostMode() {
		return lowestCostMode;
	}

	public void setMobileVirtual(String mobileVirtual) {
		this.mobileVirtual = mobileVirtual;
	}

	public String getMobileVirtual() {
		return mobileVirtual;
	}

	public void setClientFree(Integer clientFree) {
		this.clientFree = clientFree;
	}


	public Integer getClientFree() {
		return clientFree;
	}


	public void setDirectNumber(String directNumber) {
		this.directNumber = directNumber;
	}

	public String getDirectNumber() {
		return directNumber;
	}


	public void setDirectNumberRent(String directNumberRent) {
		this.directNumberRent = directNumberRent;
	}


	public String getDirectNumberRent() {
		return directNumberRent;
	}


	public void setMobileVirtualRent(String mobileVirtualRent) {
		this.mobileVirtualRent = mobileVirtualRent;
	}


	public String getMobileVirtualRent() {
		return mobileVirtualRent;
	}

	public Integer getIsIdd() {
		return isIdd;
	}

	public void setIsIdd(Integer isIdd) {
		this.isIdd = isIdd;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getClientArea() {
		return clientArea;
	}

	public void setClientArea(String clientArea) {
		this.clientArea = clientArea;
	}

	public BigDecimal getComboFee() {
		return comboFee;
	}

	public void setComboFee(BigDecimal comboFee) {
		this.comboFee = comboFee;
	}

	public BigDecimal getNumberRent() {
		return numberRent;
	}

	public void setNumberRent(BigDecimal numberRent) {
		this.numberRent = numberRent;
	}

	public BigDecimal getCreditTemp() {
		return creditTemp;
	}

	public void setCreditTemp(BigDecimal creditTemp) {
		this.creditTemp = creditTemp;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public void setHotlineTrunk(String hotlineTrunk) {
		this.hotlineTrunk = hotlineTrunk;
	}

	public String getHotlineTrunk() {
		return this.hotlineTrunk;
	}


	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}


	public Integer getComboId() {
		return comboId;
	}


	public void setNewComboId(Integer newComboId) {
		this.newComboId = newComboId;
	}


	public Integer getNewComboId() {
		return newComboId;
	}


	public void setPredictiveCallLimit(String predictiveCallLimit) {
		this.predictiveCallLimit = predictiveCallLimit;
	}


	public String getPredictiveCallLimit() {
		return predictiveCallLimit;
	}


	public void setPredictiveCallFee(String predictiveCallFee) {
		this.predictiveCallFee = predictiveCallFee;
	}


	public String getPredictiveCallFee() {
		return predictiveCallFee;
	}


	public void setPredictiveCallLimitRecord(Integer predictiveCallLimitRecord) {
		this.predictiveCallLimitRecord = predictiveCallLimitRecord;
	}


	public Integer getPredictiveCallLimitRecord() {
		return predictiveCallLimitRecord;
	}


	public void setPredictiveCallFeeRecord(Integer predictiveCallFeeRecord) {
		this.predictiveCallFeeRecord = predictiveCallFeeRecord;
	}


	public Integer getPredictiveCallFeeRecord() {
		return predictiveCallFeeRecord;
	}


	public void setTrunkAreaCode(String trunkAreaCode) {
		this.trunkAreaCode = trunkAreaCode;
	}


	public String getTrunkAreaCode() {
		return trunkAreaCode;
	}

	public BigDecimal getRateIbLeftValue() {
		return rateIbLeftValue;
	}


	public void setRateIbLeftValue(BigDecimal rateIbLeftValue) {
		this.rateIbLeftValue = rateIbLeftValue;
	}


	public BigDecimal getRateIbRightValue() {
		return rateIbRightValue;
	}


	public void setRateIbRightValue(BigDecimal rateIbRightValue) {
		this.rateIbRightValue = rateIbRightValue;
	}


	public BigDecimal getRateObLeftValue() {
		return rateObLeftValue;
	}


	public void setRateObLeftValue(BigDecimal rateObLeftValue) {
		this.rateObLeftValue = rateObLeftValue;
	}


	public BigDecimal getRateObRightValue() {
		return rateObRightValue;
	}


	public void setRateObRightValue(BigDecimal rateObRightValue) {
		this.rateObRightValue = rateObRightValue;
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

	public String getLowestCostModeFlag() {
		return lowestCostModeFlag;
	}

	public void setLowestCostModeFlag(String lowestCostModeFlag) {
		this.lowestCostModeFlag = lowestCostModeFlag;
	}

}