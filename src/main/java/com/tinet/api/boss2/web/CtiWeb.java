package com.tinet.api.boss2.web;

import java.util.List;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Cti;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.CtiListResponse;
/**
 * cti接口
 * <p>
 *  FileName： CtiWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.CtiWebImp
 */
@WebService 
public interface CtiWeb {
	
	/**
	 * 获取所有cti
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public CtiListResponse getAllCti(String ccicId, CommonRequest creq);
	
	/**
	 * 获取所有激活状态cti
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public CtiListResponse getAll(String ccicId, CommonRequest creq);
	
	/**
	 * 新增cti
	 * @param ccicId 平台ID
	 * @param cti 对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveCti(String ccicId, Cti cti,  CommonRequest creq);
	
	/**
	 * 更新
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param cti 对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateCti(String ccicId, Cti cti,  CommonRequest creq);
	
	/**
	 * 批量删除
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param ids id集合
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse batchDelete(String ccicId, List<Integer> ids,  CommonRequest creq);
} 
