package com.tinet.api.boss2.web.imp;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.EnterpriseAll;
import com.tinet.api.boss2.model.EnterpriseNumber;
import com.tinet.api.boss2.model.EnterpriseSettingAll;
import com.tinet.api.boss2.response.enity.EntInfoSyncResponse;
import com.tinet.api.boss2.response.enity.EnterpriseNumberListResponse;
import com.tinet.api.boss2.response.enity.MapDataResponse;
import com.tinet.api.boss2.service.CrontabService;
import com.tinet.api.boss2.web.CrontabWeb;

@WebService(endpointInterface = "com.tinet.api.boss2.web.CrontabWeb")
public class CrontabWebImp implements CrontabWeb {
	@Autowired
	private CrontabService crontabService;

	/**
	 * 同步所有企业信息到BOSS2
	 * @param ccicList 所有平台列表
	 * @param entTestStatusList 测试账户使用状态
	 * @param entTestExpiryDateList 测试账户过期时间
	 * @param cRequest 数据签名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EntInfoSyncResponse getEnterpriseInfo(List<Ccic> ccicList, List<EnterpriseSettingAll> entTestStatusList,
			List<EnterpriseSettingAll> entTestExpiryDateList, CommonRequest cRequest) {
		EntInfoSyncResponse entInfoResponse = new EntInfoSyncResponse();
		try {
			Map<String, Object> rtnMap = crontabService.getEntInfo(ccicList, entTestStatusList, entTestExpiryDateList);
			if (rtnMap.get("error") == null || "".equals(rtnMap.get("error"))) {
				entInfoResponse.setReturnCode("0");
				entInfoResponse.setMsg("success");
			} else {
				entInfoResponse.setReturnCode("201");
				entInfoResponse.setMsg(rtnMap.get("error").toString());
			}
			// 获取企业信息
			entInfoResponse.setEntAllList((List<EnterpriseAll>)rtnMap.get("entAllList"));
			entInfoResponse.setEntSettingAllList((List<EnterpriseSettingAll>)rtnMap.get("entSettingAllList"));
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entInfoResponse.setReturnCode("101");
			entInfoResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entInfoResponse.setReturnCode("999");
			entInfoResponse.setMsg("其他异常，请联系管理员！");
		}
		return entInfoResponse;
	}

	/**
	 * 获取全平台所有企业下月固定费用
	 * @param ccicList 所有平台列表
	 * @param cRequest 数据签名
	 * @return
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public MapDataResponse getEntTotalCost(List<Ccic> ccicList, CommonRequest cRequest) {
		MapDataResponse mapResponse = new MapDataResponse();
		try {
			Map rtnMap = crontabService.getEntTotalCost(ccicList);
			if (rtnMap.get("error") == null || "".equals(rtnMap.get("error"))) {
				mapResponse.setReturnCode("0");
				mapResponse.setMsg("success");
			} else {
				mapResponse.setReturnCode("201");
				mapResponse.setMsg(rtnMap.get("error").toString());
			}
			// 获取固定费用额度信息
			mapResponse.setData(rtnMap);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			mapResponse.setReturnCode("101");
			mapResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			mapResponse.setReturnCode("999");
			mapResponse.setMsg("其他异常，请联系管理员！");
		}
		return mapResponse;
	}

	/**
	 * 获取全平台所有企业号码：热线号码，中继号码，手机虚拟号码，客户自备号码
	 * @param ccicList 所有平台列表
	 * @param cRequest 数据签名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EnterpriseNumberListResponse getEnterpriseNumbers(List<Ccic> ccicList, CommonRequest cRequest) {
		EnterpriseNumberListResponse entNumberListResponse = new EnterpriseNumberListResponse();
		try {
			Map<String, Object> rtnMap = crontabService.getEntNumbers(ccicList);
			if (rtnMap.get("error") == null || "".equals(rtnMap.get("error"))) {
				entNumberListResponse.setReturnCode("0");
				entNumberListResponse.setMsg("success");
			} else {
				entNumberListResponse.setReturnCode("201");
				entNumberListResponse.setMsg(rtnMap.get("error").toString());
			}
			// 获取企业信息
			entNumberListResponse.setEntNumberList(((List<EnterpriseNumber>)rtnMap.get("entNumberList")));
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entNumberListResponse.setReturnCode("101");
			entNumberListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entNumberListResponse.setReturnCode("999");
			entNumberListResponse.setMsg("其他异常，请联系管理员！");
		}
		return entNumberListResponse;
	}

}
