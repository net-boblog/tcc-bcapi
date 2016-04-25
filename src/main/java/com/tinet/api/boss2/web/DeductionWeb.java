package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.api.boss2.response.enity.StatusResponse;
/**
 * 客户扣费业务接口
 * <p>
 *  FileName： DeductionWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.DeductionWebImp
 */
@WebService 
public interface DeductionWeb {
	
	/**
	 * 对客户某费用手工扣费
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param deductMonth 扣费月份
	 * @param logDeduction 扣费日志对象
	 * @param creq 数据签名
	 * @return 返回扣费后客户业务状态
	 */
	public StatusResponse deductFee(String ccicId, String deductMonth, LogDeduction logDeduction, CommonRequest creq);
} 
