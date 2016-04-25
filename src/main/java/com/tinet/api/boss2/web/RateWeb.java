package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Rate;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.RateListResponse;
/**
 * 费率套餐接口
 * <p>
 *  FileName： RateWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.RateWebImp
 */
@WebService 
public interface RateWeb {
	
	/**
	 * 取出一个费率套餐组的所有详细费率设置
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public RateListResponse getAllRate(String ccicId, Integer ratesetId, CommonRequest creq);
	
	/**
	 * 向分平台添加一条费率套餐设置信息
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param rate 要更新的rate对象。
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveRate(String ccicId, Rate rate, CommonRequest creq);
	
	/**
	 * 更新一个费率套餐组的详细费率计费规则。
	 * @param ccicId 平台ID
	 * @param rate 要更新的rate对象。
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateRate(String ccicId, Rate rate, CommonRequest creq);
	
	/**
	 * 删除一个费率套餐组的详细费率计费规则。
	 * @param ccicId 平台ID
	 * @param rateId
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse deleteRate(String ccicId, Integer rateId, CommonRequest creq);
	
	/**
	 * 复制一平台所有费率套餐组和费率套餐到另一平台
	 * @param fromCcicId
	 * @param toCcicId
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse copy(String fromCcicId, String toCcicId, CommonRequest creq);
	
	
	
} 
