package com.tinet.api.boss2.dao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.common.inc.Const;
/**
 * report_month表读写
 * <p>
 *  FileName： ReportMonthDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("reportMonthDao")
public class ReportMonthDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 根据不同的扣费类型，修正企业历史月报扣费数据。
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param feeType 扣费类型，4：手工扣一次性费用，13：座席功能费 ，17：中继占用费，16：号码月功能费，11：呼入话费，12：外呼话费
	 * @param deductMonth 扣费月份
	 * @param money 扣费金额
	 */
	@Transactional
	public void updateDeductFee(Integer enterpriseId, Integer feeType, String deductMonth, BigDecimal money){
		String updateReportMonthSql = "";
		if (feeType!=Const.LOG_DEDUCTION_FEE_TYPE_ONCE_CHARGE) { 
			// 修正企业月报手工扣费数据
			updateReportMonthSql = "update report_month set hand_charge = hand_charge+? where enterprise_id =? and month =?;";
		} else { 
			// 修正企业月报一次性费用扣费数据
			updateReportMonthSql = "update report_month set once_charge_fee = once_charge_fee+? where enterprise_id =? and month =?;";
		}
		jdbcTemplate.update(updateReportMonthSql, money, enterpriseId, deductMonth);
	}
}
