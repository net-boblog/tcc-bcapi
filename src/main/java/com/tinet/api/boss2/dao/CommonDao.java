package com.tinet.api.boss2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

/**
 * 数据库非表对象读写
 * <p>
 *  FileName： CommonDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("commonDao")
public class CommonDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public class LongMapper implements RowMapper<Long> {
		public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getLong(0);
		}
	}

	/**
	 * 获取事务ID
	 * @author louxue
	 * @return
	 */
	public Integer getTransactionId() {
		String sql = "select nextval('transaction_seq');";
		Integer transactionId = jdbcTemplate.queryForObject(sql, Integer.class);
		return transactionId;
	}
	
	/**
	 * 执行sql
	 * @param sql
	 */
	public void executeSQL(String sql){
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 只有一列的查询结果
	 * @param sql
	 * @return
	 */
	public List<String> findBySql(String sql){
		List<String> list =  new ArrayList<String>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while (rs.next()) {
			list.add(rs.getString(0));
		}
		return list;
	}
}
