package com.tinet.api.boss2.web;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.NoticeListResponse;
import com.tinet.api.boss2.response.enity.NoticeResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
/**
 * 公告业务接口
 * <p>
 *  FileName： NoticeWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.NoticeWebImp
 */
@WebService 
public interface NoticeWeb {
	
	/**
	 * 获取一平台的所有公告
	 * @param ccicId 分平台ID
	 * @param creq 数据签名
	 * @return 公告列表
	 */
	public NoticeListResponse getNotice(String ccicId, CommonRequest creq);
	
	/**
	 * 通过id获取公告
	 * @param ccicId 分平台ID
	 * @param noticeId 公告id
	 * @param creq 数据签名
	 * @return 公告对象
	 */
	public NoticeResponse getNoticeById(String ccicId, Integer noticeId, CommonRequest creq);
	
	/**
	 * 增加或修改分平台的公告
	 * @param ccicId 分平台ID
	 * @param noticeId 修改的公告ID 为""表明要增加一公告
	 * @param title 修改或增加的公告标题
	 * @param content 修改或增加的公告内容
	 * @param startTime 公告的开始时间
	 * @param endTime 公告的结束时间
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveNotice(String ccicId, String noticeId, String title, String content, String startTime, String endTime, CommonRequest creq);
	
	/**
	 * 在一分平台删除某一公告
	 * @param ccicId 分频台ID
	 * @param noticeId 要删除的公告ID
	 * @param creq 数据签名
	 * @return
	 */
	public StringDataResponse deleteNotice(String ccicId, Integer noticeId, CommonRequest creq);
	
	/**
	 * 在一分平台设置发布公告
	 * @param ccicId 分平台ID
	 * @param noticeId 要发布的公告ID
	 * @param set 发布取消公告标识
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse setPublish(String ccicId, Integer noticeId, String set, CommonRequest creq);
} 
