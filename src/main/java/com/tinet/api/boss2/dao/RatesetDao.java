package com.tinet.api.boss2.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Rateset;
import com.tinet.common.util.DateUtil;

/**
 * rateset表读写
 * <p>
 *  FileName： RateSetDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("ratesetDao")
public class RatesetDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	RateDao rateDao;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 查询平台费率套餐组信息
	 * @author zcy
	 */
	public List<Rateset> getAllRateSet()  {
		List<Rateset> ratesetList = new  ArrayList<Rateset>();
		String qrySql = "select id, rateset_name, description, price, create_time from rateset order by price asc";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(qrySql);
		while (rs.next()) {
			Rateset rateset = new Rateset();
			rateset.setId(rs.getInt("id"));
			rateset.setRatesetName(rs.getString("rateset_name"));
			rateset.setDescription(rs.getString("description"));
			rateset.setPrice(rs.getBigDecimal("price"));
			try {
				rateset.setCreateTime(DateUtil.parse(rs.getString("create_time"),DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
			} catch (InvalidResultSetAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ratesetList.add(rateset);
		}
		return ratesetList;
	}
	
	/**
	 * 向分平台添加一个费率套餐组。
	 * @author 战春雨
	 * @param ccic 分平台Ccic对象。
	 * @param rateset 费率套餐组对象。
	 * @return
	 */
	@Transactional
	public void insert(Rateset rateset){
		if (rateset.getId()!=null) {
			String sql = "insert into rateset(id,rateset_name,price,description) values(?, ?, ?, ?)";
			jdbcTemplate.update(sql, rateset.getId(), rateset.getRatesetName(), rateset.getPrice(), rateset.getDescription());
		} else {
			String sql = "insert into rateset(rateset_name,price,description) values(?, ?, ?)";
			jdbcTemplate.update(sql, rateset.getRatesetName(), rateset.getPrice(), rateset.getDescription());
		}
		
	}
	
	/**
	 * 获取rateset表下一个序列值
	 * @author zcy
	 * @return
	 */
	public Integer getNextSeq() {
		String qrySql = "select nextval('rateset_id_seq') as id;";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qrySql);
		if (rSet.next()) {
			return rSet.getInt("id");
		} 
		return -1;
	}
	
	/**
	 * 向分平台添加一个费率套餐组，并且向费率套餐中添加默认数据。
	 * @author 战春雨
	 * @param rateset 费率套餐组对象。
	 * @return
	 */
	@Transactional
	public void saveRateset(Rateset rateset){
		int id = this.getNextSeq().intValue();
		rateset.setId(id);
		this.insert(rateset);
		rateDao.insert(rateset);
	}
	
	/**
	 * 修改费率套餐组
	 * @author 战春雨
	 * @param rateset 费率套餐组对象。
	 * @return
	 */
	@Transactional
	public Integer updateRateset(Rateset rateset){
		String sql = "update rateset set rateset_name=?,price=?,description=? where id=?";
		return jdbcTemplate.update(sql, rateset.getRatesetName(), rateset.getPrice(), rateset.getDescription(), rateset.getId());
	}
	
	/**
	 * routerset表删除一条数据
	 * @author zcy
	 * @param id 费率套餐组id
	 */
	@Transactional
	public String deleteRateset(Integer id) {
		// 查询是否有企业使用
		String qurySql = "select count(*) as num from enterprise_rate where rate_ib_left=? or rate_ib_right=? or rate_ob_left=? or rate_ob_right=?";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qurySql, id, id, id, id);
		if (rSet.next()) {
			int existNum = rSet.getInt("num");
			if (existNum!=0) {
				return "该套餐组在使用中，不能删除！";
			}
		}
		String delSql = "delete from rate where rateset_id=?;delete from rateset where id=?;";
		jdbcTemplate.update(delSql, id, id);
		return null;
	}
	
	/**
	 * 获取费率套餐组名称
	 * @author zcy
	 * @param id 费率套餐组id
	 * @return
	 */
	public String getRatesetName(Integer id) {
		String qrySql = "select rateset_name from rateset where id=?";
		String name = "";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qrySql, id);
		if (rSet.next()) {
			name = rSet.getString("rateset_name");
		}
		return name;
	}
	
}
