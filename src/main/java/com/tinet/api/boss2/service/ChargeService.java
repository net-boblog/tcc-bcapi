package com.tinet.api.boss2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.BusinessDao;
import com.tinet.api.boss2.dao.LogChargeDao;
import com.tinet.api.boss2.model.LogCharge;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 企业充值业务逻辑处理层
 * <p>
 *  FileName： ChargeService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("chargeService")
public class ChargeService {
	@Autowired
	private LogChargeDao logChargeDao;
	@Autowired
	private BusinessDao businessDao;
	
	/**
	 * 给客户充值，冲正
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param logCharge 充值日志对象
	 * @return
	 */
	public Integer charge(String ccicId, Integer enterpriseId, LogCharge logCharge) {
		// 切换数据源
		DatabaseContextHolder.setDBType(ccicId);
		
		// 执行充值
		logChargeDao.charge(enterpriseId, logCharge);
		
		// 查询当前企业状态
		Integer bizStatus = businessDao.getEntBizStatus(enterpriseId);
		
		return bizStatus;
	}
}
