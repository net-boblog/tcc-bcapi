package com.tinet.api.test.boss2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.EnterpriseAll;
import com.tinet.api.boss2.model.EnterpriseNumber;
import com.tinet.api.boss2.model.EnterpriseSettingAll;
import com.tinet.api.boss2.service.CrontabService;

@SuppressWarnings("rawtypes")
public class TestCrontabService extends BaseServiceTest {

	@Autowired
	private CrontabService crontabService;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetEntInfo() {
		List<Ccic> ccicList = new ArrayList<Ccic>();
		Ccic ccic = new Ccic();
		ccic.setId(1);
		ccic.setCcicName("186平台");
		ccicList.add(ccic);
		List<EnterpriseSettingAll> entTestStatusList = new ArrayList<EnterpriseSettingAll>();
		List<EnterpriseSettingAll> entTestExpiryDateList = new ArrayList<EnterpriseSettingAll>();
		Map<String, Object> rtnMap = crontabService.getEntInfo(ccicList, entTestStatusList, entTestExpiryDateList);
		Assert.assertNull(rtnMap.get("error"));
		List<EnterpriseAll> entAllList = (List<EnterpriseAll>)rtnMap.get("entAllList");
		List<EnterpriseSettingAll> entSettingList = (List<EnterpriseSettingAll>)rtnMap.get("entSettingAllList");
		System.out.println(entAllList.size());
		System.out.println(entSettingList.size());
	}
	
	@Ignore
	public void testGetEntTotalCost() {
		List<Ccic> ccicList = new ArrayList<Ccic>();
		Ccic ccic = new Ccic();
		ccic.setId(1);
		ccic.setCcicName("186平台");
		ccicList.add(ccic);
		Map rtnMap = crontabService.getEntTotalCost(ccicList);
		Assert.assertNull(rtnMap.get("error"));
		System.out.println(rtnMap.size());
	}
	
	@Ignore
	@SuppressWarnings("unchecked")
	public void testGetEntNumber() {
		List<Ccic> ccicList = new ArrayList<Ccic>();
		Ccic ccic = new Ccic();
		ccic.setId(1);
		ccic.setCcicName("186平台");
		ccicList.add(ccic);
		Map<String, Object> rtnMap = crontabService.getEntNumbers(ccicList);
		Assert.assertNull(rtnMap.get("error"));
		System.out.println(((List<EnterpriseNumber>)rtnMap.get("entNumberList")).size());
	}
}
