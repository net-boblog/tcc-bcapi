package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.CtiDao;
import com.tinet.api.boss2.model.Cti;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * cti业务逻辑处理层
 * <p>
 *  FileName： CtiService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("ctiService")
public class CtiService {
	
	@Autowired
	private CtiDao ctiDao;
	
	/**
	 * 查询全部cti
	 * @return
	 */
	public List<Cti> getAllCti(String ccicId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		List<Cti> list = ctiDao.getAllCti();
		return list;
	}
	
	/**
	 * 查询全部激活状态cti
	 * @return
	 */
	public List<Cti> getAll(String ccicId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		List<Cti> list = ctiDao.getAll();
		return list;
	}
	
	/**
	 * 新增
	 * @author zhancy
	 */
	public void saveCti(String ccicId, Cti cti){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		ctiDao.insert(cti);
	}
	
	/**
	 * 更新
	 * @author zhancy
	 */
	public void updateCti(String ccicId, Cti cti){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		ctiDao.update(cti);
	}
	
	/**
	 * 批量删除
	 * @author zhancy
	 */
	public void batchDelete(String ccicId, List<Integer> ids){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		ctiDao.batchDelete(ids);
	}
	
}
