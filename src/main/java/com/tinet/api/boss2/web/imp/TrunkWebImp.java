package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Trunk;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.TrunkResponse;
import com.tinet.api.boss2.service.TrunkService;
import com.tinet.api.boss2.web.TrunkWeb;

/**
 * 目的号码业务接口实现
 * <p>
 *  FileName： TrunkWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.TrunkWeb
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.TrunkWeb")
public class TrunkWebImp implements TrunkWeb {
	
	@Autowired
	private TrunkService trunkService;

	/**
	 * 查询一个企业的所有直线号码、手机虚拟号码及其月租信息。
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public TrunkResponse getTrunkRent(String ccicId, Integer enterpriseId, CommonRequest creq) {
		TrunkResponse trunkResponse = new TrunkResponse();
		trunkResponse.setReturnCode("0");
		trunkResponse.setMsg("success");
		try {
			List<Trunk> trunkList = trunkService.getTrunkRent(ccicId, enterpriseId);
			trunkResponse.setTrunkList(trunkList);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			trunkResponse.setReturnCode("101");
			trunkResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			trunkResponse.setReturnCode("999");
			trunkResponse.setMsg("其他异常，请联系管理员！");
		}
		return trunkResponse;
	}

	/**
	 * 修改号码的月功能费
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param idChange enterprise_hotline表id串，多个用英文逗号分隔
	 * @param rentChange 当前计费周期最低消费字符串，多个用英文逗号分隔
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse saveTrunkRent(String ccicId, Integer enterpriseId, String idChange, String rentChange,
			CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			trunkService.saveTrunkRent(ccicId, enterpriseId, idChange, rentChange);
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
