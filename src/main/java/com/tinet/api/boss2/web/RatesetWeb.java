package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Rateset;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.RatesetListResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
/**
 * 费率套餐组接口
 * <p>
 *  FileName： RatesetWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.RatesetWebImp
 */
@WebService 
public interface RatesetWeb {
	
	/**
	 * 查询资费套餐
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public RatesetListResponse getAllRateset(String ccicId, CommonRequest creq);
	
	/**
	 * 保存资费套餐组
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param rateset 费率套餐组对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveRateset(String ccicId, Rateset rateset, CommonRequest creq);
	
	/**
	 * 修改费率套餐组
	 * @author 战春雨
	 * @param ccicId 平台ID
	 * @param rateset 费率套餐组对象。
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateRateset(String ccicId, Rateset rateset, CommonRequest creq);
	
	/**
	 * 删除一条费率套餐组
	 * @param ccicId 平台ID
	 * @param ratesetId 套餐组id
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse deleteRatesetset(String ccicId, Integer ratesetId, CommonRequest creq);
	

	/**
	 * 获取费率套餐组名称
	 * @param ccicId 平台ID
	 * @param ratesetId 路由组id
	 * @param creq 数据签名
	 * @return
	 */
	public StringDataResponse getRatesetName(String ccicId, Integer ratesetId);
} 
