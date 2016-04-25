package com.tinet.api.boss2.web;


import javax.jws.WebService;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.SystemSetting;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.IntegerDataResponse;
import com.tinet.api.boss2.response.enity.PublicVoiceListResponse;
import com.tinet.api.boss2.response.enity.PublicVoiceResponse;
/**
 * 公共语音业务接口
 * <p>
 *  FileName： PublicVoiceWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.PublicVoiceWebImp
 */
@WebService 
public interface PublicVoiceWeb {
	
	/**
	 * 通过id查找语音
	 * @param ccicId 平台ID
	 * @param id 对象id
	 * @param creq 数据签名
	 * @return
	 */
	public PublicVoiceResponse getPublicVoiceById(String ccicId, String id, CommonRequest creq);
	
	/**
	 * 查询某平台下的所有公共语音文件列表
	 * @param ccic 平台对象
	 * @param systemSetting 系统设置对象
	 * @param limit 分页参数limit
	 * @param start 分页参数offset
	 * @param creq 数据签名
	 * @return
	 */
	public PublicVoiceListResponse getPublicVoiceByCcicId(Ccic ccic, SystemSetting systemSetting, String limit, String start, CommonRequest creq);
	
	/**
	 * 获取符合条件的语音文件总记录数
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 */
	public IntegerDataResponse getCount(String ccicId, CommonRequest creq);
	
	/**
	 * 更新
	 * @param ccicId 分平台ID
	 * @param voiceName 语音名称
	 * @param description 语音描述
	 * @param id
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateVoice(String ccicId, String voiceName, String description, String id, CommonRequest creq);
	
	/**
	 * 验证是否在使用
	 * @param path
	 * @param creq 数据签名
	 * @return true为没在使用。
	 */
	public CommonResponse checkReplace(String ccicId, String path, CommonRequest creq);
} 
