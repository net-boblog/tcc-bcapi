package com.tinet.api.boss2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.TrunkDao;
import com.tinet.api.boss2.model.EnterpriseTrunk;
import com.tinet.api.boss2.model.Trunk;
import com.tinet.common.inc.Const;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 客户热线号码业务逻辑处理层
 * <p>
 *  FileName： TrunkService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("trunkService")
public class TrunkService {
	@Autowired
	private TrunkDao trunkDao;
	
	/**
	 * 查询一个企业的所有热线号码，对于400/1010号码，直线号码他们对应的目的码含区号。
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public List<Trunk> getTrunkRent(String ccicId, Integer enterpriseId){
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId); 
		// 查询企业trunk表中所有号码，返回直线号码和手机虚拟号码
		Set<EnterpriseTrunk> trunkSet = trunkDao.getAllTrunk(enterpriseId);
		List<Trunk> trunkList = new ArrayList<Trunk>();
		for (Iterator<EnterpriseTrunk> iterator = trunkSet.iterator(); iterator.hasNext();) {
			EnterpriseTrunk entTrunk = (EnterpriseTrunk) iterator.next();
			int type = entTrunk.getType().intValue();
			if (type == Const.ENTERPRISE_TRUNK_TYPE_NOT_BIND || type == Const.ENTERPRISE_TRUNK_TYPE_MOBILE_VIRTUAL) {
				Trunk trunk = new Trunk(entTrunk.getId(), entTrunk.getNumberTrunk(), entTrunk.getType(), 
						entTrunk.getRent(), entTrunk.getAreaCode(), entTrunk.getCreateTime());
				trunkList.add(trunk);
			}
		}
		return trunkList;
	}
	
	/**
	 * 修改企业直线号码或手机虚拟号码的月功能费
	 * @author louxue
	 * @param ccicId 平台ID
	 * @param enterpriseId 企业ID
	 * @param idChange trunk表id串，多个用英文逗号分隔
	 * @param rentChange 号码月租字符串，多个用英文逗号分隔
	 */
	public void saveTrunkRent(String ccicId, Integer enterpriseId, String idChange, String rentChange) {
		// 切换数据源id
		DatabaseContextHolder.setDBType(ccicId);
		trunkDao.saveTrunkRent(enterpriseId, idChange, rentChange);
	}

}
