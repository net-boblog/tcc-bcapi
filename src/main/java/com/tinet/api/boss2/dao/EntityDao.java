package com.tinet.api.boss2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * entity表读写
 * <p>
 *  FileName： EntityDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("entityDao")
public class EntityDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	/**
	 * 直销客户转移直销部门和直销经理
	 * @author louxue
	 * @param enterpriseId 企业id
	 * @param entityParentId 转移后所属上级id
	 * @param entityParentType 转移后所属上级类型
	 * @param comment 转移备注
	 * @return
	 */
	@Transactional
	public void updateEntParent(String enterpriseIds, Integer entityParentId, Integer entityParentType,
			String entityComment) {
		String sql = "update entity set entity_parent=?, entity_parent_type=?, comment= comment || ? where enterprise_id=?;";
		jdbcTemplate.update(sql, entityParentId, entityParentType, entityComment, Integer.parseInt(enterpriseIds));
	}
	
}
