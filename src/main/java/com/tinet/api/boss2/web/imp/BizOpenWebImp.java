package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.EnterpriseTemp;
import com.tinet.api.boss2.model.LogCharge;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.service.BizOpenService;
import com.tinet.api.boss2.web.BizOpenWeb;

/**
 * 客户开户业务接口实现
 * <p>
 *  FileName：BizOpenWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.BizOpenWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.BizOpenWeb")
public class BizOpenWebImp implements BizOpenWeb {

	@Autowired
	private BizOpenService bizOpenService;
	
	/**
	 * 开户开通业务客户账户，企业配置等信息初始化。
	 * @author louxue
	 * @param enterpriseTemp 开户所需客户基本信息，业务信息，企业配置
	 * @param logChargeList 充值日志
	 * @param logDeductionList 扣费日志
	 * @param account 企业账户信息
	 * @return
	 */
	@Override
	public CommonResponse openBiz(EnterpriseTemp enterpriseTemp, List<LogCharge> logChargeList, 
			List<LogDeduction> logDeductionList, Account account, CommonRequest creq) {
		CommonResponse rspon = new CommonResponse();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			bizOpenService.executeOpenBiz(enterpriseTemp, logChargeList, logDeductionList, account);
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


}
