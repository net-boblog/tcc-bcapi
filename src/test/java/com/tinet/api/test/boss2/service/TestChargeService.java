package com.tinet.api.test.boss2.service;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tinet.api.boss2.model.Enterprise;
import com.tinet.api.boss2.model.LogCharge;
import com.tinet.api.boss2.service.ChargeService;
import com.tinet.api.boss2.service.CommonService;
import com.tinet.api.boss2.service.EnterpriseService;
import com.tinet.common.inc.Const;

public class TestChargeService extends BaseServiceTest {
	@Autowired
	private ChargeService chargeService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 测试给客户充值
	 */
	@Ignore
	public void testCharge() {
		/**
		 * public LogCharge(Integer transactionId,Integer entityId,
			String entityName, Integer entityType, Integer chargeType,
			Integer accountId, Integer accountType, String chargedEntityName,
			Integer chargedEntityId, BigDecimal money, BigDecimal previousMoney,
			BigDecimal currentMoney, BigDecimal currentSumMoney, String chargeComment)
		 */
		Integer transId = commonService.getTransactionId();
		Enterprise ent = enterpriseService.getEntBaseInfo("1", 3000000);
		LogCharge logCharge = new LogCharge(transId, 2, "kefu", 13, 11, ent.getAccountId(), Const.ACCOUNT_ACCOUNT_TYPE_CASH, ent.getFullName(), 3000000, 
				new BigDecimal(1000), ent.getMoney(), ent.getMoney().add(new BigDecimal(1000)), BigDecimal.ZERO,  "bcapi接口测试充值");
		Integer bizStatus = chargeService.charge("1", 3000000, logCharge);
		logger.info(bizStatus);
	}
	
	/**
	 * 测试给客户冲正
	 */
	@Test
	public void testChargeBackout() {
		Integer transId = commonService.getTransactionId();
		Enterprise ent = enterpriseService.getEntBaseInfo("1", 3000000);
		LogCharge logCharge = new LogCharge(transId, 2, "kefu", 13, 19, ent.getAccountId(), Const.ACCOUNT_ACCOUNT_TYPE_CASH, ent.getFullName(), 3000000, 
			new BigDecimal(1000), ent.getMoney(), ent.getMoney().add(new BigDecimal(1000).negate()), BigDecimal.ZERO,  "bcapi接口测试冲正");
		Integer bizStatus = chargeService.charge("1", 3000000, logCharge);
		logger.error("@@@@@@@@@@@@" + bizStatus);
	}
}
