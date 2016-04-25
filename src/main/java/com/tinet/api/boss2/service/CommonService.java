package com.tinet.api.boss2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.CommonDao;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 公共业务逻辑处理层
 * <p>
 *  FileName： CommonService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("commonService")
public class CommonService {
	@Autowired
	private CommonDao commonDao;
	

	/**
	 * 从营帐本地获取事务id
	 * @return
	 */
	public Integer getTransactionId(){
		// 切换为本地数据源，获取事务ID
		DatabaseContextHolder.setDBType("0");
		return commonDao.getTransactionId();
	}

}
