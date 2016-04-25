package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.Combo;
import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.ComboListResponse;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.service.ComboService;
import com.tinet.api.boss2.web.ComboWeb;

@WebService(endpointInterface = "com.tinet.api.boss2.web.ComboWeb")
public class ComboWebImp implements ComboWeb{
	
	@Autowired
	ComboService comboService;
	
	/**
	 * 查询所有外呼套餐信息
	 * @author zcy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public ComboListResponse getAllCombo(String ccicId, CommonRequest creq) {
		ComboListResponse comboListResponse = new ComboListResponse();
		comboListResponse.setReturnCode("0");
		comboListResponse.setMsg("success");
		try {
			List<Combo> list = comboService.getAllCombo(ccicId);
			comboListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			comboListResponse.setReturnCode("101");
			comboListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			comboListResponse.setReturnCode("999");
			comboListResponse.setMsg("其他异常，请联系管理员！");
		}
		return comboListResponse;
	}
	
	/**
	 * 同步外呼套餐信息
	 * @author zhancy
	 * @param ccicList
	 * @param comboListBoss
	 * @return
	 */
	@Override
	public CommonResponse syncCombo2ccic(List<Ccic> ccicList, List<Combo> comboListBoss, String maxId, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			String ccicCantCon = comboService.syncCombo2ccic(ccicList, comboListBoss, maxId);
			// 构造返回结果
			if (ccicCantCon!=null && !"".equals(ccicCantCon)) {
				cResponse.setReturnCode("301");
				cResponse.setMsg("平台"+ccicCantCon+"连接失败，其他平台同步成功，请联系管理员！");
			} 
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
