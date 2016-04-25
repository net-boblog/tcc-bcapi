package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.PublicVoice;
import com.tinet.api.boss2.model.SystemSetting;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.IntegerDataResponse;
import com.tinet.api.boss2.response.enity.PublicVoiceListResponse;
import com.tinet.api.boss2.response.enity.PublicVoiceResponse;
import com.tinet.api.boss2.service.PublicVoiceService;
import com.tinet.api.boss2.web.PublicVoiceWeb;

/**
 * 公共语音业务接口实现
 * <p>
 *  FileName： PublicVoiceWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.PublicVoiceWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.PublicVoiceWeb")
public class PublicVoiceWebImp implements PublicVoiceWeb {
	
	@Autowired
	PublicVoiceService publicVoiceService;
	
	/**
	 * 通过id查找语音
	 * @param ccicId 平台ID
	 * @param id 对象id
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public PublicVoiceResponse getPublicVoiceById(String ccicId, String id, CommonRequest creq) {
		PublicVoiceResponse publicVoiceResponse = new PublicVoiceResponse();
		publicVoiceResponse.setReturnCode("0");
		publicVoiceResponse.setMsg("success");
		try {
			PublicVoice publicVoice = publicVoiceService.getPublicVoiceById(ccicId, id);
			publicVoiceResponse.setPublicVoice(publicVoice);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			publicVoiceResponse.setReturnCode("101");
			publicVoiceResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			publicVoiceResponse.setReturnCode("999");
			publicVoiceResponse.setMsg("其他异常，请联系管理员！");
		}
		return publicVoiceResponse;
	}
	
	/**
	 * 查询某平台下的所有公共语音文件列表
	 * @param ccic 平台对象
	 * @param systemSetting 系统设置对象
	 * @param limit 分页参数limit
	 * @param start 分页参数offset
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public PublicVoiceListResponse getPublicVoiceByCcicId(Ccic ccic, SystemSetting systemSetting, String limit, String start, CommonRequest creq) {
		PublicVoiceListResponse publicVoiceListResponse = new PublicVoiceListResponse();
		publicVoiceListResponse.setReturnCode("0");
		publicVoiceListResponse.setMsg("success");
		try {
			List<PublicVoice> list = publicVoiceService.getPublicVoiceByCcicId(ccic, systemSetting, limit, start);
			publicVoiceListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			publicVoiceListResponse.setReturnCode("101");
			publicVoiceListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			publicVoiceListResponse.setReturnCode("999");
			publicVoiceListResponse.setMsg("其他异常，请联系管理员！");
		}
		return publicVoiceListResponse;
	}

	/**
	 * 获取符合条件的语音文件总记录数
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 */
	@Override
	public IntegerDataResponse getCount(String ccicId, CommonRequest creq) {
		IntegerDataResponse integerDataResponse = new IntegerDataResponse();
		integerDataResponse.setReturnCode("0");
		integerDataResponse.setMsg("success");
		try {
			int count = publicVoiceService.getCount(ccicId).intValue();
			integerDataResponse.setData(count);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			integerDataResponse.setReturnCode("101");
			integerDataResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			integerDataResponse.setReturnCode("999");
			integerDataResponse.setMsg("其他异常，请联系管理员！");
		}
		return integerDataResponse;
	}

	/**
	 * 更新
	 * @param ccicId 分平台ID
	 * @param voiceName 语音名称
	 * @param description 语音描述
	 * @param id
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse updateVoice(String ccicId, String voiceName, String description, String id, CommonRequest creq) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setReturnCode("0");
		commonResponse.setMsg("success");
		try {
			publicVoiceService.updateVoice(ccicId, voiceName, description, id);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			commonResponse.setReturnCode("101");
			commonResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setReturnCode("999");
			commonResponse.setMsg("其他异常，请联系管理员！");
		}
		return commonResponse;
	}
	
	/**
	 * 验证是否在使用
	 * @param path
	 * @param creq 数据签名
	 * @return true为没在使用。
	 */
	@Override
	public CommonResponse checkReplace(String ccicId, String path, CommonRequest creq) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setReturnCode("0");
		commonResponse.setMsg("success");
		try {
			boolean isNotUsed = publicVoiceService.checkReplace(ccicId, path);
			if(!isNotUsed){
				commonResponse.setReturnCode("201");
				commonResponse.setMsg("在使用，不可以操作。");
			}
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			commonResponse.setReturnCode("101");
			commonResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setReturnCode("999");
			commonResponse.setMsg("其他异常，请联系管理员！");
		}
		return commonResponse;
	}

}
