package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.SystemSetting;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.SystemSettingListResponse;
import com.tinet.api.boss2.response.enity.SystemSettingResponse;
import com.tinet.api.boss2.service.SystemSettingService;
import com.tinet.api.boss2.web.SystemSettingWeb;

@WebService(endpointInterface = "com.tinet.api.boss2.web.SystemSettingWeb")
public class SystemSettingWebImp implements SystemSettingWeb{

	@Autowired
	SystemSettingService systemSettingService;
	
	/**
	 * 查询平台设置信息
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public SystemSettingListResponse getAllSysSetting(String ccicId, CommonRequest creq) {
		SystemSettingListResponse systemSettingListResponse = new SystemSettingListResponse();
		systemSettingListResponse.setReturnCode("0");
		systemSettingListResponse.setMsg("success");
		try {
			List<SystemSetting> list = systemSettingService.getAllSysSetting(ccicId);
			systemSettingListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			systemSettingListResponse.setReturnCode("101");
			systemSettingListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			systemSettingListResponse.setReturnCode("999");
			systemSettingListResponse.setMsg("其他异常，请联系管理员！");
		}
		return systemSettingListResponse;
	}
	
	/**
	 * 修改分平台的系统设置。
	 * @param ccic 分平台。
	 * @param id 设置的唯一标识。
	 * @param value 设置的值。
	 * @param property 设置的单位或密码。
	 * @param creq 数据签名
	 */
	@Override
	public CommonResponse updateSysSetting(String ccicId, Integer id, String value, String property, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			systemSettingService.updateSysSetting(ccicId, id, value, property);
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
	
	/**
	 * 修改分平台的系统设置
	 * @param ccic 分平台
	 * @param name
	 * @param value 设置的值
	 * @param creq 数据签名
	 */
	@Override
	public CommonResponse updateSysSettingByName(String ccicId, String name, String value, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			systemSettingService.updateSysSettingByName(ccicId, name, value);
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

	/**
	 * 获取分平台接口用户名及密码
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 */
	@Override
	public SystemSettingResponse getUsrPwd(String ccicId, CommonRequest creq) {
		SystemSettingResponse systemSettingResponse = new SystemSettingResponse();
		systemSettingResponse.setReturnCode("0");
		systemSettingResponse.setMsg("success");
		try {
			SystemSetting systemSetting = systemSettingService.getUsrPwd(ccicId);
			systemSettingResponse.setSystemSetting(systemSetting);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			systemSettingResponse.setReturnCode("101");
			systemSettingResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			systemSettingResponse.setReturnCode("999");
			systemSettingResponse.setMsg("其他异常，请联系管理员！");
		}
		return systemSettingResponse;
	}

}
