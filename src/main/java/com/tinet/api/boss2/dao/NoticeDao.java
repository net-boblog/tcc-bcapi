package com.tinet.api.boss2.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.model.Notice;
import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;
import com.tinet.common.util.StringUtil;

/**
 * notice表读写
 * <p>
 *  FileName： NoticeDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("noticeDao")
public class NoticeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 获取一平台的所有公告
	 * @return 公告列表
	 */
	public List<Notice> getNotice(){
		List<Notice> list = new ArrayList<Notice>();
		String sql = "select id,title,content,is_publish as isPublish,start_time as startTime,end_time as endTime,create_time as createTime from notice order by create_time";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while(rs.next()){
			Notice notice = new Notice();
			notice.setId(rs.getInt("id"));
			notice.setTitle(rs.getString("title"));
			notice.setContent(rs.getString("content"));
			notice.setIsPublish(rs.getInt("isPublish"));
			
			DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			try {
				date = df.parse(rs.getString("startTime"));
			} catch (InvalidResultSetAccessException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
			notice.setStartTime(date);
			try {
				date = df.parse(rs.getString("endTime"));
			} catch (InvalidResultSetAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			notice.setEndTime(date);
			try {
				date = df.parse(rs.getString("createTime"));
			} catch (InvalidResultSetAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			notice.setCreateTime(date);
			
			list.add(notice);
		}
		return list;
	}
	
	/**
	 * 通过id获取公告
	 * @param noticeId 公告id
	 * @return 公告对象
	 */
	public Notice getNoticeById(Integer noticeId){
		Notice notice = new Notice();
		String sql = "select id,title,content,is_publish as isPublish,start_time as startTime,end_time as endTime,create_time as createTime from notice where id=?";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, noticeId);
		if(rs.next()){
			notice.setId(rs.getInt("id"));
			notice.setTitle(rs.getString("title"));
			notice.setContent(rs.getString("content"));
			notice.setIsPublish(rs.getInt("isPublish"));
			
			DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			try {
				date = df.parse(rs.getString("startTime"));
			} catch (InvalidResultSetAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			notice.setStartTime(date);
			try {
				date = df.parse(rs.getString("endTime"));
			} catch (InvalidResultSetAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			notice.setEndTime(date);
			try {
				date = df.parse(rs.getString("createTime"));
			} catch (InvalidResultSetAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			notice.setCreateTime(date);
		}
		return notice;
	}
	
	/**
	 * 增加或修改分平台的公告
	 * @param noticeId 修改的公告ID 为""表明要增加一公告
	 * @param title 修改或增加的公告标题
	 * @param content 修改或增加的公告内容
	 * @param startTime 公告的开始时间
	 * @param endTime 公告的结束时间
	 * @return
	 */
	public void save(String noticeId, String title, String content, String startTime, String endTime){
		String sql = "";
		if(!noticeId.equals("")){
			sql = "update notice set title=?, content=?, start_time=?, end_time=? where id=?";
			try {
				jdbcTemplate.update(sql, title, content, DateUtil.parse(startTime, DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss), DateUtil.parse(endTime, DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss), Integer.parseInt(noticeId));
			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			sql = "insert into notice(title, content, start_time, end_time) values(?, ?, ?, ?)";
			try {
				jdbcTemplate.update(sql, title, content, DateUtil.parse(startTime, DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss), DateUtil.parse(endTime, DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 在一分平台删除某一公告
	 * @param noticeId 要删除的公告ID
	 * @return
	 */
	public String delete(Integer noticeId){
		String flag = "";
		String sql = "select id from notice where id = ? and is_publish = 1";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, noticeId);
		while (rs.next()) {
			flag = "公告发布中不能删除！";
		}
		if(StringUtil.isEmpty(flag)){
			sql = "delete from notice where id=?";
			jdbcTemplate.update(sql, noticeId);
		}
		return flag;
	}
	
	/**
	 * 在一分平台设置发布公告
	 * @param noticeId 要发布的公告ID
	 * @param set 发布取消公告标识
	 * @return
	 */
	public void setPublish(Integer noticeId, String set){
		if ("1".equals(set)) {
			// 查询平台的发布公告，如果跟要设置的发布公告不是同一个，则修改设置发布公告
			String qurySql = "select id,title,content,is_publish as isPublish,start_time as startTime,end_time as endTime,create_time as createTime from notice where is_publish=? order by create_time";
			SqlRowSet rs = jdbcTemplate.queryForRowSet(qurySql, Const.NOTICE_IS_PUBLISH_YES);
			if(rs.next()){
				if(rs.getInt("id") != noticeId){
					qurySql = "update notice set is_publish=? where id=?";
					jdbcTemplate.update(qurySql, Const.NOTICE_IS_PUBLISH_YES, noticeId);
					qurySql = "update notice set is_publish=? where id=?";
					jdbcTemplate.update(qurySql, Const.NOTICE_IS_PUBLISH_NO, rs.getInt("id"));
				}
			}else{
				qurySql = "update notice set is_publish=? where id=?";
				jdbcTemplate.update(qurySql, Const.NOTICE_IS_PUBLISH_YES, noticeId);
			}
			
		} else{
			String qurySql = "update notice set is_publish=?";
			jdbcTemplate.update(qurySql, Const.NOTICE_IS_PUBLISH_NO);
		}
	}
}
