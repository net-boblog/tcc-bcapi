package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Sbc;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.SbcListResponse;
import com.tinet.api.boss2.service.SbcService;
import com.tinet.api.boss2.web.SbcWeb;

/**
 * sbc业务接口实现
 * <p>
 *  FileName： SbcWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.SbcWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.SbcWeb")
public class SbcWebImp implements SbcWeb {
	
	@Autowired
	SbcService sbcService;
	
	/**
	 * 查询sbc
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public SbcListResponse getAllSbc(String ccicId, CommonRequest creq) {
		SbcListResponse sbcListResponse = new SbcListResponse();
		sbcListResponse.setReturnCode("0");
		sbcListResponse.setMsg("success");
		try {
			List<Sbc> list = sbcService.getAllSbc(ccicId);
			sbcListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			sbcListResponse.setReturnCode("101");
			sbcListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			sbcListResponse.setReturnCode("999");
			sbcListResponse.setMsg("其他异常，请联系管理员！");
		}
		return sbcListResponse;
	}
	
	/**
	 * 新增
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param host ip地址
	 * @param port 端口 	
	 * @param password 密码
	 * @param timeout 超时时间
	 * @param areaCode 默认区号
	 * @param callLimit 呼叫限制
	 * @param active 状态
	 * @param mac mac地址
	 * @param creq
	 * @return
	 */
	@Override
	public CommonResponse saveSbc(String ccicId, String host, String port,
			String password, String timeout, String areaCode, String callLimit,
			String active, String mac, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			sbcService.saveSbc(ccicId, host, port, password, timeout, areaCode, callLimit, active, mac);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			cResponse.setReturnCode("101");
			cResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			cResponse.setReturnCode("999");
			cResponse.setMsg("其他异常，请联系管理员！");
		}
		return cResponse;
	}
	
	/**
	 * 批量更新：包括数据和密码
	 * @author zhancy
	 * @param ccicId
	 * @param sbcList
	 * @param creq
	 * @return
	 */
	@Override
	public CommonResponse bathcUpdateSbc(String ccicId, List<Sbc> sbcList, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			sbcService.bathcUpdateSbc(ccicId, sbcList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			cResponse.setReturnCode("101");
			cResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			cResponse.setReturnCode("999");
			cResponse.setMsg("其他异常，请联系管理员！");
		}
		return cResponse;
	}
	
	/**
	 * 批量删除
	 * @author zhancy
	 */
	@Override
	public CommonResponse batchDeleteSbc(String ccicId, List<Integer> ids, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			sbcService.batchDeleteSbc(ccicId, ids);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			cResponse.setReturnCode("101");
			cResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			cResponse.setReturnCode("999");
			cResponse.setMsg("其他异常，请联系管理员！");
		}
		return cResponse;
	}
	
}
