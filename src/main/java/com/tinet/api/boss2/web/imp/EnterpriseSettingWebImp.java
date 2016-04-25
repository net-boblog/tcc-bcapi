package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseSettingResponse;
import com.tinet.api.boss2.service.EnterpriseSettingService;
import com.tinet.api.boss2.web.EnterpriseSettingWeb;

/**
 * 企业配置业务接口实现
 * <p>
 *  FileName： EnterpriseSettingWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.EnterpriseSettingWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.EnterpriseSettingWeb")
public class EnterpriseSettingWebImp implements EnterpriseSettingWeb {
	@Autowired
	private EnterpriseSettingService enterpriseSettingService;

	/**
	 * 获取企业其他配置
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public EnterpriseSettingResponse getOtherConf(String ccicId, Integer enterpriseId, CommonRequest creq) {
		EnterpriseSettingResponse entSettingResponse = new EnterpriseSettingResponse();
		entSettingResponse.setReturnCode("0");
		entSettingResponse.setMsg("success");
		try {
			List<EnterpriseSetting> entSettingList = enterpriseSettingService.getOtherConf(ccicId, enterpriseId);
			entSettingResponse.setEntSettingList(entSettingList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entSettingResponse.setReturnCode("101");
			entSettingResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entSettingResponse.setReturnCode("999");
			entSettingResponse.setMsg("其他异常，请联系管理员！");
		}
		return entSettingResponse;
	}

	/**
	 * 更新企业配置
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param entSetting 企业配置对象
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse instOrUpdateEntSetting(String ccicId, EnterpriseSetting entSetting, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			enterpriseSettingService.instOrUpdateEntSetting(ccicId, entSetting);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			crpon.setReturnCode("101");
			crpon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			crpon.setReturnCode("999");
			crpon.setMsg("其他异常，请联系管理员！");
		}
		return crpon;
	}

}
