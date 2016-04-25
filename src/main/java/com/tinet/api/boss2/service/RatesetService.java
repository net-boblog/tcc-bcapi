package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.RatesetDao;
import com.tinet.api.boss2.model.Rateset;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 资费套餐业务逻辑处理层
 * <p>
 *  FileName： RatesetService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("ratesetService")
public class RatesetService {
	
	@Autowired
	RatesetDao ratesetDao;
	
	/**
	 * 查询资费套餐
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @return
	 */
	public List<Rateset> getAllRateset(String ccicId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		// 获取资费套餐信息
		List<Rateset> list = ratesetDao.getAllRateSet();
		return list;
	}
	
	/**
	 * 保存资费套餐组
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param rateset 费率套餐组对象
	 * @return
	 */
	public void saveRateset(String ccicId, Rateset rateset){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		ratesetDao.saveRateset(rateset);
	}
	
	/**
	 * 修改费率套餐组
	 * @author 战春雨
	 * @param rateset 费率套餐组对象。
	 * @return
	 */
	public void updateRateset(String ccicId, Rateset rateset){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		ratesetDao.updateRateset(rateset);
	}
	
	/**
	 * 删除平台上一条费率套餐组
	 * @author zhancy
	 * @param ccicId 平台id
	 * @param id 费率套餐组id
	 */
	public String deleteRatesetset(String ccicId, Integer id) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		return ratesetDao.deleteRateset(id);
	}
	
	/**
	 * 获取费率套餐组名称
	 * @author zhancy
	 * @param ccicId 平台id
	 * @param ratesetId 费率套餐组id
	 * @return
	 */
	public String getRatesetName(String ccicId, Integer ratesetId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		return ratesetDao.getRatesetName(ratesetId);
	}
	
}
