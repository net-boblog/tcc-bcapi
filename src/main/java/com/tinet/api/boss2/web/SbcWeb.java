package com.tinet.api.boss2.web;

import java.util.List;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Sbc;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.SbcListResponse;
/**
 * sbc业务接口
 * <p>
 *  FileName： SbcWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.SbcWebImp
 */
@WebService 
public interface SbcWeb {
	
	/**
	 * 查询sbc
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public SbcListResponse getAllSbc(String ccicId, CommonRequest creq);
	
	/**
	 * 新增
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param host ip地址
	 * @param port 端口 	
	 * @param password 密码
	 * @param timeout 超时时间
	 * @param areaCode 默认区号
	 * @param callLimit 呼叫限制
	 * @param active 状态
	 * @param mac mac地址
	 * @param creq
	 * @return
	 */
	public CommonResponse saveSbc(String ccicId, String host, String port, String password, 
			String timeout, String areaCode, String callLimit, String active, String mac, CommonRequest creq);
	
	/**
	 * 批量更新：包括数据和密码
	 * @author zhancy
	 * @param ccicId
	 * @param sbcList
	 * @param creq
	 * @return
	 */
	public CommonResponse bathcUpdateSbc(String ccicId, List<Sbc> sbcList, CommonRequest creq);
	
	/**
	 * 批量删除
	 * @author zhancy
	 * @param ccicId
	 * @param ids
	 * @param indexs
	 * @param creq
	 * @return
	 */
	public CommonResponse batchDeleteSbc(String ccicId, List<Integer> ids, CommonRequest creq);
} 
