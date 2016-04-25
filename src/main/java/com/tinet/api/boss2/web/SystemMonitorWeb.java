package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.SystemMonitorResponse;
/**
 * 系统监控接口
 * <p>
 *  FileName： SystemMonitorWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.SystemMonitorWebImp
 */
@WebService 
public interface SystemMonitorWeb {
	
	/**
	 * 获取系统监控
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 */
	public SystemMonitorResponse getMonitor(String ccicId, CommonRequest creq);
} 
