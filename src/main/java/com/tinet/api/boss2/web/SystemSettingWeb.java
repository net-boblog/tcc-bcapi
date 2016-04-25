package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.SystemSettingListResponse;
import com.tinet.api.boss2.response.enity.SystemSettingResponse;
/**
 * 平台设置接口
 * <p>
 *  FileName： SystemSettingWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.SystemSettingWebImp
 */
@WebService 
public interface SystemSettingWeb {
	
	/**
	 * 查询平台设置信息
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public SystemSettingListResponse getAllSysSetting(String ccicId, CommonRequest creq);
	
	/**
	 * 修改分平台的系统设置
	 * @param ccic 分平台
	 * @param id 设置的唯一标识
	 * @param value 设置的值
	 * @param property 设置的单位或密码
	 * @param creq 数据签名
	 */
	public CommonResponse updateSysSetting(String ccicId, Integer id, String value ,String property, CommonRequest creq);
	
	/**
	 * 修改分平台的系统设置
	 * @param ccic 分平台
	 * @param name
	 * @param value 设置的值
	 * @param creq 数据签名
	 */
	public CommonResponse updateSysSettingByName(String ccicId, String name, String value, CommonRequest creq);
	
	/**
	 * 获取分平台接口用户名及密码
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 */
	public SystemSettingResponse getUsrPwd(String ccicId, CommonRequest creq);
} 
