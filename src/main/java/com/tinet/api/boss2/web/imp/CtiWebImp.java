package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Cti;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.CtiListResponse;
import com.tinet.api.boss2.service.CtiService;
import com.tinet.api.boss2.web.CtiWeb;

/**
 * cti业务接口实现
 * <p>
 *  FileName： CtiWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.CtiWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.CtiWeb")
public class CtiWebImp implements CtiWeb {
	
	@Autowired
	private CtiService ctiService;
	
	/**
	 * 获取所有cti 
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 */
	@Override
	public CtiListResponse getAllCti(String ccicId, CommonRequest creq) {
		CtiListResponse ctiListResponse = new CtiListResponse();
		ctiListResponse.setReturnCode("0");
		ctiListResponse.setMsg("success");
		try {
			List<Cti> list = ctiService.getAllCti(ccicId);
			ctiListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			ctiListResponse.setReturnCode("101");
			ctiListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			ctiListResponse.setReturnCode("999");
			ctiListResponse.setMsg("其他异常，请联系管理员！");
		}
		return ctiListResponse;
	}
	
	/**
	 * 获取所有激活cti 
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 */
	@Override
	public CtiListResponse getAll(String ccicId, CommonRequest creq) {
		CtiListResponse ctiListResponse = new CtiListResponse();
		ctiListResponse.setReturnCode("0");
		ctiListResponse.setMsg("success");
		try {
			List<Cti> list = ctiService.getAll(ccicId);
			ctiListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			ctiListResponse.setReturnCode("101");
			ctiListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			ctiListResponse.setReturnCode("999");
			ctiListResponse.setMsg("其他异常，请联系管理员！");
		}
		return ctiListResponse;
	}
	
	/**
	 * 新增cti
	 * @param ccicId 平台ID
	 * @param cti 对象
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse saveCti(String ccicId, Cti cti, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			ctiService.saveCti(ccicId, cti);
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
	 * 更新
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param cti 对象
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse updateCti(String ccicId, Cti cti, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			ctiService.updateCti(ccicId, cti);
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
	 * @param ccicId 平台ID
	 * @param ids id集合
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse batchDelete(String ccicId, List<Integer> ids, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			ctiService.batchDelete(ccicId, ids);
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
