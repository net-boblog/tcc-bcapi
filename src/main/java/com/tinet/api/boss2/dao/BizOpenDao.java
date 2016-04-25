package com.tinet.api.boss2.dao;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Account;
import com.tinet.api.boss2.model.Ccic;
import com.tinet.api.boss2.model.EnterpriseHotline;
import com.tinet.api.boss2.model.EnterpriseTemp;
import com.tinet.api.boss2.model.EnterpriseTrunk;
import com.tinet.api.boss2.model.Entity;
import com.tinet.api.boss2.model.Function;
import com.tinet.api.boss2.model.FunctionSelect;
import com.tinet.api.boss2.model.LogCharge;
import com.tinet.api.boss2.model.LogDeduction;
import com.tinet.common.inc.Const;
import com.tinet.common.util.DateUtil;
import com.tinet.common.util.MD5Encoder;
import com.tinet.common.util.RandomPwd;
import com.tinet.common.util.ReadFileUtil;
import com.tinet.common.util.StringUtil;

/**
 * 开户开通业务数据库多表读写实现
 * <p>
 *  FileName： BizOpenDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("OpenBizDao")
public class BizOpenDao {
	@Autowired
	private LogChargeDao logChargeDao;
	@Autowired
	private LogDeductionDao logDeductionDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 开户开通业务，客户账户、企业配置等信息初始化。
	 * @param enterpriseTemp 开户所需客户基本信息，业务信息，企业配置
	 * @param logChargeList 充值日志
	 * @param logDeductionList 扣费日志
	 * @param account 企业账户信息
	 */
	@Transactional
	public void executeOpenBiz(EnterpriseTemp enterpriseTemp, List<LogCharge> logChargeList,
			List<LogDeduction> logDeductionList, Account account) {
		Integer enterpriseId = enterpriseTemp.getEnterpriseId();
		Entity entity = enterpriseTemp.getEntity();
		Ccic ccic = enterpriseTemp.getCcic();
		StringBuffer insertSql = new StringBuffer("");
		// insert entity
		insertSql
				.append("INSERT INTO entity(enterprise_id, enterprise_name, entity_type, "
						+ "entity_parent,entity_parent_type, full_name, area_code, principal, sex, sex_title, mobile, tel, email, fax, "
						+ "address, post, status, legal_person, trade, business_no, question, answer, website, "
						+ "certificate_type, certificate_id, certificate_time, \"comment\", country, state, city, \"language\",payer_name,payer_mobile,payer_email)"
						+ " VALUES ("
						+ enterpriseId
						+ ",'"
						+ entity.getEntityName()
						+ "',"
						+ entity.getEntityType()
						+ ","
						+ entity.getEntityParent()
						+ ","
						+ Const.ENTITY_ENTITY_TYPE_DIRECT_MANAGER
						+ ",'"
						+ entity.getFullName()
						+ "','"
						+ entity.getAreaCode()
						+ "','"
						+ entity.getPrincipal()
						+ "',"
						+ entity.getSex()
						+ ",'"
						+ entity.getSexTitle()
						+ "','"
						+ entity.getMobile()
						+ "','"
						+ entity.getTel()
						+ "','"
						+ entity.getEmail()
						+ "','"
						+ entity.getFax()
						+ "','"
						+ entity.getAddress()
						+ "','"
						+ entity.getPost()
						+ "',"
						+ entity.getStatus()
						+ ",'"
						+ entity.getLegalPerson()
						+ "','"
						+ entity.getTrade()
						+ "','"
						+ entity.getBusinessNo()
						+ "','"
						+ entity.getQuestion()
						+ "','"
						+ entity.getAnswer()
						+ "','"
						+ entity.getWebsite()
						+ "',"
						+ entity.getCertificateType()
						+ ",'"
						+ entity.getCertificateId()
						+ "','"
						+ entity.getCertificateTime()
						+ "','"
						+ entity.getComment()
						+ "','"
						+ entity.getCountry()
						+ "','"
						+ entity.getState()
						+ "','"
						+ entity.getCity()
						+ "','"
						+ entity.getLanguage()
						+ "','"
						+ entity.getPayerName()
						+ "','"
						+ entity.getPayerMobile()
						+ "','"
						+ entity.getPayerEmail()
						+ "');");
		jdbcTemplate.execute(insertSql.toString());

		// insert bussiness
		insertSql.setLength(0);
		insertSql
				.append("INSERT INTO business(enterprise_id, system_id, node_id, cti_id, product_id, bargain_no,\"owner\", status,  "
						+ "is_test, client_tel, client_web,client_free, rent, rent_end, inbound_call_limit, service_start, service_end,product_type,client_area,is_idd) "
						+ "VALUES ("
						+ enterpriseId
						+ ","
						+ ccic.getSystemId()
						+ ","
						+ ccic.getNodeId()
						+ ","
						+ enterpriseTemp.getCtiId()
						+ ","
						+ enterpriseTemp.getProduct().getId()
						+ ",'"
						+ enterpriseTemp.getBargainNo()
						+ "','"
						+ enterpriseTemp.getOwner()
						+ "',"
						+ enterpriseTemp.getStatus()
						+ ","
						+ enterpriseTemp.getIsTest()
						+ ","
						+ enterpriseTemp.getClientTel()
						+ ","
						+ enterpriseTemp.getClientWeb()
						+ ","
						+ enterpriseTemp.getClientFree()
						+ ","
						+ enterpriseTemp.getRent()
						+ ",to_timestamp('"
						+ DateUtil.format(DateUtil.addMonth(new Date(), 12), DateUtil.FMT_DATE_YYYY_MM_DD)
						+ "','YYYY-MM-DD'),"
						+ enterpriseTemp.getInboundCallLimit()
						+ ",to_timestamp('"
						+ DateUtil.format(enterpriseTemp.getServiceStart(), DateUtil.FMT_DATE_YYYY_MM_DD)
						+ "','YYYY-MM-DD'), to_timestamp('"
						+ DateUtil.format(enterpriseTemp.getServiceEnd(), DateUtil.FMT_DATE_YYYY_MM_DD)
						+ "','YYYY-MM-DD'),"
						+ enterpriseTemp.getProductType()
						+ ",'"
						+ (enterpriseTemp.getClientArea() == null ? "" : enterpriseTemp.getClientArea())
						+ "',"
						+ enterpriseTemp.getIsIdd() + ");");
		jdbcTemplate.execute(insertSql.toString());

		// insert enterprise_hotline
		Set<EnterpriseHotline> entHotlineSet = enterpriseTemp.getEnterpriseHotlines();
		for (Iterator<EnterpriseHotline> iterator = entHotlineSet.iterator(); iterator.hasNext();) {
			EnterpriseHotline eHotline = (EnterpriseHotline) iterator.next();
			insertSql.setLength(0);

			int lowestCostMode = 0;

			if (eHotline.getLowestCostMode() != null) {
				lowestCostMode = eHotline.getLowestCostMode();
			}
			insertSql.setLength(0);
			insertSql
					.append("INSERT INTO enterprise_hotline(enterprise_id, hotline, is_master, lowest_cost, period, lowest_cost_next, number_trunk, type,lowest_cost_mode)"
							+ "VALUES ("
							+ enterpriseId
							+ ",'"
							+ eHotline.getHotline()
							+ "',"
							+ eHotline.getIsMaster()
							+ ", "
							+ eHotline.getLowestCost()
							+ ","
							+ eHotline.getPeriod()
							+ ","
							+ eHotline.getLowestCost()
							+ ",'"
							+ eHotline.getNumberTrunk()
							+ "',"
							+ eHotline.getType()
							+ "," + lowestCostMode + ");");
			jdbcTemplate.execute(insertSql.toString());
		}

		// insert trunk
		Set<EnterpriseTrunk> trunks = enterpriseTemp.getEnterpriseTrunks();
		for (Iterator<EnterpriseTrunk> iterator = trunks.iterator(); iterator.hasNext();) {
			EnterpriseTrunk eTrunk = (EnterpriseTrunk) iterator.next();
			insertSql.setLength(0);
			insertSql
					.append("INSERT INTO trunk(enterprise_id, area_code, number_trunk, is_billing, type, \"comment\") ")
					.append("VALUES (").append(enterpriseId).append(",'").append(eTrunk.getAreaCode()).append("','")
					.append(eTrunk.getNumberTrunk()).append("',").append(eTrunk.getIsBilling()).append(",")
					.append(eTrunk.getType()).append(",'');");
			jdbcTemplate.execute(insertSql.toString());
		}

		// insert function_select
		if (enterpriseTemp.getFunctionSelects().size() > 0) {
			for (Iterator<FunctionSelect> iterator = enterpriseTemp.getFunctionSelects().iterator(); iterator.hasNext();) {
				FunctionSelect fSelect = (FunctionSelect) iterator.next();
				Integer functionId = fSelect.getFunctionId();

				insertSql.setLength(0);
				insertSql
						.append("INSERT INTO function_select(enterprise_id, function_id, discount, function_end, function_property)"
								+ "VALUES ("
								+ enterpriseId
								+ ","
								+ functionId
								+ ","
								+ fSelect.getDiscount()
								+ ", to_timestamp('"
								+ DateUtil.format(DateUtil.addMonth(new Date(), 12), DateUtil.FMT_DATE_YYYY_MM_DD)
								+ "','YYYY-MM-DD'),'" + fSelect.getFunctionProperty() + "');");
				jdbcTemplate.execute(insertSql.toString());

				// 同时对用户开通的各功能在enterprise_setting中增加一条开启/关闭功能的记录。
				switch (functionId) {
				case Function.IB_RECORD:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_record_ib', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_record_ib', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.OB_RECORD:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_record_ob', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_record_ob', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.REMEMBER_CALL:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_remember_call', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_remember_call', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.OB:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_ob_click', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_ob_click', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.PREVIEW_OB:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_ob_preview', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_ob_preview', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.PREDICTIVE_OB:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_ob_predictive', '0', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_ob_predictive', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.DIRECT_OB:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_ob_direct', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_ob_direct', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.VOICE_MAIL:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_voicemail', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_voicemail', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.WEB400:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_web400', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_web400', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.FAX_RECEIVE:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_fax_receive', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_fax_receive', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.SMS_SEND:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_sms_send', '1', '');");
						jdbcTemplate.execute(insertSql.toString());
						// 保存企业短信小号和短信签名
						String[] smsInfoArr = StringUtil.split(fSelect.getFunctionProperty(), ",");
						insertSql.setLength(0);
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'")
								.append(Const.ENTERPRISE_SETTING_NAME_SMS_CELL).append("','").append(smsInfoArr[0])
								.append("','企业短信小号');");
						jdbcTemplate.execute(insertSql.toString());

						insertSql.setLength(0);
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'")
								.append(Const.ENTERPRISE_SETTING_NAME_SMS_SIGN).append("','").append(smsInfoArr[1])
								.append("','企业短信签名');");
						jdbcTemplate.execute(insertSql.toString());
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_sms_send', '0', '');");
						jdbcTemplate.execute(insertSql.toString());
						// 保存企业短信小号和短信签名
						insertSql.setLength(0);
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'")
								.append(Const.ENTERPRISE_SETTING_NAME_SMS_CELL).append("',").append("''")
								.append(",'企业短信小号');");
						jdbcTemplate.execute(insertSql.toString());

						insertSql.setLength(0);
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'")
								.append(Const.ENTERPRISE_SETTING_NAME_SMS_SIGN).append("',").append("''")
								.append(",'企业短信签名');");
						jdbcTemplate.execute(insertSql.toString());
					}
					break;
				case Function.WEB_CHAT:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_im', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_im', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.RESTRICT_TEL: // 默认不开启黑白名单
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'restrict_tel_type', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'restrict_tel_type', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.IVR_RECORD: // 默认不开启IVR录音
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId)
								.append(",'is_record_ivr', '1', 'ivr录音 0不开启 1开启');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId)
								.append(",'is_record_ivr', '0', 'ivr录音 0不开启 1开启');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.ADMIN_ACCESS_CONTROL:
					break;
				case Function.SMS_RECEIVE:
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_sms_receive', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'is_sms_receive', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				case Function.QUEUE_OBSERVER: // 默认不开启队列监控
					insertSql.setLength(0);
					if (fSelect.getIsOpen() == 1) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'queue_observer', '1', '');");
					}
					if (fSelect.getIsOpen() == 0) {
						insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)")
								.append(" values(").append(enterpriseId).append(",'queue_observer', '0', '');");
					}
					jdbcTemplate.execute(insertSql.toString());
					break;
				default:
					break;
				}

			}
		}

		insertSql.setLength(0);
		insertSql
				.append("INSERT INTO enterprise_rate(enterprise_id, rate_ib_left, rate_ib_right, rate_ob_left, rate_ob_right , rate_sms) ")
				.append("VALUES (").append(enterpriseId).append(",").append(enterpriseTemp.getRateIbLeft()).append(",")
				.append(enterpriseTemp.getRateIbRight()).append(",").append(enterpriseTemp.getRateObLeft()).append(",")
				.append(enterpriseTemp.getRateObRight()).append(",").append(enterpriseTemp.getRateSms()).append(");");
		jdbcTemplate.execute(insertSql.toString());

		// insert enterprise_router
		insertSql.setLength(0);
		insertSql
				.append("INSERT INTO enterprise_router(enterprise_id, ib_router_right, ob_preview_router_left, ob_predictive_router_left) ")
				.append("VALUES (").append(enterpriseId).append(",").append(enterpriseTemp.getIbRouterRight())
				.append(",").append(enterpriseTemp.getObPreviewRouterLeft()).append(",")
				.append(enterpriseTemp.getObPredictiveRouterLeft()).append(");");
		jdbcTemplate.execute(insertSql.toString());

		// insert enterprise_clid
		insertSql.setLength(0);
		insertSql
				.append("INSERT INTO enterprise_clid(enterprise_id, ib_clid_right_type, ib_clid_right_number, ob_preview_clid_left_type, ")
				.append("ob_preview_clid_left_number, ob_preview_clid_right_type,ob_preview_clid_right_number, ob_predictive_clid_left_type, ")
				.append("ob_predictive_clid_left_number, ob_predictive_clid_right_type, ob_predictive_clid_right_number) ")
				.append("VALUES (").append(enterpriseId).append(",").append(enterpriseTemp.getIbClidRightType())
				.append(",'").append(enterpriseTemp.getIbClidRightNumber()).append("',")
				.append(enterpriseTemp.getObPreviewClidLeftType()).append(",'")
				.append(enterpriseTemp.getObPreviewClidLeftNumber()).append("',")
				.append(enterpriseTemp.getObPreviewClidRightType()).append(",'")
				.append(enterpriseTemp.getObPreviewClidRightNumber()).append("',")
				.append(enterpriseTemp.getObPredictiveClidLeftType()).append(",'")
				.append(enterpriseTemp.getObPredictiveClidLeftNumber()).append("',")
				.append(enterpriseTemp.getObPredictiveClidRightType()).append(",'")
				.append(enterpriseTemp.getObPredictiveClidRightNumber()).append("');");
		jdbcTemplate.execute(insertSql.toString());

		// insert enterprise_role, three roles: 管理员，质检，配置
		String getSeqIdSql = "select nextval('enterprise_role_id_seq')";
		Integer roleAdminId = jdbcTemplate.queryForObject(getSeqIdSql, Integer.class);
		insertSql.setLength(0);
		insertSql.append("INSERT INTO enterprise_role(id, enterprise_id, name, description) VALUES (")
				.append(roleAdminId).append(",").append(enterpriseId).append(", '管理员', '系统管理员角色');");
		jdbcTemplate.execute(insertSql.toString());

		Integer roleAssignerId = jdbcTemplate.queryForObject(getSeqIdSql, Integer.class);
		insertSql.setLength(0);
		insertSql.append("INSERT INTO enterprise_role(id, enterprise_id, name, description) VALUES (")
				.append(roleAssignerId).append(",").append(enterpriseId).append(", '配置', '配置角色');");
		jdbcTemplate.execute(insertSql.toString());

		Integer roleQualityCheckerId = jdbcTemplate.queryForObject(getSeqIdSql, Integer.class);
		insertSql.setLength(0);
		insertSql.append("INSERT INTO enterprise_role(id, enterprise_id, name, description) VALUES (")
				.append(roleQualityCheckerId).append(",").append(enterpriseId).append(", '质检', '质检角色');");
		jdbcTemplate.execute(insertSql.toString());

		// inserty enterprise_user,three users:管理员admin, 质检员zj && 配置员pz
		String defaultPwd = MD5Encoder.encode("123456");
		getSeqIdSql = "select nextval('enterprise_user_id_seq')";
		Integer userAdminId = jdbcTemplate.queryForObject(getSeqIdSql, Integer.class);
		insertSql.setLength(0);
		String psw = "".equals(entity.getEntityPwd()) ? "123456" : entity.getEntityPwd();
		psw = MD5Encoder.encode(psw);
		insertSql.append("INSERT INTO enterprise_user(id,enterprise_id, name, pwd, description, role_id, is_right_on)")
				.append("VALUES (").append(userAdminId).append(",").append(enterpriseId)
				.append(",'admin','" + psw + "','系统默认管理员',").append(roleAdminId).append(",0);");
		jdbcTemplate.execute(insertSql.toString());

		Integer userQualityCheckerId = jdbcTemplate.queryForObject(getSeqIdSql, Integer.class);
		insertSql.setLength(0);
		insertSql.append("INSERT INTO enterprise_user(id,enterprise_id, name, pwd, description, role_id, is_right_on)")
				.append("VALUES (").append(userQualityCheckerId).append(",").append(enterpriseId)
				.append(",'zj','" + defaultPwd + "','系统默认质检员',").append(roleQualityCheckerId).append(",0);");
		jdbcTemplate.execute(insertSql.toString());

		Integer userAssignerId = jdbcTemplate.queryForObject(getSeqIdSql, Integer.class);
		insertSql.setLength(0);
		insertSql.append("INSERT INTO enterprise_user(id,enterprise_id, name, pwd, description, role_id, is_right_on)")
				.append("VALUES (").append(userAssignerId).append(",").append(enterpriseId)
				.append(",'pz','" + defaultPwd + "','系统默认配置员',").append(roleAssignerId).append(",0);");
		jdbcTemplate.execute(insertSql.toString());

		String qryAllPageSql = "select id from resource_page order by id";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qryAllPageSql);
		while (rSet.next()) {
			Integer pageId = rSet.getInt("id");
			insertSql.setLength(0);
			insertSql.append("INSERT INTO enterprise_role_page(enterprise_id, role_id, page_id, type)").append("VALUES (")
			.append(enterpriseId).append(",").append(roleAdminId).append(",").append(pageId).append(",").append(1)
			.append(");");
			jdbcTemplate.execute(insertSql.toString());
		}

		// insert enterprise_setting
		// enterprise_setting：使用接口
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
				.append(enterpriseId).append(",'is_use_itf', '").append(enterpriseTemp.getIsUseItf())
				.append("', '使用接口：0=不使用接口 1=使用接口');");
		jdbcTemplate.execute(insertSql.toString());
		// enterprise_setting：客户电话接入方式
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
		.append(enterpriseId).append(",'access_mode', '").append(enterpriseTemp.getAccessMode())
		.append("', '客户电话接入方式：1=直线 2=IAD 3=无线话机');");
		jdbcTemplate.execute(insertSql.toString());
		
		//是否全称录音
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
				.append(enterpriseId).append(",'is_full_record', '").append(enterpriseTemp.getIsFullRecord()).append("', '是否全称录音：0=否 1=是');");
		jdbcTemplate.execute(insertSql.toString());
		
		// 企业录音试听方式
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
				.append(enterpriseId).append(",'is_aws_record', '").append(enterpriseTemp.getIsAwsRecord()).append("', '企业录音试听方式：0=本地NFS 1=S3');");
		jdbcTemplate.execute(insertSql.toString());
		// S3目录
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
				.append(enterpriseId).append(",'aws_record_directory', '").append(enterpriseTemp.getAwsRecordDirectory()==null?"":enterpriseTemp.getAwsRecordDirectory()).append("', '企业录音试方式是S3时的目录');");
		jdbcTemplate.execute(insertSql.toString());
		
		// 外呼黑名单开关
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
				.append(enterpriseId).append(",'out_call_switch', '").append(enterpriseTemp.getOutCallSwitch()==null?"":enterpriseTemp.getOutCallSwitch()).append("', '")
				.append(enterpriseTemp.getOutCallPropertyJson()==null?"":enterpriseTemp.getOutCallPropertyJson()).append("');");
		jdbcTemplate.execute(insertSql.toString());
		
		//呼入座席自动置忙开关/次数 
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
				.append(enterpriseId).append(",'seat_na_pause','0','0')");
		jdbcTemplate.execute(insertSql.toString());
		// enterprise_setting：是否开启接口调听录音频次试听或下载(1表示是，0表示否，默认是0)
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
			.append(enterpriseId).append(",'interface_call_frequency','0,100', '接口访问频次控制；逗号前0不开启，1开启，逗号后，超时时常是整数，单位是次数，默认是100次');");
		jdbcTemplate.execute(insertSql.toString());
		// enterprise_setting：是否开启座席状态全局广播，默认关闭，关闭时只对本座席及班长席广播（1表示是，0表示否，默认是0)
		insertSql.setLength(0);
		insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
				.append(enterpriseId).append(",'client_status_broadcast','0', '是否开启座席状态全局广播，默认关闭，关闭时只对本座席及班长席广播（1表示是，0表示否，默认是0)');");
		jdbcTemplate.execute(insertSql.toString());

		// insert team
		// get team_id
		String getTeamIdSql = "select nextval('team_id_seq')";
		Integer teamId = jdbcTemplate.queryForObject(getTeamIdSql, Integer.class);

		insertSql.setLength(0);
		insertSql.append("insert into team(id,enterprise_id, name, comment)").append(" VALUES (").append(teamId)
				.append(",").append(enterpriseId).append(",'默认团队','默认团队');");
		jdbcTemplate.execute(insertSql.toString());

		// 若是测试账户，默认需向client、sipConf和customer各添加一条信息，给默认的队列各添加一条queueMember，向enterprise_setting中添加使用有效日期。
		if (enterpriseTemp.getIsTest() == Const.ENTERPRISE_TEMP_IS_TEST_YES) {
			//设置一个技能A
			// get skill_id
			String getSkillIdSql = "select nextval('skill_id_seq')";
			Integer skillId = jdbcTemplate.queryForObject(getSkillIdSql, Integer.class);
			insertSql.setLength(0);
			insertSql.append("insert into skill(id,enterprise_id,name,comment)").append("values(").append(skillId)
					.append(",").append(enterpriseId).append(",'A','技能A')");
			jdbcTemplate.execute(insertSql.toString());

			String queueName1 = "";
			String queueName2 = "";
			int queueId1 = 0;
			int queueId2 = 0;
			//新增2个队列，业务咨询、投诉及故障申告
			for (int i = 0; i < 2; i++) {
				// get queue_id
				String getQueueIdSql = "select nextval('queue_id_seq')";
				Integer queueId = jdbcTemplate.queryForObject(getQueueIdSql, Integer.class);
				String qno = "000" + i;
				String description = "";
				if (i == 0) {
					description += "业务咨询";
					queueName1 = String.valueOf(enterpriseId) + String.valueOf(qno);
					queueId1 = queueId;
				} else if (i == 1) {
					description += "投诉及故障申告";
					queueName2 = String.valueOf(enterpriseId) + String.valueOf(qno);
					queueId2 = queueId;
				}
				insertSql.setLength(0);
				insertSql
						.append("insert into queue (id,queue_name, description, qno, enterprise_id, music_class, queue_timeout, say_agentno,context, "
								+ "member_timeout, retry, timeout_priority, member_delay,wrapup_time, max_len, strategy, set_interfacevar, timeout_restart,"
								+ " service_level, weight, vip_support, join_empty, ring_inuse, leave_when_empty, queue_youarenext, queue_thereare, "
								+ "queue_callswaiting, queue_quantity1, queue_quantity2, announce_position, announce_position_limit, announce_holdtime, "
								+ "announce_round_seconds, min_announce_frequency, event_when_called) ")
						.append("values (")
						.append(queueId)
						.append(",'" + String.valueOf(enterpriseId) + String.valueOf(qno))
						.append("','")
						.append(description)
						.append("','")
						.append(qno)
						.append("',")
						.append(enterpriseId)
						.append(",'default', 600 , 'FALSE', 'default', 25, 5, 'conf', 0, 30, 5, 'order', 1, 'TRUE', 10, 1, 0, 'unavailable', "
								+ "'no', 'unavailable', '', '', '', '', '', 'no' , 0, 'no', 10, 15, 'TRUE')");
				jdbcTemplate.execute(insertSql.toString());
				//为队列分配技能
				insertSql.setLength(0);
				insertSql.append("insert into queue_skill (enterprise_id, queue_id, skill_id, skill_level)")
						.append("values(").append(enterpriseId).append(",").append(queueId).append(",").append(skillId)
						.append(", 1)");
				jdbcTemplate.execute(insertSql.toString());
			}

			//新建5个座席，同时sipConfig，queue_member添加数据
			for (int i = 0; i < 5; i++) {
				int cno = 2000 + i;
				//get sipConfig_id
				String getSipIdSql = "select nextval('sip_conf_id_seq')";
				Integer sipId = jdbcTemplate.queryForObject(getSipIdSql, Integer.class);
				// get client_id
				String getClientSeqIdSql = "select nextval('client_id_seq')";
				Integer clientId = jdbcTemplate.queryForObject(getClientSeqIdSql, Integer.class);
				String sipConfName = String.valueOf(enterpriseId) + String.valueOf(cno);
				//insert sipConfig
				insertSql.setLength(0);
				insertSql
						.append("insert into sip_conf(id,name, enterprise_id, call_limit,ip_addr,music_on_hold, type, ")
						.append(" host, default_ip, context, can_reinvite, language, nat, port, username, disallow,")
						.append(" allow, reg_seconds, reg_exten, can_call_forward, last_ms)")
						.append(" values(")
						.append(sipId)
						.append(",'" + sipConfName + "',")
						.append(enterpriseId)
						.append(",")
						.append(" 1,'', '', 'friend', '', '', 'default', 'no', 'zh', 'yes', '5060', "
								+ " '','all',  'g729,gsm,ulaw,alaw', '0', '', 'yes', '0' )");
				jdbcTemplate.execute(insertSql.toString());

				// insert client
				insertSql.setLength(0);
				insertSql
						.append("insert into client(id, enterprise_id, cno, crm_id, team_id, sip_conf_id, active, wrapup, area_code, name, pwd, "
								+ "power, ob_clid, call_power, login_status, device_status, sex, birth, certificate_id, mobile, other_tel, fax, email, address,"
								+ " post, client_type, is_ob, ib_record, ob_record, is_agent_observer, is_skill_observer, is_send_msg, is_msg_record, last_login_time)")
						.append(" values(").append(clientId).append(",").append(enterpriseId).append(",").append(cno)
						.append(",'',").append(teamId).append(",").append(sipId).append(",1, 30, '010', '").append(cno)
						.append("', '" + MD5Encoder.encode(RandomPwd.randomString(8)) + "',")
						.append("0, '' , 0, 0, 0, 0, '', '', '', '', '', '', '', '', 2, 1, 1, 1, 0, 0, 0, 0, 0)");
				jdbcTemplate.execute(insertSql.toString());

				//为座席分配技能
				insertSql.setLength(0);
				insertSql.append("insert into client_skill (enterprise_id, client_id, skill_id, skill_level)")
						.append("values(").append(enterpriseId).append(",").append(clientId).append(",")
						.append(skillId).append(", 1)");
				jdbcTemplate.execute(insertSql.toString());

				for (int j = 0; j < 2; j++) {
					String queueName = "";
					int queueId = 0;
					if (j == 0) {
						queueName = queueName1;
						queueId = queueId1;
					}
					if (j == 1) {
						queueName = queueName2;
						queueId = queueId2;
					}
					//insert queue_member
					insertSql.setLength(0);
					insertSql
							.append("insert into queue_member(queue_name, queue_id, interface, state_interface, member_name, "
									+ "penalty, tel, client_id, paused, online)");
					insertSql.append("values('").append(queueName).append("',").append(queueId).append(",'")
							.append("SIP/未知号码@" + queueName).append("','").append("SIP/" + sipConfName).append("','")
							.append(sipConfName).append("',1,'',").append(clientId).append(",").append("0, 0)");
					jdbcTemplate.execute(insertSql.toString());
				}
			}

			// insert enterprise_setting
			insertSql.setLength(0);
			insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
					.append(enterpriseId).append(",'")
					.append(Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_EXPIRY_DATE).append("','").append("")
					.append("','测试账户使用有效日期');");
			jdbcTemplate.execute(insertSql.toString());
			insertSql.setLength(0);
			insertSql.append("insert into enterprise_setting(enterprise_id, name, value, property)").append(" values(")
					.append(enterpriseId).append(",'").append(Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_STATUS)
					.append("','").append(Const.ENTERPRISE_SETTING_TEST_STATUS_FREE).append("','测试账户使用状态');");
			jdbcTemplate.execute(insertSql.toString());

		} else {
			//正式客户增加IVR配置
			//设置一个技能A
			// get skill_id
			String getSkillIdSql = "select nextval('skill_id_seq')";
			Integer skillId = jdbcTemplate.queryForObject(getSkillIdSql, Integer.class);
			insertSql.setLength(0);
			insertSql.append("insert into skill(id,enterprise_id,name,comment)").append("values(").append(skillId)
					.append(",").append(enterpriseId).append(",'a','业务咨询')");
			jdbcTemplate.execute(insertSql.toString());

			String queueName = "";
			//新增1个队列，业务咨询
			// get queue_id
			String getQueueIdSql = "select nextval('queue_id_seq')";
			Integer queueId = jdbcTemplate.queryForObject(getQueueIdSql, Integer.class);
			String qno = "0000";
			String description = "";
			description += "业务咨询";
			queueName = String.valueOf(enterpriseId) + qno;
			insertSql.setLength(0);
			insertSql
					.append("insert into queue (id,queue_name, description, qno, enterprise_id, music_class, queue_timeout, say_agentno,context, "
							+ "member_timeout, retry, timeout_priority, member_delay,wrapup_time, max_len, strategy, set_interfacevar, timeout_restart,"
							+ " service_level, weight, vip_support, join_empty, ring_inuse, leave_when_empty, queue_youarenext, queue_thereare, "
							+ "queue_callswaiting, queue_quantity1, queue_quantity2, announce_position, announce_position_limit, announce_holdtime, "
							+ "announce_round_seconds, min_announce_frequency, event_when_called) ")
					.append("values (").append(queueId).append(",'" + queueName).append("','").append(description).append("','")
					.append(qno).append("',").append(enterpriseId)
					.append(",'default', 600 , 'FALSE', 'default', 25, 5, 'conf', 0, 30, 5, 'order', 1, 'TRUE', 10, 1, 1, 'unavailable', "
							+ "'no', 'unavailable', '', '', '', '', '', 'no' , 0, 'no', 10, 15, 'TRUE')");
			jdbcTemplate.execute(insertSql.toString());
			//为队列分配技能
			insertSql.setLength(0);
			insertSql.append("insert into queue_skill (enterprise_id, queue_id, skill_id, skill_level)")
					.append("values(").append(enterpriseId).append(",").append(queueId).append(",").append(skillId)
					.append(", 1)");
			jdbcTemplate.execute(insertSql.toString());

			//新建1个座席，同时sipConfig，queue_member添加数据
			String cno = "2000";
			//get sipConfig_id
			String getSipIdSql = "select nextval('sip_conf_id_seq')";
			Integer sipId = jdbcTemplate.queryForObject(getSipIdSql, Integer.class);
			// get client_id
			String getClientSeqIdSql = "select nextval('client_id_seq')";
			Integer clientId = jdbcTemplate.queryForObject(getClientSeqIdSql, Integer.class);
			String sipConfName = String.valueOf(enterpriseId) + String.valueOf(cno);
			//insert sipConfig
			insertSql.setLength(0);
			insertSql
					.append("insert into sip_conf(id,name, enterprise_id, call_limit,ip_addr,music_on_hold, type, ")
					.append(" host, default_ip, context, can_reinvite, language, nat, port, username, disallow,")
					.append(" allow, reg_seconds, reg_exten, can_call_forward, last_ms)")
					.append(" values(").append(sipId).append(",'" + sipConfName + "',").append(enterpriseId).append(",")
					.append(" 1,'', '', 'friend', '', '', 'default', 'no', 'zh', 'yes', '5060', '','all', 'g729,gsm,ulaw,alaw', '0', '', 'yes', '0' )");
			jdbcTemplate.execute(insertSql.toString());

			// insert client
			insertSql.setLength(0);
			insertSql
					.append("insert into client(id, enterprise_id, cno, crm_id, team_id, sip_conf_id, active, wrapup, area_code, name, pwd, "
							+ "power, ob_clid, call_power, login_status, device_status, sex, birth, certificate_id, mobile, other_tel, fax, email, address,"
							+ " post, client_type, is_ob, ib_record, ob_record, is_agent_observer, is_skill_observer, is_send_msg, is_msg_record, last_login_time)")
					.append(" values(").append(clientId).append(",").append(enterpriseId).append(",'").append(cno)
					.append("','',").append(teamId).append(",").append(sipId).append(",1, 30, '010', '").append("张三")
					.append("', '" + MD5Encoder.encode(RandomPwd.randomString(8)) + "',")
					.append("0, '' , 0, 0, 0, 0, '', '', '', '', '', '', '', '', 2, 1, 1, 1, 0, 0, 0, 0, 0)");
			jdbcTemplate.execute(insertSql.toString());

			//为座席分配技能
			insertSql.setLength(0);
			insertSql.append("insert into client_skill (enterprise_id, client_id, skill_id, skill_level)")
					.append("values(").append(enterpriseId).append(",").append(clientId).append(",").append(skillId)
					.append(", 1)");
			jdbcTemplate.execute(insertSql.toString());

			//insert queue_member
			insertSql.setLength(0);
			insertSql.append("insert into queue_member(queue_name, queue_id, interface, state_interface, member_name, "
					+ "penalty, tel, client_id, paused, online)");
			insertSql.append("values('").append(queueName).append("',").append(queueId).append(",'")
					.append("SIP/未知号码@" + queueName).append("','").append("SIP/" + sipConfName).append("','")
					.append(sipConfName).append("',1,'',").append(clientId).append(",").append("0, 0)");
			jdbcTemplate.execute(insertSql.toString());

		}

		//insert into account
		long arrearsTime = account.getArrearsTime() == null ? 0 : account.getArrearsTime().getTime() / 1000;
		insertSql.setLength(0);
		insertSql.append("insert into account(id, enterprise_id,money,credit,"
				+ "account_type,pay_type,active,create_time,"
				+ "credit_temp,arrears_time,credit_days,credit_temp_days) " + "values("
				+ account.getId()+","+ enterpriseId	+ ","+ account.getMoney()+ ","+ 0 + ","
				+ 0	+ "," + 1 + "," + 1 + ",'" + DateUtil.dateToString(new Date(), DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss)
				+ "'," + 0 + "," + arrearsTime + "," + 0 + "," + 0 + ");");
		jdbcTemplate.execute(insertSql.toString());

		// insert into log_charge
		if (logChargeList!=null && logChargeList.size()>0) {
			for (LogCharge logCharge : logChargeList) {
				logChargeDao.saveLogCharge(logCharge);
			}
		}

		boolean isUserCombo = false;
		// insert into log_deduction
		if (logDeductionList!=null && logDeductionList.size()>0) {
			for (LogDeduction logDeduction : logDeductionList) {
				if (logDeduction.getFeeType() == Const.LOG_DEDUCTION_FEE_TYPE_OB_COMBO_FEE) {
					isUserCombo = true;
				}
				logDeductionDao.saveLogDeduction(logDeduction);
			}
		}

		//insert into enterprise_combo
		//insert into enterprise_combo_order
		String getEnterpriseComboOrderIdSql = "select nextval('enterprise_combo_order_id_seq')";
		try {
			Integer enterpriseComboOrderId = jdbcTemplate.queryForObject(getEnterpriseComboOrderIdSql, Integer.class);
			insertSql.setLength(0);
			insertSql
					.append("insert into enterprise_combo_order(id,enterprise_id,combo_id,valid_start_time,valid_end_time,start_time,end_time,"
							+ "new_combo_id,status,remark) values ("
							+ enterpriseComboOrderId
							+ ","
							+ enterpriseId
							+ ","
							+ enterpriseTemp.getComboId()
							+ ",'"
							+ new Date().getTime()
							/ 1000
							+ "','"
							+ DateUtil.parse("2099-01-01 00:00:00", DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss).getTime()
							/ 1000
							+ "','"
							+ new Date().getTime()
							/ 1000
							+ "','"
							+ DateUtil.parse(DateUtil.getDayInMonth(new Date(), false) + " 23:59:59",
									DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss).getTime()
							/ 1000
							+ "',"
							+ enterpriseTemp.getComboId() + "," + Const.COMOBO_VALID_ON + ",'');");
				jdbcTemplate.execute(insertSql.toString());
				if (isUserCombo) {
					insertSql.setLength(0);
					insertSql
						.append("insert into enterprise_combo (enterprise_id,combo_id,client_count,deduct_fee,threshold,start_time,end_time,combo_order_id) values("
								+ enterpriseId
								+ ","
								+ enterpriseTemp.getComboId()
								+ ","
								+ (enterpriseTemp.getClientWeb() + enterpriseTemp.getClientFree())
								+ ","
								+ enterpriseTemp.getDeuctFee()
								+ ","
								+ enterpriseTemp.getThreshold()
								+ ",'"
								+ new Date().getTime()
								/ 1000
								+ "','"
								+ DateUtil.parse(DateUtil.getDayInMonth(new Date(), false) + " 23:59:59",
										DateUtil.FMT_DATE_YYYY_MM_DD_HH_mm_ss).getTime()
								/ 1000
								+ "',"
								+ enterpriseComboOrderId + ");");
					jdbcTemplate.execute(insertSql.toString());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		// 增加默认满意度调查流程
		insertSql.setLength(0);
		String getInvestigationIdSql = "select nextval('enterprise_investigation_id_seq')";
		Integer investigationId = jdbcTemplate.queryForObject(getInvestigationIdSql, Integer.class);
		insertSql
				.append("insert into enterprise_investigation(id,enterprise_id, path, path_name, action, property, parent_id)")
				.append(" values(").append(investigationId).append(",").append(enterpriseId).append(",").append("1").append(",")
				.append("'按键选择'").append(",").append(2).append(",").append("'investigation;3;1-1.1,2-1.1,3-1.1;;'")
				.append(",").append(0).append(")");
		jdbcTemplate.execute(insertSql.toString());

		insertSql.setLength(0);
		insertSql
				.append("insert into enterprise_investigation(enterprise_id, path, path_name, action, property, parent_id)")
				.append(" values(").append(enterpriseId).append(",").append("1.1").append(",").append("'播报感谢调查语音'")
				.append(",").append(1).append(",").append("'1;investigation_bye;3;;'").append(",")
				.append(investigationId).append(")");
		jdbcTemplate.execute(insertSql.toString());

		// 初始化企业IVR导航
		insertSql.setLength(0);
		insertSql.append("select init_enterprise_ivr(").append(enterpriseId).append(")");
		jdbcTemplate.execute(insertSql.toString());

		this.createTables(enterpriseId);
	}
	
	/**
	 * 创建企业表并初始化企业数据
	 * @param enterpriseId
	 */
	@Transactional
	private void createTables(Integer enterpriseId) {
		URL url = null;
		
		// create table business_record_comment
		url = BizOpenDao.class.getResource("/sql/ccic2/business_record_comment.sql");
		String createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("business_record_comment_template", "business_record_comment_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);
		
		// create table business_record
		url = BizOpenDao.class.getResource("/sql/ccic2/business_record.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("business_record_template", "business_record_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);
		
		// create table cdr_ib_detail
		url = BizOpenDao.class.getResource("/sql/ccic2/cdr_ib_detail.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("cdr_ib_detail_template", "cdr_ib_detail_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);
		// create table cdr_ib
		url = BizOpenDao.class.getResource("/sql/ccic2/cdr_ib.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("cdr_ib_template", "cdr_ib_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);
		// create table cdr_ob_detail
		url = BizOpenDao.class.getResource("/sql/ccic2/cdr_ob_detail.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("cdr_ob_detail_template", "cdr_ob_detail_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);
		// create table cdr_ob
		url = BizOpenDao.class.getResource("/sql/ccic2/cdr_ob.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("cdr_ob_template", "cdr_ob_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);
		// create table cdr_sms
		url = BizOpenDao.class.getResource("/sql/ccic2/cdr_sms.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("cdr_sms_template", "cdr_sms_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);
		// create table customer
		url = BizOpenDao.class.getResource("/sql/ccic2/customer.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("customer_template", "customer_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);

		// create table task_inventory
		url = BizOpenDao.class.getResource("/sql/ccic2/task_inventory.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("task_inventory_template", "task_inventory_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);

		// create table tel_number
		url = BizOpenDao.class.getResource("/sql/ccic2/tel_number.sql");
		createTabSql = ReadFileUtil.readFile(url.getPath());
		createTabSql = createTabSql.replace("tel_number_template", "tel_number_" + enterpriseId);
		jdbcTemplate.execute(createTabSql);
		
		// insert into customize_field
		StringBuffer insertSql = new StringBuffer("");
		url = BizOpenDao.class.getResource("/sql/ccic2/customize_field.sql");
		insertSql.setLength(0);
		insertSql.append(ReadFileUtil.readFile(url.getPath()).replace("enterprise_id_template", enterpriseId.toString()));
		jdbcTemplate.execute(insertSql.toString());
		
		// insert into enterprise_button
		url = BizOpenDao.class.getResource("/sql/ccic2/enterprise_button.sql");
		insertSql.setLength(0);
		insertSql.append(ReadFileUtil.readFile(url.getPath()).replace("enterprise_id_template", enterpriseId.toString()));
		jdbcTemplate.execute(insertSql.toString());
		
		// insert into enterprise_pause_customize, give five status initializtion.
		url = BizOpenDao.class.getResource("/sql/ccic2/enterprise_setting.sql");
		insertSql.setLength(0);
		insertSql.append(ReadFileUtil.readFile(url.getPath()).replace("enterprise_id_template", enterpriseId.toString()));
		jdbcTemplate.execute(insertSql.toString());
		
		// insert into enterprise_setting
		url = BizOpenDao.class.getResource("/sql/ccic2/enterprise_pause_customize.sql");
		insertSql.setLength(0);
		insertSql.append(ReadFileUtil.readFile(url.getPath()).replace("enterprise_id_template", enterpriseId.toString()));
		jdbcTemplate.execute(insertSql.toString());
	}
}
