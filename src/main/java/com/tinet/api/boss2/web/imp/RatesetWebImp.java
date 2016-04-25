package com.tinet.api.boss2.web.imp;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Rateset;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.RatesetListResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
import com.tinet.api.boss2.service.RatesetService;
import com.tinet.api.boss2.web.RatesetWeb;

@WebService(endpointInterface = "com.tinet.api.boss2.web.RatesetWeb")
public class RatesetWebImp implements RatesetWeb{

	@Autowired
	RatesetService ratesetService;
	
	/**
	 * 查询资费套餐
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public RatesetListResponse getAllRateset(String ccicId, CommonRequest creq){
		RatesetListResponse ratesetListResponse = new RatesetListResponse();
		ratesetListResponse.setReturnCode("0");
		ratesetListResponse.setMsg("success");
		try {
			List<Rateset> list = ratesetService.getAllRateset(ccicId);
			ratesetListResponse.setList(list);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			ratesetListResponse.setReturnCode("101");
			ratesetListResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			ratesetListResponse.setReturnCode("999");
			ratesetListResponse.setMsg("其他异常，请联系管理员！");
		}
		return ratesetListResponse;
	}
	
	/**
	 * 保存资费套餐组
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param rateset 费率套餐组对象
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse saveRateset(String ccicId, Rateset rateset, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			ratesetService.saveRateset(ccicId, rateset);
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
	
	/**
	 * 修改费率套餐组
	 * @author 战春雨
	 * @param ccicId 平台ID
	 * @param rateset 费率套餐组对象。
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse updateRateset(String ccicId, Rateset rateset, CommonRequest creq){
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			ratesetService.updateRateset(ccicId, rateset);
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
	
	/**
	 * 删除一条费率套餐组
	 * @author zhancy
	 * @param ccicId 平台id
	 * @param ratesetId 套餐组id
	 * @param creq 数据签名
	 * @return
	 */
	@Override
	public CommonResponse deleteRatesetset(String ccicId, Integer ratesetId, CommonRequest creq) {
		CommonResponse cResponse = new CommonResponse();
		cResponse.setReturnCode("0");
		cResponse.setMsg("success");
		try {
			String rsInfo = ratesetService.deleteRatesetset(ccicId, ratesetId);
			if (rsInfo!=null && !"".equals(rsInfo) ) {
				cResponse.setReturnCode("301");
				cResponse.setMsg("success");
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
	
	/**
	 * 获取套餐组名称
	 * @author zhancy
	 */
	@Override
	public StringDataResponse getRatesetName(String ccicId, Integer ratesetId) {
		StringDataResponse sdResponse = new StringDataResponse();
		sdResponse.setReturnCode("0");
		sdResponse.setMsg("success");
		try {
			sdResponse.setData(ratesetService.getRatesetName(ccicId, ratesetId));
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			sdResponse.setReturnCode("101");
			sdResponse.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			sdResponse.setReturnCode("999");
			sdResponse.setMsg("其他异常，请联系管理员！");
		}
		return sdResponse;
	}
}
