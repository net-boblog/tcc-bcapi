package com.tinet.api.boss2.dao;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.tinet.api.boss2.model.EnterpriseCombo;

/**
 * enterprise_combo表读写
 * <p>
 *  FileName： EnterpriseComboDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseComboDao")
public class EnterpriseComboDao {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 根据enterpriseId查询企业当前月套餐使用明细
	 * @return
	 */
	public EnterpriseCombo getEntComboDetail(String enterpriseId){
		EnterpriseCombo enterpriseCombo = new EnterpriseCombo();
		long nowTime = new Date().getTime()/1000;
		String sql = "select client_count,deduct_fee,threshold,start_time,end_time from enterprise_combo where enterprise_id = ? and start_time <= ? and end_time >= ? ";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, Integer.parseInt(enterpriseId), nowTime, nowTime);
		if(rs.next()){
			enterpriseCombo.setClientCount(rs.getInt("client_count"));
			enterpriseCombo.setDeductFee(rs.getBigDecimal("deduct_fee"));
			enterpriseCombo.setThreshold(rs.getBigDecimal("threshold"));
			enterpriseCombo.setStartTime(rs.getLong("start_time"));;
			enterpriseCombo.setEndTime(rs.getLong("end_time"));
			return enterpriseCombo;
		} else {
			return null;
		}
	}
}
