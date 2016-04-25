package com.tinet.api.boss2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.Combo;
import com.tinet.common.util.DateUtil;

/**
 * combo表读写
 * <p>
 *  FileName： ComboDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("comboDao")
public class ComboDao {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public class ComboMapper implements RowMapper<Combo> {
		public Combo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Combo combo = new Combo();
			combo.setId(rs.getInt("id"));
			combo.setName(rs.getString("name"));
			combo.setRent(rs.getBigDecimal("rent"));
			combo.setThreshold(rs.getInt("threshold"));
			combo.setPeriod(rs.getInt("period"));
			combo.setValid(rs.getInt("valid"));
			combo.setRemark(rs.getString("remark"));
			combo.setCreateTime(rs.getDate("create_time"));
			return combo;
		}
	}
	
	/**
	 * 查询企业某时刻订购的外呼套餐信息
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public Combo getEntCombo(Integer enterpriseId, Date date) {
		Combo combo = null;
		StringBuffer qrySql = 
				new StringBuffer("select id, name, rent, threshold, period, valid, remark,create_time from combo where id  = " +
				"(select combo_id from enterprise_combo_order where enterprise_id = ? and end_time = ?)");
		try {
			// 取date所在月份最后一天的日期
			Date endTimeDate = DateUtil.parse(DateUtil.getDayInMonth(date, false) + " 23:59:59", DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
			long endTimeL = endTimeDate.getTime()/1000;
			combo = jdbcTemplate.queryForObject(qrySql.toString(), new Object[]{enterpriseId, endTimeL}, new ComboMapper());
		} catch (ParseException e) {
			logger.error("日期格式转换出错！");
			e.printStackTrace();
		} catch (EmptyResultDataAccessException e) {
			logger.error("企业"+enterpriseId+"没有外呼套餐~");
			combo = null;
		}
		return combo;
		
	}
	
	/**
	 * 修改企业下月外呼套餐包
	 * @param enterpriseId 企业ID
	 * @param newComboId 下月外呼套餐包ID
	 */
	@Transactional
	public void updateNextCombo(Integer enterpriseId, Integer newComboId) {
		String updateSql = "update enterprise_combo_order set new_combo_id = ? where enterprise_id = ? ";
		jdbcTemplate.update(updateSql, newComboId, enterpriseId);
		
	}
	
	/**
	 * 外呼套餐列表
	 * @author zcy
	 */
	public List<Combo> getAllCombo(){
		List<Combo> comboList = new ArrayList<Combo>();
		String sql = "select id,name,rent,threshold,period,valid,remark from combo order by CONVERT(text2bytea(name),'utf8','gbk') asc";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while (rs.next()) {
			Combo combo = new Combo();
			combo.setId(rs.getInt("id"));
			combo.setName(rs.getString("name"));
			combo.setRent(rs.getBigDecimal("rent"));
			combo.setThreshold(rs.getInt("threshold"));
			combo.setPeriod(rs.getInt("period"));
			combo.setValid(rs.getInt("valid"));
			combo.setRemark(rs.getString("remark"));
			comboList.add(combo);
		}
		return comboList;
	}
	
	/**
	 * 同步
	 * @author zcy
	 * @param comboListBoss combo在boss2中的集合
	 * @param comboListCcic combo在ccic2中的集合
	 */
	@Transactional
	public void syncCombo2ccic(Ccic ccic, List<Combo> comboListBoss, List<Combo> comboListCcic, String maxId){
		for(Combo comboBoss : comboListBoss){		
			if(comboListCcic.contains(comboBoss)) {
				// 更新
				this.update(comboBoss);
			} else {
				// 增加
				this.insert(comboBoss);
			}
		}
		if (maxId!=null&&!maxId.equals("0")) {
			this.setNextSeq(maxId);
		}
	}
	
	/**
	 * 新增
	 * @author zcy
	 */
	@Transactional
	public void insert(Combo comboBoss){
		String sql = "insert into combo (id, name, rent, threshold, period, valid, remark) values (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, comboBoss.getId(), comboBoss.getName(), comboBoss.getRent(), comboBoss.getThreshold(), comboBoss.getPeriod(), comboBoss.getValid(), comboBoss.getRemark());
	}
	
	/**
	 * 更新
	 * @author zcy
	 */
	@Transactional
	public void update(Combo comboBoss){
		String sql = "update combo set name=?, rent=?, threshold=?, period=?, valid=?, remark=? where id = ?";
		jdbcTemplate.update(sql, comboBoss.getName(), comboBoss.getRent(), comboBoss.getThreshold(), comboBoss.getPeriod(), comboBoss.getValid(), comboBoss.getRemark(), comboBoss.getId());
	}
	
	/**
	 * 设置序列combo表的序列id
	 * @author zcy
	 */
	@Transactional
	public void setNextSeq(String maxId){
		String sql = "SELECT setval('combo_id_seq',"+ maxId +")";
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 根据comboId返回对应combo对象
	 * @author louxue
	 * @param comboId
	 * @return
	 */
	public Combo get(Integer comboId){
		Combo combo = null;
		String sql = "select id, name, rent, threshold, period, valid, remark,create_time from combo where id = ?";
		try {
			combo = jdbcTemplate.queryForObject(sql, new Object[]{comboId}, new ComboMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("id为"+comboId+"的外呼套餐不存在！");
			e.printStackTrace();
		} catch (DataAccessException e) {
			logger.error("数据库操作失败！");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("其他异常！");
			e.printStackTrace();
		}
		return combo;
	}
}
