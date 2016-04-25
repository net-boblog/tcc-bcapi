package com.tinet.api.test.boss2.service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.dao.EnterpriseSettingDao;
import com.tinet.api.boss2.dao.EntityDao;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.api.boss2.service.EnterpriseService;
import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;
import com.tinet.common.util.StringUtil;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 客户管理业务功能测试
 * @author louxue
 *
 */
public class TestEnterpriseService extends BaseServiceTest {
	@Autowired
	private EntityDao entityDao;
	@Autowired
	private EnterpriseSettingDao enterpriseSettingDao;
	@Autowired
	private EnterpriseService enterpriseService;

	/**
	 * 客户转移功能测试
	 * 校验测试结果：select entity_parent,entity_parent_type,comment from entity where enterprise_id=3000001;
	 * 回滚修改：update entity set comment='' where enterprise_id=3000001;
	 */
	@Ignore
	@Transactional
	@Rollback(true)
	public void testUpdateEntParent(){ 
		enterpriseService.updateEntParent("1", "3000001", 18, 5, "做测试"); 
		DatabaseContextHolder.setDBType("1");	// 切换数据源id  
		JdbcTemplate jdbc = entityDao.getJdbcTemplate(); // 获得jdbcTemplate
		List<Map<String,Object>> entList = jdbc.queryForList("select entity_parent, entity_parent_type, comment from entity where enterprise_id=3000001");
		for(Map<String,Object> map : entList){
			Assert.assertEquals(18, map.get("entity_parent"));
			Assert.assertEquals(5, map.get("entity_parent_type"));
			System.out.println("@@entity::comment = " + map.get("comment"));
	    }
	} 
	
	/**
	 * 测试账户转正功能测试
	 * 校验测试结果：select enterprise_id,is_test,status,entity_status from enterprise where enterprise_id in(3000001,3000007);
	 * 回滚修改：update business set is_test=1 where enterprise_id in(3000001,3000007);
	 */
	@Ignore
	@Transactional
	@Rollback(true)
	public void testExecuteTest2Direct() {
		Set<Integer> ccicSet = new HashSet<Integer>();
		ccicSet.add(1);
		ccicSet.add(18);
		String entIds = "3000001,3000007,3000113,3000114";
		enterpriseService.executeTest2Direct(ccicSet, entIds);

		// 校验ccicId=1上客户3000001的是否测试账户标志
		DatabaseContextHolder.setDBType("1"); // 切换数据源id  
		JdbcTemplate jdbc = entityDao.getJdbcTemplate(); // 获得jdbcTemplate
		List<Map<String, Object>> entList = jdbc
				.queryForList("select enterprise_id,is_test from business where enterprise_id=3000001");
		for (Map<String, Object> map : entList) {
			Assert.assertEquals(0, map.get("is_test"));
		}
		// 校验ccicId=18上客户3000001的是否测试账户标志
		DatabaseContextHolder.setDBType("18"); // 切换数据源id  
		jdbc = entityDao.getJdbcTemplate(); // 获得jdbcTemplate
		entList = jdbc.queryForList("select enterprise_id,is_test from business where enterprise_id=3000113");
		for (Map<String, Object> map : entList) {
			Assert.assertEquals(0, map.get("is_test"));
		}
	}
	
	/**
	 * 测试账户延期功能测试
	 * 选择校验数据：
	 * select * from enterprise_setting where enterprise_id in(select enterprise_id from enterprise_setting 
	 	where name='enterprise_test_status' and value='1') and name ='enterprise_test_expiry_date' 
		and enterprise_id in(select enterprise_id from business where is_test=1 and status!=4) order by id desc;
	 * 校验测试结果：select * from enterprise_setting where name ='enterprise_test_expiry_date' and enterprise_id in (3000149);
	 * 回滚修改：update enterprise_setting set value=(value::date - integer '15')  where name ='enterprise_test_expiry_date' and enterprise_id in (3000149);
	 */
	@Ignore
	public void testExecuteExtend15DaysTest() {
		// 先读出ccicId=1上测试账户3000149的有效期
		DatabaseContextHolder.setDBType("1"); // 切换数据源id  
		String expiryDateOld = enterpriseSettingDao.getEntSettingValue(3000149, Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_EXPIRY_DATE);
		
		Set<Integer> ccicSet = new HashSet<Integer>();
		ccicSet.add(1);
		String entIds = "3000149";
		enterpriseService.executeExtend15Days(ccicSet, entIds);

		// 校验ccicId=1上测试账户3000149的有效期
		DatabaseContextHolder.setDBType("1"); // 切换数据源id  
		String expiryDateNew = enterpriseSettingDao.getEntSettingValue(3000149, Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_EXPIRY_DATE);
		try {
			Assert.assertEquals(15, DateUtil.getIntervalDays(expiryDateOld, expiryDateNew));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试申请1平台上的一个测试账户给id为18的entity
	 */
	@Ignore
	public void TestExecuteApplyTestEnt() {
		enterpriseService.executeApplyTestEnt("1", 18, 5);
	}
	
	@Ignore
	public void testGetEntInfoForBizChange(){
		Map<String, Object> map = enterpriseService.getEntInfoForBizChange("1", 3000000, 6);
		Assert.assertFalse(StringUtil.isNotEmpty((String)map.get("error")));
	}
	
	@Test
	public void testGetEntAllInfo(){
		Enterprise ent = enterpriseService.getEntAllInfo("1", 3000086);
		Assert.assertTrue(StringUtil.isNotEmpty(String.valueOf(ent.getEntityParent())));
	}
}

