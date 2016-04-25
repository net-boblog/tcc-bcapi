package com.tinet.api.test.boss2.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tinet.api.boss2.model.Combo;
import com.tinet.api.boss2.service.ComboService;

/**
 * 外呼套餐包业务功能单元测试
 * @author louxue
 *
 */
public class TestComboService extends BaseServiceTest {
	@Autowired
	private ComboService comboService;
	
	
	@Test
	public void testGetEntCombo(){
		Combo combo = comboService.getEntCombo("1", 3000128, new Date());
		Assert.assertNotNull(combo);
	}
}
