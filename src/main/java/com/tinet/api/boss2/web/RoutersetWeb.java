package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.RoutersetListResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
/**
 * 平台企业路由组业务接口
 * <p>
 *  FileName： RoutersetWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.RoutersetWebImp
 */
@WebService 
public interface RoutersetWeb {
	
	/**
	 * 获取平台上所有的企业路由组信息。
	 * @param ccicId 平台id
	 * @param creq 数据签名
	 * @return
	 */
	public RoutersetListResponse getAllRouterset(String ccicId, CommonRequest creq); 
	
	/**
	 * 新增一条路由组信息
	 * @param ccicId  平台id
	 * @param name 路由组名称
	 * @param description 路由组描述
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveRouterset(String ccicId, String name, String description, CommonRequest creq);
	
	/**
	 * 新增一条路由组信息
	 * @param ccicId 平台id
	 * @param id 路由组id
	 * @param name 路由组名称
	 * @param description 路由组描述
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateRouterset(String ccicId, Integer id, String name, String description, CommonRequest creq);
	
	/**
	 * 删除一条路由组信息
	 * @param ccicId 平台id
	 * @param routersetId 路由组id
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse deleteRouterset(String ccicId, Integer routersetId, CommonRequest creq);
	
	/**
	 * 获取路由组名称
	 * @param ccicId 平台id
	 * @param routersetId 路由组id
	 * @param creq 数据签名
	 * @return
	 */
	public StringDataResponse getRoutersetName(String ccicId, Integer routersetId, CommonRequest creq);
	
} 
