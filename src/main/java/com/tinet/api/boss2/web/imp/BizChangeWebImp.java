package com.tinet.api.boss2.web.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.BizChangeRequest;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseResponse;
import com.tinet.api.boss2.response.enity.LogDeductionListResponse;
import com.tinet.api.boss2.service.AccountService;
import com.tinet.api.boss2.service.BizChangeService;
import com.tinet.api.boss2.service.EnterpriseSettingService;
import com.tinet.api.boss2.web.BizChangeWeb;
import com.tinet.common.inc.Const;
import com.tinet.common.util.StringUtil;

/**
 * 客户业务变更接口实现
 * <p>
 *  FileName：BizChangeWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.BizChangeWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.BizChangeWeb")
public class BizChangeWebImp implements BizChangeWeb {
	@Autowired
	private BizChangeService bizChangeService;
	@Autowired
	private EnterpriseSettingService enterpriseSettingService;
	@Autowired
	private AccountService accountService;
	
	/**
	 * 更新企业基本信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterprise 企业信息对象
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse updateEntBaseInfo(String ccicId, Enterprise enterprise, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			bizChangeService.saveEntBaseInfo(ccicId, enterprise);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			crpon.setReturnCode("101");
			crpon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			crpon.setReturnCode("999");
			crpon.setMsg("其他异常，请联系管理员！");
		}
		return crpon;
	}

	/**
	 * 更新企业业务信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param bizChangeReqObj 存储企业业务信息变更内容的对象
	 * @param creq 数据签名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LogDeductionListResponse updateEntBizInfo(String ccicId, BizChangeRequest bizChangeReqObj, CommonRequest creq) {
		LogDeductionListResponse rspon = new LogDeductionListResponse();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			
			Map<String, Object> rtnMap = bizChangeService.saveEntBizInfo(ccicId, bizChangeReqObj);
			if (rtnMap.get("error") != null) {
				rspon.setReturnCode("301");
				rspon.setMsg(rtnMap.get("error").toString());
			} else {
				rspon.setLogDeductionList((List<LogDeduction>)rtnMap.get("logDeductionList"));
				rspon.setStatus((Integer)rtnMap.get("bizStatus"));
			}
		
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			rspon.setReturnCode("101");
			rspon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			rspon.setReturnCode("999");
			rspon.setMsg("其他异常，请联系管理员！");
		}
		return rspon;
	}

	/**
	 * 更新企业配置，没有则新增，否则执行更新。
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param entSetting 企业设置对象
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse updateEntSetting(String ccicId, EnterpriseSetting entSetting, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			enterpriseSettingService.instOrUpdateEntSetting(ccicId, entSetting);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			crpon.setReturnCode("101");
			crpon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			crpon.setReturnCode("999");
			crpon.setMsg("其他异常，请联系管理员！");
		}
		return crpon;
	}
	
	/**
	 * 更新企业短信签名，若短信功能未开启，则不更新。
	 * @author louxue
	 * @param ccicId 平台ID  
	 * 变更类型：9-短信签名
	 * @param entSetting 企业设置对象
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse updateEntSmsSign(String ccicId, EnterpriseSetting entSetting, CommonRequest creq){
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			String rs = bizChangeService.updateEntSmsSign(ccicId, entSetting);
			if (!StringUtil.isEmpty(rs)) {
				crpon.setReturnCode("301");
				crpon.setMsg(rs);
			}
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			crpon.setReturnCode("101");
			crpon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			crpon.setReturnCode("999");
			crpon.setMsg("其他异常，请联系管理员！");
		}
		return crpon;
	}

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
	public LogDeductionListResponse executeClientChange4Crontab(String ccicId, Integer enterpriseId, String newValue, 
			boolean isDeduct, CommonRequest creq){
		LogDeductionListResponse rspon = new LogDeductionListResponse();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			Map<String, Object> rtnMap = bizChangeService.executeClientChange4Crontab(ccicId, enterpriseId, newValue, isDeduct);
			List<LogDeduction> logDeductions = new ArrayList<LogDeduction>();
			if (rtnMap.get("logDeduction") != null) {
				logDeductions.add((LogDeduction)rtnMap.get("logDeduction"));
			}
			rspon.setLogDeductionList(logDeductions);
			
			rspon.setStatus((Integer)rtnMap.get("bizStatus"));
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			rspon.setReturnCode("101");
			rspon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			rspon.setReturnCode("999");
			rspon.setMsg("其他异常，请联系管理员！");
		}
		return rspon;
	}
	
	/**
	 * 直销经理提交业务变更单,即时更新企业座席座席数
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param type  变更类型：1-座席数变更 
	 * @param newValue 变更为的值
	 *  type	  newValue					格式描述
	 * type=1	"0,0.000;5;0"	clinetWeb,rent;clientFree,clientTel	
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public LogDeductionListResponse executeClientChange(String ccicId, Integer enterpriseId, String newValue, CommonRequest creq) {
		LogDeductionListResponse rspon = new LogDeductionListResponse();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			Map<String, Object> rtnMap = bizChangeService.executeClientChange(ccicId, enterpriseId, newValue);
			if (rtnMap.get("error") != null) {
				rspon.setReturnCode("301");
				rspon.setMsg(rtnMap.get("error").toString());
			} 
			
			List<LogDeduction> logDeductions = new ArrayList<LogDeduction>();
			if (rtnMap.get("logDeduction") != null) {
				logDeductions.add((LogDeduction)rtnMap.get("logDeduction"));
			}
			rspon.setLogDeductionList(logDeductions);
			
			rspon.setStatus((Integer)rtnMap.get("bizStatus"));
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			rspon.setReturnCode("101");
			rspon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			rspon.setReturnCode("999");
			rspon.setMsg("其他异常，请联系管理员！");
		}
		return rspon;
	}
	
	/**
	 * 直销经理提交业务变更单,更新企业座席月租/号码最低消费/短信费率/客户呼入费率/外呼客户费率
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param type  变更类型： 4-座席月租  5-号码最低消费 6-客户呼入费率 7-外呼客户费率变更  8-短信费率  
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
	public CommonResponse executeEntRateChange(String ccicId, Integer enterpriseId, Integer type, String newValue, CommonRequest creq) {
		CommonResponse rpon = new CommonResponse();
		rpon.setReturnCode("0");
		rpon.setMsg("success");
		try {
			bizChangeService.executeEntRateChange(ccicId, enterpriseId, type, newValue);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			rpon.setReturnCode("101");
			rpon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			rpon.setReturnCode("999");
			rpon.setMsg("其他异常，请联系管理员！");
		}
		return rpon;
	}

	/**
	 * 修改企业业务状态：1) 停机-->注销	2) 正常/欠费-->停机	3) 欠费/停机-->正常
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param status 修改为的状态
	 * @param creq 数据签名
	 * @return 
	 */
	@Override
	public LogDeductionListResponse changeBizStatus(String ccicId, String enterpriseId, String status, CommonRequest creq) {
		LogDeductionListResponse rspon = new LogDeductionListResponse();
		List<LogDeduction> logDeductions = new ArrayList<LogDeduction>();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			// 返回结果
			Map<String, Object> rtnMap = bizChangeService.changeBizStatus(ccicId, enterpriseId, status);
			if (rtnMap != null) {
				if (rtnMap.get("error")!=null && !"".equals(rtnMap.get("error").toString())) {
					rspon.setReturnCode("302");
					rspon.setMsg(rtnMap.get("error").toString());
				}
				if (Integer.parseInt(status) == Const.ENTERPRISE_BUSINESS_STATUS_NORMAL) {
					if (rtnMap.get("logdeduction")!=null) {
						logDeductions.add((LogDeduction)rtnMap.get("logdeduction"));
						rspon.setLogDeductionList(logDeductions);
					}
				} else if (Integer.parseInt(status) == Const.ENTERPRISE_BUSINESS_STATUS_LOGOUT) {
					// 删除多个号码可能会有多条扣费日志，每个号码一条
					if (rtnMap.get("logdeductions")!=null) {
						logDeductions.addAll((List<LogDeduction>)rtnMap.get("logdeductions"));
						rspon.setLogDeductionList(logDeductions);
					}
				}
				
				rspon.setStatus((Integer)rtnMap.get("bizStatus"));
			}
			
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			rspon.setReturnCode("101");
			rspon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			rspon.setReturnCode("999");
			rspon.setMsg("其他异常，请联系管理员！");
		}
		return rspon;
	}


	/**
	 * 直销经理初始化测试账号，保留测试账户状态，有效期和上级。
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名对象
	 * @return 初始化了的企业信息对象，含后台管理员密码变更
	 */
	@Override
	public EnterpriseResponse initTestEnterpriseBiz(String ccicId, Integer enterpriseId, CommonRequest creq) {
		EnterpriseResponse entRresp = new EnterpriseResponse();
		entRresp.setReturnCode("0");
		entRresp.setMsg("success");
		try {
			Map<String, Object> rtnMap = bizChangeService.initTestEnterpriseBiz(ccicId, enterpriseId);
			entRresp.setEnterprise((Enterprise) rtnMap.get("enterprise"));
			if (StringUtil.isNotEmpty((String)rtnMap.get("error"))) {
				entRresp.setReturnCode("301");
				entRresp.setMsg(rtnMap.get("error").toString());
			}
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entRresp.setReturnCode("101");
			entRresp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entRresp.setReturnCode("999");
			entRresp.setMsg("其他异常，请联系管理员！");
		}
		return entRresp;
	}

}
