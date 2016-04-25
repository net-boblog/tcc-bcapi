package com.tinet.api.boss2.web.imp;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.LogCharge;
import com.tinet.api.boss2.response.enity.StatusResponse;
import com.tinet.api.boss2.service.ChargeService;
import com.tinet.api.boss2.web.ChargeWeb;

/**
 * 客户充值业务接口实现
 * <p>
 *  FileName： ChargeWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.ChargeWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.ChargeWeb")
public class ChargeWebImp implements ChargeWeb {
	@Autowired
	private ChargeService chargeService;

	/**
	 * 给企业账户充值，冲正
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId	企业ID
	 * @param logCharge 充值对象
	 * @param creq 数据签名
	 * @return 返回企业当前业务状态
	 */
	@Override
	public StatusResponse charge(String ccicId, Integer enterpriseId, LogCharge logCharge, CommonRequest creq) {
		StatusResponse rspon = new StatusResponse();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			Integer bizStatus = chargeService.charge(ccicId, enterpriseId, logCharge);
			rspon.setStatus(bizStatus);
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
