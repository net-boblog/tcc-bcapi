package com.tinet.api.boss2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.SbcDao;
import com.tinet.api.boss2.model.Sbc;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 平台sbc业务逻辑处理层
 * <p>
 *  FileName： SbcService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("sbcService")
public class SbcService {
	
	@Autowired
	SbcDao sbcDao;
	
	/**
	 * 获取平台sbc
	 * @param ccicId
	 * @return
	 */
	public List<Sbc> getAllSbc(String ccicId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		return sbcDao.getAllSbc();
	}
	
	/**
	 * sbc表新增一条数据
	 * @author zhancy
	 * @param ccicId
	 * @param host
	 * @param port
	 * @param password
	 * @param timeout
	 * @param areaCode
	 * @param callLimit
	 * @param active
	 * @param mac
	 */
	public void saveSbc(String ccicId, String host, String port, String password, 
			String timeout, String areaCode, String callLimit, String active, String mac){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		sbcDao.insert(host, port, password, timeout, areaCode, callLimit, active, mac);
	}
	
	/**
	 * 批量更新：包括数据和密码
	 * @author zhancy
	 * @param ccicId
	 * @param host
	 * @param port
	 * @param password
	 * @param timeout
	 * @param areaCode
	 * @param callLimit
	 * @param active
	 * @param mac
	 * @param ids
	 * @param indexs
	 */
	public void bathcUpdateSbc(String ccicId, List<Sbc> sbcList){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		sbcDao.bathcUpdateSbcAndPwd(sbcList);
	}
	
	/**
	 * 批量删除
	 * @author zhancy
	 * @param ccicId
	 * @param ids
	 * @param indexs
	 */
	public void batchDeleteSbc(String ccicId, List<Integer> ids){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		sbcDao.batchDeleteSbc(ids);
	}
	
}
