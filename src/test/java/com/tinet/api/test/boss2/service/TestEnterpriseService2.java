package com.tinet.api.test.boss2.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import com.tinet.api.boss2.dao.EntityDao;
import com.tinet.api.boss2.service.EnterpriseService;
import com.tinet.core.jdbc.DatabaseContextHolder;

public class TestEnterpriseService2 extends BaseServiceTest {
	private static ApplicationContext context;
	private EntityDao dao;
	private EnterpriseService enterpriseService;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext(new String[] { "spring/applicationContext-core.xml" });
		dao = (EntityDao) context.getBean("entityDao", EntityDao.class);
		enterpriseService = context.getBean("enterpriseService", EnterpriseService.class);
	}

	/**
	 * 测试客户转移
	 */
	@Rollback(value = true)
	public void updateEntParent() {
		enterpriseService.updateEntParent("1", "3000001", 18, 5, "做测试");
		DatabaseContextHolder.setDBType("1"); // 切换数据源id  
		JdbcTemplate jdbc = dao.getJdbcTemplate(); // 获得jdbcTemplate
		List<Map<String, Object>> entList = jdbc
				.queryForList("select entity_parent, entity_parent_type, comment from entity where enterprise_id=3000001");
		for (Map<String, Object> map : entList) {
			Assert.assertEquals(18, map.get("entity_parent"));
			Assert.assertEquals(5, map.get("entity_parent_type"));
			System.out.println("@@entity::comment = " + map.get("comment"));
		}
	}

	/**
	 * 测试测试账户转正功能
	 */
	@Test
	@Rollback(true)
	public void updateEntTest2DirectTest() {
		Set<Integer> ccicSet = new HashSet<Integer>();
		ccicSet.add(1);
		ccicSet.add(18);
		String entIds = "3000001,3000007,3000113,3000114";
		enterpriseService.executeTest2Direct(ccicSet, entIds);

		// 校验ccicId=1上客户3000001的是否测试账户标志
		DatabaseContextHolder.setDBType("1"); // 切换数据源id  
		JdbcTemplate jdbc = dao.getJdbcTemplate(); // 获得jdbcTemplate
		List<Map<String, Object>> entList = jdbc
				.queryForList("select enterprise_id,is_test from business where enterprise_id=3000001");
		for (Map<String, Object> map : entList) {
			Assert.assertEquals(1, map.get("is_test"));
		}
		// 校验ccicId=3上客户3000001的是否测试账户标志
		DatabaseContextHolder.setDBType("18"); // 切换数据源id  
		jdbc = dao.getJdbcTemplate(); // 获得jdbcTemplate
		entList = jdbc.queryForList("select enterprise_id,is_test from business where enterprise_id=3000113");
		for (Map<String, Object> map : entList) {
			Assert.assertEquals(1, map.get("is_test"));
		}
	}

}
