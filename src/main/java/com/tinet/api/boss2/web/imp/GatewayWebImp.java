package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Gateway;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.GatewayListResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
import com.tinet.api.boss2.service.GatewayService;
import com.tinet.api.boss2.web.GatewayWeb;

/**
 * 平台网关设备管理业务接口实现
 * <p>
 *  FileName： RoutersetWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.GatewayWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.GatewayWeb")
public class GatewayWebImp implements GatewayWeb {
	
	@Autowired
	private GatewayService gatewayService;

	/**
	 * 获取平台上所有的网关设备配置信息。
	 * @param ccicId 平台id。
	 * @param creq 数据签名
	 * @return
	 */
	public GatewayListResponse getAllGateway(String ccicId, CommonRequest creq) {
		GatewayListResponse listResp = new GatewayListResponse();
		listResp.setReturnCode("0");
		listResp.setMsg("success");
		try {
			List<Gateway> gatewayList = gatewayService.getAllGateway(ccicId);
			listResp.setGatewayList(gatewayList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			listResp.setReturnCode("101");
			listResp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			listResp.setReturnCode("999");
			listResp.setMsg("其他异常，请联系管理员！");
		}
		return listResp;
	}
	
	/**
	 * 平台上新增一条网关设备配置
	 * @param ccicId  平台id
	 * @param gateway 网关设备配置对象
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse saveGateway(String ccicId, Gateway gateway, CommonRequest creq) {
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			gatewayService.saveGateway(ccicId, gateway);
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
	 * 更新平台上一条网关设备配置
	 * @param ccicId  平台id
	 * @param gateway 网关设备配置对象
	 * @param creq 数据签名
	 * @return
	 */
	public StringDataResponse updateGateway(String ccicId, Gateway gateway, CommonRequest creq) {
		StringDataResponse strDataResponse = new StringDataResponse();
		strDataResponse.setReturnCode("0");
		strDataResponse.setMsg("success");
		try {
			String routersetIds = gatewayService.updateGateway(ccicId, gateway);
			strDataResponse.setData(routersetIds);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			strDataResponse.setReturnCode("101");
			strDataResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			strDataResponse.setReturnCode("999");
			strDataResponse.setMsg("其他异常，请联系管理员！");
		}
		return strDataResponse;
	}
	
	/**
	 * 删除平台上一条网关设备配置
	 * @param ccicId 平台id
	 * @param gatewayId 网关设备配置id
	 * @param creq 数据签名
	 * @return
	 */
	public CommonResponse deleteGateway(String ccicId, Integer gatewayId, CommonRequest creq) {
		StringDataResponse rspon = new StringDataResponse();
		rspon.setReturnCode("0");
		rspon.setMsg("success");
		try {
			String rsInfo = gatewayService.deleteGateway(ccicId, gatewayId);
			
			if (rsInfo != null && !"".equals(rsInfo)) {
				rspon.setReturnCode("301");
				rspon.setMsg(rsInfo);
			}
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			rspon.setReturnCode("101");
			rspon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			rspon.setReturnCode("999");
			rspon.setMsg("其他异常，请联系管理员！");
		}
		return rspon;
	}
	
}
