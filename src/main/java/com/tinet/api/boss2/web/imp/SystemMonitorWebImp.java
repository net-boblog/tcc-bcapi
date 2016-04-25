package com.tinet.api.boss2.web.imp;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.SystemMonitorResponse;
import com.tinet.api.boss2.service.SystemMonitorService;
import com.tinet.api.boss2.web.SystemMonitorWeb;

/**
 * 系统监控业务接口实现
 * <p>
 *  FileName： SystemMonitorWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.SystemMonitorWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.SystemMonitorWeb")
public class SystemMonitorWebImp implements SystemMonitorWeb {
	
	@Autowired
	SystemMonitorService systemMonitorService;
	
	/**
	 * 获取系统监控
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 */
	@Override
	public SystemMonitorResponse getMonitor(String ccicId, CommonRequest creq) {
		SystemMonitorResponse systemMonitorResponse = new SystemMonitorResponse();
		systemMonitorResponse.setReturnCode("0");
		systemMonitorResponse.setMsg("success");
		try {
			systemMonitorResponse.setSystemMonitor(systemMonitorService.getMonitor(ccicId));
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			systemMonitorResponse.setReturnCode("101");
			systemMonitorResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			systemMonitorResponse.setReturnCode("999");
			systemMonitorResponse.setMsg("其他异常，请联系管理员！");
		}
		return systemMonitorResponse;
	}
	
	
}
