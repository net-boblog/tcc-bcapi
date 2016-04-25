package com.tinet.api.boss2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * enterprise_clid表读写
 * <p>
 *  FileName： EnterpriseClidDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseClidDao")
public class EnterpriseClidDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 更新企业外显号码
	 * @param enterpriseId 企业ID
	 * @param ibClidRightType 呼入转座席外显号码类型
	 * @param IbClidRightNumber 呼入转座席外显号码
	 * @param obPreviewClidLeftType 预览外呼客户侧外显号码类型
	 * @param obPreviewClidLeftNumber 预览外呼客户侧外显号码
	 * @param obPreviewClidRightType 预览外呼座席侧外显号码类型
	 * @param obPreviewClidRightNumber 预览外呼座席侧外显号码
	 * @param obPredictiveClidLeftType 预测外呼客户侧外显号码类型
	 * @param obPredictiveClidLeftNumber 预测外呼客户侧外显号码
	 * @param obPredictiveClidRightType 预测外呼座席侧外显号码类型
	 * @param obPredictiveClidRightNumber 预测外呼座席侧外显号码
	 */
	@Transactional
	public void updateEnterpriseClid(Integer enterpriseId, Integer ibClidRightType, String IbClidRightNumber, 
			Integer obPreviewClidLeftType, 	String obPreviewClidLeftNumber, Integer obPreviewClidRightType, String obPreviewClidRightNumber, 
			Integer obPredictiveClidLeftType, String obPredictiveClidLeftNumber, Integer obPredictiveClidRightType, String obPredictiveClidRightNumber)  {
		String updateSql = "UPDATE enterprise_clid SET ib_clid_right_type = ?, ib_clid_right_number = ?, ob_preview_clid_left_type = ?, "
				+ "ob_preview_clid_left_number = ?, ob_preview_clid_right_type = ?, ob_preview_clid_right_number=?, "
				+ "ob_predictive_clid_left_type =?, ob_predictive_clid_left_number=?, ob_predictive_clid_right_type=?, ob_predictive_clid_right_number=?"
				+ " WHERE enterprise_id = ?;";
		jdbcTemplate.update(updateSql, ibClidRightType, IbClidRightNumber, obPreviewClidLeftType, obPreviewClidLeftNumber,
				obPreviewClidRightType, obPreviewClidRightNumber, obPredictiveClidLeftType, obPredictiveClidLeftNumber,
				obPredictiveClidRightType, obPredictiveClidRightNumber, enterpriseId);
	}
	
}
