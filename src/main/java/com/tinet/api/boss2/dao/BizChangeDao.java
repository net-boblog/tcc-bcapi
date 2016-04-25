package com.tinet.api.boss2.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.BizChangeRequest;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.api.boss2.model.EnterpriseHotline;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.api.boss2.model.MobileVirtual;
import com.tinet.api.boss2.model.Trunk;
import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;
import com.tinet.common.util.MD5Encoder;
import com.tinet.common.util.StringUtil;

/**
 * 业务变更数据库多表读写实现
 * <p>
 *  FileName： BizChangeDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("bizChangeDao")
public class BizChangeDao {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private EnterpriseUserDao enterpriseUserDao;
	@Autowired
	private EnterpriseSettingDao enterpriseSettingDao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private LogDeductionDao logDeductionDao;
	@Autowired
	private EnterpriseHotlineDao enterpriseHotlineDao;
	@Autowired
	private TrunkDao trunkDao;
	@Autowired
	private ComboDao comboDao;
	@Autowired
	private EnterpriseVoiceDao enterpriseVoiceDao;
	@Autowired
	private EnterpriseRateDao enterpriseRateDao;
	@Autowired
	private EnterpriseRouterDao enterpriseRouterDao;
	@Autowired
	private EnterpriseClidDao enterpriseClidDao;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private QueueMemberDao queueMemberDao;
	
	/**
	 * 修改企业信息基本信息和后台admin用户登录密码
	 * @author louxue
	 * @param enterprise 企业信息对象
	 */
	@Transactional
	public void saveEntBaseAndAdminPwd(Enterprise enterprise){
		// 修改企业基本信息
		enterpriseDao.saveEntBaseInfo(enterprise);
		
		// 若界面录入企业的密码不为空，则修改企业后台登录用户admin的登录密码
		if (enterprise.getEntityPwd()!=null && !"".equals(enterprise.getEntityPwd())) {
			enterpriseUserDao.saveEntUserPwd(enterprise.getEnterpriseId(), "admin", MD5Encoder.encode(enterprise.getEntityPwd()));
		}
	}

	/**
	 * 更新企业业务信息界面所有信息(BOSS2客服操作)
	 * @author louxue
	 * @param oldEnterprise 原企业信息对象
	 * @param bizChangeReqObj 存储业务信息变更内容的对象
	 * @param clientFeeTransId 座席月租扣费事务ID，若为null，则不扣费。
	 * @param lowestCostTransId 最低消费扣费事务ID，若为null，则不扣费。
	 * @param numberRentTransId 号码月租扣费事务ID，若为null，则不扣费。
	 * @param trunkUseFeeTransId 中继占用费扣费事务ID，若为null，则不扣费。
	 * @return 返回扣费日志列表
	 */
	@Transactional
	public List<LogDeduction> saveEntAllBizInfo(Enterprise oldEnterprise, BizChangeRequest bizChangeReqObj, 
			Integer clientFeeTransId, Integer lowestCostTransId, Integer numberRentTransId, Integer trunkUseFeeTransId) {
		List<LogDeduction> logDeductions = new ArrayList<LogDeduction>();
		
		Enterprise enterprise = bizChangeReqObj.getEnterprise();
		Integer enterpriseId = enterprise.getEnterpriseId();
		Integer period = oldEnterprise.getPeriod();
		
		// 保存business信息
		businessDao.saveBusinessInfo(enterprise);
		
		// 对新增的电脑座席，做座席月功能费扣除
		if (clientFeeTransId != null) {
			LogDeduction logDeduction = logDeductionDao.deductClientFee(enterpriseId, oldEnterprise.getAccountId(), oldEnterprise.getFullName(),
					enterprise.getClientWeb(), enterprise.getRent(), clientFeeTransId, oldEnterprise.getClientWeb());
			if (logDeduction != null) {
				logDeductions.add(logDeduction);
			}
		}
		
		// 更新企业短信签名
		if (!enterprise.getSmsSign().equals(oldEnterprise.getSmsSign())) {
			enterpriseSettingDao.updateEntSetting(enterpriseId, Const.ENTERPRISE_SETTING_NAME_SMS_SIGN, enterprise.getSmsSign(), null);
		}
		
		// 新增的热线号码
		List<EnterpriseHotline> entHotlineAddList = bizChangeReqObj.getEntHotlineAdd();
		// 删除的热线号码
		List<EnterpriseHotline> entHotlineDelList = bizChangeReqObj.getEntHotlineDel();
		// 新增的直线号码
		List<Trunk> entTrunkAddList = bizChangeReqObj.getEntTrunkAdd();
		// 删除的直线号码
		List<Trunk> entTrunkDelList = bizChangeReqObj.getEntTrunkDel();
		// 新增的热线号码的目的码
		List<Trunk> entHotlineTrunkAddList = bizChangeReqObj.getEntHotlineTrunkAdd();
		// 删除的热线号码的目的码
		List<Trunk> entHotlineTrunkDelList = bizChangeReqObj.getEntHotlineTrunkDel();
		// 新增手机虚拟号
		List<MobileVirtual> mobileVirtualAddList = bizChangeReqObj.getMobileVirtualAdd();
		// 删除手机虚拟号
		List<MobileVirtual> mobileVirtualDelList = bizChangeReqObj.getMobileVirtualDel();
		// 目的码有变化的热线号码
		List<EnterpriseHotline> entHotlineTrunkChangeList = bizChangeReqObj.getEntHotlineTrunkChange();
		
		// 目的码及其区号map
		Map<String, String> trunkWithAreaCode = bizChangeReqObj.getTrunkWithAreaCode();
		
		// 热线号码和目的号码更新
		synchronized (this) {
			// 删除、新增热线号码，修改热线号码的目的码。
			String delHotlines = enterpriseHotlineDao.delAndInsEntHotline(enterpriseId, enterprise.getMasterHotline(), period, entHotlineAddList, entHotlineDelList, 
					entHotlineTrunkChangeList, entTrunkAddList, entTrunkDelList, mobileVirtualAddList, mobileVirtualDelList);
			
			// 删除目的码
			trunkDao.deleteEntTrunk(enterpriseId, entTrunkDelList, entHotlineTrunkDelList, mobileVirtualDelList);
			
			// 新增目的码
			trunkDao.insertEnterpriseTrunk(enterpriseId, enterprise.getBillingTrunk(),trunkWithAreaCode, 
					entTrunkAddList, entHotlineTrunkAddList, mobileVirtualAddList);
			
			// 更改主热线号码
			if(!enterprise.getMasterHotline().equals(oldEnterprise.getMasterHotline())){
				enterpriseHotlineDao.updateMaterHotline(enterpriseId, enterprise.getMasterHotline());
			}
			
			// 更改计费目的码
			if(enterprise.getBillingTrunk()!=null && !enterprise.getBillingTrunk().equals(oldEnterprise.getBillingTrunk())){
				trunkDao.updateBillingTrunk(enterpriseId, enterprise.getBillingTrunk());
			}
			
			// 年低消的热线号码删除执行扣低消结余
			if(lowestCostTransId != null){
				List<LogDeduction> logDeductionList = logDeductionDao.deductLowestCost(enterpriseId, oldEnterprise.getAccountId(), 
						oldEnterprise.getFullName(), delHotlines, lowestCostTransId);
				if (logDeductionList != null && logDeductionList.size() > 0) {
					logDeductions.addAll(logDeductionList);
				}
			}
			
			// 新增的手机虚拟号码和直线号码扣除当月剩余天数月功能费
			if(numberRentTransId != null){
				LogDeduction logDeduction = logDeductionDao.deductNumberRent(enterpriseId, oldEnterprise.getAccountId(), oldEnterprise.getFullName(),
						mobileVirtualAddList,entTrunkAddList,numberRentTransId);
				if (logDeduction != null) {
					logDeductions.add(logDeduction);
				}
			}
		}
		
		// 更新下月外呼套餐包
		if(enterprise.getNewComboId() != null && oldEnterprise.getNewComboId() != null
				&& !enterprise.getNewComboId().equals(oldEnterprise.getNewComboId())){
			comboDao.updateNextCombo(enterpriseId, enterprise.getNewComboId());
		}
		
		// 更新企业费率表
		enterpriseRateDao.updateEnterpriseRate(enterpriseId, enterprise.getRateIbLeft(), enterprise.getRateIbRight(), 
				enterprise.getRateObLeft(), enterprise.getRateObRight(), enterprise.getRateSms());

		// 更新企业开通功能enterprise_setting表
		enterpriseSettingDao.updateOpenFuncs(enterpriseId, bizChangeReqObj.getFunctionNameSet());
		
		// 更新使用接口
		enterpriseSettingDao.updateEntSetting(enterpriseId, Const.ENTERPRISE_SETTING_NAME_IS_USE_ITF, enterprise.getIsUseItf().toString(), null);
		
		// 更新客户电话接入方式
		enterpriseSettingDao.updateEntSetting(enterpriseId, Const.ENTERPRISE_SETTING_NAME_ACCESS_MODE, enterprise.getAccessMode().toString(), null);
		
		// 更改是否全程录音
		EnterpriseSetting fullRecordSetting = new EnterpriseSetting(enterpriseId, 
				Const.ENTERPRISE_SETTING_NAME_IS_FULL_RECORD, enterprise.getIsFullRecord().toString(), null);
		enterpriseSettingDao.instOrUpdateEntSetting(fullRecordSetting);
		
		// 更改录音试听方式
		EnterpriseSetting recordSetting = new EnterpriseSetting(enterpriseId, 
				Const.ENTERPRISE_SETTING_IS_AWS_RECORD, enterprise.getIsAwsRecord().toString(), null);
		enterpriseSettingDao.instOrUpdateEntSetting(recordSetting);
		
		// 更新S3目录
		EnterpriseSetting directorySetting = new EnterpriseSetting(enterpriseId, 
				Const.ENTERPRISE_SETTING_AWS_RECORD_DIRECTORY, enterprise.getAwsRecordDirectory(), null);
		enterpriseSettingDao.instOrUpdateEntSetting(directorySetting);
		
		// 外呼黑名单开关
		EnterpriseSetting outCallSwitchSetting = new EnterpriseSetting(enterpriseId, 
				Const.ENTERPRISE_SETTING_OUT_CALL_SWITCH, enterprise.getOutCallSwitch(), enterprise.getOutCallPropertyJson());
		enterpriseSettingDao.instOrUpdateEntSetting(outCallSwitchSetting);
		
		// 更新预测外呼开关
		EnterpriseSetting predictiveCallLimitSetting = new EnterpriseSetting(enterpriseId, 
				Const.ENTERPRISE_SETTING_PREDICTIVE_CALL_LIMIT, enterprise.getPredictiveCallLimit(), "预测外呼企业并发限制");
		EnterpriseSetting predictiveCallFeeSetting = new EnterpriseSetting(enterpriseId, 
				Const.ENTERPRISE_SETTING_PREDICTIVE_CALL_FEE, enterprise.getPredictiveCallFee(), "预测外呼每并发资费");
		enterpriseSettingDao.updateIsPredictiveOpen(enterpriseId, enterprise.getIsPredictiveOpen(), 
				predictiveCallLimitSetting, predictiveCallFeeSetting);
		
		// 如果企业预测外呼开关由关闭到开启，则修改所有企业语音文件的状态为未审核，反之，修改状态为审核通过
		if (!oldEnterprise.getIsPredictiveOpen().equals(enterprise.getIsPredictiveOpen())) {
			if(enterprise.getIsPredictiveOpen() == Const.IS_PREDICTIVE_OPEN_ON){
				enterpriseVoiceDao.updateVoiceStatus(enterpriseId, Const.ENTERPRISE_VOICE_AUDIT_STATUS_UNAUDITED);
			} else {
				enterpriseVoiceDao.updateVoiceStatus(enterpriseId, Const.ENTERPRISE_VOICE_AUDIT_STATUS_AUDITPASS);
			}
		}
		
		// 预测外呼开关开启且预测外呼企业并发限制增加，扣除当月中继占用费
		if(trunkUseFeeTransId != null){
			LogDeduction logDeduction = logDeductionDao.deductTrunkUseFee(enterpriseId, oldEnterprise.getAccountId(), oldEnterprise.getFullName(),
					enterprise.getPredictiveCallLimit(), enterprise.getPredictiveCallFee(), trunkUseFeeTransId, oldEnterprise.getPredictiveCallLimit()==null?"0":oldEnterprise.getPredictiveCallLimit());
			if (logDeduction != null) {
				logDeductions.add(logDeduction);
			}
		}
		
		// 更新企业外显号码设置
		enterpriseClidDao.updateEnterpriseClid(enterpriseId, enterprise.getIbClidRightType(), enterprise.getIbClidRightNumber(), 
				enterprise.getObPreviewClidLeftType(), enterprise.getObPreviewClidLeftNumber(), enterprise.getObPreviewClidRightType(), 
				enterprise.getObPreviewClidRightNumber(), enterprise.getObPredictiveClidLeftType(), enterprise.getObPredictiveClidLeftNumber(),
				enterprise.getObPredictiveClidRightType(), enterprise.getObPredictiveClidRightNumber());
		
		// 预览外呼客户侧外显号码修改则，更新企业client表的ob_clid字段
		clientDao.updateClientObCLid(enterpriseId, enterprise.getObPreviewClidLeftType(), enterprise.getObPreviewClidLeftNumber(), 
				enterprise.getHotlineTrunk(), enterprise.getDirectNumber(), enterprise.getMobileVirtual(), trunkWithAreaCode);

		// 更新企业路由选择表
		enterpriseRouterDao.updateEnterpriseRouter(enterpriseId, enterprise.getIbRouterRight(), 
				enterprise.getObPreviewRouterLeft(), enterprise.getObPredictiveRouterLeft());
		
		
		return logDeductions;
	}
	
	/**
	 * 座席数设置到期自动变更
	 * @param enterprise 企业基本信息对象
	 * @param newValue 座席及其月租串,格式为：clinetWeb,rent;clientFree,clientTel,如"0,0.000;5;0"
	 * @param transId 事务ID
	 * @param isDeduct 是否扣费，true表示须执行扣费，当前的第二天不是月初1号；false表示不须扣费，当前的第二天是月初1号
	 * @return
	 */
	@Transactional
	public Map<String, Object> changeClientCount4Crontab(Enterprise enterprise, String newValue, Integer transId, boolean isDeduct) {
		
		Integer enterpriseId = enterprise.getEnterpriseId();
		
		// 返回对象
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 座席数
		int oldClientWeb = enterprise.getClientWeb();
		int newClientWeb = Integer.parseInt(newValue.split(",")[0]);
		int newClientFree = Integer.parseInt(newValue.split(";")[1]);
		int newClientTel = Integer.parseInt(newValue.split(";")[2]);
		if (isDeduct && oldClientWeb < newClientWeb) {
			// 执行扣费
			LogDeduction logDeduction = logDeductionDao.deductClientFee(enterpriseId, enterprise.getAccountId(), enterprise.getFullName(), newClientWeb, 
					enterprise.getRent(), transId, oldClientWeb);
			map.put("logDeduction", logDeduction);
		}
		
		// 修改企业座席数
		businessDao.updateClientCount(enterpriseId, newClientWeb, newClientFree, newClientTel);
		return map;
	}
	
	/**
	 * 更新企业座席数
	 * @param enterpriseId 企业ID
	 * @param newValue 	座席及其月租串,格式为clinetWeb,rent;clientFree,clientTel,如"0,0.000;5;0"
	 * @param transId 事务ID
	 * @return
	 */
	@Transactional
	public Map<String, Object> changeClientCount(Enterprise enterprise, String newValue, Integer transId) {
		
		Integer enterpriseId = enterprise.getEnterpriseId();
		
		// 返回对象
		Map<String, Object> map = new HashMap<String, Object>();
				
		// 座席数
		int oldClientWeb = enterprise.getClientWeb();
		int oldClientFree = enterprise.getClientFree();
		int oldClientTel = enterprise.getClientTel();
		int newClientWeb = Integer.parseInt(newValue.split(",")[0]);
		int newClientFree = Integer.parseInt(newValue.split(";")[1]);
		int newClientTel = Integer.parseInt(newValue.split(";")[2]);
		
		// 检查当前在线座席数
		String rsInfo = queueMemberDao.checkOnlineClientCount(enterpriseId, newClientWeb, newClientFree, newClientTel, 
				oldClientWeb, oldClientFree, oldClientTel);
		if (StringUtil.isNotEmpty(rsInfo)) {
			map.put("error", rsInfo);
			return map;
		}
		
		// 当付费电脑座席数增加扣除新增座席月功能费，记录log_deduction
		if (oldClientWeb < newClientWeb) {
			// 执行扣费
			LogDeduction logDeduction = logDeductionDao.deductClientFee(enterpriseId, enterprise.getAccountId(), enterprise.getFullName(), newClientWeb, 
					enterprise.getRent(), transId, oldClientWeb);
			map.put("logDeduction", logDeduction);
		}
		
		// 修改企业座席数
		businessDao.updateClientCount(enterpriseId, newClientWeb, newClientFree, newClientTel);
		return map;
	}
	
	/**
	 * 修改企业业务状态：1) 停机-->注销	2) 正常/欠费-->停机	3) 欠费/停机-->正常
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param toStatus 修改为的状态
	 * @return 返回修改结果
	 */
	@Transactional
	public Map<String, Object> changeBizStatus(Integer enterpriseId, Integer toStatus, Integer transId){
		// 返回结果
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		int nowStatus = businessDao.getEntBizStatus(enterpriseId).intValue();
		Enterprise enterprise = new Enterprise();
		enterprise.setStatus(nowStatus);
		if (nowStatus == toStatus) {
			rsMap.put("error", "客户"+enterpriseId+"当前的业务状态已为" + enterprise.getStatusDesc() + "，不需修改！");
			return rsMap;
		}
		
		// 客户业务状态修改
		if (toStatus == Const.ENTERPRISE_BUSINESS_STATUS_LOGOUT) {	// 停机-->注销
			return this.changeStatusToLogout(enterpriseId, transId);
		} else if (toStatus == Const.ENTERPRISE_BUSINESS_STATUS_STOP) {		// 正常/欠费-->停机
			return this.changeStatusToStop(enterpriseId);
		} else if (toStatus == Const.ENTERPRISE_BUSINESS_STATUS_NORMAL) {	// 欠费/停机-->正常
			return this.changeStatusToNormal(enterpriseId, transId);
		} else {
			rsMap.put("error", "不允许修改客户"+enterpriseId+"的业务状态为欠费！");
			return rsMap;
		}
	}
	
	/**
	 * 客户业务状态置为停机，正常/欠费-->停机
	 * @param enterpriseId 企业ID
	 * @return
	 */
	@Transactional
	private Map<String, Object> changeStatusToStop(Integer enterpriseId){
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		int nowStatus = businessDao.getEntBizStatus(enterpriseId);
		// 当前为正常或欠费允许置为停机状态
		if (nowStatus == Const.ENTERPRISE_BUSINESS_STATUS_NORMAL || nowStatus == Const.ENTERPRISE_BUSINESS_STATUS_SUSPEND) {
			businessDao.updateBizStatus(enterpriseId, Const.ENTERPRISE_BUSINESS_STATUS_STOP);
		} else {
			rsMap.put("error", "客户"+enterpriseId+"当前业务状态费正常或欠费，不能停机！");
			return rsMap;
		}
		return rsMap;
	}
	
	/**
	 * 注销客户
	 * @param enterpriseId 企业ID
	 * @return
	 */
	@Transactional
	private Map<String, Object> changeStatusToLogout(Integer enterpriseId, Integer transId){
		// 返回结果
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		// 获取客户当前信息
		Enterprise enterprise = enterpriseDao.getEntAllInfo(enterpriseId);
		// 客户当前状态
		int nowStatus = enterprise.getStatus();
		if (nowStatus != Const.ENTERPRISE_BUSINESS_STATUS_STOP) {
			rsMap.put("error", "客户"+enterpriseId+"当前业务状态非停机，不能注销！");
			return rsMap;
		}
		
		// 年最低消费用户扣呼入低消结余
		if (enterprise.getPeriod() == Const.ENTERPRISE_HOTLINE_PERIOD_YEAR) {
			// 扣除按呼入计费的热线号码的年最低消费结余
			if (enterprise.getHotline()!=null && !"".equals(enterprise.getHotline()) && !enterprise.getLowestCostMode().contains("1")) { 
				List<LogDeduction> logDeductionList = logDeductionDao.deductLowestCost(enterpriseId, enterprise.getAccountId(), enterprise.getFullName(), 
						"'" + enterprise.getHotline().replaceAll(",", "','") + "'", transId);
				if (logDeductionList != null && logDeductionList.size() > 0) {
					rsMap.put("logDeductions", logDeductionList);
				}
			}
		}
		
		// 清除热线号码信息，及trunk表中的信息
		enterpriseHotlineDao.deleteEntAllHotline(enterpriseId);
		trunkDao.deleteEntAllTrunk(enterpriseId);
		
	/**
	 * C2后台已拿掉号码认证功能菜单。
	 	// 清理企业号码认证信息
		String removeSql = "delete from enterprise_clid_attestation where enterprise_id =?";
		businessDao.getJdbcTemplate().update(removeSql, enterpriseId);
	 */

		// 修改客户业务状态，合同结束时间
		businessDao.updateBizStatus(enterpriseId, Const.BUSINESS_STATUS_CLOSE);
		businessDao.updateServiceEnd(enterpriseId, new Date());
		
		// 清理路由信息
		enterpriseRouterDao.deleteEntAllRouter(enterpriseId);
		
		// 清理费率信息
		enterpriseRateDao.deleteEntAllRate(enterpriseId);
		
		return rsMap;
	}
	
	/**
	 * 已开户客户再次开通业务，欠费/停机-->正常
	 * @param enterpriseId
	 * @return
	 */
	@Transactional
	private Map<String, Object> changeStatusToNormal(Integer enterpriseId, Integer transId){
		// 返回结果
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		// 获取客户当前信息
		Enterprise enterprise = enterpriseDao.getEntBaseInfo(enterpriseId);
		// 检查当前业务状态
		int nowStatus = enterprise.getStatus().intValue();
		if (nowStatus != Const.ENTERPRISE_BUSINESS_STATUS_SUSPEND && nowStatus != Const.ENTERPRISE_BUSINESS_STATUS_STOP) {	// 欠费/停机-->正常 ID
			rsMap.put("error", "客户"+enterpriseId+"当前业务状态非欠费或停机，不能开通业务！");
			return rsMap;
		}
		
		// 可用账户余额
		BigDecimal usableAccountMoney = enterprise.getMoney().add(enterprise.getCredit()).add(enterprise.getCreditTemp());
		// 检查账户余额
		if (usableAccountMoney.compareTo(BigDecimal.ZERO) < 0) {
			rsMap.put("error", "账户余额小于0，请充值！");
			return rsMap;
		}
		
		if (nowStatus == Const.ENTERPRISE_BUSINESS_STATUS_STOP) {	// 停机-->正常
			if (enterprise.getMasterHotline() == null || "".equals(enterprise.getMasterHotline())) {
				rsMap.put("error", "请为客户选择主热线号码，再开通！");
				return rsMap;
			}
			/**
			 * 停机开通业务各费用扣除说明：
			 * 座席功能费，停机状态不扣，所以再开通时需检查并做费用扣除。
			 * 号码呼入最低消费，号码月功能费，停机状态扣除，不用扣。
			 * 外呼套餐费按外呼费率走实际消费，不做套餐折算，不用扣。
			 * 中继使用费  检查预测外呼功能是否开启，开启的话根据是否在有效期内扣中继占用费，未开启不扣。 目前C2将停掉此功能，先不做，不用扣。
			 */
			
			// 当前座席功能费扣除到的截止日期
			Date expiryDate = enterprise.getRentEnd();
			
			// 扣费有效期在当前日期之前，需扣座席功能费
			if (expiryDate.before(new Date())) {
				
				// 为计算扣费金额做准备
				Date nowDate = new Date();
				String firstDay = DateUtil.getDayInMonth(nowDate, true);
				String lastDay = DateUtil.getDayInMonth(nowDate, false);
				long dayToEnd = 0;
				long allDays = 0;
				try {
					dayToEnd = DateUtil.getIntervalDays(DateUtil.formatDate(nowDate, DateUtil.FMT_DATE_YYYY_MM_DD), lastDay) + 1;
					allDays = DateUtil.getIntervalDays(firstDay, lastDay) + 1;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				// 计算扣费金额，扣费时间段为当前时间到月底
				BigDecimal deductMoney = new BigDecimal(enterprise.getClientWeb()).multiply(enterprise.getRent()).multiply(new BigDecimal(dayToEnd))
						.divide(new BigDecimal(allDays),3,RoundingMode.HALF_UP);
				
				// 检查账户余额是否够扣费
				if (usableAccountMoney.compareTo(deductMoney) < 0) {
					rsMap.put("error", "现金账户余额不足扣除座席月功能费"+deductMoney+"元，请充值！");
					return rsMap;
				}
				
				// 扣费备注
				String deductComment = "企业" + enterpriseId + "业务状态由停机变为正常扣除座席月功能费";
				// 扣除座席功能费
				LogDeduction logDeduction = logDeductionDao.deductFee(enterpriseId, enterprise.getAccountId(), enterprise.getFullName(), 
						new BigDecimal(enterprise.getClientWeb()), enterprise.getRent(), transId, Const.LOG_DEDUCTION_FEE_TYPE_CLIENT_RENT, deductComment);
				rsMap.put("logDeductions", logDeduction);
			}
			
		} 
		
		// 修改客户业务状态为正常
		businessDao.updateBizStatus(enterpriseId, Const.ENTERPRISE_BUSINESS_STATUS_NORMAL);
		return rsMap;
	}
	
}
