package com.tinet.api.boss2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.SystemSetting;
import com.tinet.api.boss2.model.VoiceAudit;
import com.tinet.common.inc.Const;
import com.tinet.common.util.MD5Encoder;

/**
 * enterprise_voice表读写
 * <p>
 *  FileName： EnterpriseVoiceDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseVoiceDao")
public class EnterpriseVoiceDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SystemSettingDao systemSettingDao;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 更新企业所有语音文件状态
	 * @param enterpriseId 企业ID
	 * @param voiceStatus 语音文件状态
	 */
	@Transactional
	public void updateVoiceStatus(Integer enterpriseId, Integer voiceStatus) {
		String updateSql = " UPDATE enterprise_voice SET audit_status =? where enterprise_id = ?;";
		jdbcTemplate.update(updateSql, voiceStatus, enterpriseId);
	}
	
	/**
	 * 更新企业某一语音文件状态
	 * @param voiceId 语音文件id
	 * @param voiceStatus 语音文件状态
	 * @param auditComment 审核不通过时的理由
	 */
	@Transactional
	public void updateEntVoiceStatus(Integer voiceId, Integer auditStatus, String auditComment) {
		if (auditStatus.intValue() == Const.ENTERPRISE_VOICE_AUDIT_STATUS_AUDITFAIL) {
			String updateSql = " UPDATE enterprise_voice SET audit_status =?, audit_comment=? where id = ?;";
			jdbcTemplate.update(updateSql, auditStatus, auditComment, voiceId);
		} else {
			String updateSql = " UPDATE enterprise_voice SET audit_status =? where id = ?;";
			jdbcTemplate.update(updateSql, auditStatus, voiceId);
		}
	}
	
	/**
	 * 获取分平台所有非注销企业的企业语音，从audit_voice视图中读取
	 * @param ccic 平台信息
	 * @param masterHotline 主热线号码，支持号码前缀匹配查询
	 * @param auditStatus 语音文件审核状态
	 * @return
	 */
	public List<VoiceAudit> getAllAuditVoice(Ccic ccic, String masterHotline, String auditStatus) {
		// 返回
		List<VoiceAudit> voiceList = new ArrayList<VoiceAudit>();
		// 构造查询sql
		StringBuffer qrySql = new StringBuffer("select enterprise_id, enterprise_name, master_hotline, voice_id, voice_name, description, audit_status, audit_comment, path, create_time ");
		qrySql.append(" from audit_voice where 1=1");
		
		if (masterHotline!=null && !"".equals(masterHotline)) {
			qrySql.append(" and master_hotline like '").append(masterHotline).append("'");
		}
		
		if (auditStatus!=null && !"".equals(auditStatus)) {
			qrySql.append(" and auditStatus =").append(auditStatus);
		}
		
		// 排序
		qrySql.append(" order by enterprise_id,voice_id;");
		SqlRowSet rs = jdbcTemplate.queryForRowSet(qrySql.toString());
		
		while(rs.next()) {
			SystemSetting systemSetting = systemSettingDao.getUsrPwd();
			// 构造VoiceAudit实体
			String url = "http://" + ccic.getCcicUrl() + Const.SOUNDS_IVR_VOICE_PATH + rs.getString("path") +"?from=boss&userName=" 
					+ systemSetting.getValue()+"&pwd=" + MD5Encoder.encode(systemSetting.getProperty());
			VoiceAudit voiceAudit = new VoiceAudit(rs.getInt("enterprise_id"), ccic.getId(), rs.getString("enterprise_name"), 
					rs.getString("master_hotline"),	rs.getInt("voice_id"), rs.getString("voice_name"), rs.getString("description"),
					rs.getInt("audit_status"), rs.getString("audit_comment"), url, rs.getString("path"), rs.getDate("create_time"));
			voiceList.add(voiceAudit);
		}
		
		return voiceList;
	}

}
