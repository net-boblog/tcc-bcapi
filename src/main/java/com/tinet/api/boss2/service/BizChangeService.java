package com.tinet.api.boss2.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.BizChangeDao;
import com.tinet.api.boss2.dao.BusinessDao;
import com.tinet.api.boss2.dao.EnterpriseDao;
import com.tinet.api.boss2.dao.EnterpriseHotlineDao;
import com.tinet.api.boss2.dao.EnterpriseRateDao;
import com.tinet.api.boss2.dao.EnterpriseSettingDao;
import com.tinet.api.boss2.dao.QueueMemberDao;
import com.tinet.api.boss2.model.BizChangeRequest;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.api.boss2.model.MobileVirtual;
import com.tinet.api.boss2.model.Trunk;
import com.tinet.common.inc.Const;
import com.tinet.common.util.StringUtil;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 企业业务变更逻辑处理层
 * <p>
 *  FileName： BizChangeService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("bizChangeService")
public class BizChangeService {
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private BizChangeDao bizChangeDao;
	@Autowired
	private EnterpriseSettingDao enterpriseSettingDao;
	@Autowired
	private CommonService commonService;
	@Autowired
	private EnterpriseRateDao enterpriseRateDao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private EnterpriseHotlineDao enterpriseHotlineDao;
	@Autowired
	private QueueMemberDao queueMemberDao;
	
	/**
	 * 更新企业基本信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterprise 企业信息对象
	 */
	public void saveEntBaseInfo(String ccicId, Enterprise enterprise){
		 // 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		bizChangeDao.saveEntBaseAndAdminPwd(enterprise);
	}
	
	/**
	 * 更新BOSS2客服操作企业业务信息界面所有信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param bizChangeReqObj 存储企业业务信息变更内容的对象
	 * @return
	 */
	public Map<String, Object> saveEntBizInfo(String ccicId, BizChangeRequest bizChangeReqObj) {
		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		// 要返回的扣费日志列表
		List<LogDeduction> logDeductionList = new ArrayList<LogDeduction>();
		
		DatabaseContextHolder.setDBType(ccicId);
		
		// 更新前的企业信息对象
		Enterprise oldEnterprise = new Enterprise();
		Integer enterpriseId = bizChangeReqObj.getEnterprise().getEnterpriseId();
		oldEnterprise = enterpriseDao.getEntAllInfo(enterpriseId);
		
		
		// 检查当前在线座席数
		Integer clientWeb = bizChangeReqObj.getEnterprise().getClientWeb();
		Integer clientFree = bizChangeReqObj.getEnterprise().getClientFree(); 
		Integer clientTel = bizChangeReqObj.getEnterprise().getClientTel(); 
		String rsInfo = queueMemberDao.checkOnlineClientCount(enterpriseId, clientWeb, clientFree, clientTel, 
				oldEnterprise.getClientWeb(), oldEnterprise.getClientFree(), oldEnterprise.getClientTel());
		if (StringUtil.isNotEmpty(rsInfo)) {
			rtnMap.put("error", rsInfo);
			return rtnMap;
		}
		
		// 检查电脑座席数是否变化，去BOSS2获取座席扣费事务ID clientFeeTransId
		Integer clientFeeTransId = null;
		if (oldEnterprise.getStatus() != Const.BUSINESS_STATUS_NO_SERVICE) { // 未开通客户,座席数发生变化,不做座席月租扣除
			if (clientWeb.intValue() > oldEnterprise.getClientWeb().intValue()) { // 其他业务状态客户若座席数发生变化,需扣费
				clientFeeTransId = commonService.getTransactionId();
			}
		}
		
		// 年低消的热线号码删除，需扣年低消结余，去BOSS2获取低消结余扣费事务ID LowestCostTransId
		Integer LowestCostTransId = null;
		if (oldEnterprise.getPeriod() == Const.ENTERPRISE_HOTLINE_PERIOD_YEAR && 
				((bizChangeReqObj.getEntHotlineDel()!=null && bizChangeReqObj.getEntHotlineDel().size()>0) 
						|| (bizChangeReqObj.getEntTrunkDel()!=null && bizChangeReqObj.getEntTrunkDel().size()>0) 
						|| (bizChangeReqObj.getMobileVirtualDel()!=null && bizChangeReqObj.getMobileVirtualDel().size()>0))) {
			LowestCostTransId = commonService.getTransactionId();
		}
		
		// 手机虚拟号码和直线号码有新增，需扣除当月剩余天数月功能费，去BOSS2获取低消结余扣费事务ID numberRentTransId
		Integer numberRentTransId = null;
		if ((bizChangeReqObj.getEntTrunkAdd()!=null && bizChangeReqObj.getEntTrunkAdd().size() > 0) 
				|| (bizChangeReqObj.getMobileVirtualAdd()!=null && bizChangeReqObj.getMobileVirtualAdd().size() > 0)) {
			BigDecimal rent = new BigDecimal(0);
			
			if (bizChangeReqObj.getEntTrunkAdd()!=null) {
				for (Trunk trunk : bizChangeReqObj.getEntTrunkAdd()) {
					rent = rent.add(trunk.getRent());
				}
			}
			
			if (bizChangeReqObj.getMobileVirtualAdd()!=null) {
				for (MobileVirtual mobileVirtual : bizChangeReqObj.getMobileVirtualAdd()) {
					rent = rent.add(mobileVirtual.getRent());
				}
			}
			
			if (rent.compareTo(BigDecimal.ZERO)>0) {
				numberRentTransId = commonService.getTransactionId();
			}
		}
		
		// 若预测外呼开关开启 且预测外呼并发数增加，需扣中继占用费 ，去BOSS2获取低消结余扣费事务ID trunkUseFeeTransId
		Integer trunkUseFeeTransId = null;
		int isOpen = bizChangeReqObj.getEnterprise().getIsPredictiveOpen().intValue();
		if (isOpen == Const.IS_PREDICTIVE_OPEN_ON 
				&& bizChangeReqObj.getEnterprise().getPredictiveCallLimit().compareTo(oldEnterprise.getPredictiveCallLimit()==null?"0":oldEnterprise.getPredictiveCallLimit()) > 0) {
			trunkUseFeeTransId = commonService.getTransactionId();
		}
		
		// 切换数据源id，更新业务信息
		DatabaseContextHolder.setDBType(ccicId);
		logDeductionList =  bizChangeDao.saveEntAllBizInfo(oldEnterprise, bizChangeReqObj, 
				clientFeeTransId, LowestCostTransId, numberRentTransId, trunkUseFeeTransId);
		rtnMap.put("logDeductionList", logDeductionList);
		
		// 查询企业当前业务状态
		Integer bizStatus = businessDao.getEntBizStatus(enterpriseId);
		rtnMap.put("bizStatus", bizStatus);
		
		return rtnMap;
	}

	/**
	 * 更新企业短信签名
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param entSetting 企业短信设置对象
	 * @return
	 */
	public String updateEntSmsSign(String ccicId, EnterpriseSetting entSetting) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		
		Integer enterpriseId = entSetting.getEnterpriseId();
		
		//  检查短信功能是否开启
		String isSmsSend = enterpriseSettingDao.getEntSettingValue(enterpriseId, "is_sms_send");
		if (Integer.parseInt(isSmsSend) == Const.IS_PREDICTIVE_OPEN_OFF) {
			return "企业未开通短信功能，不能修改签名！";
		}
		// 更新短信签名
		enterpriseSettingDao.updateEntSetting(enterpriseId, entSetting.getName(), entSetting.getValue(), null);
		return null;
	}
	
	/**
	 * 直销经理提交业务变更单,定时更新企业座席座席数
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param newValue 变更为的值
	 *  type	  newValue					格式描述
	 * type=1	"0,0.000;5;0"	clinetWeb,rent;clientFree,clientTel	
	 * @return
	 */
	public Map<String, Object> executeClientChange4Crontab(String ccicId, Integer enterpriseId, String newValue, boolean isDeduct){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		// 读取当前企业信息
		Enterprise enterprise = enterpriseDao.getEntBaseInfo(enterpriseId);
		
		// 座席数
		int oldClientWeb = enterprise.getClientWeb();
		int newClientWeb = Integer.parseInt(newValue.split(",")[0]);
		// 检查电脑座席数是否增加,若增加则获取扣费事务ID
		Integer transId = null;
		if (isDeduct && newClientWeb > oldClientWeb) {
			transId = commonService.getTransactionId(); 
		}
		// 切换数据源
		DatabaseContextHolder.setDBType(ccicId);
		// 执行座席数变更
		Map<String, Object> map = bizChangeDao.changeClientCount4Crontab(enterprise, newValue, transId, isDeduct);
		
		// 获取当前业务状态
		map.put("bizStatus", businessDao.getEntBizStatus(enterpriseId));
		return map;
	}
	
	/**
	 * 直销经理提交业务变更单,即时更新企业座席座席数
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param newValue 变更为的值
	 *  type	  newValue					格式描述
	 * type=1	"0,0.000;5;0"	clinetWeb,rent;clientFree,clientTel	
	 * @return
	 */
	public Map<String, Object> executeClientChange(String ccicId, Integer enterpriseId, String newValue){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		// 读取当前企业信息
		Enterprise enterprise = enterpriseDao.getEntBaseInfo(enterpriseId);
		
		// 座席数
		int oldClientWeb = enterprise.getClientWeb();
		int newClientWeb = Integer.parseInt(newValue.split(",")[0]);
		// 检查电脑座席数是否增加,若增加则获取扣费事务ID
		Integer transId = null;
		if (newClientWeb > oldClientWeb) {
			transId = commonService.getTransactionId(); 
		}
		// 切换数据源
		DatabaseContextHolder.setDBType(ccicId);
		// 执行座席数变更
		Map<String, Object> map = bizChangeDao.changeClientCount(enterprise, newValue, transId);
		
		// 获取当前业务状态
		map.put("bizStatus", businessDao.getEntBizStatus(enterpriseId));
		return map;
	}
	
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
	 * type=8	"0.100"			smsRate
	 * @return
	 */
	public void executeEntRateChange(String ccicId, Integer enterpriseId, Integer type, String newValue){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		if (type == Const.LOG_CHANGE_TYPE_CLIENT_WEB_RENT) { // 座席月租费
			businessDao.updateRent(enterpriseId, new BigDecimal(newValue.split(",")[1]));
		} else if (type == Const.LOG_CHANGE_TYPE_LOWEST_COST) { // 号码最低消费
			enterpriseHotlineDao.updateLowestNext(enterpriseId, newValue);
		} else if (type == Const.LOG_CHANGE_TYPE_IB_RATE) { // 客户呼入费率
			enterpriseRateDao.updateEntIbLeftRate(enterpriseId, Integer.parseInt(newValue.split(",")[0]));
		} else if (type == Const.LOG_CHANGE_TYPE_OB_RATE) { // 外呼客户费率
			enterpriseRateDao.updateEntObLeftRate(enterpriseId, Integer.parseInt(newValue.split(",")[0]));
		} else if (type == Const.LOG_CHANGE_TYPE_SMS_RATE){ // 短信费率
			enterpriseRateDao.updateEntSmsRate(enterpriseId, new BigDecimal(newValue));
		}
	}
	
	/**
	 * 修改企业业务状态：1) 停机-->注销	2) 正常/欠费-->停机	3) 欠费/停机-->正常
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param status 修改为的状态
	 * @return
	 */
	public Map<String, Object> changeBizStatus(String ccicId, String enterpriseId, String status){
		
		// 获取事务id，可能用到
		Integer transId = commonService.getTransactionId();
		
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		
		// 修改企业业务状态
		return bizChangeDao.changeBizStatus(Integer.parseInt(enterpriseId), Integer.parseInt(status), transId);
	}
	
	/**
	 * 直销经理初始化测试账号，保留测试账户状态，有效期和上级。
	 * @param ccicId
	 * @param enterpriseId
	 * @return
	 */
	public Map<String, Object> initTestEnterpriseBiz(String ccicId, Integer enterpriseId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
//		bizChangeDao.initTestEnterpriseBiz(enterpriseId);
		return null;
	}
}
