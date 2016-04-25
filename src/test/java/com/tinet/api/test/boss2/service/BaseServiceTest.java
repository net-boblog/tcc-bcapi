package com.tinet.api.test.boss2.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 单元测试基类。
 *<p>
 * 文件名： BaseServiceTest.java
 *<p>
 * Copyright (c) 2006-2010 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 周营昭
 * @since 1.0
 * @version 1.0
 * @see  com.tinet.ccic.test.wm.service.CustomerServiceTest
 */
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-core.xml"})
public class BaseServiceTest extends //AbstractTransactionalJUnit4SpringContextTests {
			AbstractJUnit4SpringContextTests {
	
}
