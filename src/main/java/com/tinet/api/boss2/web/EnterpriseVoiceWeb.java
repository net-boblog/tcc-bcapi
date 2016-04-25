package com.tinet.api.boss2.web;

import java.util.List;

import javax.jws.WebService;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseVoiceListResponse;

/**
 * 企业语音文件业务接口
 * <p>
 *  FileName： EnterpriseVoiceWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.EnterpriseVoiceWebImp
 */
@WebService 
public interface EnterpriseVoiceWeb {
	
	/**
	 * 修改某一企业语音文件的审核状态
	 * @param ccicId 平台id
	 * @param voiceId 语音文件id
	 * @param auditStatus 状态
	 * @param auditComment 审核不通过时的理由
	 * @param cRequest 数据签名
	 * @return
	 */
	public CommonResponse updateEntVoiceStatus(String ccicId, Integer voiceId, Integer auditStatus, String auditComment, CommonRequest cRequest);
	
	/**
	 * 查询全平台或某平台的所有企业语音文件
	 * @param ccicList 平台集合
	 * @param masterHotline 主热线号码，支持号码前缀匹配查询
	 * @param auditStatus 语音文件审核状态
	 * @param cRequest 数据签名
	 * @return
	 */
	public EnterpriseVoiceListResponse getAllEntVoice(List<Ccic> ccicList, String masterHotline, String auditStatus, CommonRequest cRequest);

}
