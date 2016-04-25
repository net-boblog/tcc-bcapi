package com.tinet.api.boss2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.tinet.api.boss2.model.SystemMonitor;

/**
 * <p>
 *  FileName： SystemMonitorDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("systemMonitorDao")
public class SystemMonitorDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 获取监控
	 * @return
	 */
	public SystemMonitor getMonitor(){
		String sqlBusiness = "select count(*) numB from business";
		String sqlQueue = "select count(*) numQ from queue";
		String sqlClient = "select count(*) numC from client";
		
		SystemMonitor systemMonitor = new SystemMonitor();
		
		SqlRowSet rsBusiness = jdbcTemplate.queryForRowSet(sqlBusiness);
		if(rsBusiness.next()){
			systemMonitor.setEnterpriseNum(rsBusiness.getInt("numB"));
		}
		
		SqlRowSet rsQueue = jdbcTemplate.queryForRowSet(sqlQueue);
		if(rsQueue.next()){
			systemMonitor.setQueueNum(rsQueue.getInt("numQ"));
		}
		
		SqlRowSet rsClient = jdbcTemplate.queryForRowSet(sqlClient);
		if(rsClient.next()){
			systemMonitor.setClientNum(rsClient.getInt("numC"));
		}
		systemMonitor.setCallTogetherNum(0);
		return systemMonitor;
	}
}
