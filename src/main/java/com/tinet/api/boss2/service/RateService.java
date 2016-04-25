package com.tinet.api.boss2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.RateDao;
import com.tinet.api.boss2.dao.RatesetDao;
import com.tinet.api.boss2.model.Rate;
import com.tinet.api.boss2.model.Rateset;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 资费套餐业务逻辑处理层
 * <p>
 *  FileName： RateService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("rateService")
public class RateService {
	
	@Autowired
	RateDao rateDao;
	
	@Autowired
	RatesetDao ratesetDao;
	
	/**
	 * 取出一个费率套餐组的所有详细费率设置
	 * @author zhancy
	 * @param ccicId 平台ID
	 * @param ratesetId
	 * @return
	 */
	public List<Rate> getAllRate(String ccicId, Integer ratesetId) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		List<Rate> list = rateDao.getAllRate(ratesetId);
		return list;
	}
	
	/**
	 * 向分平台添加一条费率套餐设置信息
	 * @author zcy
	 */
	public void saveRate(String ccicId, Rate rate){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		rateDao.insert(rate);
	}
	
	/**
	 * 更新一个费率套餐组的详细费率计费规则。
	 * @author zcy
	 * @param rate 要更新的rate对象。
	 */
	public void updateRate(String ccicId, Rate rate){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		rateDao.updateRate(rate);
	}
	
	/**
	 * 删除一条费率套餐设置信息
	 * @author zcy
	 * @param rateId
	 */
	public void deleteRate(String ccicId, Integer	rateId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		rateDao.deleteRate(rateId);
	}
	
	/**
	 * 复制一平台所有费率套餐组和费率套餐到另一平台
	 * @author zcy
	 * @param toCcicId
	 * @param fromCcicId
	 * @return
	 */
	public String batchCopy(String fromCcicId, String toCcicId) {
		// 切换数据源为fromCcicId，读取所有被拷贝信息
		DatabaseContextHolder.setDBType(fromCcicId); 
		List<Rate> rateListFrom = rateDao.getAll();
		List<Rateset> ratesetListFrom = ratesetDao.getAllRateSet();
		
		// 对toCcicId做数据检查，并写库
		DatabaseContextHolder.setDBType(toCcicId);
		List<Rateset> ratesetListTo = ratesetDao.getAllRateSet();
		
		// 源与目标是否冲突校验
		String msg = this.checkRateset(ratesetListFrom, ratesetListTo);
		if (msg!=null && !"".equals(msg)) {
			return msg;
		}
		
		// 构造Rate Rateset新增集合
		List<Rate> rateListAdd = new ArrayList<Rate>();
		List<Rateset> ratesetListAdd = new ArrayList<Rateset>();
		this.createObject(rateListFrom, ratesetListFrom, rateListAdd, ratesetListAdd);
		
		// 执行新增
		rateDao.batchSave(rateListAdd, ratesetListAdd);
		return msg;
	}
	
	/**
	 * 验证分平台费率套餐名称的唯一性。
	 * @param ratesetListFrom
	 * @param ratesetListTo
	 * @return 返回验证结果信息。
	 */
	public String checkRateset(List<Rateset> ratesetListFrom, List<Rateset> ratesetListTo){
		String rsName = "";
		if (ratesetListFrom!=null && ratesetListTo.size()>0) {
			for (Rateset ratesetFrom : ratesetListFrom) {
				for (Rateset ratesetTo : ratesetListTo) {
					if (ratesetFrom.getRatesetName().equals(ratesetTo.getRatesetName())) {
						rsName += ratesetFrom.getRatesetName() + "、";
					}
				}
			}
		}
		String msg = "";
		if(rsName != null && rsName.length() > 0){
			rsName = rsName.substring(0, rsName.length() - 1);
			msg += "套餐组名称 " + rsName + " 已存在!";
		}
		return msg;
	}
	
	/**
	 * 构造rateset，rate新增集合
	 * @param rateListFrom
	 * @param ratesetListFrom
	 * @param rateListAdd
	 * @param ratesetListAdd
	 */
	public void createObject(List<Rate> rateListFrom, List<Rateset> ratesetListFrom, List<Rate> rateListAdd, List<Rateset> ratesetListAdd){
		// 构造Rate集合
		for(Rate rateFrom : rateListFrom){
			Rate rateTo = new Rate();
			rateTo.setId(rateFrom.getId());
			rateTo.setPrefix(rateFrom.getPrefix());
			rateTo.setFirstTime(rateFrom.getFirstTime());
			rateTo.setFirstRate(rateFrom.getFirstRate());
			rateTo.setSecondTime(rateFrom.getFirstTime());
			rateTo.setSecondRate(rateFrom.getSecondRate());
			rateTo.setComment(rateFrom.getComment());
			rateTo.setRatesetId(rateFrom.getRatesetId());
			rateListAdd.add(rateTo);
		}
		
		// 构造Rateset集合
		for(Rateset ratesetFrom : ratesetListFrom){
			Rateset ratesetTo = new Rateset();
			ratesetTo.setId(ratesetFrom.getId());
			ratesetTo.setRatesetName(ratesetFrom.getRatesetName());
			ratesetTo.setDescription(ratesetFrom.getDescription());
			ratesetTo.setPrice(ratesetFrom.getPrice());
			ratesetListAdd.add(ratesetTo);
		}
		
		// 给rateset的id重新赋值
		int rsId = -1;
		for(Rateset ratesetAdd : ratesetListAdd){
			rsId = ratesetDao.getNextSeq();
			Integer oldRatesetId = ratesetAdd.getId();
			ratesetAdd.setId(rsId);
			for(Rate rateAdd : rateListAdd){
				if(rateAdd.getRatesetId().intValue() == oldRatesetId.intValue()){
					rateAdd.setRatesetId(rsId);
				}
			}
		}
	}
}
