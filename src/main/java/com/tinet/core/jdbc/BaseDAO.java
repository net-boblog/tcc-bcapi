package com.tinet.core.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;


public interface BaseDAO {
	public JdbcTemplate getJdbcTemplate();

}
