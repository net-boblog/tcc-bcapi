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

import com.tinet.api.boss2.model.Rate;
import com.tinet.api.boss2.model.Rateset;
import com.tinet.common.util.DateUtil;

/**
 * rate表读写
 * <p>
 *  FileName： RateDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("rateDao")
public class RateDao {
	
	@Autowired
	RatesetDao ratesetDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 向分平台添加一个费率组，添加费率套餐组时默认添加这个费率套餐。
	 * @author 战春雨
	 * @param rateset 费率组对象。
	 * @return
	 */
	@Transactional
	public void insert(Rateset rateset){
		String sql = "insert into rate(rateset_id, prefix, first_time, first_rate, second_time, second_rate, comment) values(?, ?, ?, ?, ?, ?, ?)";
		int ratesetId = rateset.getId();
		for(int i = 0; i < 10; i++){
			jdbcTemplate.update(sql, ratesetId, i, 60, rateset.getPrice(), 60, rateset.getPrice(), i+"开头");
		}
		jdbcTemplate.update(sql, ratesetId, "unknown_number", 60, rateset.getPrice(), 60, rateset.getPrice(), "未知号码");
	}
	
	/**
	 * 向分平台添加一条费率套餐设置信息
	 * @author zcy
	 */
	@Transactional
	public void insert(Rate rate){
		String sql = "insert into rate(rateset_id, prefix, first_time, first_rate, second_time, second_rate, comment) values(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, rate.getRatesetId(), rate.getPrefix(), rate.getFirstTime(), rate.getFirstRate(), rate.getSecondTime(), rate.getSecondRate(), rate.getComment());
	}
	
	/**
	 * 取出一个费率套餐组的所有详细费率设置
	 * @author zcy
	 */
	public List<Rate> getAllRate(Integer ratesetId)  {
		List<Rate> ratesetList = new  ArrayList<Rate>();
		String sql = "select r.id, r.prefix, r.first_time, r.first_rate, r.second_time, r.second_rate, r.comment, r.create_time from rate r where r.rateset_id = ? order by prefix ";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, ratesetId);
		while(rs.next()){
			Rate rate = new Rate();
			rate.setId(rs.getInt("id"));
			rate.setPrefix(rs.getString("prefix"));
			rate.setFirstTime(rs.getInt("first_time"));
			rate.setFirstRate(rs.getBigDecimal("first_rate"));
			rate.setSecondTime(rs.getInt("second_time"));
			rate.setSecondRate(rs.getBigDecimal("second_rate"));
			rate.setComment(rs.getString("comment"));
			try {
				rate.setCreateTime(DateUtil.parse(rs.getString("create_time"),DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
			} catch (InvalidResultSetAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ratesetList.add(rate);
		}
		return ratesetList;
	}
	
	/**
	 * 获取分平台的全部费率套餐组详细费率计费规则
	 * @author zcy
	 */
	public List<Rate> getAll(){
		List<Rate> ratesetList = new  ArrayList<Rate>();
		String sql = "select id,rateset_id,prefix,first_time,first_rate,second_time ,second_rate,comment from rate";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while(rs.next()){
			Rate rate = new Rate();
			rate.setId(rs.getInt("id"));
			rate.setRatesetId(rs.getInt("rateset_id"));
			rate.setPrefix(rs.getString("prefix"));
			rate.setFirstTime(rs.getInt("first_time"));
			rate.setFirstRate(rs.getBigDecimal("first_rate"));
			rate.setSecondTime(rs.getInt("second_time"));
			rate.setSecondRate(rs.getBigDecimal("second_rate"));
			rate.setComment(rs.getString("comment"));
			ratesetList.add(rate);
		}
		return ratesetList;
	}
	
	/**
	 * 修改一条费率套餐设置信息
	 * @author zcy
	 * @param rate 要更新的rate对象。
	 */
	@Transactional
	public void updateRate(Rate rate){
		String sql = "update rate set prefix=?, first_time=?, first_rate=?, second_time=?, second_rate=?, comment=? where id=?";
		jdbcTemplate.update(sql, rate.getPrefix(), rate.getFirstTime(), rate.getFirstRate(), rate.getSecondTime(), rate.getSecondRate(), rate.getComment(), rate.getId());
	}
	
	/**
	 * 删除一条费率套餐设置信息
	 * @author zcy
	 * @param rateId
	 */
	@Transactional
	public void deleteRate(Integer rateId){
		String sql = "delete from rate where id =?";
		jdbcTemplate.update(sql, rateId);
	}
	
	/**
	 * 批量保存
	 * @param rateListAdd
	 * @param ratesetListAdd
	 */
	@Transactional
	public void batchSave(List<Rate> rateListAdd, List<Rateset> ratesetListAdd){
		// 保存Rateset
		for(Rateset rateset : ratesetListAdd){
			ratesetDao.insert(rateset);
		}
		
		// 保存Rate
		for(Rate rate : rateListAdd){
			this.insert(rate);
		}
	}
}
