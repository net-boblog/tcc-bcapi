package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.PublicVoiceDao;
import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.PublicVoice;
import com.tinet.api.boss2.model.SystemSetting;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 公共语音库业务逻辑处理层
 * <p>
 *  FileName： PublicVoiceService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("publicVoiceService")
public class PublicVoiceService {
	
	@Autowired
	private PublicVoiceDao publicVoiceDao;
	
	/**
	 * 通过id查找语音
	 */
	public PublicVoice getPublicVoiceById(String ccicId, String id){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		return publicVoiceDao.getPublicVoiceById(id);
	}
	
	/**
	 * 查询某平台下的所有公共语音文件列表
	 * @param ccic 平台对象
	 * @param systemSetting 系统设置对象
	 * @param limit 分页参数limit
	 * @param start 分页参数offset
	 * @return
	 */
	public List<PublicVoice> getPublicVoiceByCcicId(Ccic ccic, SystemSetting systemSetting, String limit, String start){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccic.getId().toString());
		return publicVoiceDao.getPublicVoiceByCcicId(ccic, systemSetting, limit, start);
	}
	
	
	/**
	 * 获取符合条件的语音文件总记录数
	 * @param ccicId 平台ID
	 * @return
	 */
	public Integer getCount(String ccicId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		return publicVoiceDao.getCount();
	}
	
	/**
	 * 更新
	 * @param ccicId 分平台ID
	 * @param voiceName 语音名称
	 * @param description 语音描述
	 * @param id
	 */
	public void updateVoice(String ccicId, String voiceName, String description, String id){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		publicVoiceDao.update(voiceName, description, id);
	}
	
	/**
	 * 验证是否在使用
	 * @param path
	 * @return true为没在使用。
	 */
	public boolean checkReplace(String ccicId, String path){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		return publicVoiceDao.checkRepalce(path);
	}
}
