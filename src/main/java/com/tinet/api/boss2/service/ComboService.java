package com.tinet.api.boss2.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.ComboDao;
import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.Combo;
import com.tinet.common.util.StringUtil;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 外呼套餐包业务逻辑处理层
 * <p>
 *  FileName： ComboService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("comboService")
public class ComboService {
	@Autowired
	private ComboDao comboDao;
	
	/**
	 * 查询企业某时刻订购的外呼套餐信息
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public Combo getEntCombo(String ccicId, Integer enterpriseId, Date date) {
		DatabaseContextHolder.setDBType(ccicId); // 切换数据源id
		return comboDao.getEntCombo(enterpriseId, date);
	}
	
	/**
	 * 查询外呼套餐
	 * @author zcy
	 * @param ccicId 平台ID
	 * @return
	 */
	public List<Combo> getAllCombo(String ccicId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		return comboDao.getAllCombo();
	}
	
	/**
	 * 新增外呼套餐
	 * @author zcy
	 * @param ccicId 平台ID
	 * @param comboBoss Combo对象
	 * @return
	 */
	public void insert(String ccicId, Combo comboBoss){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		comboDao.insert(comboBoss);
	}
	
	/**
	 * 更新外呼套餐
	 * @author zcy
	 * @param ccicId 平台ID
	 * @param comboBoss Combo对象
	 * @return
	 */
	public void update(String ccicId, Combo comboBoss){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		comboDao.update(comboBoss);
	}
	
	/**
	 * 同步boss2外呼套餐到ccic2
	 * @author zcy
	 * @param ccicId 平台ID
	 * @param comboListBoss combo在boss2中的集合
	 * @return
	 */
	public String syncCombo2ccic(List<Ccic> ccicList, List<Combo> comboListBoss, String maxId){
		String ccicCantCon = "";
		for(Ccic ccic : ccicList){
			// 切换数据源id
			DatabaseContextHolder.setDBType(ccic.getId().toString());
			try {
				List<Combo> comboListCcic = comboDao.getAllCombo();
				comboDao.syncCombo2ccic(ccic, comboListBoss, comboListCcic, maxId);
			} catch (Exception e) {
				ccicCantCon += ccic.getCcicName()+",";
				e.printStackTrace();
			}
		}
		if(!"".equals(ccicCantCon)){
			ccicCantCon = StringUtil.substringBeforeLast(ccicCantCon, ",");
		}
		return ccicCantCon;
	}
	
}
