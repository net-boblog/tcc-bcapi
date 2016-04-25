package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.NoticeDao;
import com.tinet.api.boss2.model.Notice;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 公告业务逻辑处理层
 * <p>
 *  FileName： NoticeService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("noticeService")
public class NoticeService {
	
	@Autowired
	NoticeDao noticeDao;
	
	/**
	 * 获取一平台的所有公告
	 * @param ccicId 分平台ID
	 * @return 公告列表
	 */
	public List<Notice> getNotice(String ccicId){
		// 切换数据源ID
		DatabaseContextHolder.setDBType(ccicId);
		return noticeDao.getNotice();
	}
	
	/**
	 * 通过id获取公告
	 * @param ccicId 分平台ID
	 * @param noticeId 公告id
	 * @return 公告对象
	 */
	public Notice getNoticeById(String ccicId, Integer noticeId){
		// 切换数据源ID
		DatabaseContextHolder.setDBType(ccicId);
		return noticeDao.getNoticeById(noticeId);
	}
	
	/**
	 * 增加或修改分平台的公告
	 * @param ccicId 分平台ID
	 * @param noticeId 修改的公告ID 为""表明要增加一公告
	 * @param title 修改或增加的公告标题
	 * @param content 修改或增加的公告内容
	 * @param startTime 公告的开始时间
	 * @param endTime 公告的结束时间
	 * @return
	 */
	public void saveNotice(String ccicId, String noticeId, String title, String content, String startTime, String endTime){
		// 切换数据源ID
		DatabaseContextHolder.setDBType(ccicId);
		noticeDao.save(noticeId, title, content, startTime, endTime);
	}
	
	/**
	 * 在一分平台删除某一公告
	 * @param ccicId 分频台ID
	 * @param noticeId 要删除的公告ID
	 * @return
	 */
	public String deleteNotice(String ccicId, Integer noticeId){
		// 切换数据源ID
		DatabaseContextHolder.setDBType(ccicId);
		return noticeDao.delete(noticeId);
	}
	
	/**
	 * 在一分平台设置发布公告
	 * @param ccicId 分平台ID
	 * @param noticeId 要发布的公告ID
	 * @param set 发布取消公告标识
	 * @return
	 */
	public void setPublish(String ccicId, Integer noticeId, String set){
		// 切换数据源ID
		DatabaseContextHolder.setDBType(ccicId);
		noticeDao.setPublish(noticeId, set);
	}

}
