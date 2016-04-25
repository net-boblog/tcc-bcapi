package com.tinet.api.test.boss2.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.service.BizChangeService;
import com.tinet.common.inc.Const;

public class TestBizChangeService extends BaseServiceTest {

	@Autowired
	private BizChangeService bizChangeService;
	
	/**
	 * 测试修改企业短信签名
	 */
	@Ignore
	public void testUpdateEntSmsSign(){
		EnterpriseSetting entSetting = new EnterpriseSetting(3000000, Const.ENTERPRISE_SETTING_NAME_SMS_SIGN, "123", null);
		String rs = bizChangeService.updateEntSmsSign("1", entSetting);
		System.out.println(rs);
		Assert.assertEquals(rs, "企业未开通短信功能，不能修改签名！");
		
		entSetting = new EnterpriseSetting(3000163, Const.ENTERPRISE_SETTING_NAME_SMS_SIGN, "bcapi修改短信smsSign", null);
		rs = bizChangeService.updateEntSmsSign("1", entSetting);
		Assert.assertNull(rs);
	}
	
	/**
	 * 测试企业费率修改
	 */
	@Ignore
	public void testExecuteEntSmsRateChange(){
		// 短信费率修改
		bizChangeService.executeEntRateChange("1", 3000000, Const.LOG_CHANGE_TYPE_SMS_RATE, "0.08");
	}
	
	/**
	 * 测试企业热线号码最低消费修改
	 */
	@Test
	public void testExecuteLowestCostChange(){
		// 修改号码最低消费
		bizChangeService.executeEntRateChange("1", 3000162, Const.LOG_CHANGE_TYPE_LOWEST_COST, "{69121306:0}");
	}
	
	/**
	 * 测试企业座席数修改
	 */
	@Ignore
	public void testExecuteEntClientChange(){
		bizChangeService.executeClientChange("1", 3000162, "0,0.000;5;0");
	}
}
