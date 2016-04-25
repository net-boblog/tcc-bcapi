package com.tinet.api.boss2.dao;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.EnterpriseTrunk;
import com.tinet.api.boss2.model.MobileVirtual;
import com.tinet.api.boss2.model.Trunk;
import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;
import com.tinet.common.util.StringUtil;

/**
 * trunk表读写
 * <p>
 *  FileName： TrunkDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("trunkDao")
public class TrunkDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	/**
	 * 查询企业所有目的号码
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public Set<EnterpriseTrunk> getAllTrunk(Integer enterpriseId) {
		Set<EnterpriseTrunk> entTrunkList = new HashSet<EnterpriseTrunk>();
		StringBuffer qrySql = new StringBuffer();
		qrySql.append("select id, number_trunk, area_code, is_billing, type, rent, create_time ")
			.append(" from trunk ")
			.append(" where enterprise_id = ?")
			.append(" and type in(" + Const.ENTERPRISE_TRUNK_TYPE_NOT_BIND + "," + 
				Const.ENTERPRISE_TRUNK_TYPE_BIND + "," + Const.ENTERPRISE_TRUNK_TYPE_MOBILE_VIRTUAL + ") ")
			.append(" order by rent desc ,type desc, number_trunk");
		List<Map<String, Object>> entHotlineMapList = jdbcTemplate.queryForList(qrySql.toString(), enterpriseId);
		for (Map<String, Object> map : entHotlineMapList) {
			EnterpriseTrunk entTrunk = new EnterpriseTrunk();
			entTrunk.setId((Integer)map.get("id"));
			entTrunk.setNumberTrunk(map.get("number_trunk").toString());
			entTrunk.setAreaCode(map.get("area_code").toString());
			entTrunk.setIsBilling((Integer)map.get("is_billing"));
			entTrunk.setType((Integer)map.get("type"));
			entTrunk.setRent(new BigDecimal(map.get("rent").toString()));
			try {
				entTrunk.setCreateTime(DateUtil.parse(map.get("create_time").toString(), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			entTrunkList.add(entTrunk);
		}
		return entTrunkList;
		
	}
	
	/**
	 * 修改企业直线号码或手机虚拟号码的月功能费
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param idChange trunk表id串，多个用英文逗号分隔
	 * @param rentChange 号码月租字符串，多个用英文逗号分隔
	 */
	@Transactional
	public void saveTrunkRent(Integer enterpriseId, String idChange, String rentChange) {
		String sql = "update trunk set rent = ? where enterprise_id = ? and id = ?";
		if(StringUtil.isNotEmpty(idChange)){
			String[] idChangeArry = idChange.split(",");
			String[] rentChangeArry = rentChange.split(",");
			for (int i = 0; i < idChangeArry.length; i++) {
				jdbcTemplate.update(sql, new BigDecimal(rentChangeArry[i]), enterpriseId, Integer.parseInt(idChangeArry[i]));
			}
		}
	}
	
	/**
	 * 修改企业的计费目的码
	 * @param enterpriseId 企业ID
	 * @param billTrunk 计费目的码，不含区号，多个之间使用英文逗号分隔。
	 */
	@Transactional
	public void updateBillingTrunk(Integer enterpriseId, String billTrunk) {
		// 先修改所有目的码为不计费
		String updateSql = "update trunk set is_billing = " + Const.ENTERPRISE_TRUNK_IS_BILLING_NO + "where enterprise_id = ?";
		jdbcTemplate.update(updateSql, enterpriseId);
		
		// 标记计费的目的码
		if(billTrunk!=null && !"".equals(billTrunk)){
			updateSql = "update trunk set is_billing = " + Const.ENTERPRISE_TRUNK_IS_BILLING_YES + "where enterprise_id = " + enterpriseId +
			"and number_trunk in ('"+ billTrunk.replaceAll(",", "','") +"')";
			jdbcTemplate.update(updateSql);
		}
		
	}
	
	/**
	 * 删除企业trunk表所有号码
	 * @param enterpriseId 企业ID
	 */
	@Transactional
	public void deleteEntAllTrunk(Integer enterpriseId) {
		String delSql = "delete from trunk where enterprise_id = ?";
		jdbcTemplate.update(delSql, enterpriseId);
	}
	
	/**
	 * 删除企业目的码
	 * @param argsList
	 */
	@Transactional
	public void deleteTrunk(List<Object[]> argsList) {
		
		if (argsList!=null && argsList.size()>0) {
			String delSql = "delete from trunk where number_trunk=? and enterprise_id = ?";
			int[] argsTypes = {Types.VARCHAR, Types.INTEGER};
			jdbcTemplate.batchUpdate(delSql, argsList, argsTypes);
		}
	}
	
	/**
	 * 删除企业目的码 
	 * @param enterpriseId 企业ID
	 * @param entTrunkDelList 要删除的直线号码集合
	 * @param entHotlineTrunkDelList 要删除的热线号码的目的码集合
	 * @param mobileVirtualDelList 要删除的手机虚拟号码集合
	 */
	@Transactional
	public void deleteEntTrunk(Integer enterpriseId, List<Trunk> entTrunkDelList, List<Trunk> entHotlineTrunkDelList, 
			List<MobileVirtual> mobileVirtualDelList) {
		
		List<Object[]> trunkArgsList = new ArrayList<Object[]>();
		
		if (entTrunkDelList!=null) {
			for(Trunk delTrunk : entTrunkDelList) {
				trunkArgsList.add(new Object[]{delTrunk.getTrunk(), enterpriseId});
			}
		}
		
		if (entHotlineTrunkDelList!=null) {
			for (Trunk delTrunk : entHotlineTrunkDelList) {
				trunkArgsList.add(new Object[]{delTrunk.getTrunk(), enterpriseId});
			}
		}
		
		if (mobileVirtualDelList!=null) {
			for (MobileVirtual mobileDel : mobileVirtualDelList) {
				trunkArgsList.add(new Object[]{mobileDel.getMobile(), enterpriseId});
			}
		}
		this.deleteTrunk(trunkArgsList);
	}
	
	/**
	 * trunk表批量新增数据
	 * @param argsList
	 */
	@Transactional
	public void insertTrunk(List<Object[]> argsList){
		String instSql = "INSERT INTO trunk(enterprise_id, area_code, number_trunk, is_billing, type, \"comment\",rent) values (?,?,?,?,?,?,?);";
		int[] argsTypes = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.NUMERIC};
		jdbcTemplate.batchUpdate(instSql, argsList, argsTypes);
	}
	
	/**
	 * trunk表新增数据
	 * @param enterpriseId 企业ID
	 * @param billingTrunk	所有计费号码，不含区号，多个之间英文逗号分隔
	 * @param trunkWithAreaCode 企业的目的码与区号的key-value对
	 * @param entTrunkAdd 新增的直线号码集合
	 * @param entHotlineTrunkAdd 新增的热线号码的目的码的集合
	 * @param mobileVirtualAdd 新增的手机虚拟号码集合
	 */
	@Transactional
	public void insertEnterpriseTrunk(Integer enterpriseId,	String billingTrunk, Map<String, String> trunkWithAreaCode, List<Trunk> entTrunkAdd, 
			List<Trunk> entHotlineTrunkAdd, List<MobileVirtual> mobileVirtualAdd) {
		// 新增目的号码
		List<Object[]> instTrunkArgsList = new ArrayList<Object[]>();
		
		if (entTrunkAdd!=null) {
			for(Trunk entTrunk : entTrunkAdd) {
				String trunk = entTrunk.getTrunk();
				Integer isBilling = billingTrunk.contains(trunk) ? Const.ENTERPRISE_TRUNK_IS_BILLING_YES : Const.ENTERPRISE_TRUNK_IS_BILLING_NO;
				instTrunkArgsList.add(new Object[]{enterpriseId, trunkWithAreaCode.get(trunk), trunk, isBilling, 
						Const.ENTERPRISE_TRUNK_TYPE_NOT_BIND, entTrunk.getComment(), entTrunk.getRent()});
			}
		}
		
		if (entHotlineTrunkAdd!=null) {
			for(Trunk entTrunk : entHotlineTrunkAdd) {
				String trunk = entTrunk.getTrunk();
				Integer isBilling = billingTrunk.contains(trunk) ? Const.ENTERPRISE_TRUNK_IS_BILLING_YES : Const.ENTERPRISE_TRUNK_IS_BILLING_NO;
				instTrunkArgsList.add(new Object[]{enterpriseId, trunkWithAreaCode.get(trunk), trunk, isBilling, 
						Const.ENTERPRISE_TRUNK_TYPE_BIND, entTrunk.getComment(), entTrunk.getRent()});
			}
		}
		
		if (mobileVirtualAdd!=null) {
			for(MobileVirtual mobileVirtual : mobileVirtualAdd) {
				String mobile = mobileVirtual.getMobile();
				Integer isBilling = billingTrunk.contains(mobile) ? Const.ENTERPRISE_TRUNK_IS_BILLING_YES : Const.ENTERPRISE_TRUNK_IS_BILLING_NO;
				instTrunkArgsList.add(new Object[]{enterpriseId, trunkWithAreaCode.get(mobile), mobile, isBilling, 
						Const.ENTERPRISE_TRUNK_TYPE_MOBILE_VIRTUAL, "", mobileVirtual.getRent()});
			}
		}
		if (instTrunkArgsList.size() > 0) {
			this.insertTrunk(instTrunkArgsList);
		}
	}
}
