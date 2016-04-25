package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.VoiceAudit;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseVoiceListResponse;
import com.tinet.api.boss2.service.EnterpriseVoiceService;
import com.tinet.api.boss2.web.EnterpriseVoiceWeb;
/**
 * 企业语音文件业务接口实现
 * <p>
 *  FileName： EnterpriseVoiceWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.EnterpriseVoiceWebImp
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.EnterpriseVoiceWeb")
public class EnterpriseVoiceWebImp implements EnterpriseVoiceWeb {
	@Autowired
	private EnterpriseVoiceService enterpriseVoiceService;
	
	/**
	 * 修改企业语音文件的审核状态
	 * @param ccicId 平台id
	 * @param voiceId 语音文件id
	 * @param auditStatus 状态
	 * @param auditComment 审核不通过时的理由
	 * @param cRequest 数据签名
	 * @return
	 */
	@Override
	public CommonResponse updateEntVoiceStatus(String ccicId, Integer voiceId, Integer auditStatus, String auditComment,
			CommonRequest cRequest) {
		CommonResponse cRspon = new CommonResponse();
		cRspon.setReturnCode("0");
		cRspon.setMsg("success");
		try {
			enterpriseVoiceService.updateStatus(ccicId, voiceId, auditStatus, auditComment);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			cRspon.setReturnCode("101");
			cRspon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			cRspon.setReturnCode("999");
			cRspon.setMsg("其他异常，请联系管理员！");
		}
		return cRspon;
	}

	/**
	 * 查询全平台或某平台的所有企业语音文件
	 * @param ccicList 平台集合
	 * @param masterHotline 主热线号码，支持号码前缀匹配查询
	 * @param auditStatus 语音文件审核状态
	 * @param cRequest 数据签名
	 * @return
	 */
	@Override
	public EnterpriseVoiceListResponse getAllEntVoice(List<Ccic> ccicList, String masterHotline, String auditStatus,
			CommonRequest cRequest) {
		EnterpriseVoiceListResponse listRspon = new EnterpriseVoiceListResponse();
		listRspon.setReturnCode("0");
		listRspon.setMsg("success");
		try {
			List<VoiceAudit> voiceList = enterpriseVoiceService.getAllAuditVoice(ccicList, masterHotline, auditStatus);
			listRspon.setVoiceList(voiceList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			listRspon.setReturnCode("101");
			listRspon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			listRspon.setReturnCode("999");
			listRspon.setMsg("其他异常，请联系管理员！");
		}
		return listRspon;
	}

}
