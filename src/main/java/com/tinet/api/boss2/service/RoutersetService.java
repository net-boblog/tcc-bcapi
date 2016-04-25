package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.RoutersetDao;
import com.tinet.api.boss2.model.Routerset;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 平台企业可配置路由组信息业务逻辑处理层
 * <p>
 *  FileName： RoutersetService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("routersetService")
public class RoutersetService {
	@Autowired
	private RoutersetDao routersetDao;
	
	/**
	 * 获取平台上所有的企业路由组信息。
	 * @param ccicId 平台id。
	 * @return
	 */
	public List<Routerset> getAllRouterset(String ccicId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		// 获取所有企业路由组
		List<Routerset> routersetList = routersetDao.getAllRouterset();
		return routersetList;
	}
	
	/**
	 * 平台上新增一条企业路由组表数据
	 * @param ccicId 平台id
	 * @param name 路由组名称
	 * @param description 路由组描述
	 */
	public void saveRouterset(String ccicId, String name, String description) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		routersetDao.insert(null, name, description);
	}
	
	/**
	 * 更新平台上一条企业路由组表数据
	 * @param ccicId 平台id
	 * @param id 路由组id
	 * @param name 路由组名称
	 * @param description 路由组描述
	 */
	public void updateRouterset(String ccicId, Integer id, String name, String description) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		routersetDao.update(id, name, description);
	}
	
	/**
	 * 删除平台上一条企业路由组表数据
	 * @param ccicId 平台id
	 * @param id 路由组id
	 */
	public String deleteRouterset(String ccicId, Integer id) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		return routersetDao.delete(id);
	}
	
	/**
	 * 获取路由组名称
	 * @param ccicId 平台id
	 * @param routersetId 路由组id
	 * @return
	 */
	public String getRoutersetName(String ccicId, Integer routersetId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		return routersetDao.getRoutersetName(routersetId);
	}
	
}
