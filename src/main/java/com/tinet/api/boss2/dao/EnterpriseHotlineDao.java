package com.tinet.api.boss2.dao;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.EnterpriseHotline;
import com.tinet.api.boss2.model.MobileVirtual;
import com.tinet.api.boss2.model.Trunk;
import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;
import com.tinet.common.util.StringUtil;

/**
 * enterprise_hotline表读写
 * <p>
 *  FileName： EnterpriseHotlineDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseHotlineDao")
public class EnterpriseHotlineDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	/**
	 * 查询企业所有热线号码
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public Set<EnterpriseHotline> getAllHotline(Integer enterpriseId) {
		Set<EnterpriseHotline> hotlineList = new HashSet<EnterpriseHotline>();
		StringBuffer qrySql = new StringBuffer();
		qrySql.append(
				" select eh.id, eh.enterprise_id, hotline, is_master, eh.number_trunk as number_trunk, area_code, eh.type as type, ")
				.append(" period, lowest_cost, lowest_cost_next, lowest_cost_mode, t.rent, fee_deduct_time, eh.create_time ")
				.append(" from enterprise_hotline eh,trunk t")
				.append(" where eh.enterprise_id = ? and eh.enterprise_id = t.enterprise_id  and eh.number_trunk = t.number_trunk")
				.append(" order by type,hotline;");
		List<Map<String, Object>> entHotlineMapList = jdbcTemplate.queryForList(qrySql.toString(), enterpriseId);
		for (Map<String, Object> map : entHotlineMapList) {
			EnterpriseHotline entHotline = new EnterpriseHotline();
			entHotline.setId((Integer)map.get("id"));
			entHotline.setEnterpriseId(enterpriseId);
			entHotline.setHotline(map.get("hotline").toString());
			entHotline.setIsMaster((Integer)map.get("is_master"));
			entHotline.setNumberTrunk(map.get("number_trunk").toString());
			entHotline.setAreaCode(map.get("area_code").toString());
			entHotline.setType((Integer)map.get("type"));
			entHotline.setPeriod((Integer)map.get("period"));
			entHotline.setLowestCost(new BigDecimal(map.get("lowest_cost").toString()));
			entHotline.setLowestCostNext(new BigDecimal(map.get("lowest_cost_next").toString()));
			entHotline.setLowestCostMode((Integer)map.get("lowest_cost_mode"));
			entHotline.setRent(new BigDecimal(map.get("rent").toString()));
			try {
				entHotline.setFeeDeductTime(map.get("fee_deduct_time") == null ? null:DateUtil.parse(map.get("fee_deduct_time").toString(), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
				entHotline.setCreateTime(DateUtil.parse(map.get("create_time").toString(), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			hotlineList.add(entHotline);
		}
		return hotlineList;
		
	}
	
	/**
	 * 查询一个企业的所有热线号码，400/1010号码和直线号码对应的目的码含区号。
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public List<EnterpriseHotline> getAllHotlineList(Integer enterpriseId){
		Set<EnterpriseHotline> hotlineSet = this.getAllHotline(enterpriseId);
		List<EnterpriseHotline> hotlineList = new ArrayList<EnterpriseHotline>();
		for (Iterator<EnterpriseHotline> iterator = hotlineSet.iterator(); iterator.hasNext();) {
			EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
			int type = entHotline.getType().intValue();
			if (type == Const.ENTERPRISE_HOTLINE_TYPE_400_NUMBER || type == Const.ENTERPRISE_HOTLINE_TYPE_DIRECT_NUMBER) {
				entHotline.setNumberTrunk(entHotline.getAreaCode()+entHotline.getNumberTrunk());
			}
			hotlineList.add(entHotline);
		}
		return hotlineList;
	}
	
	/**
	 * 修改企业的热线号码最低消费信息
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param idChange enterprise_hotline表id串，英文逗号分隔
	 * @param lowestCostChange 当前计费周期最低消费字符串，多个用英文逗号分隔
	 * @param lowestCostNextChange 下个计费周期最低消费字符串，多个用英文逗号分隔
	 * @param lowestCostModeChange 当前计费周期计费模式字符串，多个用英文逗号分隔
	 */
	@Transactional
	public void saveLowestCost(Integer enterpriseId, String idChange, String lowestCostChange, String lowestCostNextChange, String lowestCostModeChange){
		String updateSql = "update enterprise_hotline set lowest_cost=?,lowest_cost_next=?,lowest_cost_mode=? where id=? and enterprise_id=?";
		if (StringUtil.isNotEmpty(idChange)) {
			String[] idArry = idChange.split(","); 
			String[] lowestCostArry = lowestCostChange.split(","); 
			String[] lowestCostNextArry = lowestCostNextChange.split(","); 
			String[] lowestCostModeArry = lowestCostModeChange.split(","); 
			for (int i = 0; i < idArry.length; i++) {
				jdbcTemplate.update(updateSql, new BigDecimal(lowestCostArry[i]), new BigDecimal(lowestCostNextArry[i]), 
						Integer.parseInt(lowestCostModeArry[i]), Integer.parseInt(idArry[i]), enterpriseId);
			}
		}
	}
	
	/**
	 * 修改企业的主热线号码
	 * @param enterpriseId 企业ID
	 * @param masterHotline 主热线号码
	 */
	@Transactional
	public void updateMaterHotline(Integer enterpriseId, String masterHotline) {
		// 先修改企业所有热心号码都不是主热线号码
		String updateSql = "update enterprise_hotline set is_master = ? where enterprise_id = ?";
		jdbcTemplate.update(updateSql, Const.ENTERPRISE_HOTLINE_IS_MASTER_NO, enterpriseId);
		
		// 标记主热线号码，有且只有一个
		updateSql = "update enterprise_hotline set is_master =? where enterprise_id = ?  and hotline = ?";
		jdbcTemplate.update(updateSql, Const.ENTERPRISE_HOTLINE_IS_MASTER_YES, enterpriseId, masterHotline);
	}
	
	/**
	 * enterprise_hotline表批量新增数据
	 * @param argsList
	 */
	@Transactional
	public void insertHotline(List<Object[]> argsList) {
		String insterSql = "INSERT INTO enterprise_hotline(enterprise_id, hotline, is_master, lowest_cost, period, lowest_cost_next, number_trunk, type, lowest_cost_mode) "
				+ "values (?,?,?,?,?,?,?,?,?)";
		int[] argsTypes = {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.NUMERIC, Types.INTEGER, Types.NUMERIC, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
		jdbcTemplate.batchUpdate(insterSql, argsList, argsTypes);
	}
	
	/**
	 * 删除企业enterprise_hotline表所有号码
	 * @param enterpriseId 企业ID
	 */
	@Transactional
	public void deleteEntAllHotline(Integer enterpriseId) {
		String hotlineDelSql ="delete from enterprise_hotline where enterprise_id=?;";
		jdbcTemplate.update(hotlineDelSql, enterpriseId);
	}
	
	/**
	 * 删除企业热线号码
	 * @param enterpriseId 企业ID
	 * @param tempDelHots 要删除的热线号码，多个之间英文逗号分隔，格式例如 '80203521','80203522'
	 */
	@Transactional
	public void deleteHotline(Integer enterpriseId, String tempDelHots) {
		String hotlineDelSql ="delete from enterprise_hotline where enterprise_id=? and hotline in ("+tempDelHots+");";
		jdbcTemplate.update(hotlineDelSql, enterpriseId);
	}
	
	/**
	 * 修改热线号码的目的码
	 * @param enterpriseId 企业ID
	 * @param hotline 热线号码
	 * @param trunk 目的码
	 */
	@Transactional
	public void updateHotlineTrunk(List<Object[]> argsList) {
		String updateSql = "update enterprise_hotline set number_trunk = ? where hotline = ? and enterprise_id = ?";
		int[] argsTypes = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
		jdbcTemplate.batchUpdate(updateSql, argsList, argsTypes);
	}
	
	/**
	 * enterprise_hotline表的增删改
	 * @param enterpriseId 企业ID
	 * @param masterHotline	主热线号码
	 * @param period	计费周期
	 * @param entHotlineAddList 新增的400/1010号码集合
	 * @param entHotlineDelList 删除的400/1010号码集合
	 * @param entHotlineTrunkChangeList 目的码变更的400/1010号码集合
	 * @param entTrunkAddList 新增的直线号码集合
	 * @param entTrunkDelList 删除的直线号码集合
	 * @param mobileVirtualAddList 新增的手机虚拟号码集合
	 * @param mobileVirtualDelList 删除的手机虚拟号码集合
	 * @return 返回被删除的热线号码集合，多个之间英文逗号分隔，格式例如 '40010102389','80203522'
	 */
	@Transactional
	public String delAndInsEntHotline(Integer enterpriseId, String masterHotline, Integer period,
			List<EnterpriseHotline> entHotlineAddList, List<EnterpriseHotline> entHotlineDelList, List<EnterpriseHotline> entHotlineTrunkChangeList,  
			List<Trunk> entTrunkAddList, List<Trunk> entTrunkDelList, List<MobileVirtual> mobileVirtualAddList, List<MobileVirtual> mobileVirtualDelList) {
		// 删除热线号码
		String delHotlines = "";
		if (entHotlineDelList!=null && entHotlineDelList.size()>0) {
			for(EnterpriseHotline delHot :entHotlineDelList) {
				delHotlines +="'"+ delHot.getHotline() +"',";
			}
		}
		if (entTrunkDelList!=null && entTrunkDelList.size() > 0) {
			for(Trunk delHot :entTrunkDelList) {
				delHotlines +="'"+ delHot.getTrunk() +"',";
			}
		}
		if (mobileVirtualDelList!=null && mobileVirtualDelList.size()>0) {
			for(MobileVirtual delHot :mobileVirtualDelList) {
				delHotlines +="'"+ delHot.getMobile() +"',";
			}
		}
		if (StringUtil.isNotEmpty(delHotlines)) {
			delHotlines = StringUtil.substringBeforeLast(delHotlines, ",");
			this.deleteHotline(enterpriseId, delHotlines);
		}
		
		// 新增热线号码
		List<Object[]> instHotlineArgsList = new ArrayList<Object[]>();
		if (entHotlineAddList!=null) {
			for (EnterpriseHotline entHotline :entHotlineAddList) {
				String hotline = entHotline.getHotline();
				Integer ismaster = masterHotline.equals(hotline) ? Const.ENTERPRISE_HOTLINE_IS_MASTER_YES : Const.ENTERPRISE_HOTLINE_IS_MASTER_NO;
				BigDecimal lowestCost = (period == Const.ENTERPRISE_HOTLINE_PERIOD_YEAR) ? entHotline.getLowestCost() : entHotline.getLowestCost().divide(new BigDecimal(12), 3, BigDecimal.ROUND_HALF_UP);
				instHotlineArgsList.add(new Object[]{enterpriseId, hotline, ismaster, lowestCost, period, lowestCost, 
						entHotline.getNumberTrunk(), Const.ENTERPRISE_HOTLINE_TYPE_400_NUMBER, entHotline.getLowestCostMode()});
			}
		}
		if (entTrunkAddList!=null) {
			for (Trunk trunk : entTrunkAddList) {
				Integer ismaster = masterHotline.equals(trunk.getTrunk()) ? Const.ENTERPRISE_HOTLINE_IS_MASTER_YES : Const.ENTERPRISE_HOTLINE_IS_MASTER_NO;
				instHotlineArgsList.add(new Object[]{enterpriseId, trunk.getTrunk(), ismaster, BigDecimal.ZERO, period, BigDecimal.ZERO, 
						trunk.getTrunk(), Const.ENTERPRISE_HOTLINE_TYPE_DIRECT_NUMBER, Const.ENTERPRISE_HOTLINE_LOWEST_COST_MODE_IB});
			}
		}
		
		if (mobileVirtualAddList!=null) {
			for (MobileVirtual mobileVirtual: mobileVirtualAddList) {
				Integer ismaster = masterHotline.equals(mobileVirtual.getMobile()) ? Const.ENTERPRISE_HOTLINE_IS_MASTER_YES : Const.ENTERPRISE_HOTLINE_IS_MASTER_NO;
				instHotlineArgsList.add(new Object[]{enterpriseId, mobileVirtual.getMobile(), ismaster, BigDecimal.ZERO, period, BigDecimal.ZERO, 
						mobileVirtual.getMobile(), Const.ENTERPRISE_HOTLINE_TYPE_MOBILE_VIRTUAL, Const.ENTERPRISE_HOTLINE_LOWEST_COST_MODE_IB});
			}
		}
		
		if (instHotlineArgsList.size() > 0) {
			this.insertHotline(instHotlineArgsList);
		}
		
		// 修改热线号码的目的码
		List<Object[]> updateTrunkHotlineArgsList = new ArrayList<Object[]>();
		if (entHotlineTrunkChangeList!=null) {
			for(EnterpriseHotline hotline : entHotlineTrunkChangeList){
				updateTrunkHotlineArgsList.add(new Object[]{hotline.getNumberTrunk(), hotline.getHotline(), enterpriseId});
			}
			if (updateTrunkHotlineArgsList.size() > 0) {
				this.updateHotlineTrunk(updateTrunkHotlineArgsList);
			}
		}
		
		return delHotlines;
	}
	
	/**
	 * 修改号码下个计费周期的最低消费
	 * @param enterpriseId 企业ID
	 * @param newValue 热线号码及其低消的json串，{hotline1:lowestCostnext1;hotline2:lowestCostnext2;....}
	 */
	@Transactional
	public void updateLowestNext(Integer enterpriseId, String newValue){
		String updateSql = "update enterprise_hotline set lowest_cost_next=? where hotline=? and enterprise_id=?";
		JSONObject json = JSONObject.fromObject(newValue);
		JSONArray names  = json.names();
		for (int i = 0; i < names.size(); i++) {
			String hotline = names.getString(i);
			String lowestCostNext = json.getString(hotline);
			jdbcTemplate.update(updateSql, new BigDecimal(lowestCostNext), hotline, enterpriseId);
		}
		
	}
}
