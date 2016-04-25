package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Router;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.RouterListResponse;
import com.tinet.api.boss2.service.RouterService;
import com.tinet.api.boss2.web.RouterWeb;

/**
 * 路由组详细规则配置业务接口实现
 * <p>
 *  FileName： RouterWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.RouterWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.RouterWeb")
public class RouterWebImp implements RouterWeb {
	
	@Autowired
	private RouterService routerService;

	/**
	 * 获取某企业路由组的详细规则配置
	 * @param ccicId 平台id
	 * @param routersetId 路由组id
	 * @param creq 数据签名
	 * @return
	 */
	public RouterListResponse getSetRouter(String ccicId, Integer routersetId, CommonRequest creq) {
		RouterListResponse RouterListResp = new RouterListResponse();
		RouterListResp.setReturnCode("0");
		RouterListResp.setMsg("success");
		try {
			List<Router> RouterList = routerService.getSetRouter(ccicId, routersetId);
			RouterListResp.setRouterList(RouterList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			RouterListResp.setReturnCode("101");
			RouterListResp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			RouterListResp.setReturnCode("999");
			RouterListResp.setMsg("其他异常，请联系管理员！");
		}
		return RouterListResp;
	}
	
	/**
	 * 获取某平台所有企业路由组的所有详细规则配置
	 * @param ccicId 平台id
	 * @param creq 数据签名
	 * @return
	 */
	public RouterListResponse getAllRouter(String ccicId, CommonRequest creq) {
		RouterListResponse RouterListResp = new RouterListResponse();
		RouterListResp.setReturnCode("0");
		RouterListResp.setMsg("success");
		try {
			List<Router> RouterList = routerService.getAllRouter(ccicId);
			RouterListResp.setRouterList(RouterList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			RouterListResp.setReturnCode("101");
			RouterListResp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			RouterListResp.setReturnCode("999");
			RouterListResp.setMsg("其他异常，请联系管理员！");
		}
		return RouterListResp;
	}
	
	/**
	 * 某企业路由组表新增一组详细规则配置
	 * @param ccicId 平台id
	 * @param routerList router对象集合
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse batchSaveRouter(String ccicId, List<Router> routerList, CommonRequest creq) {
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			routerService.batchSaveRouter(ccicId, routerList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			comnResponse.setReturnCode("101");
			comnResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			comnResponse.setReturnCode("999");
			comnResponse.setMsg("其他异常，请联系管理员！");
		}
		return comnResponse;
	}
	
	/**
	 * 批量更新路由详细规则配置
	 * @param ccicId  平台id
	 * @param routerList 路由详细规则集合
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse batchUpdateRouter(String ccicId, List<Router> routerList, CommonRequest creq){
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			routerService.batchUpdateRouter(ccicId, routerList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			comnResponse.setReturnCode("101");
			comnResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			comnResponse.setReturnCode("999");
			comnResponse.setMsg("其他异常，请联系管理员！");
		}
		return comnResponse;
	}
	
	/**
	 * 批量删除路由组详细规则配置
	 * @param ccicId 平台id
	 * @param ids 路由详细规则id数组
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse batchDeleteRouter(String ccicId, List<Integer> ids, CommonRequest creq) {
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			routerService.batchDeleteRouter(ccicId, ids);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			comnResponse.setReturnCode("101");
			comnResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			comnResponse.setReturnCode("999");
			comnResponse.setMsg("其他异常，请联系管理员！");
		}
		return comnResponse;
	}
	
	/**
	 * 复制一平台所有企业路由和基本路由到另一平台
	 * @param fromCcicId
	 * @param toCcicId
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse copy(String fromCcicId, String toCcicId, CommonRequest creq) {
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			String rsInfo = routerService.batchCopy(fromCcicId, toCcicId);
			if (rsInfo!=null && !"".equals(rsInfo)) {
				comnResponse.setReturnCode("301");
				comnResponse.setMsg(rsInfo);
			}
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			comnResponse.setReturnCode("101");
			comnResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			comnResponse.setReturnCode("999");
			comnResponse.setMsg("其他异常，请联系管理员！");
		}
		return comnResponse;
	}
}
