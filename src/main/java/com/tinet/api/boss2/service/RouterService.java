package com.tinet.api.boss2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.GatewayDao;
import com.tinet.api.boss2.dao.RouterDao;
import com.tinet.api.boss2.dao.RoutersetDao;
import com.tinet.api.boss2.model.Gateway;
import com.tinet.api.boss2.model.Router;
import com.tinet.api.boss2.model.Routerset;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 平台企业路由组详细规则配置信息业务逻辑处理层
 * <p>
 *  FileName： RouterService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("routerService")
public class RouterService {
	@Autowired
	private RouterDao routerDao;
	@Autowired
	private RoutersetDao routersetDao;
	@Autowired
	private GatewayDao gatewayDao;
	
	/**
	 * 获取某企业路由组的详细规则配置
	 * @param ccicId 平台id
	 * @param routersetId 路由组ID
	 * @return
	 */
	public List<Router> getSetRouter(String ccicId, Integer routersetId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		// 获取某一企业路由组的所有号码详细规则配置
		List<Router> routerList = routerDao.getAllRouter(routersetId);
		return routerList;
	}
	
	/**
	 * 获取某平台所有企业路由组的所有详细规则配置
	 * @param ccicId 平台id
	 * @param routersetId 路由组ID
	 * @return
	 */
	public List<Router> getAllRouter(String ccicId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		// 获取平台所有企业路由组的所有号码详细规则配置
		List<Router> routerList = routerDao.getAllRouter();
		return routerList;
	}
	
	/**
	 * 某企业路由组表新增一组详细规则配置
	 * @param ccicId 平台id
	 * @param routerList router对象集合
	 */
	public void batchSaveRouter(String ccicId, List<Router> routerList) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		routerDao.batchInsert(routerList);
	}
	
	/**
	 * 批量更新路由组详细规则配置
	 * @param ccicId 平台id
	 * @param routerList 路由详细规则集合
	 */
	public void batchUpdateRouter(String ccicId, List<Router> routerList) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		routerDao.batchUpdate(routerList);
	}
	
	/**
	 * 批量删除路由组详细规则配置
	 * @param ccicId 平台id
	 * @param ids 路由详细规则id数组
	 */
	public void batchDeleteRouter(String ccicId, List<Integer> ids) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		routerDao.batchDelete(ids);
	}
	
	/**
	 * 复制一平台所有企业路由和基本路由到另一平台
	 * @param toCcicId
	 * @param fromCcicId
	 * @return
	 */
	public String batchCopy(String fromCcicId, String toCcicId) {
		// 切换数据源为fromCcicId，读取所有被拷贝信息
		DatabaseContextHolder.setDBType(fromCcicId); 
		List<Gateway> gatewayListFrom = gatewayDao.getAllGateway();
		List<Routerset> routersetListFrom = routersetDao.getAllRouterset();
		List<Router> routerListFrom = routerDao.getAllRouter();
		
		// 对toCcicId做数据检查，并写库
		DatabaseContextHolder.setDBType(toCcicId); 
		List<Gateway> gatewayListTo = gatewayDao.getAllGateway();
		List<Routerset> routersetListTo = routersetDao.getAllRouterset();
		
		// 源与目标是否冲突校验
		String msg = this.checkGatewayAndRouterset(gatewayListFrom, gatewayListTo, routersetListFrom, routersetListTo);
		if (msg!=null && !"".equals(msg)) {
			return msg;
		}
		
		// 构造gateway，routerset，router新增集合
		List<Gateway> gatewayListAdd = new ArrayList<Gateway>();
		List<Routerset> routersetListAdd = new ArrayList<Routerset>();
		List<Router> routerListAdd = new ArrayList<Router>();
		this.createObject(gatewayListFrom, routersetListFrom, routerListFrom, gatewayListAdd, routersetListAdd, routerListAdd);
		
		// 执行新增
		routerDao.batchSave(gatewayListAdd, routersetListAdd, routerListAdd);
		
		return msg;
	}
	
	/**
	 * 网关和路由组唯一性检查
	 * @param gatewayListFrom
	 * @param gatewayListTo
	 * @param routersetListFrom
	 * @param routersetListTo
	 * @return
	 */
	private String checkGatewayAndRouterset(List<Gateway> gatewayListFrom, List<Gateway> gatewayListTo, 
			List<Routerset> routersetListFrom, List<Routerset> routersetListTo) {
		String GWName = "";
		if (gatewayListFrom!=null && gatewayListFrom.size()>0) {
			for (Gateway gatewayFrom : gatewayListFrom) {
				for (Gateway gatewayTo : gatewayListTo) {
					if (gatewayTo.getName().equals(gatewayFrom.getName())) {
						GWName += gatewayTo.getName() + "、";
					}
				}
			}
		}
		
		String RSName = "";
		if (routersetListFrom!=null && routersetListFrom.size()>0) {
			for (Routerset routerFrom : routersetListFrom) {
				for (Routerset routerTo : routersetListTo) {
					if (routerFrom.getName().equals(routerTo.getName())) {
						RSName += routerFrom.getName() + "、";
					}
				}
			}
		}
		
		String msg = "";
		if(GWName != null && GWName.length() > 0){
			GWName = GWName.substring(0, GWName.length() - 1);
			msg += "网关名称 "+GWName+" 已存在！。。。";
		}
		if(RSName != null && RSName.length() > 0){
			RSName = RSName.substring(0, RSName.length() - 1);
			msg += "路由名称 "+RSName+" 已存在!";
		}
		
		return msg;
	}
	
	/**
	 * 构造gateway，routerset，router新增集合
	 * @param gatewayListFrom
	 * @param routersetListFrom
	 * @param routerListFrom
	 * @param gatewayList
	 * @param routersetList
	 * @param routerList
	 */
	private void createObject(List<Gateway> gatewayListFrom, List<Routerset> routersetListFrom, List<Router> routerListFrom,
			List<Gateway> gatewayListAdd, List<Routerset> routersetListAdd, List<Router> routerListAdd) {
		// 构造新增网关集合
		for (Gateway fromGateway : gatewayListFrom) {
			Gateway toGateway = new Gateway();
			toGateway.setId(fromGateway.getId());
			toGateway.setPrefix(fromGateway.getPrefix());
			toGateway.setPostfix(fromGateway.getPostfix());
			toGateway.setIpAddr(fromGateway.getIpAddr());
			toGateway.setPort(fromGateway.getPort());
			toGateway.setAreaCode(fromGateway.getAreaCode());
			toGateway.setName(fromGateway.getName());
			toGateway.setCreateTime(new Date());
			toGateway.setCallLimit(fromGateway.getCallLimit());
			toGateway.setDisallow(fromGateway.getDisallow());
			toGateway.setAllow(fromGateway.getAllow());
			toGateway.setDtmf(fromGateway.getDtmf());
			toGateway.setRegister(fromGateway.getRegister());
			gatewayListAdd.add(toGateway);
		}
		
		// 构造新增路由组集合
		for (Routerset fromRouterset : routersetListFrom) {
			Routerset toRouterset = new Routerset();
			toRouterset.setId(fromRouterset.getId());
			toRouterset.setName(fromRouterset.getName());
			toRouterset.setDescription(fromRouterset.getDescription());
			toRouterset.setDefaultRouter(fromRouterset.getDefaultRouter());
			routersetListAdd.add(toRouterset);
		}
		
		// 构造新增路由规则集合
		for (Router fromRouter : routerListFrom) {
			Router toRouter = new Router();
			toRouter.setId(fromRouter.getId());
			toRouter.setDescription(fromRouter.getDescription());
			toRouter.setPrefix(fromRouter.getPrefix());
			toRouter.setPriority(fromRouter.getPriority());
			toRouter.setGatewayId(fromRouter.getGatewayId());
			toRouter.setRoutersetId(fromRouter.getRoutersetId());
			routerListAdd.add(toRouter);
		}
		
		// 给gateway和routerset的id重新赋值
		int gwId = -1;
		for (Gateway gatewayAdd : gatewayListAdd) {
			gwId = gatewayDao.getNextSeq();
			Integer oldGatewayId = gatewayAdd.getId();
			gatewayAdd.setId(gwId);
			for (Router routerAdd : routerListAdd) {
				if(routerAdd.getGatewayId().intValue() == oldGatewayId.intValue()){
					routerAdd.setGatewayId(gwId);
				}
			}
		}
		
		int rsId = -1;
		for (Routerset routersetAdd : routersetListAdd) {
			rsId = routersetDao.getNextSeq();
			Integer oldRoutersetId = routersetAdd.getId();
			routersetAdd.setId(rsId);
			for (Router routerAdd : routerListAdd) {
				if(routerAdd.getRoutersetId().intValue() == oldRoutersetId.intValue()){
					routerAdd.setRoutersetId(rsId);
				}
			}
		}
	}
	
}
