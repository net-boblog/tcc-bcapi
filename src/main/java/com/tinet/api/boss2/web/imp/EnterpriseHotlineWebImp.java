package com.tinet.api.boss2.web.imp;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.Combo;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.EnterpriseHotline;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseHotlineResponse;
import com.tinet.api.boss2.service.ComboService;
import com.tinet.api.boss2.service.EnterpriseHotlineService;
import com.tinet.api.boss2.web.EnterpriseHotlineWeb;

/**
 * 热线号码业务接口实现
 * <p>
 *  FileName：EnterpriseHotlineWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.EnterpriseHotlineWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.EnterpriseHotlineWeb")
public class EnterpriseHotlineWebImp implements EnterpriseHotlineWeb {
	
	@Autowired
	private EnterpriseHotlineService enterpriseHotlineService;
	@Autowired
	private ComboService comboService;

	/**
	 * 查询一个企业的所有热线号码信息（对于400/1010号码，直线号码他们对应的目的码含区号。）及其外呼套餐信息。
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public EnterpriseHotlineResponse getAllHotline(String ccicId, Integer enterpriseId, CommonRequest creq) {
		EnterpriseHotlineResponse entHotlineRspon = new EnterpriseHotlineResponse();
		entHotlineRspon.setReturnCode("0");
		entHotlineRspon.setMsg("success");
		try {
			List<EnterpriseHotline> entHotlineList = enterpriseHotlineService.getAllHotline(ccicId, enterpriseId);
			Combo combo = comboService.getEntCombo(ccicId, enterpriseId, new Date());
			entHotlineRspon.setEntHotlineList(entHotlineList);
			entHotlineRspon.setCombo(combo);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entHotlineRspon.setReturnCode("101");
			entHotlineRspon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entHotlineRspon.setReturnCode("999");
			entHotlineRspon.setMsg("其他异常，请联系管理员！");
		}
		return entHotlineRspon;
	}

	/**
	 * 修改热线号码的最低消费信息
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param idChange enterprise_hotline表id串，多个用英文逗号分隔
	 * @param lowestCostChange 当前计费周期最低消费字符串，多个用英文逗号分隔
	 * @param lowestCostNextChange 下个计费周期最低消费字符串，多个用英文逗号分隔
	 * @param lowestCostModeChange 当前计费周期计费模式字符串，多个用英文逗号分隔
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse saveLowestCost(String ccicId, Integer enterpriseId, String idChange,
			String lowestCostChange, String lowestCostNextChange, String lowestCostModeChange, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			enterpriseHotlineService.saveLowestCost(ccicId, enterpriseId, idChange, lowestCostChange, lowestCostNextChange, lowestCostModeChange);
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
