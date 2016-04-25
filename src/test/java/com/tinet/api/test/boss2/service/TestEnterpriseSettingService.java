package com.tinet.api.test.boss2.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tinet.api.boss2.dao.EnterpriseSettingDao;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.service.EnterpriseSettingService;

/**
 * 企业配置业务功能单元测试
 * @author louxue
 *
 */
public class TestEnterpriseSettingService extends BaseServiceTest {
	@Autowired
	private EnterpriseSettingService enterpriseSettingService;
	@Autowired
	private EnterpriseSettingDao enterpriseSettingDao;
	
	
	@Test
	public void testGetOtherConfCombo(){
		List<EnterpriseSetting> entSettingList = enterpriseSettingService.getOtherConf("1", 3000128);
		Assert.assertEquals(4, entSettingList.size());
	}
	
	@Test
	public void testInstOrUpdateEntSetting(){
		EnterpriseSetting entSetting = new EnterpriseSetting(3000128, "is_record_tel", "0", "");
		enterpriseSettingService.instOrUpdateEntSetting("1", entSetting);
		
		String value = enterpriseSettingDao.getEntSettingValue(3000128, "is_record_tel");
		Assert.assertEquals("0", value);
	}
}
