package com.tinet.api.test.boss2.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tinet.api.boss2.model.Routerset;
import com.tinet.api.boss2.service.RoutersetService;

public class TestRoutersetService extends BaseServiceTest {

	@Autowired
	private RoutersetService routersetService;
	
	@Ignore
	public void testGetAllRouterset() {
		List<Routerset> routersetList = routersetService.getAllRouterset("1");
		Assert.assertEquals(4, routersetList.size());
	}
	
	@Test
	public void testGetRoutersetName() {
		String name = routersetService.getRoutersetName("1", -1);
		Assert.assertEquals("默认路由", name);
	}
	
}
