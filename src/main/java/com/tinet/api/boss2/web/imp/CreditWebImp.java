package com.tinet.api.boss2.web.imp;

import java.math.BigDecimal;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.StatusResponse;
import com.tinet.api.boss2.service.CreditService;
import com.tinet.api.boss2.web.CreditWeb;

/**
 * 客户信用管理接口实现
 * <p>
 *  FileName： CreditWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.CreditWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.CreditWeb")
public class CreditWebImp implements CreditWeb {
	
	@Autowired
	private CreditService creditService;

	/**
	 * 信用申请审批通过,更新企业信用额度
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param credit 信用额度
	 * @param creditDays 信用有效期,自然天数
	 * @param creditType 信用类型,0-临时信用额度 1-固定信用额度
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public StatusResponse setCredit(String ccicId, Integer enterpriseId, BigDecimal credit, Integer creditDays,
			Integer creditType, CommonRequest creq) {
		StatusResponse rspon = new StatusResponse();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			Integer bizStatus = creditService.setCredit(ccicId, enterpriseId, credit, creditDays, creditType);
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
