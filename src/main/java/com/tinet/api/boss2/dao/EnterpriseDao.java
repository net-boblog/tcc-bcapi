package com.tinet.api.boss2.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.tinet.api.boss2.model.Combo;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.api.boss2.model.EnterpriseAll;
import com.tinet.api.boss2.model.EnterpriseHotline;
import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.model.EnterpriseTrunk;
import com.tinet.api.boss2.model.Rateset;
import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;
import com.tinet.common.util.JacksonUtil;
import com.tinet.common.util.StringUtil;

/**
 * 客户管理数据库读写实现
 * <p>
 *  FileName： EnterpriseDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseDao")
public class EnterpriseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityDao entityDao;
	@Autowired
	private EnterpriseUserDao enterpriseUserDao;
	@Autowired
	private EnterpriseSettingDao enterpriseSettingDao;
	@Autowired
	private EnterpriseHotlineDao enterpriseHotlineDao;
	@Autowired
	private TrunkDao trunkDao;
	@Autowired
	private RatesetDao ratesetDao;
	@Autowired
	private ComboDao comboDao;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public class EntTestInfoMapper implements RowMapper<Enterprise> {
		public Enterprise mapRow(ResultSet rs, int rowNum) throws SQLException {
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterpriseId(rs.getInt("enterprise_id"));
			enterprise.setEntityType(rs.getInt("entity_type"));
			enterprise.setEnterpriseName(rs.getString("enterprise_name"));
			enterprise.setFullName(rs.getString("full_name"));
			enterprise.setEntityParent(rs.getInt("entity_parent"));
			enterprise.setEntityParentType(rs.getInt("entity_parent_type"));
			enterprise.setCreateTime(rs.getDate("create_time"));
			enterprise.setEntityStatus(rs.getInt("entity_status"));
			enterprise.setProductId(rs.getInt("product_id"));
			enterprise.setMasterHotline(rs.getString("master_hotline"));
			enterprise.setLowestCost(rs.getBigDecimal("lowest_cost"));
			enterprise.setStatus(rs.getInt("status"));
			enterprise.setPeriod(rs.getInt("period"));
			enterprise.setServiceStart(rs.getDate("service_start"));
			return enterprise;
		}
	}
	
	public class EntBaseInfoMapper implements RowMapper<Enterprise> {
		public Enterprise mapRow(ResultSet rs, int rowNum) throws SQLException {
			Enterprise ent = new Enterprise();
			ent.setEnterpriseName(rs.getString("enterprise_name"));
			ent.setEntityParent(rs.getInt("entity_parent"));
			ent.setEntityParentType(rs.getInt("entity_parent_type"));
			ent.setFullName(rs.getString("full_name"));
			ent.setAreaCode(rs.getString("area_code"));
			ent.setMobile(rs.getString("mobile"));
			ent.setTel(rs.getString("tel"));
			ent.setEmail(rs.getString("email"));
			ent.setFax(rs.getString("fax"));
			ent.setAddress(rs.getString("address"));
			ent.setPost(rs.getString("post"));
			ent.setEntityStatus(rs.getInt("entity_status"));
			ent.setBusinessNo(rs.getString("business_no"));
			ent.setWebsite(rs.getString("website"));
			ent.setTrade(rs.getString("trade"));
			ent.setLegalPerson(rs.getString("legal_person"));
			ent.setPrincipal(rs.getString("principal"));
			ent.setComment(rs.getString("comment"));
			ent.setPayerName(rs.getString("payer_name"));
			ent.setPayerMobile(rs.getString("payer_mobile"));
			ent.setPayerEmail(rs.getString("payer_email"));
			ent.setCreateTime(rs.getDate("create_time"));
			
			ent.setBusinessId(rs.getInt("business_id"));
			ent.setCtiId(rs.getInt("cti_id"));
			ent.setBargainNo(rs.getString("bargain_no"));
			ent.setProductId(rs.getInt("product_id"));
			ent.setStatus(rs.getInt("status"));
			ent.setOwner(rs.getString("owner"));
			ent.setIsTest(rs.getInt("is_test"));
			ent.setServiceStart(rs.getDate("service_start"));
			ent.setServiceEnd(rs.getDate("service_end"));
			ent.setClientTel(rs.getInt("client_tel"));
			ent.setClientWeb(rs.getInt("client_web"));
			ent.setClientFree(rs.getInt("client_free"));
			ent.setInboundCallLimit(rs.getInt("inbound_call_limit"));
			ent.setRent(new BigDecimal(rs.getBigDecimal("rent").stripTrailingZeros().toPlainString()));
			ent.setRentEnd(rs.getDate("rent_end"));
			ent.setMasterHotline(rs.getString("master_hotline"));
			ent.setLowestCost(new BigDecimal(rs.getBigDecimal("lowest_cost").stripTrailingZeros().toPlainString()));
			ent.setTotalLowestCost(new BigDecimal(rs.getBigDecimal("total_lowest_cost").stripTrailingZeros().toPlainString()));
			ent.setPeriod(rs.getInt("period"));
			
			ent.setAccountId(rs.getInt("account_id"));
			ent.setMoney(rs.getBigDecimal("money"));
			ent.setCredit(rs.getBigDecimal("credit"));
			ent.setCreditTemp(rs.getBigDecimal("credit_temp"));
			return ent;
		}
	}
	
	/**
	 * 查找企业基本信息
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public Enterprise getEntBaseInfo(Integer enterpriseId){
		Enterprise ent = new Enterprise();
		
		// 查询企业的基本信息，业务信息和账户信息
		StringBuffer qrySql = new StringBuffer("");
		qrySql.append("select enterprise_name, entity_parent, entity_parent_type, full_name, area_code, mobile, tel,")
			.append(" email, fax, address, post, entity_status, business_no, website, trade, legal_person, principal, comment,")
			.append(" business_id, cti_id, bargain_no, product_id, status, service_start, service_end, is_test, client_tel, client_web, client_free, owner,")
			.append(" inbound_call_limit, rent, rent_end, master_hotline, coalesce(lowest_cost,0) as lowest_cost, period, ")
			.append(" payer_name, payer_mobile, payer_email, e.create_time, total_lowest_cost, account_id, money, credit, credit_temp ")
			.append(" from enterprise e")
			.append(" where e.enterprise_id = ?");
		ent = jdbcTemplate.queryForObject(qrySql.toString(), new EntBaseInfoMapper(), enterpriseId);
		
		// 若是测试账户，则查找使用有效期
		if (ent.getIsTest().intValue() == Const.ENTERPRISE_TEMP_IS_TEST_YES) {
			String expiryDate = enterpriseSettingDao.getEntSettingValue(enterpriseId, Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_EXPIRY_DATE);
			ent.setExpiryDate(expiryDate);
		}
				
//		// 查找下月套餐包总资费
//		qrySql.setLength(0);
//		qrySql.append("select ")
//			.append(ent.getClientWeb() + ent.getClientTel() + ent.getClientFree())
//			.append("*c.rent as deduct_fee from enterprise_combo_order ec,combo c")
//			.append(" where ec.enterprise_id=? and ec.new_combo_id = c.id");
//		BigDecimal comboFee = jdbcTemplate.queryForObject(qrySql.toString(), new Object[]{enterpriseId}, BigDecimal.class);
//		ent.setComboFee(comboFee == null ? new BigDecimal(0) :comboFee);
//		
//		// 查找当前所有号码功能费
//		qrySql.setLength(0);
//		qrySql.append("select sum(rent) as toatl_number_rent from trunk where enterprise_id=?;");
//		BigDecimal numberRent = jdbcTemplate.queryForObject(qrySql.toString(), new Object[]{enterpriseId}, BigDecimal.class);
//		ent.setNumberRent(numberRent == null ? new BigDecimal(0) : numberRent);
		ent.setEnterpriseId(enterpriseId);
		return ent;
	}
	
	/**
	 * 查找企业的全部信息
	 * @param enterpriseId
	 * @return
	 */
	public Enterprise getEntAllInfo(Integer enterpriseId){
		Enterprise ent = this.getEntBaseInfo(enterpriseId);
		
		StringBuffer qrySql = new StringBuffer("");
		// 查找企业business信息
		qrySql.append("select is_idd, product_type, client_area ")
			.append(" from business where enterprise_id = ?");
		List<Map<String, Object>> bizMapList = jdbcTemplate.queryForList(qrySql.toString(), enterpriseId);
		for (Map<String, Object> map : bizMapList) {
			ent.setIsIdd((Integer)map.get("is_idd"));
			ent.setProductType((Integer)map.get("product_type"));
			ent.setClientArea(map.get("client_area").toString());
		}
		
		// 查找企业透传号码信息
		qrySql.setLength(0);
		qrySql.append(
			"select ib_clid_right_type, ib_clid_right_number,ob_preview_clid_left_type, ob_preview_clid_left_number,")
			.append(" ob_preview_clid_right_type, ob_preview_clid_right_number, ob_predictive_clid_left_type, ")
			.append(" ob_predictive_clid_left_number, ob_predictive_clid_right_type, ob_predictive_clid_right_number")
			.append(" from enterprise_clid where enterprise_id = ?");
		List<Map<String, Object>> entClidMapList = jdbcTemplate.queryForList(qrySql.toString(), enterpriseId);
		for (Map<String, Object> map : entClidMapList) {
			ent.setIbClidRightType((Integer)map.get("ib_clid_right_type"));
			ent.setIbClidRightNumber(map.get("ib_clid_right_number").toString());
			ent.setObPreviewClidLeftType((Integer)map.get("ob_preview_clid_left_type"));
			ent.setObPreviewClidLeftNumber(map.get("ob_preview_clid_left_number").toString());
			ent.setObPreviewClidRightType((Integer)map.get("ob_preview_clid_right_type"));
			ent.setObPreviewClidRightNumber(map.get("ob_preview_clid_right_number").toString());
			ent.setObPredictiveClidLeftType((Integer)map.get("ob_predictive_clid_left_type"));
			ent.setObPredictiveClidLeftNumber(map.get("ob_predictive_clid_left_number").toString());
			ent.setObPredictiveClidRightType((Integer)map.get("ob_predictive_clid_right_type"));
			ent.setObPredictiveClidRightNumber(map.get("ob_predictive_clid_right_number").toString());
		}
		
		// 查找企业路由配置信息
		qrySql.setLength(0);
		qrySql.append(
			"select ib_router_right, (select name from routerset where id = ib_router_right) as ib_router_right_name,")
			.append(" ob_preview_router_left, (select name from routerset where id = ob_preview_router_left) as ob_preview_router_left_name, ")
			.append(" ob_predictive_router_left, (select name from routerset where id = ob_predictive_router_left) as ob_predictive_router_left_name ")
			.append(" from enterprise_router where enterprise_id =? ");
		List<Map<String, Object>> entRouterMapList = jdbcTemplate.queryForList(qrySql.toString(), enterpriseId);
		for (Map<String, Object> map : entRouterMapList) {
			ent.setIbRouterRight((Integer)map.get("ib_router_right"));
			ent.setIbRouterRightName(map.get("ib_router_right_name").toString());
			ent.setObPreviewRouterLeft((Integer)map.get("ob_preview_router_left"));
			ent.setObPreviewRouterLeftName(map.get("ob_preview_router_left_name").toString());
			ent.setObPredictiveRouterLeft((Integer)map.get("ob_predictive_router_left"));
			ent.setObPredictiveRouterLeftName(map.get("ob_predictive_router_left_name").toString());
		}
		
		// 查找企业选择开通的功能
		qrySql.setLength(0);
		qrySql.append("select array_agg(function_id) as functionId from function_select where enterprise_id = ?");
		String functionIds = jdbcTemplate.queryForObject(qrySql.toString(), new Object[]{enterpriseId}, String.class);
		if (functionIds != null && !"".equals(functionIds)) {
			ent.setFunctionId(functionIds.replaceAll("\\{|\\}", ""));
		}
		
		// 查找enterprise_setting信息
		qrySql.setLength(0);
		qrySql.append("select name,value,property,create_time from enterprise_setting where enterprise_id = ?");
		List<Map<String, Object>> entSettingMapList = jdbcTemplate.queryForList(qrySql.toString(), enterpriseId);
		Set<EnterpriseSetting> enterpriseSettings = new TreeSet<EnterpriseSetting>(
				new Comparator<EnterpriseSetting>(){
					public int compare(EnterpriseSetting a, EnterpriseSetting b) {
						return a.getName().compareTo(b.getName());
					}
				}
		);
		for (Map<String, Object> map : entSettingMapList) {
			String name = map.get("name").toString();
			if (name.equals(Const.ENTERPRISE_SETTING_NAME_SMS_SIGN)) {
				// 企业短信签名
				ent.setSmsSign(map.get("value").toString());
			} else if (name.equals(Const.ENTERPRISE_SETTING_NAME_SMS_CELL)) {
				// 企业短信小号
				ent.setSmsCell(map.get("value").toString());
			} else if (name.equals(Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_IS_PREDICTIVE_OPEN)) {
				// 批量外呼开关是否开启
				ent.setIsPredictiveOpen(Integer.parseInt(map.get("value").toString()));
			} else if (name.equals(Const.ENTERPRISE_SETTING_NAME_IS_USE_ITF)) {
				// 是否使用接口
				ent.setIsUseItf(Integer.parseInt(map.get("value").toString()));
			} else if (name.equals(Const.ENTERPRISE_SETTING_NAME_ACCESS_MODE)) {
				// 客户电话接入方式
				ent.setAccessMode(Integer.parseInt(map.get("value").toString()));
			} else if (name.equals(Const.ENTERPRISE_SETTING_NAME_IS_FULL_RECORD)) {
				// 是否全程录音
				ent.setIsFullRecord(map.get("value")==null?0:Integer.parseInt(map.get("value").toString()));
			} else if (name.equals(Const.ENTERPRISE_SETTING_IS_AWS_RECORD)) {
				// 企业录音试听方式
				ent.setIsAwsRecord(map.get("value")==null?0:Integer.parseInt(map.get("value").toString()));
			} else if (name.equals(Const.ENTERPRISE_SETTING_AWS_RECORD_DIRECTORY)) {
				// 企业S3目录
				ent.setAwsRecordDirectory(map.get("value")==null?"":map.get("value").toString());
			} else if (name.equals(Const.ENTERPRISE_SETTING_OUT_CALL_SWITCH)) {
				// 外呼黑名单开关及参数配置
				ent.setOutCallSwitch(map.get("value")==null?"":map.get("value").toString());
				if (map.get("property")!=null&&!"".equals(map.get("property").toString())) {
					JsonNode jsonProperty;
					try {
						jsonProperty = JacksonUtil.getInstance().readValue(map.get("property").toString(), JsonNode.class);
						ent.setOutCallTimes(jsonProperty.get("outCallTimes").asText());
						ent.setOutCallBridgeTime(jsonProperty.get("outCallBridgeTime").asText());
						ent.setClearDays(jsonProperty.get("clearDays").asText());
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					ent.setOutCallTimes("");
					ent.setOutCallBridgeTime("");
					ent.setClearDays("");
				}
			} else if (name.equals("is_sms_send") || name.equals("is_record_ib") 
					|| name.equals("is_ob_click") || name.equals("is_fax_receive")) {
				EnterpriseSetting enterpriseSetting = new EnterpriseSetting();
				enterpriseSetting.setId((Integer)map.get("id"));
				enterpriseSetting.setEnterpriseId((Integer)map.get("enterprise_id"));
				enterpriseSetting.setName(map.get("name").toString());
				enterpriseSetting.setValue(map.get("value").toString());
				enterpriseSetting.setProperty(map.get("property")==null?"":map.get("property").toString());
				enterpriseSetting.setCreateTime((Date)map.get("create_time"));
				enterpriseSettings.add(enterpriseSetting);
			}
		}
		ent.setEnterpriseSettings(enterpriseSettings);
		
		// 查询是否开通短信功能
		String isSmsSend = enterpriseSettingDao.getEntSettingValue(enterpriseId, "is_sms_send");
		ent.setIsSmsSend(isSmsSend == null ? 0 : Integer.parseInt(isSmsSend));
		
		// 查询是否开通传真功能
		String isFaxReceive = enterpriseSettingDao.getEntSettingValue(enterpriseId, "is_fax_receive");
		ent.setIsFaxReceive(isFaxReceive == null ? 0 : Integer.parseInt(isFaxReceive));
		
		// 客户预测外呼企业并发限制
		String predictiveCallLimit = enterpriseSettingDao.getEntSettingValue(enterpriseId, Const.ENTERPRISE_SETTING_PREDICTIVE_CALL_LIMIT);
		if (predictiveCallLimit == null) {
			ent.setPredictiveCallLimit("");
			ent.setPredictiveCallLimitRecord(0);
		} else {
			ent.setPredictiveCallLimit(predictiveCallLimit);
			ent.setPredictiveCallLimitRecord(1);
		}
		
		// 查找客户预测外呼企业并发资费
		String predictiveCallFee = enterpriseSettingDao.getEntSettingValue(enterpriseId, Const.ENTERPRISE_SETTING_PREDICTIVE_CALL_FEE);
		if (predictiveCallFee == null) {
			ent.setPredictiveCallFee("");
			ent.setPredictiveCallFeeRecord(0);
		} else {
			ent.setPredictiveCallFee(predictiveCallFee);
			ent.setPredictiveCallFeeRecord(1);
		}
		
		// 查找费率套餐
		qrySql.setLength(0);
		qrySql.append(
			"select rate_ib_left, (select rateset_name from rateset where id=rate_ib_left) as rate_ib_left_name, ")
			.append(" rate_ib_right, (select rateset_name from rateset where id=rate_ib_right) as rate_ib_right_name,")
			.append(" rate_ob_left, (select rateset_name from rateset where id=rate_ob_left) as rate_ob_left_name, ")
			.append(" rate_ob_right, (select rateset_name from rateset where id=rate_ob_right) as rate_ob_right_name,")
			.append(" rate_sms").append(" from enterprise_rate").append(" where enterprise_id = ?;");
		List<Map<String, Object>> entRateMapList = jdbcTemplate.queryForList(qrySql.toString(), enterpriseId);
		for (Map<String, Object> map : entRateMapList) {
			ent.setRateIbLeft((Integer)map.get("rate_ib_left"));
			ent.setRateIbLeftName(map.get("rate_ib_left_name").toString());
			ent.setRateIbRight((Integer)map.get("rate_ib_right"));
			ent.setRateIbRightName(map.get("rate_ib_right_name").toString());
			ent.setRateObLeft((Integer)map.get("rate_ob_left"));
			ent.setRateObLeftName(map.get("rate_ob_left_name").toString());
			ent.setRateObRight((Integer)map.get("rate_ob_right"));
			ent.setRateObRightName(map.get("rate_ob_right_name").toString());
			ent.setRateSms(new BigDecimal(map.get("rate_sms").toString()).stripTrailingZeros());
		}
		
		// 查找企业套餐订购表
		qrySql.setLength(0);
		qrySql.append("select combo_id, new_combo_id from enterprise_combo_order a where enterprise_id =? and status = 1");
		List<Map<String, Object>> entComboOrderMapList = jdbcTemplate.queryForList(qrySql.toString(), enterpriseId);
		for (Map<String, Object> map : entComboOrderMapList) {
			ent.setComboId((Integer)map.get("combo_id"));
			ent.setNewComboId((Integer)map.get("new_combo_id"));
		}
		
		/**
		 * 查找企业所有trunk数据
		 */
		Set<EnterpriseTrunk> entTrunkList = trunkDao.getAllTrunk(enterpriseId);
		String billingTrunk = "";
		String numberTrunk = "";
		String areaCode = "";
		for (Iterator<EnterpriseTrunk> iterator = entTrunkList.iterator(); iterator.hasNext();) {
			EnterpriseTrunk trunk = (EnterpriseTrunk) iterator.next();
			numberTrunk += trunk.getNumberTrunk();
			numberTrunk += ",";
			areaCode += trunk.getAreaCode();
			areaCode += ",";
			if (trunk.getIsBilling() == Const.ENTERPRISE_TRUNK_IS_BILLING_YES) {
				billingTrunk += trunk.getNumberTrunk();
				billingTrunk += ",";
			}
		}
		if (billingTrunk != null && billingTrunk.length() > 0) {
			billingTrunk = billingTrunk.substring(0, billingTrunk.length() - 1);
		}
		ent.setBillingTrunk(billingTrunk);
		if(StringUtil.isNotEmpty(numberTrunk)){
			numberTrunk = numberTrunk.substring(0, numberTrunk.length() - 1);
			areaCode = areaCode.substring(0, areaCode.length() - 1);
		}
		ent.setNumberTrunk(numberTrunk);
		ent.setTrunkAreaCode(areaCode);
		ent.setEnterpriseTrunks(entTrunkList);
		
		/**
		 * 查找所有热线号码
		 */
		Set<EnterpriseHotline> hotlineSet = enterpriseHotlineDao.getAllHotline(enterpriseId);
		ent.setEnterpriseHotlines(hotlineSet);
		String hotline = ""; // 企业所有400/1010号码
		String hotlineTrunk = ""; // 企业所有绑定了400/1010号码的目的码
		String lowestCostMode = ""; // 企业所有热线号码的计费模式
		// 直线号码
		String directNumber = "";
		String directNumberRent = "";
		// 手机虚拟号
		String mobileVirtual = "";
		String mobileVirtualRent = "";
		
		// 低消计费标识
		int lowestCostModeFlagInt = 0;
		for (Iterator<EnterpriseHotline> iterator = hotlineSet.iterator(); iterator.hasNext();) {
			EnterpriseHotline entHotline = (EnterpriseHotline) iterator.next();
			int type = entHotline.getType().intValue();
			lowestCostModeFlagInt += entHotline.getLowestCostMode();
			if(type == Const.ENTERPRISE_HOTLINE_TYPE_400_NUMBER){
				//热线号码
				hotline += entHotline.getHotline();
				hotline += ",";
				lowestCostMode += entHotline.getLowestCostMode();
				lowestCostMode += ",";
				hotlineTrunk += entHotline.getNumberTrunk();
				hotlineTrunk += ",";
			}else if(type == Const.ENTERPRISE_HOTLINE_TYPE_DIRECT_NUMBER){
				//直线号码
				directNumber += entHotline.getHotline();
				directNumber += ",";
				directNumberRent += entHotline.getRent();
				directNumberRent += ",";
			}else if(type == Const.ENTERPRISE_HOTLINE_TYPE_MOBILE_VIRTUAL){
				//手机虚拟号码
				mobileVirtual += entHotline.getHotline();
				mobileVirtual += ",";
				mobileVirtualRent += entHotline.getRent();
				mobileVirtualRent += ",";
			}
		}
		if(hotline.length() > 0){
			hotline = hotline.substring(0, hotline.length() - 1);
			ent.setHotline(hotline);
			lowestCostMode = lowestCostMode.substring(0, lowestCostMode.length() - 1);
			ent.setLowestCostMode(lowestCostMode);
			hotlineTrunk = hotlineTrunk.substring(0, hotlineTrunk.length() - 1);
			ent.setHotlineTrunk(hotlineTrunk);
		}
		if(directNumber.length() > 0){
			directNumber = directNumber.substring(0, directNumber.length() - 1);
			ent.setDirectNumber(directNumber);
			directNumberRent = directNumberRent.substring(0, directNumberRent.length() - 1);
			ent.setDirectNumberRent(directNumberRent);
		}
		if(mobileVirtual.length() > 0){
			mobileVirtual = mobileVirtual.substring(0, mobileVirtual.length() - 1);
			ent.setMobileVirtual(mobileVirtual);
			mobileVirtualRent = mobileVirtualRent.substring(0, mobileVirtualRent.length() - 1);
			ent.setMobileVirtualRent(mobileVirtualRent);
		}
		
		ent.setLowestCostModeFlag(lowestCostModeFlagInt>0?"1":"0");
		return ent;
	}
	
	/**
	 * 查询直销经理可做业务变更的企业信息
	 * @param enterpriseId
	 * @param entityParent
	 * @return 返回企业信息对象enterprise
	 */
	public Map<String, Object> getEntForBizChange(Integer enterpriseId, Integer entityParent) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enterprise enterprise = new Enterprise();

		// 从数据库的enterprise视图中取出Enterprise
		StringBuffer sql = new StringBuffer("");
		sql.append("select enterprise.enterprise_id, enterprise_name, full_name, master_hotline, lowest_cost, period, product_id,")
			.append("  enterprise.create_time, client_web, client_free, client_tel, rent, money, credit, credit_temp, arrears_time, credit_valid_time from enterprise")
			.append(" where 1=1 and enterprise.enterprise_id =? and entity_parent = ?");
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql.toString(), enterpriseId, entityParent);
		
		if (rs.next()) {
			enterprise.setEnterpriseId(enterpriseId);
			enterprise.setEnterpriseName(rs.getString("enterprise_name"));
			enterprise.setFullName(rs.getString("full_name"));
			enterprise.setMasterHotline(rs.getString("master_hotline"));
			enterprise.setLowestCost(rs.getBigDecimal("lowest_cost"));
			enterprise.setPeriod(rs.getInt("period"));
			try {
				enterprise.setCreateTime(DateUtil.parse(rs.getString("create_time"), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			enterprise.setClientWeb(rs.getInt("client_web"));
			enterprise.setClientFree(rs.getInt("client_free"));
			enterprise.setClientTel(rs.getInt("client_tel"));
			enterprise.setRent(rs.getBigDecimal("rent"));
			enterprise.setMoney(rs.getBigDecimal("money"));
			enterprise.setCredit(rs.getBigDecimal("credit"));
			enterprise.setCreditTemp(rs.getBigDecimal("credit_temp"));
			enterprise.setArrearsTime(new Date(rs.getLong("arrears_time")*1000));
			enterprise.setCreditValidTime(new Date(rs.getLong("credit_valid_time")*1000));
			// 获取产品名称
			String productSql = "select product_name from product where id=?;";
			SqlRowSet rsProdct = jdbcTemplate.queryForRowSet(productSql, rs.getInt("product_id"));
			if (rsProdct.next()) {
				enterprise.setProductName(rsProdct.getString("product_name"));
			}
		} else {
			map.put("error", "未找到客户信息！");
			map.put("enterprise", enterprise);
			return map;
		}

		sql.setLength(0);
		sql.append("select rate_ib_left, rate_ob_left, rate_sms from enterprise_rate where enterprise_id = ?;");
		rs = jdbcTemplate.queryForRowSet(sql.toString(), enterpriseId);
		if (rs.next()) {
			enterprise.setRateIbLeft(rs.getInt("rate_ib_left"));
			enterprise.setRateObLeft(rs.getInt("rate_ob_left"));
			enterprise.setRateSms(rs.getBigDecimal("rate_sms"));
		} else {
			map.put("error", "未找到客户使用费率信息！");
			map.put("enterprise", enterprise);
			return map;
		}
		
		String value = enterpriseSettingDao.getEntSettingValue(enterpriseId, Const.ENTERPRISE_SETTING_NAME_SMS_SIGN);
		if (value != null) {
			enterprise.setSmsSign(value);
		} else {
			map.put("error", "未找到客户使用短信签名！");
			map.put("enterprise", enterprise);
			return map;
		}
		
		List<EnterpriseHotline> entHotlinelist = enterpriseHotlineDao.getAllHotlineList(enterpriseId);
		if(entHotlinelist.size() > 0){
			enterprise.setEnterpriseHotlineList(entHotlinelist);
		} else {
			map.put("error", "未找到客户使用热线号码信息！");
			map.put("enterprise", enterprise);
			return map;
		}
		
		List<Rateset> ratesetList = ratesetDao.getAllRateSet();
		if(ratesetList.size() == 0){
			map.put("error", "未找到分平台费率配置信息！");
			map.put("enterprise", enterprise);
			return map;
		}
		
		enterprise.setRateSetList(ratesetList);
		map.put("enterprise", enterprise);
		return map;
	}
	
	/**
	 * 保存企业基本信息
	 * @author louxue
	 * @param enterprise 企业信息对象
	 */
	@Transactional
	public void saveEntBaseInfo(Enterprise enterprise){
		StringBuffer updateSql = new StringBuffer("update entity ");
		updateSql.append("set enterprise_name = '").append(enterprise.getEnterpriseName()).append("', ")
				.append(" full_name = '").append(enterprise.getFullName()).append("',")
				.append(" payer_name = '").append(enterprise.getPayerName()).append("',")
				.append(" payer_email = '").append(enterprise.getPayerEmail()).append("',")
				.append(" payer_mobile = '").append(enterprise.getPayerMobile()).append("',")
				.append("area_code = '").append(enterprise.getAreaCode()).append("', ").append("principal = '")
				.append(enterprise.getPrincipal()).append("', ").append("mobile = '")
				.append(enterprise.getMobile()).append("', ").append("tel = '").append(enterprise.getTel())
				.append("', ").append("email = '").append(enterprise.getEmail()).append("', ").append("fax = '")
				.append(enterprise.getFax()).append("', ").append("address = '").append(enterprise.getAddress())
				.append("', ").append("post = '").append(enterprise.getPost()).append("', ")
				.append("legal_person = '").append(enterprise.getLegalPerson()).append("', ").append("trade = '")
				.append(enterprise.getTrade()).append("', ").append("business_no = '")
				.append(enterprise.getBusinessNo()).append("', ").append("website = '")
				.append(enterprise.getWebsite()).append("', ").append("comment = '")
				.append(enterprise.getComment()).append("' ").append("where enterprise_id = ")
				.append(enterprise.getEnterpriseId());
		jdbcTemplate.update(updateSql.toString());
	}
	
	/**
	 * 查询所有空闲状态的测试账户
	 * @author louxue
	 * @return
	 */
	private List<Enterprise> getAllFreeTestEnt() {
		List<Enterprise> entList = new ArrayList<Enterprise>();
		String qrySql = "select e.enterprise_id,entity_type,enterprise_name,full_name,entity_parent,entity_parent_type,e.create_time,entity_status,"
				+ "product_id,master_hotline,status,lowest_cost,service_start,period "
				+ " from enterprise e,enterprise_setting es"
				+ " where e.enterprise_id = es.enterprise_id and is_test = ? and es.name = ? and es.value = ?";

		entList = jdbcTemplate.query(qrySql, new Object[] { Const.ENTERPRISE_TEMP_IS_TEST_YES,
				Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_STATUS, "0" }, new EntTestInfoMapper());
		return entList;
	}

	/**
	 * 随机获取一个空闲测试账户，获取成功修改其使用状态，有效期和所属经理信息。
	 * @param entityParentId 申请者id
	 * @param entityParentType 申请者类型
	 * @return
	 */
	@Transactional
	public Enterprise executeApplyTestEnt(Integer entityParentId, Integer entityParentType) {
		Enterprise enterprise = new Enterprise();
		List<Enterprise> entList = this.getAllFreeTestEnt();
		if (entList != null && entList.size() > 0) {
			int totalNum = entList.size();
			// get one enterprise by random
			enterprise = entList.get(new Random().nextInt(totalNum));
			Integer enterpriseId = enterprise.getEnterpriseId();

			// update enterprise_setting enterprise_test_status
			enterpriseSettingDao.updateEntSetting(enterpriseId, Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_STATUS,
					String.valueOf(Const.ENTERPRISE_SETTING_TEST_STATUS_USED), null);
			Date date = null;
			String tempDate = "";
			try {
				date = DateUtil.addDay(DateUtil.parse(DateUtil.getCurrentDateTime()), 15);
				tempDate = DateUtil.format(date, DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss);
				// update enterprise_setting enterprise_test_expiry_date
				enterpriseSettingDao.updateEntSetting(enterpriseId,
						Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_EXPIRY_DATE, tempDate, null);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// update entity_parent
			entityDao.updateEntParent(enterpriseId.toString(), entityParentId, entityParentType, "");

			enterprise.setEntityParent(entityParentId);
			enterprise.setEntityParentType(entityParentType);

			// init & get admin pwd
			enterprise.setEntityPwd(enterpriseUserDao.resetEntAdminPwd(enterpriseId));

			return enterprise;
		} else {
			return null;
		}
	}

	/**
	 * 查找同步到BOSS2的企业信息
	 * @param ccicId 平台ID
	 * @return
	 */
	public List<EnterpriseAll> getEntSyncInfo(Integer ccicId){
		List<EnterpriseAll> entInfoList = new ArrayList<EnterpriseAll>();
		
		// 查询企业的基本信息，业务信息和账户信息
		StringBuffer qrySql = new StringBuffer("");
		qrySql.append("select a.enterprise_id,a.entity_parent,a.entity_parent_type,a.enterprise_name,a.full_name,a.master_hotline,a.status,a.inbound_call_limit,a.cti_id,")
			.append(" a.entity_status,a.product_id,a.period,a.lowest_cost,a.total_lowest_cost,a.is_test,a.service_start,a.service_end,a.trade,")
			.append(" a.create_time, a.legal_person, a.address, a.principal, a.mobile, a.email, a.payer_name, a.payer_mobile, a.payer_email,")
			.append(" a.account_id,a.money, a.credit,a.credit_temp,a.arrears_time,a.credit_valid_time,a.client_web,a.client_tel,a.client_free,a.rent,b.product_type,b.client_area, ")
			.append(ccicId).append(" as ccic_id, eco.combo_id ")
			.append(" from enterprise a left join business b on a.enterprise_id = b.enterprise_id")
			.append(" left join enterprise_combo_order eco on a.enterprise_id = eco.enterprise_id order by a.enterprise_id;");
		SqlRowSet rs = jdbcTemplate.queryForRowSet(qrySql.toString());
		while (rs.next()) {
			EnterpriseAll ent = new EnterpriseAll();
			ent.setEnterpriseId(rs.getInt("enterprise_id"));
			ent.setEntityParent(rs.getInt("entity_parent"));
			ent.setEntityParentType(rs.getInt("entity_parent_type"));
			ent.setEnterpriseName(rs.getString("enterprise_name"));
			ent.setFullName(rs.getString("full_name"));
			ent.setMasterHotline(rs.getString("master_hotline")==null?"":rs.getString("master_hotline"));
			ent.setStatus(rs.getInt("status"));
			ent.setEntityStatus(rs.getInt("entity_status"));
			ent.setProductId(rs.getInt("product_id"));
			ent.setPeriod(rs.getInt("period"));
			ent.setLowestCost(rs.getBigDecimal("lowest_cost"));
			ent.setTotalLowestCost(rs.getBigDecimal("total_lowest_cost"));
			ent.setIsTest(rs.getInt("is_test"));
			try {
				ent.setServiceStart(DateUtil.parse(rs.getString("service_start"),DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
				ent.setServiceEnd(DateUtil.parse(rs.getString("service_end"),DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
				ent.setCreateTime(DateUtil.parse(rs.getString("create_time"),DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ent.setCcicId(rs.getInt("ccic_id"));
			ent.setComboId(rs.getInt("combo_id"));
			ent.setClientWeb(rs.getInt("client_web"));
			ent.setRent(rs.getBigDecimal("rent"));
			ent.setClientTel(rs.getInt("client_tel"));
			ent.setClientFree(rs.getInt("client_free"));
			ent.setAccountId(rs.getInt("account_id"));
			ent.setMoney(rs.getBigDecimal("money"));
			ent.setCreditTemp(rs.getBigDecimal("credit_temp"));
			ent.setCredit(rs.getBigDecimal("credit"));
			ent.setClientArea(rs.getString("client_area"));
			ent.setProductType(rs.getInt("product_type"));
			ent.setArrearsTime(rs.getLong("arrears_time"));
			ent.setCreditValidTime(rs.getLong("credit_valid_time"));
			ent.setTrade(rs.getString("trade")==null?"":rs.getString("trade"));
			ent.setLegalPerson(rs.getString("legal_person")==null?"":rs.getString("legal_person"));
			ent.setAddress(rs.getString("address")==null?"":rs.getString("address"));
			ent.setPrincipal(rs.getString("principal")==null?"":rs.getString("principal"));
			ent.setMobile(rs.getString("mobile")==null?"":rs.getString("mobile"));
			ent.setEmail(rs.getString("email")==null?"":rs.getString("email"));
			ent.setPayerName(rs.getString("payer_name")==null?"":rs.getString("payer_name"));
			ent.setPayerMobile(rs.getString("payer_mobile")==null?"":rs.getString("payer_mobile"));
			ent.setPayerEmail(rs.getString("payer_email")==null?"":rs.getString("payer_email"));
			ent.setInboundCallLimit(rs.getInt("inbound_call_limit"));
			ent.setCtiId(rs.getInt("cti_id"));
			entInfoList.add(ent);
		}
		return entInfoList;
	}
	
	/**
	 * 获取所有企业的下月固定费用总和
	 * @return
	 */
	public Map<Integer, BigDecimal> getEntTotalCost() {
		Map<Integer, BigDecimal> rtnMap = new HashMap<Integer, BigDecimal>();
		StringBuffer sql = new StringBuffer();
		sql.append("select enterprise_id,client_tel,client_free,client_web,rent from enterprise where status != " + Const.ENTERPRISE_BUSINESS_STATUS_LOGOUT);
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql.toString());
		while (rs.next()) {
			BigDecimal totalCost = BigDecimal.ZERO;
			Integer enterpriseId = rs.getInt("enterprise_id");
			int clientTel = rs.getInt("client_tel");
			int clientWeb = rs.getInt("client_web");
			int clientFree = rs.getInt("client_free");
			// 座席功能费
			BigDecimal clientRentCost = rs.getBigDecimal("rent").multiply(new BigDecimal(rs.getInt("client_web")));
			totalCost = totalCost.add(clientRentCost);
			// 最低消费
			sql.setLength(0);
			sql.append("select sum(case when period=" + Const.ENTERPRISE_HOTLINE_PERIOD_MONTH+" then lowest_cost_next else lowest_cost_next/12 end)" +
					" as total_lowest_cost from enterprise_hotline where enterprise_id=" + enterpriseId);
			SqlRowSet feeRs = jdbcTemplate.queryForRowSet(sql.toString());
			while(feeRs.next()){
				totalCost = totalCost.add(feeRs.getBigDecimal("total_lowest_cost"));
			}
			// 号码月功能费
			sql.setLength(0);
			sql.append("select sum(rent) as total_number_cost from trunk where enterprise_id=" + enterpriseId);
			feeRs = jdbcTemplate.queryForRowSet(sql.toString());
			while(feeRs.next()){
				totalCost = totalCost.add(feeRs.getBigDecimal("total_number_cost"));
			}
			// 中继占用费
			sql.setLength(0);
			sql.append("select value from enterprise_setting where name = '" + Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_IS_PREDICTIVE_OPEN +  "' and enterprise_id=" + enterpriseId);
			feeRs = jdbcTemplate.queryForRowSet(sql.toString());
			while(feeRs.next()){
				String isObPredictive = feeRs.getString("value");
				if(isObPredictive != null && String.valueOf(Const.IS_PREDICTIVE_OPEN_ON).equals(isObPredictive)){
					String obPredictiveCallLimit = "0";
					String obPredictiveCallFee = "0";
					
					// 查询预测外呼并发限制数
					sql.setLength(0);
					sql.append("select value from enterprise_setting where name = '" + Const.ENTERPRISE_SETTING_PREDICTIVE_CALL_LIMIT +  "' and enterprise_id=" + enterpriseId);
					SqlRowSet entSettingRs = jdbcTemplate.queryForRowSet(sql.toString());
					while(entSettingRs.next()){
						obPredictiveCallLimit = entSettingRs.getString("value");
					}
					// 查询预测外呼每并发资费
					sql.setLength(0);
					sql.append("select value from enterprise_setting where name = '" + Const.ENTERPRISE_SETTING_PREDICTIVE_CALL_FEE +  "' and enterprise_id=" + enterpriseId);
					entSettingRs = jdbcTemplate.queryForRowSet(sql.toString());
					while(entSettingRs.next()){
						obPredictiveCallFee = entSettingRs.getString("value");
					}
					
					totalCost = totalCost.add(new BigDecimal(obPredictiveCallLimit).multiply(new BigDecimal(obPredictiveCallFee)));
				}
			}
			
			// 外呼套餐费
			sql.setLength(0);
			sql.append("select new_combo_id from enterprise_combo_order where enterprise_id="+enterpriseId);
			feeRs = jdbcTemplate.queryForRowSet(sql.toString());
			while(feeRs.next()){
				int newComboId = feeRs.getInt("new_combo_id");
				if(newComboId != 0){
					Combo combo = comboDao.get(newComboId);
					int clientCount = clientFree + clientTel + clientWeb;
					totalCost = totalCost.add(new BigDecimal(clientCount).multiply(combo.getRent()));
				}
			}
			rtnMap.put(enterpriseId, totalCost.setScale(3, BigDecimal.ROUND_HALF_UP));
		}
		
		return rtnMap;
	}
}
