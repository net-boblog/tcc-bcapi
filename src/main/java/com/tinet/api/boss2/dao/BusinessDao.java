package com.tinet.api.boss2.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Enterprise;
import com.tinet.common.inc.Const;

/**
 * business表读写
 * <p>
 *  FileName： BusinessDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("businessDao")
public class BusinessDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	/**
	 * 查询企业业务状态
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public Integer getEntBizStatus(Integer enterpriseId){
		String qrySql = "select status from business where enterprise_id=?;";
		return jdbcTemplate.queryForObject(qrySql, new Object[]{enterpriseId}, Integer.class);
	}
	
	/**
	 * 查询企业是否测试账号
	 * @param enterpriseId 企业ID
	 * @return 返回是否测试账号：0-不是，1-是
	 */
	public Integer getEntIsTest(Integer enterpriseId){
		String qrySql = "select is_test from business where enterprise_id=?;";
		return jdbcTemplate.queryForObject(qrySql, new Object[]{enterpriseId}, Integer.class);
	}
	
	/**
	 * 测试账户转正
	 * @author louxue
	 * @param enterpriseIds 所有转正企业id
	 * @return
	 */
	@Transactional
	public void executeTest2Direct(String enterpriseIds) {
		String sql = "update business set is_test =? where enterprise_id in(" + enterpriseIds + ") and is_test =?;";
		jdbcTemplate.update(sql, Const.ENTERPRISE_TEMP_IS_TEST_NO, Const.ENTERPRISE_TEMP_IS_TEST_YES);
	}
	
	/**
	 * 保存企业business信息
	 * @param enterprise 存储企业信息对象
	 */
	@Transactional
	public void saveBusinessInfo(Enterprise enterprise){
		StringBuffer updateSql = new StringBuffer("update business ");
		updateSql.append("set ")
			.append(" cti_id = ").append(enterprise.getCtiId()).append(",")
			.append(" bargain_no = '").append(enterprise.getBargainNo()).append("',")
			.append(" client_tel = ").append(enterprise.getClientTel()).append(",")
			.append(" client_web = ").append(enterprise.getClientWeb()).append(",")
			.append(" client_free = ").append(enterprise.getClientFree()).append(",")
			.append(" rent = ").append(enterprise.getRent()).append(",")
			.append(" inbound_call_limit = ").append(enterprise.getInboundCallLimit()).append(",")
			.append(" is_idd = ").append(enterprise.getIsIdd()).append(",")
			.append(" product_type = ").append(enterprise.getProductType()).append(",")
			.append(" client_area = '").append(enterprise.getClientArea()).append("'")
//			.append("client_css = '").append(enterprise.getClientCss()).append("', ")
//			.append("crm_type = ").append(enterprise.getCrmType()).append(", ")
//			.append("crm_url = '").append(enterprise.getCrmUrl()).append("', ")
//			.append("popscreen_type = ").append(enterprise.getPopscreenType()).append(", ")
//			.append("popscreen_url = '").append(enterprise.getPopscreenUrl()).append("', ")
//			.append("api_support = '").append(enterprise.getApiSupport()).append("', ")
//			.append("service_end = to_timestamp('")	.append(DateUtil.format(enterprise.getServiceEnd(), DateUtil.FMT_DATE_YYYY_MM_DD)).append("','YYYY-MM-DD') ")
			.append(" where id = ").append(enterprise.getBusinessId()).append(";");
		jdbcTemplate.update(updateSql.toString());
	}
	
	/**
	 * 更新企业电脑座席月功能费最后一次扣费的扣费时间段的截止时间点。
	 * @param enterpriseId 企业ID
	 * @param rentEnd 最后一次扣费的扣费时间段的截止时间点，格式： yyyy-MM-DD 23:59:59
	 */
	@Transactional
	public void updateRentEnd(Integer enterpriseId, Date rentEnd){
		StringBuffer updateRentEndSql = new StringBuffer("");
		updateRentEndSql.append("update business set rent_end= ? where enterprise_id =?");
		jdbcTemplate.update(updateRentEndSql.toString(), rentEnd, enterpriseId);
	}
	
	/**
	 * 更新企业电脑座席月租
	 * @param enterpriseId 企业ID
	 * @param rent 座席月租
	 */
	@Transactional
	public void updateRent(Integer enterpriseId, BigDecimal rent){
		String updateRentSql = "update business set rent= ?  where enterprise_id =?;";
		jdbcTemplate.update(updateRentSql, rent, enterpriseId);
	}
	
	/**
	 * 更新企业座席数，同时检查和变更呼入并发限制数。
	 * @param enterpriseId 企业ID
	 * @param clientWeb 付费电脑座席数
	 * @param clientFree 免费电脑座席数
	 * @param clientTel 电话座席数
	 */
	@Transactional
	public void updateClientCount(Integer enterpriseId, Integer clientWeb, Integer clientFree, Integer clientTel){
		String updateSql = "update business set client_web=?, client_free=?, client_tel=?, inbound_call_limit = (case when inbound_call_limit < ? then ? "
						+ "else inbound_call_limit end) where enterprise_id = ?";
		jdbcTemplate.update(updateSql, clientWeb, clientFree, clientTel, 
				Math.ceil((clientWeb+clientFree)*1.5), Math.ceil((clientFree+clientWeb)*1.5), enterpriseId);
		
	}
	
	/**
	 * 修改企业业务状态
	 * @param enterpriseId
	 * @param status
	 */
	@Transactional
	public void updateBizStatus(Integer enterpriseId, Integer status) {
		String updateSql = "update business set status =? where enterprise_id=?;";
		jdbcTemplate.update(updateSql, status, enterpriseId);
	}
	
	/**
	 * 修改企业合同结束时间
	 * @param enterpriseId
	 * @param status
	 */
	@Transactional
	public void updateServiceEnd(Integer enterpriseId, Date endDate) {
		String updateSql = "update business set service_end =? where enterprise_id=?;";
		jdbcTemplate.update(updateSql, endDate, enterpriseId);
	}
}
