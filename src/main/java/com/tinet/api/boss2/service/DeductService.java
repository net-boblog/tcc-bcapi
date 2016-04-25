package com.tinet.api.boss2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.BusinessDao;
import com.tinet.api.boss2.dao.LogDeductionDao;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 客户扣费业务逻辑处理层
 * <p>
 *  FileName： DeductService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("deductService")
public class DeductService {
	@Autowired
	private LogDeductionDao logDeductionDao;
	@Autowired
	private BusinessDao businessDao;
	
	/**
	 * 对客户某费用手工扣费
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param logDeduction 扣费日志对象
	 * @return 返回扣费后客户业务状态
	 */
	public Integer deduct(String ccicId, String deductMonth, LogDeduction logDeduction) {
		// 切换数据源
		DatabaseContextHolder.setDBType(ccicId);
		
		// 执行扣费，修正月报数据
		logDeductionDao.deductHandFee(deductMonth, logDeduction);
		
		// 查询当前企业状态
		Integer bizStatus = businessDao.getEntBizStatus(logDeduction.getEnterpriseId());
		
		return bizStatus;
	}
}
