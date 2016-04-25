package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.GatewayDao;
import com.tinet.api.boss2.dao.RouterDao;
import com.tinet.api.boss2.model.Gateway;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 平台网关设备管理业务逻辑处理层
 * <p>
 *  FileName： GatewayService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("gatewayService")
public class GatewayService {
	@Autowired
	private GatewayDao gatewayDao;
	@Autowired
	private RouterDao routerDao;
	
	/**
	 * 获取平台上所有网关设备信息。
	 * @param ccicId 平台id。
	 * @return
	 */
	public List<Gateway> getAllGateway(String ccicId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		// 获取所有企业路由组
		List<Gateway> gatewayList = gatewayDao.getAllGateway();
		return gatewayList;
	}
	
	/**
	 * 平台上新增一条网关设备配置
	 * @param ccicId 平台id
	 * @param gateway 网关设备配置对象
	 */
	public void saveGateway(String ccicId, Gateway gateway) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		gatewayDao.insert(gateway);
	}
	
	/**
	 * 更新平台上一条网关设备配置
	 * @param ccicId 平台id
	 * @param gateway 网关设备配置对象
	 */
	public String updateGateway(String ccicId, Gateway gateway) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		gatewayDao.update(gateway);
		// 查询该网关下所有路由
		return routerDao.getDifRoutersetIds(gateway.getId());
	}
	
	/**
	 * 删除平台上一条网关设备配置
	 * @param ccicId 平台id
	 * @param gatewayId 网关设备配置id
	 */
	public String deleteGateway(String ccicId, Integer gatewayId) {
		
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		String rsInfo = gatewayDao.delete(gatewayId);
		if (rsInfo!=null && !"".equals(rsInfo)) {
			return rsInfo;
		} 
		return null;
	}
	
}
