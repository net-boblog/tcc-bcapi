package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Routerset;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.RoutersetListResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
import com.tinet.api.boss2.service.RoutersetService;
import com.tinet.api.boss2.web.RoutersetWeb;

/**
 * 平台企业路由组业务接口实现
 * <p>
 *  FileName： RoutersetWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.RoutersetWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.RoutersetWeb")
public class RoutersetWebImp implements RoutersetWeb {
	
	@Autowired
	private RoutersetService routersetService;

	/**
	 * 获取平台上所有的企业路由组信息。
	 * @param ccicId 平台id。
	 * @param creq 数据签名
	 * @return
	 */
	public RoutersetListResponse getAllRouterset(String ccicId, CommonRequest creq){
		RoutersetListResponse routersetListResp = new RoutersetListResponse();
		routersetListResp.setReturnCode("0");
		routersetListResp.setMsg("success");
		try {
			List<Routerset> routersetList = routersetService.getAllRouterset(ccicId);
			routersetListResp.setRoutersetList(routersetList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			routersetListResp.setReturnCode("101");
			routersetListResp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			routersetListResp.setReturnCode("999");
			routersetListResp.setMsg("其他异常，请联系管理员！");
		}
		return routersetListResp;
	}
	
	/**
	 * 新增一条路由组信息
	 * @param ccicId  平台id
	 * @param name 路由组名称
	 * @param description 路由组描述
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveRouterset(String ccicId, String name, String description, CommonRequest creq){
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			routersetService.saveRouterset(ccicId, name, description);
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
	 * 更新一条路由组信息
	 * @param ccicId  平台id
	 * @param id 路由组id
	 * @param name 路由组名称
	 * @param description 路由组描述
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse updateRouterset(String ccicId, Integer id, String name, String description, CommonRequest creq){
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			routersetService.updateRouterset(ccicId, id, name, description);
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
	 * 删除一条路由组信息
	 * @param ccicId 平台id
	 * @param routersetId 路由组id
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse deleteRouterset(String ccicId, Integer routersetId, CommonRequest creq) {
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			String rsInfo = routersetService.deleteRouterset(ccicId, routersetId);
			if (rsInfo!=null && !"".equals(rsInfo) ) {
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
	
	/**
	 * 获取路由组名称
	 * @param ccicId 平台id
	 * @param routersetId 路由组id
	 * @param creq 数据签名
	 * @return
	 */
	public StringDataResponse getRoutersetName(String ccicId, Integer routersetId, CommonRequest creq) {
		StringDataResponse stringResponse = new StringDataResponse();
		stringResponse.setReturnCode("0");
		stringResponse.setMsg("success");
		try {
			String routersetName = routersetService.getRoutersetName(ccicId, routersetId);
			stringResponse.setData(routersetName);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			stringResponse.setReturnCode("101");
			stringResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			stringResponse.setReturnCode("999");
			stringResponse.setMsg("其他异常，请联系管理员！");
		}
		return stringResponse;
	}
	
}
