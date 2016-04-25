package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Gateway;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.GatewayListResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
/**
 * 平台网关设备管理业务接口
 * <p>
 *  FileName： GatewayWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.GatewayWebImp
 */
@WebService 
public interface GatewayWeb {
	
	/**
	 * 获取平台上所有的网关设备配置信息。
	 * @param ccicId 平台id。
	 * @param creq 数据签名
	 * @return
	 */
	public GatewayListResponse getAllGateway(String ccicId, CommonRequest creq); 
	
	/**
	 * 平台上新增一条网关设备配置
	 * @param ccicId  平台id
	 * @param gateway 网关设备配置对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveGateway(String ccicId, Gateway gateway, CommonRequest creq);
	
	/**
	 * 更新平台上一条网关设备配置
	 * @param ccicId  平台id
	 * @param gateway 网关设备配置对象
	 * @param creq 数据签名
	 * @return
	 */
	public StringDataResponse updateGateway(String ccicId, Gateway gateway, CommonRequest creq);
	
	/**
	 * 删除平台上一条网关设备配置
	 * @param ccicId 平台id
	 * @param gatewayId 网关设备配置id
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse deleteGateway(String ccicId, Integer gatewayId, CommonRequest creq);
	
} 
