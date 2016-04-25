package com.tinet.core.jdbc;

/**
 * 
 * @author wangguiyu
 * @descrice 多个登录用户可能需要同时切换数据源，所以这里需要定义线程安全的ThreadLocal 
 * @more 用户切换数据源只要在程序中使用 DBContextHolder.setDBType("数据源标识") 即可完成数据源切换
 *
 */
public class DatabaseContextHolder {
    
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDBType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDBType() {
		return contextHolder.get();
	}

	public static void clearDBType() {
		contextHolder.remove();
	}
}