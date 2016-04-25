package com.tinet.api.boss2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.EnterpriseVoiceDao;
import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.VoiceAudit;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 企业语音文件业务逻辑处理层
 * <p>
 *  FileName： EnterpriseVoiceService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */

@Service("enterpriseVoiceService")
public class EnterpriseVoiceService {
	@Autowired
	private EnterpriseVoiceDao enterpriseVoiceDao;

	/**
	 * 获取分平台所有非注销企业的企业语音，从audit_voice视图中读取
	 * @param ccicList 要查询的平台信息
	 * @param masterHotline 主热线号码，支持号码前缀匹配查询
	 * @param auditStatus 语音文件审核状态
	 * @return
	 */
	public List<VoiceAudit> getAllAuditVoice(List<Ccic> ccicList, String masterHotline, String auditStatus) {
		List<VoiceAudit> voiceList = new ArrayList<VoiceAudit>();
		if (ccicList!=null && ccicList.size() > 0) {
			for (Ccic ccic : ccicList) {
				// 切换数据源id
				DatabaseContextHolder.setDBType(ccic.getId().toString());
				List<VoiceAudit> voiceListSub = enterpriseVoiceDao.getAllAuditVoice(ccic, masterHotline, auditStatus);
				if (voiceListSub!=null && voiceListSub.size() > 0) {
					voiceList.addAll(voiceListSub);
				}
			}
		} 
		return voiceList;
	}
	
	/**
	 * 修改某一企业语音文件的审核状态
	 * @param ccicId 平台id
	 * @param voiceId 语音文件id
	 * @param auditStatus 状态
	 * @param auditComment 审核不通过时的理由
	 * @return
	 */
	public void updateStatus(String ccicId, Integer voiceId, Integer auditStatus, String auditComment) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		enterpriseVoiceDao.updateEntVoiceStatus(voiceId, auditStatus, auditComment);
	}
}
