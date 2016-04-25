package com.tinet.api.boss2.web.imp;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.api.boss2.response.enity.StatusResponse;
import com.tinet.api.boss2.service.DeductService;
import com.tinet.api.boss2.web.DeductionWeb;

/**
 * 客户扣费业务接口实现
 * <p>
 *  FileName： DeductionWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.DeductionWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.DeductionWeb")
public class DeductionWebImp implements DeductionWeb {
	@Autowired
	private DeductService deductService;

	/**
	 * 对客户某费用手工扣费
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param deductMonth 扣费月份
	 * @param logDeduction 扣费日志对象
	 * @param creq 数据签名
	 * @return 返回扣费后客户业务状态
	 */
	@Override
	public StatusResponse deductFee(String ccicId, String deductMonth, LogDeduction logDeduction, CommonRequest creq) {
		StatusResponse rspon = new StatusResponse();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			Integer bizStatus = deductService.deduct(ccicId, deductMonth, logDeduction);
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
