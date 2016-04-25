package com.tinet.api.boss2.web;

import java.util.List;

import javax.jws.WebService;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.Combo;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.ComboListResponse;
import com.tinet.api.boss2.response.enity.CommonResponse;
/**
 * 外呼套餐接口
 * <p>
 *  FileName： ComboWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.ComboWebImp
 */
@WebService 
public interface ComboWeb {
	
	/**
	 * 查询所有外呼套餐信息
	 * @author zcy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	public ComboListResponse getAllCombo(String ccicId, CommonRequest creq);
	
	/**
	 * 同步外呼套餐信息
	 * @param ccicList
	 * @param comboListBoss
	 * @param creq
	 * @return
	 */
	public CommonResponse syncCombo2ccic(List<Ccic> ccicList, List<Combo> comboListBoss, String maxId, CommonRequest creq);
} 
