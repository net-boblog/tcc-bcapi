package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Rate;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.RateListResponse;
import com.tinet.api.boss2.service.RateService;
import com.tinet.api.boss2.web.RateWeb;

@WebService(endpointInterface = "com.tinet.api.boss2.web.RateWeb")
public class RateWebImp implements RateWeb{

	@Autowired
	RateService rateService;
	
	/**
	 * 取出一个费率套餐组的所有详细费率设置
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public RateListResponse getAllRate(String ccicId, Integer ratesetId, CommonRequest creq){
		RateListResponse rateListResponse = new RateListResponse();
		rateListResponse.setReturnCode("0");
		rateListResponse.setMsg("success");
		try {
			List<Rate> list = rateService.getAllRate(ccicId, ratesetId);
			rateListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			rateListResponse.setReturnCode("101");
			rateListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			rateListResponse.setReturnCode("999");
			rateListResponse.setMsg("其他异常，请联系管理员！");
		}
		return rateListResponse;
	}
	
	/**
	 * 向分平台添加一条费率套餐设置信息
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse saveRate(String ccicId, Rate rate, CommonRequest creq){
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setReturnCode("0");
		commonResponse.setMsg("success");
		try {
			rateService.saveRate(ccicId, rate);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			commonResponse.setReturnCode("101");
			commonResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setReturnCode("999");
			commonResponse.setMsg("其他异常，请联系管理员！");
		}
		return commonResponse;
	}

	/**
	 * 更新一个费率套餐组的详细费率计费规则。
	 * @author zhancy
	 * @param ccic 分平台Ccic对象。
	 * @param rate 要更新的rate对象。
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse updateRate(String ccicId, Rate rate, CommonRequest creq) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setReturnCode("0");
		commonResponse.setMsg("success");
		try {
			rateService.updateRate(ccicId, rate);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			commonResponse.setReturnCode("101");
			commonResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setReturnCode("999");
			commonResponse.setMsg("其他异常，请联系管理员！");
		}
		return commonResponse;
	}
	
	/**
	 * 删除一个费率套餐组的详细费率计费规则。
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param rateId
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse deleteRate(String ccicId, Integer rateId, CommonRequest creq) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setReturnCode("0");
		commonResponse.setMsg("success");
		try {
			rateService.deleteRate(ccicId, rateId);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			commonResponse.setReturnCode("101");
			commonResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setReturnCode("999");
			commonResponse.setMsg("其他异常，请联系管理员！");
		}
		return commonResponse;
	}
	
	/**
	 * 复制一平台所有费率套餐组和费率套餐到另一平台
	 * @author zhancy
	 * @param fromCcicId
	 * @param toCcicId
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse copy(String fromCcicId, String toCcicId, CommonRequest creq) {
		CommonResponse comnResponse = new CommonResponse();
		comnResponse.setReturnCode("0");
		comnResponse.setMsg("success");
		try {
			String rateInfo = rateService.batchCopy(fromCcicId, toCcicId);
			if (rateInfo!=null && !"".equals(rateInfo)) {
				comnResponse.setReturnCode("301");
				comnResponse.setMsg(rateInfo);
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
