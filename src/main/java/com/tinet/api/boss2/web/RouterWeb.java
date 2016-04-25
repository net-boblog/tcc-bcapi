package com.tinet.api.boss2.web;

import java.util.List;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Router;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.RouterListResponse;
/**
 * 路由组详细规则配置业务接口
 * <p>
 *  FileName： RouterWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.RouterWebImp
 */
@WebService 
public interface RouterWeb {
	
	/**
	 * 获取某企业路由组的详细规则配置
	 * @param ccicId 平台id
	 * @param routersetId 路由组id
	 * @param creq 数据签名
	 * @return
	 */
	public RouterListResponse getSetRouter(String ccicId, Integer routersetId, CommonRequest creq); 
	
	/**
	 * 获取某平台所有企业路由组的所有详细规则配置
	 * @param ccicId 平台id
	 * @param creq 数据签名
	 * @return
	 */
	public RouterListResponse getAllRouter(String ccicId, CommonRequest creq); 
	
	/**
	 * 某企业路由组表新增一组详细规则配置
	 * @param ccicId 平台id
	 * @param routerList router对象集合
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse batchSaveRouter(String ccicId, List<Router> routerList, CommonRequest creq);
	
	/**
	 * 批量更新路由详细规则配置
	 * @param ccicId  平台id
	 * @param routerList 路有详细规则集合
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse batchUpdateRouter(String ccicId, List<Router> routerList, CommonRequest creq);
	
	/**
	 * 批量删除路由组详细规则配置
	 * @param ccicId 平台id
	 * @param ids 路由详细规则id数组
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse batchDeleteRouter(String ccicId, List<Integer> ids, CommonRequest creq);
	
	/**
	 * 复制一平台所有企业路由及基本路由到另一平台
	 * @param fromCcicId
	 * @param toCcicId
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse copy(String fromCcicId, String toCcicId, CommonRequest creq);
} 
