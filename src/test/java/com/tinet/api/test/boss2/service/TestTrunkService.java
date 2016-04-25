package com.tinet.api.test.boss2.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tinet.api.boss2.dao.TrunkDao;
import com.tinet.api.boss2.model.Trunk;
import com.tinet.api.boss2.service.TrunkService;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 热线号码管理业务功能单元测试
 * @author louxue
 *
 */
public class TestTrunkService extends BaseServiceTest {
	@Autowired
	private TrunkService trunkService;
	@Autowired
	private TrunkDao trunkDao;
	
	@Ignore
	public void testGetTrunkRent(){
		List<Trunk> entHotlineList = trunkService.getTrunkRent("1", 3000128);
		Assert.assertEquals(3, entHotlineList.size());
	}
	
	@Test
	public void testSaveLowestCost(){
		trunkService.saveTrunkRent("1", 3000128, "159,170", "10,0");
		DatabaseContextHolder.setDBType("1");	// 切换数据源id  
		JdbcTemplate jdbc = trunkDao.getJdbcTemplate(); // 获得jdbcTemplate
		List<Map<String,Object>> trunkList = jdbc.queryForList("select number_trunk, rent from trunk where id = 159");
		for(Map<String,Object> map : trunkList){
			Assert.assertEquals("56077739", map.get("number_trunk"));
			Assert.assertEquals(new BigDecimal("10.000"), map.get("rent"));
	    }
	}
}
