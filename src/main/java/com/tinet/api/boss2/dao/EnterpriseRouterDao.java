package com.tinet.api.boss2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * enterprise_router表读写
 * <p>
 *  FileName： EnterpriseRouterDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseRouterDao")
public class EnterpriseRouterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 更新企业路由表
	 * @param enterpriseId 企业ID
	 * @param ibRouterRight 呼入呼出转座席侧路由
	 * @param obPreviewRouterLeft 预测外呼呼客户侧路由
	 * @param obPredictiveRouterLeft 预览外呼呼客户侧路由
	 */
	@Transactional
	public void updateEnterpriseRouter(Integer enterpriseId, Integer ibRouterRight, Integer obPreviewRouterLeft, Integer obPredictiveRouterLeft)  {
		String updateSql = " UPDATE enterprise_router SET ib_router_right = ?, ob_preview_router_left = ?, ob_predictive_router_left = ? "
				+ " where enterprise_id = ?;";
		jdbcTemplate.update(updateSql, ibRouterRight, obPreviewRouterLeft, obPredictiveRouterLeft, enterpriseId);
	}
	
	/**
	 * 删除企业的路由配置信息
	 * @param enterpriseId 企业ID
	 */
	@Transactional
	public void deleteEntAllRouter(Integer enterpriseId) {
		String delSql = "delete from enterprise_router where enterprise_id=?";
		jdbcTemplate.update(delSql, enterpriseId);
	}
}
