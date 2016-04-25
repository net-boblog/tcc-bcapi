package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.BizChangeRequest;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseResponse;
import com.tinet.api.boss2.response.enity.LogDeductionListResponse;
/**
 * 客户业务变更接口
 * <p>
 *  FileName： BizChangeWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.BizChangeWebImp
 */
@WebService 
public interface BizChangeWeb {
	/**
	 * 更新企业基本信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterprise 企业信息对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateEntBaseInfo(String ccicId, Enterprise enterprise, CommonRequest creq);
	
	/**
	 * 更新企业业务信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param bizChangeReqObj 存储企业业务信息变更内容的对象
	 * @param creq 数据签名
	 * @return
	 */
	public LogDeductionListResponse updateEntBizInfo(String ccicId, BizChangeRequest bizChangeReqObj, CommonRequest creq);
	
	/**
	 * 更新企业配置，没有则新增，否则执行更新。
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param entSetting 企业设置对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateEntSetting(String ccicId, EnterpriseSetting entSetting, CommonRequest creq);
	
	/**
	 * 更新企业短信签名，若短信功能未开启，则不更新。
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param entSetting 企业设置对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateEntSmsSign(String ccicId, EnterpriseSetting entSetting, CommonRequest creq);
	
	/**
	 * 直销经理提交业务变更单,定时更新企业座席数
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param newValue 变更为的值
	 *  type	  newValue					格式描述
	 * type=1	"0,0.000;5;0"	clinetWeb,rent;clientFree,clientTel	
	 * @param creq 数据签名
	 * @return
	 */
	public LogDeductionListResponse executeClientChange4Crontab(String ccicId, Integer enterpriseId, String newValue, boolean isDeduct, CommonRequest creq);
	
	/**
	 * 直销经理提交业务变更单,即时更新企业座席数
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param newValue 变更为的值
	 *  type	  newValue					格式描述
	 * type=1	"0,0.000;5;0"	clinetWeb,rent;clientFree,clientTel	
	 * @param creq 数据签名
	 * @return
	 */
	public LogDeductionListResponse executeClientChange(String ccicId, Integer enterpriseId, String newValue, CommonRequest creq);
	
	/**
	 * 直销经理提交业务变更单,更新企业座席月租/号码最低消费/短信费率/客户呼入费率/外呼客户费率
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param type  变更类型：1-座席数变更 4-座席月租  5-号码最低消费 6-客户呼入费率 7-外呼客户费率变更  8-短信费率  9-短信签名
	 * @param newValue 变更为的值
	 *  type	  newValue					格式描述
	 * type=4	"0,0.000" 		clinetWeb,rent
	 * type=5	"{4006869009:1000}"  {hotline1:lowestCostnext1;hotline2:lowestCostnext2;....}
	 * type=6	"1,0.12费率套餐"	ratesetId,rateSetName
	 * type=7	"2,0.15费率套餐"	ratesetId,rateSetName
	 * type=8	"0.100"		smsRate
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse executeEntRateChange(String ccicId, Integer enterpriseId, Integer type, String newValue, CommonRequest creq);
	
	/**
	 * 修改企业业务状态：1) 停机-->注销	2) 正常/欠费-->停机	3) 欠费/停机-->正常
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param status 修改为的状态
	 * @param creq 数据签名
	 * @return
	 */
	public LogDeductionListResponse changeBizStatus(String ccicId, String enterpriseId, String status, CommonRequest creq);
	
	/**
	 * 直销经理初始化测试账号，保留测试账户状态，有效期和上级。
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名对象
	 * @return 初始化了的企业信息对象，含后台管理员密码变更
	 */
	public EnterpriseResponse initTestEnterpriseBiz(String ccicId, Integer enterpriseId, CommonRequest creq);
} 
