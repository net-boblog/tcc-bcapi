package com.tinet.api.boss2.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Account;
import com.tinet.common.inc.Const;

/**
 * account表读写
 * <p>
 *  FileName： AccountDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("accountDao")
public class AccountDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 获取企业现金账户信息
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public Account getAccountInfo(Integer enterpriseId) {
		Account account = new Account();
		account.setEnterpriseId(enterpriseId);
		String sql = "select id, money,credit,credit_temp,credit_days,credit_temp_days,arrears_time,credit_valid_time from account where enterprise_id = ?"
				+ " and account_type = " + Const.ACCOUNT_ACCOUNT_TYPE_CASH + " and active = " + Const.ACCOUNT_ACTIVE_OK ;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, enterpriseId);
		if (rs.next()) {
			account.setId(rs.getInt("id"));
			account.setMoney(rs.getBigDecimal("money"));
			account.setCredit(rs.getBigDecimal("credit"));
			account.setCreditTemp(rs.getBigDecimal("credit_temp"));
			account.setCreditDays(rs.getInt("credit_days"));
			account.setCreditTempDays(rs.getInt("credit_temp_days"));
			if (rs.getLong("arrears_time")!= 0) {
				account.setArrearsTime(new Date(rs.getLong("arrears_time") * 1000));
			}
			if (rs.getLong("credit_valid_time")!= 0) {
				account.setCreditValidTime(new Date(rs.getLong("credit_valid_time") * 1000));
			}
		}
		return account;
	}
	
	
	@Transactional
	public void testMoney(Integer enterpriseId, BigDecimal money){
		Account account = this.getAccountInfo(enterpriseId);
		System.out.println("@@before:" + account.getMoney());
		String uptAccountSql = "update account set money=money-? where enterprise_id=? and account_type=?;";
		jdbcTemplate.update(uptAccountSql, money, enterpriseId, Const.ACCOUNT_ACCOUNT_TYPE_CASH);
		account = this.getAccountInfo(enterpriseId);
		System.out.println("@@after:" + account.getMoney());
	}
	
	/**
	 * 扣费修改企业账户余额，同时检查arears_time和业务状态
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param money 账户扣减金额,money>=0
	 * @return 返回更新的记录数updateRecNums,取值为1或2,为1表示只更新了account,为2表示该客户欠费了。
	 * @throws SQLException 
	 */
	@Transactional
	public Integer deductAccountAndCheckBizStatus(Integer enterpriseId, BigDecimal money){
		Integer updateRecNums = 0;
		int updateRecNumArry[] = {0,0};
		
		// 更新账户余额及欠费时间点
		String uptAccountSql = "update account set money=money-?, arrears_time = (case when money >= 0 and money-? <0 then ? else arrears_time end) "
				+ " where enterprise_id=? and account_type=?";
		updateRecNumArry[0] = jdbcTemplate.update(uptAccountSql, money, money, new Date().getTime()/1000, enterpriseId, Const.ACCOUNT_ACCOUNT_TYPE_CASH);
		
		// 根据账户余额及信用额度更新业务状态
		String uptBizSql = "update business set status=? where enterprise_id=? and status=?  "
				+ " and exists (select id from account where enterprise_id=? and account_type=? and (money+credit+credit_temp)<0)";
		updateRecNumArry[1] = jdbcTemplate.update(uptBizSql, Const.BUSINESS_STATUS_PAUSED, enterpriseId, Const.BUSINESS_STATUS_OK, enterpriseId, Const.ACCOUNT_ACCOUNT_TYPE_CASH );
		updateRecNums = updateRecNumArry[0] + updateRecNumArry[1];
		return updateRecNums;
	}
	
	/**
	 * 充值修改企业账户余额，同时检查arears_time和业务状态
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param money 账户充值金额,money>=0
	 * @return 返回更新的记录数updateRecNums,取值为1或2,为1表示只更新了account,为2表示该客户业务状态由欠费变为正常了。
	 * @throws SQLException 
	 */
	@Transactional
	public Integer chargeAccountAndCheckBizStatus(Integer enterpriseId, BigDecimal money){
		Integer updateRecNums = 0;
		int updateRecNumArry[] = {0,0};
		
		// 更新账户余额及欠费时间点
		String uptAccountSql = "update account set money=money+?, arrears_time = (case when money < 0 and money +? >= 0 then ? else arrears_time end) "
				+ " where enterprise_id=? and account_type=?";
		updateRecNumArry[0] = jdbcTemplate.update(uptAccountSql, money, money, 0, enterpriseId, Const.ACCOUNT_ACCOUNT_TYPE_CASH);
		
		// 根据账户余额及信用额度更新业务状态
		String uptBizSql = "update business set status=? where enterprise_id=? and status=? "
				+ " and exists (select id from account where enterprise_id=? and account_type=? and (money+credit+credit_temp)>=0)";
		updateRecNumArry[1] = jdbcTemplate.update(uptBizSql, Const.BUSINESS_STATUS_OK, enterpriseId, Const.BUSINESS_STATUS_PAUSED, enterpriseId, Const.ACCOUNT_ACCOUNT_TYPE_CASH );
		updateRecNums = updateRecNumArry[0] + updateRecNumArry[1];
		return updateRecNums;
	}
	
	/**
	 * 更新企业信用额度，同时检查arears_time和业务状态
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param credit	信用额度
	 * @param creditDays 信用有效期,自然天数
	 * @param creditType 信用类型,0-临时信用额度  1-固定信用额度
	 */
	@Transactional
	public void updateCredit(Integer enterpriseId, BigDecimal credit, Integer creditDays, Integer creditType) {
		
		// 设置信用额度为0,则返回
		if (credit.compareTo(BigDecimal.ZERO) <= 0) {
			return;
		}
		
		// 更新信用额度
		String updateSql = "";
		if (creditType == Const.CREDIT_TYPE_TEMP) { // 0-临时信用额度
			updateSql = "update account set credit_temp =?, credit_temp_days =?,  credit= 0, credit_days = 0, credit_valid_time =? where enterprise_id =? and account_type = ?";
		} else if (creditType == Const.CREDIT_TYPE_FIXED) { // 1-固定信用额度
			// 固定信用额度永久有效，设置为0
			creditDays = 0;
			updateSql = "update account set credit =?, credit_days =?, credit_temp = 0, credit_temp_days = 0, credit_valid_time =? where enterprise_id =? and account_type = ?";
		} else {
			return;
		}
		jdbcTemplate.update(updateSql, credit, creditDays, new Date().getTime()/1000, enterpriseId, Const.ACCOUNT_ACCOUNT_TYPE_CASH);
		
		// 根据账户余额及信用额度更新业务状态
		String uptBizSql = "update business set status=? where enterprise_id=? and status=? "
				+ " and exists (select id from account where enterprise_id=? and account_type=? and (money+credit+credit_temp)>=0)";
		jdbcTemplate.update(uptBizSql, Const.BUSINESS_STATUS_OK, enterpriseId, Const.BUSINESS_STATUS_PAUSED, enterpriseId, Const.ACCOUNT_ACCOUNT_TYPE_CASH );
		
	}
	
	/**
	 * 获取account表下一个序列值
	 * @return
	 */
	public Integer getNextSeq() {
		String qrySql = "select nextval('account_id_seq') as id;";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qrySql);
		if (rSet.next()) {
			return rSet.getInt("id");
		} 
		return -1;
	}
}
