package com.tinet.api.boss2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.PublicVoice;
import com.tinet.api.boss2.model.SystemSetting;
import com.tinet.common.inc.Const;
import com.tinet.common.util.MD5Encoder;

/**
 * public_voice表读写
 * <p>
 *  FileName： PublicVoiceDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("publicVoiceDao")
public class PublicVoiceDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 通过id查找语音
	 */
	public PublicVoice getPublicVoiceById(String id){
		PublicVoice publicVoice = new PublicVoice();
		//获取公共语音库文件实体
		String selectSql = "select id, voice_name, path, description from public_voice where id=?";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(selectSql, Integer.parseInt(id));
		while(rs.next()) {
			publicVoice.setVoiceId(rs.getInt("id"));
			publicVoice.setVoiceName(rs.getString("voice_name"));
			publicVoice.setPath(rs.getString("path"));
			publicVoice.setDescription(rs.getString("description"));
		}
		return publicVoice;
	}
	
	/**
	 * 查询某平台下的所有公共语音文件列表
	 * @return
	 */
	public List<PublicVoice> getPublicVoiceByCcicId(Ccic ccic, SystemSetting systemSetting, String limit, String start){
		List<PublicVoice> publicVoiceList = new ArrayList<PublicVoice>();
		String sql = "select * from public_voice order by id limit ? offset ?";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, Integer.parseInt(limit), Integer.parseInt(start));
		while(rs.next()) {
			StringBuilder url = new StringBuilder("http://");
			url.append(ccic.getCcicUrl()).append(Const.SOUNDS_IVR_VOICE_PATH).append(rs.getString("path")).append("?from=boss&userName=").
			append(systemSetting.getValue()).append("&pwd=").append(MD5Encoder.encode(systemSetting.getProperty()));
			//构造PublicVoice实体
			PublicVoice publicVoice = new PublicVoice(rs.getInt("id"),rs.getString("voice_name"),
					rs.getString("path"),rs.getString("description"),url.toString());
			publicVoiceList.add(publicVoice);
		}
		return publicVoiceList;
	}
	
	/**
	 * 获取符合条件的语音文件总记录数
	 * @return
	 */
	public Integer getCount(){
		int count = 1;
		String sql = "select count(*) from public_voice";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
	
	/**
	 * 更新
	 */
	@Transactional
	public void update(String voiceName, String description, String id){
		String sql = "update public_voice set voice_name=?,description=? where id=?";
		jdbcTemplate.update(sql, voiceName, description, Integer.parseInt(id));
	}
	
	/**
	 * 验证是否在使用
	 * @param path
	 * @return true为没在使用。
	 */
	@Transactional
	public boolean checkRepalce(String path){
		Boolean flag = false;
		Integer countQue = 1;
		Integer countIvr = 1;
		String sqlQue = "select count(*) from queue where  music_class=?";
		SqlRowSet rsQue = jdbcTemplate.queryForRowSet(sqlQue, path.substring(0, path.lastIndexOf(".")).replaceAll("/",""));
		while(rsQue.next()) {
			countQue = rsQue.getInt("count");
		}
		
		String sqlIvr = "select count(*) from enterprise_ivr where  property like ? or property like ?";
		SqlRowSet rsIvr = jdbcTemplate.queryForRowSet(sqlIvr, "%" + path.substring(0, path.lastIndexOf(".")) + "%", "%" + path.substring(0, path.lastIndexOf(".")) + "%");
		while(rsIvr.next()) {
			countIvr = rsIvr.getInt("count");
		}
		
		if(countQue == 0 && countIvr == 0){
			flag = true;
		}
		return flag;
	}
}
