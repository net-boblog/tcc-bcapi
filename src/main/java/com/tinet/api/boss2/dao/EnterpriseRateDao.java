package com.tinet.api.boss2.dao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * enterprise_rate表读写
 * <p>
 *  FileName： EnterpriseRateDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseRateDao")
public class EnterpriseRateDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 更新企业费率表
	 * @param enterpriseId 企业ID
	 * @param ibLeftRate 呼入客户侧费率
	 * @param ibRightRate 呼入座席侧费率
	 * @param obLeftRate 外呼客户侧费率
	 * @param obRightRate 外呼座席侧费率
	 * @param smsRate 短信费率
	 */
	@Transactional
	public void updateEnterpriseRate(Integer enterpriseId, Integer ibLeftRate, Integer ibRightRate, 
			Integer obLeftRate, Integer obRightRate, BigDecimal smsRate)  {
		String updateSql = "UPDATE enterprise_rate SET rate_ib_left = ?, rate_ib_right = ?, rate_ob_left = ?, "
				+ "rate_ob_right = ?, rate_sms = ? where enterprise_id = ?;";
		jdbcTemplate.update(updateSql, ibLeftRate, ibRightRate, obLeftRate, obRightRate, smsRate, enterpriseId);
	}
	
	/**
	 * 更新企业短信费率
	 * @param enterpriseId 企业ID
	 * @param smsRate 短信费率
	 */
	@Transactional
	public void updateEntSmsRate(Integer enterpriseId, BigDecimal smsRate)  {
		String updateSql = "UPDATE enterprise_rate SET rate_sms = ? where enterprise_id = ?;";
		jdbcTemplate.update(updateSql, smsRate, enterpriseId);
	}
	
	/**
	 * 更新企业客户呼入费率
	 * @param enterpriseId 企业ID
	 * @param ibLeftRate 客户呼入费率
	 */
	@Transactional
	public void updateEntIbLeftRate(Integer enterpriseId, Integer ibLeftRate)  {
		String updateSql = "UPDATE enterprise_rate SET rate_ib_left = ? where enterprise_id = ?;";
		jdbcTemplate.update(updateSql, ibLeftRate, enterpriseId);
	}
	
	/**
	 * 更新企业外呼客户费率
	 * @param enterpriseId 企业ID
	 * @param obLeftRate 外呼客户费率
	 */
	@Transactional
	public void updateEntObLeftRate(Integer enterpriseId, Integer obLeftRate)  {
		String updateSql = "UPDATE enterprise_rate SET rate_ob_left = ? where enterprise_id = ?;";
		jdbcTemplate.update(updateSql, obLeftRate, enterpriseId);
	}
	
	
	/**
	 * 删除企业的费率配置信息
	 * @param enterpriseId 企业ID
	 */
	@Transactional
	public void deleteEntAllRate(Integer enterpriseId) {
		String delSql = "delete from enterprise_rate where enterprise_id=?";
		jdbcTemplate.update(delSql, enterpriseId);
	}
}
