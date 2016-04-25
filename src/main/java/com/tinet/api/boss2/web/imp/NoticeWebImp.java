package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Notice;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.NoticeResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
import com.tinet.api.boss2.response.enity.NoticeListResponse;
import com.tinet.api.boss2.service.NoticeService;
import com.tinet.api.boss2.web.NoticeWeb;

/**
 * 公告业务接口实现
 * <p>
 *  FileName： NoticeWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.NoticeWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.NoticeWeb")
public class NoticeWebImp implements NoticeWeb {
	
	@Autowired
	NoticeService noticeService;
	
	/**
	 * 获取一平台的所有公告
	 * @param ccicId 分平台ID
	 * @param creq 数据签名
	 * @return 公告列表
	 */
	@Override
	public NoticeListResponse getNotice(String ccicId, CommonRequest creq) {
		NoticeListResponse noticeListResponse = new NoticeListResponse();
		noticeListResponse.setReturnCode("0");
		noticeListResponse.setMsg("success");
		try {
			List<Notice> list = noticeService.getNotice(ccicId);
			noticeListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			noticeListResponse.setReturnCode("101");
			noticeListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			noticeListResponse.setReturnCode("999");
			noticeListResponse.setMsg("其他异常，请联系管理员！");
		}
		return noticeListResponse;
	}
	
	/**
	 * 通过id获取公告
	 * @param ccicId 分平台ID
	 * @param noticeId 公告id
	 * @param creq 数据签名
	 * @return 公告对象
	 */
	@Override
	public NoticeResponse getNoticeById(String ccicId, Integer noticeId, CommonRequest creq) {
		NoticeResponse noticeResponse = new NoticeResponse();
		noticeResponse.setReturnCode("0");
		noticeResponse.setMsg("success");
		try {
			Notice notice = noticeService.getNoticeById(ccicId, noticeId);
			noticeResponse.setNotice(notice);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			noticeResponse.setReturnCode("101");
			noticeResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			noticeResponse.setReturnCode("999");
			noticeResponse.setMsg("其他异常，请联系管理员！");
		}
		return noticeResponse;
	}

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
	@Override
	public CommonResponse saveNotice(String ccicId, String noticeId,
			String title, String content, String startTime, String endTime,
			CommonRequest creq) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setReturnCode("0");
		commonResponse.setMsg("success");
		try {
			noticeService.saveNotice(ccicId, noticeId, title, content, startTime, endTime);
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
	 * 在一分平台删除某一公告
	 * @param ccicId 分频台ID
	 * @param noticeId 要删除的公告ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public StringDataResponse deleteNotice(String ccicId, Integer noticeId, CommonRequest creq) {
		StringDataResponse stringDataResponse = new StringDataResponse();
		stringDataResponse.setReturnCode("0");
		stringDataResponse.setMsg("success");
		try {
			String data = noticeService.deleteNotice(ccicId, noticeId);
			stringDataResponse.setData(data);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			stringDataResponse.setReturnCode("101");
			stringDataResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			stringDataResponse.setReturnCode("999");
			stringDataResponse.setMsg("其他异常，请联系管理员！");
		}
		return stringDataResponse;
	}
	
	/**
	 * 在一分平台设置发布公告
	 * @param ccicId 分平台ID
	 * @param noticeId 要发布的公告ID
	 * @param set 发布取消公告标识
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse setPublish(String ccicId, Integer noticeId, String set, CommonRequest creq) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setReturnCode("0");
		commonResponse.setMsg("success");
		try {
			noticeService.setPublish(ccicId, noticeId, set);
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
