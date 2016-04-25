package com.tinet.api.boss2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.SystemMonitorDao;
import com.tinet.api.boss2.model.SystemMonitor;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 系统监控业务逻辑处理层
 * <p>
 *  FileName： SystemMonitorService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("systemMonitorService")
public class SystemMonitorService {
	
	@Autowired
	SystemMonitorDao systemMonitorDao;
	
	public SystemMonitor getMonitor(String ccicId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		return systemMonitorDao.getMonitor();
	}
}
