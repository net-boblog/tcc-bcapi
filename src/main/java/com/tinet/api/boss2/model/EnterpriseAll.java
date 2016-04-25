package com.tinet.api.boss2.model;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;

import com.tinet.common.util.DateUtil;

/**
 * 分平台客户信息存储类
 * <p>
 * 	文件名： EnterpriseAll.java
 * <p>
 * @author chenhui
 * @since
 * @version 
 */
public class EnterpriseAll extends BaseStandardEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4414345400342261793L;

	private BigDecimal moneyArrears;//欠款金额
	private String directManage;//直销经理
	private String directSection;//直销部门
	private Integer entityParent;
	private Integer entityParentType;
	private Integer ccicId;
	private String enterpriseName;
	private String fullName;
	private String masterHotline;
	private Integer status;// 业务状态 0:未开通 1:正常 2:欠费 3:停机 4:注销
	private Integer entityStatus; // 实体状态：1正常 2禁止 3锁定，仅用于登录控制
	private Integer productId;
	private Integer period;//号码最低消费周期 1:月最低消费 2:年最低消费
	private BigDecimal lowestCost;//主热线号码最低消费
	private BigDecimal totalLowestCost;//企业所有热线号码最低消费总和
	private Integer isTest;//是否测试用户 1是，2否
	private Integer testStatus;//测试账户使用状态
	private String applyDate;
	private String expiryDate;
	private BigDecimal moneyIb;//话费账户余额 
	private BigDecimal moneyOb;//外呼话费账户余额
	private BigDecimal moneyFunc;//功能费账户余额
	private BigDecimal moneySms;//短信账户余额
	private Date serviceStart;// 业务开通时间
	private Date serviceEnd;//企业合同结束时间
	private Date createTime;
	private String ccicName;
	private Integer clientWeb;//电脑座席数
	private BigDecimal rent;// 电脑座席月租
	
	private Integer isUseItf;//使用接口
	private Integer accessMode;//接入方式
	
	private Integer clientTel; // 电话座席数
	private Integer clientFree; // 免费电脑座席数
	private Integer accountId; // 现金账户id
	private BigDecimal money; // 现金账户余额 
	private BigDecimal creditTemp; // 临时信用额度
	private BigDecimal credit; // 固定信用额度 
	private Integer comboId; // 套餐ID
	private long arrearsTime; // 现金账户欠费时间点
	private String arrearsTimeDate; // 现金账户欠费时间点
	private long creditValidTime; // 信用额度指定生效时间点
	private String creditValidDate; // 信用额度指定生效时间点
	private String creditTypeDesc; // 信用类型描述
	private String creditValue; // 信用额度
	private Integer creditDays; // 临时信用有效期
	private Integer creditTempDays; // 固定信用有效期
	private Integer productType;//产品类型
	private String clientArea;//座席所在地
	private String trade; // 客户所属行业
	private String legalPerson; // 法人代表
	private String address; // 联系地址
	private String principal;	// 负责人姓名
	private String mobile;		// 负责人手机
	private String email;		// 负责人邮箱
	private String payerName; // 付款人姓名
	private String payerMobile; // 付款人手机
	private String payerEmail; // 付款人邮箱
	private Enterprise enterprise; // 将EnterpriseAll对象转化为Enterprise
	private Integer inboundCallLimit;
	private Integer ctiId;
	
	public EnterpriseAll() {
		super();
		this.clientTel = 0;
		this.clientWeb = 0;
		this.clientFree = 0;
		this.accountId = 0;
		this.comboId = 0;
		this.money = new BigDecimal(0);
		this.creditTemp = new BigDecimal(0);
		this.credit = new BigDecimal(0);
		enterprise = new Enterprise();
	}
	//构造方法。所有映射库字段
	public EnterpriseAll(Integer enterpriseId,Integer entityParent,Integer entityParentType,Integer ccicId,
			String enterpriseName,String fullName, String masterHotline,Integer status,Integer entityStatus,
			Integer productId,Integer period,BigDecimal lowestCost,BigDecimal totalLowestCost,Integer isTest,
			BigDecimal moneyIb, BigDecimal moneyOb, BigDecimal moneyFunc, BigDecimal moneySms,Date serviceStart,
			Date serviceEnd,Date createTime,Integer clientWeb, BigDecimal rent){
		super();
		this.setEnterpriseId(enterpriseId);
		this.setEntityParent(entityParent);
		this.setEntityParentType(entityParentType);
		this.setCcicId(ccicId);
		this.setEnterpriseName(enterpriseName);
		this.setFullName(fullName);
		this.setMasterHotline(masterHotline);
		this.setStatus(status);
		this.setEntityStatus(entityStatus);
		this.setProductId(productId);
		this.setPeriod(period);
		this.setLowestCost(lowestCost);
		this.setTotalLowestCost(totalLowestCost);
		this.setIsTest(isTest);
		this.setMoneyIb(moneyIb);
		this.setMoneyOb(moneyOb);
		this.setMoneyFunc(moneyFunc);
		this.setMoneySms(moneySms);
		this.setServiceStart(serviceStart);
		this.setServiceEnd(serviceEnd);
		this.setCreateTime(createTime);
		this.setClientWeb(clientWeb);
		this.setRent(rent);
	}
	
	public Integer getEnterpriseId() {
		return getId();
	}
	public void setEnterpriseId(Integer id) {
		super.setId(id);
	}
	public Integer getEntityParent() {
		return entityParent;
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
	public Integer getCcicId() {
		return ccicId;
	}
	public void setCcicId(Integer ccicId) {
		this.ccicId = ccicId;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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
	public String getStatusDesc(){
		if (this.getStatus()!=null) {
			switch (this.getStatus()) {
				case 0:
					return "未开通";
				case 1 :
					return "正常";
				case 2 :
					return "欠费";
				case 3 :
					return "停机";
				case 4 :
					return "注销";
				default:
					break;
			}
		}
		return "";
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getEntityStatusDesc() {
		if (this.getEntityStatus()!=null) {
			switch (this.getEntityStatus()) {
				case 1:
					return  "正常";
				case 2:
					return  "禁用";
				case 3:
					return  "锁定";
					
				default:
					break;
			}
		}
		return "";
	}
	public Integer getEntityStatus() {
		return entityStatus;
	}
	public void setEntityStatus(Integer entityStatus) {
		this.entityStatus = entityStatus;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public BigDecimal getLowestCost() {
		return lowestCost;
	}
	public void setLowestCost(BigDecimal lowestCost) {
		this.lowestCost = lowestCost;
	}
	public BigDecimal getTotalLowestCost() {
		return totalLowestCost;
	}
	public void setTotalLowestCost(BigDecimal totalLowestCost) {
		this.totalLowestCost = totalLowestCost;
	}
	public Integer getIsTest() {
		return isTest;
	}
	public void setIsTest(Integer isTest) {
		this.isTest = isTest;
	}
	public Date getServiceStart() {
		return serviceStart;
	}
	public void setServiceStart(Date serviceStart) {
		this.serviceStart = serviceStart;
	}
	public Date getServiceEnd() {
		return serviceEnd;
	}
	public void setServiceEnd(Date serviceEnd) {
		this.serviceEnd = serviceEnd;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getMoneyIb() {
		return moneyIb;
	}
	public void setMoneyIb(BigDecimal moneyIb) {
		this.moneyIb = moneyIb;
	}
	public BigDecimal getMoneyOb() {
		return moneyOb;
	}
	public void setMoneyOb(BigDecimal moneyOb) {
		this.moneyOb = moneyOb;
	}
	public BigDecimal getMoneyFunc() {
		return moneyFunc;
	}
	public void setMoneyFunc(BigDecimal moneyFunc) {
		this.moneyFunc = moneyFunc;
	}
	public BigDecimal getMoneySms() {
		return moneySms;
	}
	public void setMoneySms(BigDecimal moneySms) {
		this.moneySms = moneySms;
	}
	public void setDirectSection(String directSection) {
		this.directSection = directSection;
	}
	public String getDirectSection() {
		return directSection;
	}
	public void setDirectManage(String directManage) {
		this.directManage = directManage;
	}
	public String getDirectManage() {
		return directManage;
	}
	public void setMoneyArrears(BigDecimal moneyArrears) {
		this.moneyArrears = moneyArrears;
	}
	public BigDecimal getMoneyArrears() {
		return moneyArrears;
	}
	public void setTestStatus(Integer testStatus) {
		this.testStatus = testStatus;
	}
	public Integer getTestStatus() {
		return testStatus;
	}
	public void setCcicName(String ccicName) {
		this.ccicName = ccicName;
	}
	@Transient
	public String getCcicName() {
		return ccicName;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Transient
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setClientWeb(Integer clientWeb) {
		this.clientWeb = clientWeb;
	}
	public Integer getClientWeb() {
		return clientWeb;
	}
	
	@Transient
	public Enterprise getEnterprise() {
		enterprise.setEnterpriseId(this.getId());
		enterprise.setProductId(this.getProductId());
		enterprise.setPeriod(this.getPeriod());
		enterprise.setEntityStatus(this.getEntityStatus());
		enterprise.setEntityParent(this.getEntityParent());
		enterprise.setCreateTime(this.getCreateTime());
		enterprise.setStatus(this.getStatus());
		enterprise.setCcicId(this.getCcicId());
		enterprise.setMasterHotline(this.getMasterHotline());
		enterprise.setTotalLowestCost(this.getTotalLowestCost());
		enterprise.setEnterpriseName(this.getEnterpriseName());
		enterprise.setServiceEnd(this.getServiceEnd());
		enterprise.setExpiryDate(this.getExpiryDate());
		enterprise.setFullName(this.getFullName());
		enterprise.setLowestCost(this.getLowestCost());
		enterprise.setInboundCallLimit(this.getInboundCallLimit());
		enterprise.setCtiId(this.getCtiId());
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public void setIsUseItf(Integer isUseItf) {
		this.isUseItf = isUseItf;
	}
	@Transient
	public Integer getIsUseItf() {
		return isUseItf;
	}
	@Transient
	public String getIsUseItfDesc(){
		String isUseItf = "";
		if (this.getIsUseItf()!=null) {
			switch (this.getIsUseItf()) {
				case 0:
					isUseItf = "不使用";
					break;
				case 1:
					isUseItf = "使用";
					break;
				default:
					break;
			}
		}
		return isUseItf;
	}
	public void setAccessMode(Integer accessMode) {
		this.accessMode = accessMode;
	}
	@Transient
	public Integer getAccessMode() {
		return accessMode;
	}
	@Transient
	public String getAccessModeDesc(){
		String accessMode = "";
		if (this.getAccessMode()!=null) {
			switch (this.getAccessMode()) {
			case 1:
				accessMode = "直线";
				break;
			case 2:
				accessMode = "IAD";
				break;
			case 3:
				accessMode = "无线话机";
			case 4:
				accessMode = "软电话";
			default:
				break;
			}
		}
		return accessMode;
	}
	
	public BigDecimal getRent() {
		return rent;
	}
	
	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}
	
	public Integer getClientTel() {
		return clientTel;
	}
	
	public void setClientTel(Integer clientTel) {
		this.clientTel = clientTel;
	}
	
	public Integer getClientFree() {
		return clientFree;
	}
	
	public void setClientFree(Integer clientFree) {
		this.clientFree = clientFree;
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
	
	public Integer getComboId() {
		return comboId;
	}
	
	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
	
	public long getArrearsTime() {
		return arrearsTime;
	}
	
	public void setArrearsTime(long arrearsTime) {
		this.arrearsTime = arrearsTime;
	}

	public String getArrearsTimeDate() {
		if (this.getArrearsTime() == 0) {
			arrearsTimeDate = "";
		} else {
			arrearsTimeDate = DateUtil.dateToString(new Date(this.getArrearsTime() * 1000), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
		}
		return arrearsTimeDate;
	}
	
	public void setArrearsTimeDate(String arrearsTimeDate) {
		this.arrearsTimeDate = arrearsTimeDate;
	}
	
	
	public long getCreditValidTime() {
		return creditValidTime;
	}

	public void setCreditValidTime(long creditValidTime) {
		this.creditValidTime = creditValidTime;
	}
	
	public String getCreditValidDate() {
		if (this.getCreditValidTime() == 0) {
			creditValidDate = "";
		} else {
			creditValidDate = DateUtil.dateToString(new Date(this.getCreditValidTime() * 1000), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
		}
		return creditValidDate;
	}
	
	public void setCreditValidDate(String creditValidDate) {
		this.creditValidDate = creditValidDate;
	}
	
	public String getCreditTypeDesc() {
		if (this.getCredit()!=null) {
			if (this.getCredit().compareTo(new BigDecimal("0"))!=0) {
				creditTypeDesc = "固定";
			} else if (this.getCreditTemp().compareTo(new BigDecimal("0"))!=0){
				creditTypeDesc = "临时";
			} else {
				creditTypeDesc = "";
			}
		}
		return creditTypeDesc;
	}
	
	public void setCreditTypeDesc(String creditTypeDesc) {
		this.creditTypeDesc = creditTypeDesc;
	}
	
	public String getCreditValue() {
		if (this.getCredit().compareTo(new BigDecimal("0"))!=0) {
			creditValue = this.getCredit().toString();
		} else if (this.getCreditTemp().compareTo(new BigDecimal("0"))!=0){
			creditValue = this.getCreditTemp().toString();
		} else {
			creditValue = "0";
		}
		return creditValue;
	}
	
	public void setCreditValue(String creditValue) {
		this.creditValue = creditValue;
	}
	
	public Integer getCreditDays() {
		return creditDays;
	}
	
	public void setCreditDays(Integer creditDays) {
		this.creditDays = creditDays;
	}
	
	public Integer getCreditTempDays() {
		return creditTempDays;
	}
	
	public void setCreditTempDays(Integer creditTempDays) {
		this.creditTempDays = creditTempDays;
	}
	public void setClientArea(String clientArea) {
		this.clientArea = clientArea;
	}
	public String getClientArea() {
		return clientArea;
	}
	
	public String getTrade() {
		return trade;
	}
	
	public void setTrade(String trade) {
		this.trade = trade;
	}
	
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public Integer getProductType() {
		return productType;
	}
	
	public String getProductTypeDesc(){
		String desc = "";
		if (this.getProductType()!=null) {
			switch (this.getProductType()) {
				case 1:
					desc = "标准座席";
					break;
				case 2:
					desc = "400送座席";
					break;
				case 6:
					desc = "1010送座席";
					break;
				case 3:
					desc = "外呼套餐包";
					break;
				case 7:
					desc = "智能语音";
					break;
				case 4:
					desc = "总分机";
					break;
				case 5:
					desc = "其他";
					break;
				default:
					break;
			}
		}
		return desc;
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
	public Integer getInboundCallLimit() {
		return inboundCallLimit;
	}
	public void setInboundCallLimit(Integer inboundCallLimit) {
		this.inboundCallLimit = inboundCallLimit;
	}
	public Integer getCtiId() {
		return ctiId;
	}
	public void setCtiId(Integer ctiId) {
		this.ctiId = ctiId;
	}
}
