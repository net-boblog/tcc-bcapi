package com.tinet.api.boss2.web.imp;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.EnterpriseCombo;
import com.tinet.api.boss2.response.enity.EnterpriseComboResponse;
import com.tinet.api.boss2.service.EnterpriseComboService;
import com.tinet.api.boss2.web.EnterpriseComboWeb;

@WebService(endpointInterface = "com.tinet.api.boss2.web.EnterpriseComboWeb")
public class EnterpriseComboWebImp implements EnterpriseComboWeb{
	
	@Autowired
	EnterpriseComboService enterpriseComboService;
	
	/**
	 * 根据enterpriseId查询企业当前月套餐使用明细
	 * @return
	 */
	@Override
	public EnterpriseComboResponse getEntComboDetail(String ccicId, String enterpriseId, CommonRequest creq) {
		EnterpriseComboResponse enterpriseComboResponse = new EnterpriseComboResponse();
		enterpriseComboResponse.setReturnCode("0");
		enterpriseComboResponse.setMsg("success");
		try {
			EnterpriseCombo enterpriseCombo = enterpriseComboService.getEntComboDetail(ccicId, enterpriseId);
			if(enterpriseCombo != null){
				enterpriseComboResponse.setEnterpriseCombo(enterpriseCombo);
			} else {
				enterpriseComboResponse.setReturnCode("201");
				enterpriseComboResponse.setMsg("企业" + enterpriseId +"本月未生成套餐数据");
			}
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			enterpriseComboResponse.setReturnCode("101");
			enterpriseComboResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			enterpriseComboResponse.setReturnCode("999");
			enterpriseComboResponse.setMsg("其他异常，请联系管理员！");
		}
		return enterpriseComboResponse;
	}
}
