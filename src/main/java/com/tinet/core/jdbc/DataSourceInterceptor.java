package com.tinet.core.jdbc;

import org.aspectj.lang.JoinPoint;

/**
 * 
 * @author wangguiyu
 *
 */
public class DataSourceInterceptor {

	public void setdataSourceMysql(JoinPoint jp) {
		
		DatabaseContextHolder.setDBType("dataSourceMySql");
	}
	
	public void setdataSourceOracle(JoinPoint jp) {
		DatabaseContextHolder.setDBType("dataSourceOracle");
	}
}