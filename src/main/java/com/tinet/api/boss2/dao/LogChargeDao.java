package com.tinet.api.boss2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.LogCharge;

/**
 * log_charge表读写
 * <p>
 *  FileName： LogChargeDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("logChargeDao")
public class LogChargeDao {
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	/**
	 * 记录一条充值日志
	 * @author louxue
	 * @param logCharge 充值日志对象
	 */
	@Transactional
	public void saveLogCharge(LogCharge logCharge){
		// 记录充值日志sql
		String insertSql = "insert into log_charge (transaction_id, operator, charge_type, account_id, account_type, enterprise_name, enterprise_id," 
			+ "money, previous_money, current_money, current_sum_money, comment, create_time) values ( "
			+ logCharge.getTransactionId() + ",'" 
			+ logCharge.getEntityName() + "'," 
			+ logCharge.getChargeType() + "," 
			+ logCharge.getAccountId() + "," 
			+ logCharge.getAccountType() + ",'" 
			+ logCharge.getChargedEntityName() + "'," 
			+ logCharge.getChargedEntityId() + "," 
			+ logCharge.getMoney() + "," 
			+ logCharge.getPreviousMoney() + "," 
			+ logCharge.getCurrentMoney() + "," 
			+ logCharge.getCurrentSumMoney() + ",'"
			+ logCharge.getChargeComment() + "', now());";
		jdbcTemplate.update(insertSql);
	}
	
	/**
	 * 给客户充值，冲正。
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param logCharge 充值日志对象
	 * @return
	 */
	@Transactional
	public void charge(Integer enterpriseId, LogCharge logCharge){
		int chargeType = logCharge.getChargeType().intValue();
		if (chargeType == 11 || chargeType == 36) { // 客服给客户充值 或直销经理提交赠送充值
			accountDao.chargeAccountAndCheckBizStatus(enterpriseId, logCharge.getMoney());
		} else if (chargeType == 19) { // 客服给客户冲正
			accountDao.deductAccountAndCheckBizStatus(enterpriseId, logCharge.getMoney());
		}
		
		// 记录充值日志
		this.saveLogCharge(logCharge);
	}
}
