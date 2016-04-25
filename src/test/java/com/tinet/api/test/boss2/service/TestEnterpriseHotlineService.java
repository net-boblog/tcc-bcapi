package com.tinet.api.test.boss2.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tinet.api.boss2.dao.EnterpriseHotlineDao;
import com.tinet.api.boss2.model.EnterpriseHotline;
import com.tinet.api.boss2.service.EnterpriseHotlineService;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 热线号码管理业务功能单元测试
 * @author louxue
 *
 */
public class TestEnterpriseHotlineService extends BaseServiceTest {
	@Autowired
	private EnterpriseHotlineService enterpriseHotlineService;
	@Autowired
	private EnterpriseHotlineDao enterpriseHotlineDao;
	
	
	@Ignore
	public void testGetAllHotline(){
		List<EnterpriseHotline> entHotlineList = enterpriseHotlineService.getAllHotline("1", 3000128);
		Assert.assertEquals(4, entHotlineList.size());
	}
	
	@Test
	public void testSaveLowestCost(){
		enterpriseHotlineService.saveLowestCost("1", 3000128, "163", "0", "10", "0");
		DatabaseContextHolder.setDBType("1");	// 切换数据源id  
		JdbcTemplate jdbc = enterpriseHotlineDao.getJdbcTemplate(); // 获得jdbcTemplate
		List<Map<String,Object>> entHotlineList = jdbc.queryForList("select hotline, lowest_cost, lowest_cost_next, lowest_cost_mode from enterprise_hotline where id=163");
		for(Map<String,Object> map : entHotlineList){
			System.out.println(map.get("hotline"));
			Assert.assertEquals("4006322237", map.get("hotline"));
			Assert.assertEquals(new BigDecimal("0.000"), map.get("lowest_cost"));
			Assert.assertEquals(new BigDecimal("10.000"), map.get("lowest_cost_next"));
			Assert.assertEquals(0, map.get("lowest_cost_mode"));
	    }
	}
}
