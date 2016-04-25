package com.tinet.api.boss2.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.api.boss2.model.MobileVirtual;
import com.tinet.api.boss2.model.Trunk;
import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;

/**
 * log_deduction表读写
 * <p>
 *  FileName： LogDeductionDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("logDeductionDao")
public class LogDeductionDao {
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private ReportMonthDao reportMonthDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 记录一条扣费日志
	 * @author louxue
	 * @param logDeduction 扣费日志对象
	 */
	@Transactional
	public void saveLogDeduction(LogDeduction logDeduction){
		// 费用开始和结束时间
		String feeStartTimeStr = DateUtil.formatDate(logDeduction.getFeeStartTime(),
				DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
		String feeEndTimeStr = DateUtil.formatDate(logDeduction.getFeeEndTime(), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
		
		// 记录扣费日志log_deduction
		String sql = "insert into log_deduction (transaction_id, account_id, enterprise_id, enterprise_name, total_lowest_cost, money, previous_money, "
				+ " current_money, fee_type, fee_start_time, fee_end_time, comment)" + " values("
				+ logDeduction.getTransactionId() + ","
				+ logDeduction.getAccountId() + ","
				+ logDeduction.getEnterpriseId() + ",'"
				+ logDeduction.getEnterpriseName() + "',"
				+ logDeduction.getTotalLowestCost()	+ ","
				+ logDeduction.getMoney() + ","
				+ logDeduction.getPreviousMoney() + ","
				+ logDeduction.getCurrentMoney() + ","
				+ logDeduction.getFeeType()	+ ",'"
				+ feeStartTimeStr + "','"
				+ feeEndTimeStr + "','"
				+ logDeduction.getDeductComment() + "');";
		jdbcTemplate.update(sql);
	}
	
	/**
	 * 扣除企业当前时间至月底扣费类型为feeType扣费数量为count，单价为price的费用，并记录扣费日志
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param accountId 账户ID
	 * @param fullName 企业全称
	 * @param count 扣费数量为count
	 * @param price 扣费单价
	 * @param transId 扣费事务ID
	 * @param feeType 扣费类型
	 * @param deductComment 扣费说明
	 * @return
	 */
	@Transactional
	public LogDeduction deductFee(Integer enterpriseId, Integer accountId, String fullName, BigDecimal count, BigDecimal price, 
			Integer transId, Integer feeType, String deductComment) {
		LogDeduction logDeduction = null;
		Date nowDate = new Date();
		String firstDay = DateUtil.getDayInMonth(nowDate, true);
		String lastDay = DateUtil.getDayInMonth(nowDate, false);
		long dayToEnd = 0;
		long allDays = 0;
		try {
			dayToEnd = DateUtil.getIntervalDays(DateUtil.formatDate(nowDate, DateUtil.FMT_DATE_YYYY_MM_DD), lastDay) + 1;
			allDays = DateUtil.getIntervalDays(firstDay, lastDay) + 1;
			
			// 计算扣费金额，扣费时间段为当前时间到月底
			BigDecimal deductMoney = count.multiply(price).multiply(new BigDecimal(dayToEnd))
					.divide(new BigDecimal(allDays),3,RoundingMode.HALF_UP);
		
			if(deductMoney.compareTo(BigDecimal.ZERO) > 0) {
				// 查询企业当前账户余额
				Account account = accountDao.getAccountInfo(enterpriseId);
				
				// 构造扣费日志对象
				logDeduction = new LogDeduction(transId, accountId, enterpriseId, fullName, 
						deductMoney, account.getMoney(), account.getMoney().subtract(deductMoney), feeType, 
						nowDate, DateUtil.parse(lastDay + " 23:59:59", DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss), deductComment);
				
				// 更改账户余额和业务状态
				accountDao.deductAccountAndCheckBizStatus(enterpriseId, deductMoney);
				
				// 若是扣座席费，则更改座席月功能费最后一次扣费的截止时间
				if (feeType == Const.LOG_DEDUCTION_FEE_TYPE_CLIENT_RENT) {
					businessDao.updateRentEnd(enterpriseId, DateUtil.parse(lastDay + " 23:59:59", DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
				}
				
				// 记录扣费日志log_deduction
				this.saveLogDeduction(logDeduction);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return logDeduction;
	}
	
	/**
	 * 扣除企业当前时间至月底的新增电脑座席数的座席月功能费
	 * @author louxue
	 * @param oldEnterprise 企业信息对象
	 * @param newClientWeb 新电脑座席数
	 * @param newClientRent 原电脑座席数
	 * @param transactionId 扣费事务ID
	 * @return 返回扣费日志对象
	 */
	@Transactional
	public LogDeduction deductClientFee(Integer enterpriseId, Integer accountId, String fullName, 
			Integer newClientWeb, BigDecimal newClientRent, Integer transactionId, Integer oldClientWeb){
		
		if (newClientWeb > oldClientWeb) {
			
			// 扣费座席数
			Integer deductClientWeb = newClientWeb - oldClientWeb;
			
			// 扣费注释
			String deductComment = "企业电脑座席数由" + oldClientWeb + "修改为" + newClientWeb
					+ "，数目增加，进行扣费。当前扣费电脑座席数" + deductClientWeb+ "，座席月租：" + newClientRent + "元/席。";
			
			// 执行扣费
			LogDeduction logDeduction = this.deductFee(enterpriseId, accountId, fullName, new BigDecimal(deductClientWeb), newClientRent, 
					transactionId, Const.LOG_DEDUCTION_FEE_TYPE_CLIENT_RENT, deductComment);
			
			return logDeduction;
			
		} else {
			return null;
		}
		
		
	}
	
	/**
	 * 扣除企业当前时间至月底的新增预测外呼并发数的中继占用费
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param accountId 账户ID
	 * @param fullName 企业全称
	 * @param callLimit 当前预测外呼并发限制数
	 * @param callFee 当前每并发资费
	 * @param trunkUseFeeTransId 中继占用费扣费事务ID
	 * @param oldCallLimit 原预测外呼并发限制数
	 * @return
	 */
	@Transactional
	public LogDeduction deductTrunkUseFee(Integer enterpriseId, Integer accountId, String fullName, String callLimit, String callFee, 
			Integer trunkUseFeeTransId, String oldCallLimit) {
		// 扣费并发数
		Integer deductCallLimit = Integer.parseInt(callLimit);
		if (oldCallLimit!=null && !"".equals(oldCallLimit)) {
			deductCallLimit = Integer.parseInt(callLimit) - Integer.parseInt(oldCallLimit);
		} else {
			oldCallLimit = "0";
		}
		
		// 扣费注释
		String deductComment = "企业预测外呼并发数由" + oldCallLimit + "修改为" + callLimit
				+ "，数目增加，进行扣费。当前扣费并发数" + deductCallLimit + "，预测外呼每并发资费" + callFee + "元。";
		
		// 执行扣费
		LogDeduction logDeduction = this.deductFee(enterpriseId, accountId, fullName, new BigDecimal(deductCallLimit), new BigDecimal(callFee), trunkUseFeeTransId, 
				Const.LOG_DEDUCTION_FEE_TYPE_PREDICTIVE_CALL_FEE, deductComment);
		
		return logDeduction;
	}

	/**
	 * 扣除企业当前时间至月底的新增直线号码或手机虚拟号码的号码月租费
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param accountId	账户ID
	 * @param fullName 企业全称
	 * @param mobileVirtualAddList 新增手机虚拟号码集合
	 * @param entTrunkAddList 新增直线号码集合
	 * @param numberRentTransId 扣费事务ID
	 * @return
	 */
	@Transactional
	public LogDeduction deductNumberRent(Integer enterpriseId, Integer accountId, String fullName, 
			List<MobileVirtual> mobileVirtualAddList, List<Trunk> entTrunkAddList, Integer numberRentTransId) {
		
		// 新增号码月租总和
		BigDecimal totalRent = new BigDecimal(0);
		String mobile = "";
		String mobileRent = "";
		String trunk = "";
		String trunkRent = "";
		if (mobileVirtualAddList != null) {
			for (MobileVirtual mobileVirtual : mobileVirtualAddList) {
				BigDecimal rent = mobileVirtual.getRent();
				totalRent = totalRent.add(rent);
				mobile += mobileVirtual.getMobile() + ",";
				mobileRent += rent + "元/月,";
			}
		}
		
		if (entTrunkAddList != null) {
			for (Trunk t : entTrunkAddList) {
				totalRent = totalRent.add(t.getRent());
				trunk += t.getTrunk() + ",";
				trunkRent += t.getRent() + "元/月,";
			}
		}
		
		// 扣费注释
		String deductComment = "企业";
		if (!"".equals(mobile)) {
			deductComment += "新增手机虚拟号码：" + mobile.substring(0,mobile.length()-1) + "，月功能费：" + mobileRent.substring(0,mobileRent.length()-1)+"；";
		}
		if (!"".equals(trunk)) {
			deductComment += "新增直线号码：" + trunk.substring(0,trunk.length()-1) + "，月功能费：" + trunkRent.substring(0,trunkRent.length()-1)+"；";
		}
		
		// 执行扣费
		LogDeduction logDeduction = this.deductFee(enterpriseId, accountId, fullName, new BigDecimal("1"), totalRent, 
													numberRentTransId, Const.LOG_DEDUCTION_FEE_TYPE_TRUNK_RENT, deductComment);
		
		return logDeduction;
	}
	
	/**
	 * 删除号码或注销客户按号码扣除呼入年最低消费的低消结余
	 * @author louxue
	 * @param enterpriseId 企业ID
	 * @param accountId	账户ID
	 * @param fullName 企业全称
	 * @param hotlines 删除的热线号码集合，多个之间英文逗号分隔，格式例如 '40010102389','80203522'
	 * @param lowestCostTransId 扣费事务ID
	 * @return 返回各号码的扣费日志对象集合
	 */
	@Transactional
	public List<LogDeduction> deductLowestCost(Integer enterpriseId, Integer accountId, String fullName, 
			String hotlines, Integer lowestCostTransId){
		List<LogDeduction> logDeductions = new ArrayList<LogDeduction>();
		
		// 查询按呼入计费的各400/1010号码
		String sql = "select hotline, lowest_cost, fee_deduct_time, create_time from enterprise_hotline where hotline in (" +
				hotlines + ") and type= " + Const.ENTERPRISE_HOTLINE_TYPE_400_NUMBER +" and lowest_cost_mode = " + Const.ENTERPRISE_HOTLINE_LOWEST_COST_MODE_IB;
		List<Map<String, Object>> hotlineList = jdbcTemplate.queryForList(sql);
		
		// 对每一个号码，计算其低消结余
		Date nowDate = new Date();
		
		// 每个号码记录一条扣费日志，多个号码多条
		for (Map<String, Object> map : hotlineList) {
			
			String hotline = map.get("hotline").toString();
			BigDecimal lowestCost = new BigDecimal(map.get("lowest_cost").toString());
			Date feeDeductTime = (Date)(map.get("fee_deduct_time")!=null ? map.get("fee_deduct_time"): map.get("create_time"));
			
			// 查询cdr表，计算自上次扣低消以来的实际呼入消费总额
			String fromAndWhereSql = "from cdr_ib_" + enterpriseId + " where to_timestamp(start_time) between '"+ feeDeductTime +"' and '" + nowDate +"' and hotline ='"+hotline+"'";
			String selectUniqueIdStr = " select unique_id " + fromAndWhereSql;
			String qryCdrDetailSql = "select coalesce(sum(coalesce(cost,0)),0) as call_ib_detail_cost from cdr_ib_detail_" + enterpriseId
				+ " where to_timestamp(start_time) between '"+ feeDeductTime +"' and '" + nowDate +"' and main_unique_id in(" + selectUniqueIdStr + ")";
			String qryCdrSql = "select coalesce(sum(coalesce(cost,0)),0) as call_ib_cost " + fromAndWhereSql;
			
			String cdrIbLeftCost = "";
			String cdrIbRightCost = "";
			SqlRowSet rsCdrIb = jdbcTemplate.queryForRowSet(qryCdrSql);
			SqlRowSet rsCdrIbDetail = jdbcTemplate.queryForRowSet(qryCdrDetailSql);
			while (rsCdrIb.next()) {
				cdrIbLeftCost = rsCdrIb.getString("call_ib_cost");
			}
			
			while (rsCdrIbDetail.next()) {
				cdrIbRightCost = rsCdrIbDetail.getString("call_ib_detail_cost");
			}
			
			// 呼入总实际消费
			BigDecimal ibCost = new BigDecimal((cdrIbLeftCost == null|| cdrIbLeftCost.equals(""))?"0":cdrIbLeftCost)
				.add(new BigDecimal((cdrIbRightCost == null|| cdrIbRightCost.equals(""))?"0":cdrIbRightCost)); 
			
			// 扣费金额
			BigDecimal deductMoney = new BigDecimal(0);
			if (lowestCost.compareTo(ibCost)>0) {
				deductMoney = deductMoney.add(lowestCost.subtract(ibCost));
			}
			
			// 扣费注释
			String deductComment = "客户" + enterpriseId + "于" + DateUtil.format(nowDate, DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss) + "删除号码:";
			deductComment += hotline + "，呼入低消为"+lowestCostTransId+"元/年，截止删除时本计费周期呼入实际消费为" + ibCost + "元；";
			deductComment += "年最低消费结余为" + deductMoney + "元。";
			
			// 执行扣费
			LogDeduction logDeduction = null;
			if (deductMoney.compareTo(BigDecimal.ZERO) > 0) {
				
				// 更改账户余额和业务状态
				accountDao.deductAccountAndCheckBizStatus(enterpriseId, deductMoney);
				
				// 查询企业当前账户余额
				Account account = accountDao.getAccountInfo(enterpriseId);
				// 构造扣费日志对象
				logDeduction = new LogDeduction(lowestCostTransId, accountId, enterpriseId, fullName, 
						deductMoney, account.getMoney(), account.getMoney().subtract(deductMoney), Const.LOG_DEDUCTION_FEE_TYPE_LOWEST_COST, 
						feeDeductTime, nowDate, deductComment);
				logDeduction.setTotalLowestCost(lowestCost);
				this.saveLogDeduction(logDeduction);
				logDeductions.add(logDeduction);
			}
			
		}
		
		return logDeductions;
	}
	
	/**
	 * 对客户某费用手工扣费
	 * @author louxue
	 * @param deductMonth 扣费月份
	 * @param logDeduction 扣费日志对象
	 */
	@Transactional
	public void deductHandFee(String deductMonth, LogDeduction logDeduction) {
		Integer enterpriseId = logDeduction.getEnterpriseId();
		int feeType = logDeduction.getFeeType();
		BigDecimal money = logDeduction.getMoney();
		
		// 修正月报
		reportMonthDao.updateDeductFee(enterpriseId, feeType, deductMonth, money);
		
		// 更改账户余额,检查业务状态并修改
		if (logDeduction.getFeeType().intValue() != Const.LOG_DEDUCTION_FEE_TYPE_ONCE_CHARGE) { // 一次性费用不扣现金账户
			accountDao.deductAccountAndCheckBizStatus(enterpriseId, money);
		}
		
		// 保存扣费日志
		this.saveLogDeduction(logDeduction);
		
	}
}
