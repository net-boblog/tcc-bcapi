
package com.tinet.common.inc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Const {
	
	/*
	 * ccic: system_id & node_id definition
	 */
	public static final int CCIC_SYSTEM_ID_SMART400 = 1;
	public static final int CCIC_SYSTEM_ID_SUPERHOTLINE = 2;
	public static final int CCIC_SYSTEM_ID_HCC = 3;
	
	public static final int CCIC_NODE_ID_BEIJING = 1;
	public static final int CCIC_NODE_ID_SHANGHAI = 2;
	public static final int CCIC_NODE_ID_XIAN = 3;
	public static final int CCIC_NODE_ID_WUHAN = 4;
	public static final int CCIC_NODE_ID_LANGFANG = 5;
	public static final int CCIC_NODE_ID_CHONGQING = 6;
	public static final int CCIC_NODE_ID_NINGBO = 7;
	public static final int CCIC_NODE_ID_YINCHUAN = 8;
	public static final int CCIC_NODE_ID_GUANGZHOU = 9;
	public static final int CCIC_NODE_ID_SHENZHEN = 10;
	
	/*
	 * log_login login_type definition
	 */
	public static final int LOG_LOGIN_LOGIN_TYPE_LOGIN = 1;
	public static final int LOG_LOGIN_LOGIN_TYPE_LOGOUT = 2;
	
	/*
	 * log_login result definition
	 */
	public static final int LOG_LOGIN_RESULT_SUCCESS= 1;
	public static final int LOG_LOGIN_RESULT_WRONG_PASSWORD = 2;	// 密码无措
	public static final int LOG_LOGIN_RESULT_UNAVAILABLE_ENTITY_NAME = 3;	// 用户名不存在
	public static final int LOG_LOGIN_RESULT_UNAVAILABLE_LOGIN_STATUS = 4;	// 被禁止登录
	public static final int LOG_LOGIN_RESULT_LOCKED_ENTITY_NAME = 5;	// 当前用户被系统防骚扰机制锁定
	public static final int LOG_LOGIN_RESULT_UNAVAILABLE_SYSTEM_SETTING = 6; // 系统设置不对
	public static final int LOG_LOGIN_RESULT_WRONG_SECURITYCODE = 7;	// 图片验证码验证失败
	public static final int LOG_LOGIN_RESULT_UNKNOWEN_EXCEPTION = 8;	//	其他异常
	public static final int LOG_LOGIN_RESULT_IP_AUTH_FAIL = 9;  // 登录ip验证失败
	public static final int LOG_LOGIN_RESULT_EXE_TELAUTH = 10;	// 	跳转至语音验证界面
	public static final int LOG_LOGIN_RESULT_EMPTY_MOBILE = 11;	// 手机号码为空
	public static final int LOG_LOGIN_RESULT_WEB_CALL_FAIL = 12; 	// 调用语音外呼接口失败
	public static final int LOG_LOGIN_RESULT_TELCODE_AUTH_FAIL = 13;	// 语音验证码验证失败
	
	/*
	 * log_action result definition
	 */
	public static final int LOG_ACTION_RESULT_FAILURE = 0;
	public static final int LOG_ACTION_RESULT_SUCCESS = 1;
	
	/*
	 * entity entity_type definition
	 */
	public static final int ENTITY_ENTITY_TYPE_AGENT1 = 1;// 实体级别， 1=一级代理商,2=二级代理商,3=客户, 4=直销部门, 5=销售经理, 11=系统管理员, 12=出纳, 13=客服, 14=维护客服, 15=大区总监,16=渠道经理，17=直销副总
	public static final int ENTITY_ENTITY_TYPE_AGENT2 = 2;
	public static final int ENTITY_ENTITY_TYPE_ENTERPRISE = 3;
	public static final int ENTITY_ENTITY_TYPE_DIRECT_SECTION = 4;
	public static final int ENTITY_ENTITY_TYPE_DIRECT_MANAGER = 5;
	public static final int ENTITY_ENTITY_TYPE_ADMIN = 11;
	public static final int ENTITY_ENTITY_TYPE_CASHIER =12;
	public static final int ENTITY_ENTITY_TYPE_CUSTOMER_SERVICE = 13;
	public static final int ENTITY_ENTITY_TYPE_AREA_SUPERVISOR = 15;
	public static final int ENTITY_ENTITY_TYPE_CHANNEL_MANAGER = 16;
	public static final int ENTITY_ENTITY_TYPE_DEPUTY_GENERAL = 17;
	
	/*
	 *  entity sex,entity_parent,certificate_type default value definition
	 */
	public static final int ENTITY_SEX_DEFAULT = 0;
	public static final int ENTITY_ENTITY_PARENT_DEFAULT = 0;
	public static final int ENTITY_CERTIFICATE_TYPE_DEFAULT = 0;
	public static final String ENTITY_ENTITY_PWD_DEFAULT = "123456";
	public static final String ENTITY_COUNTRY_DEFAULT = "China";
	public static final String ENTITY_LANGUAGE_DEFAULT = "zh-CN";
	
	/*
	 * entity sex definition
	 */
	public static final int ENTITY_SEX_MALE = 0;
	public static final int ENTITY_SEX_FEMALE = 1;
	
	/*
	 * entity certificate_type definition
	 */
	public static final int ENTITY_CERTIFICATE_TYPE_IDENTITY = 0;
	public static final int ENTITY_CERTIFICATE_TYPE_PASSPORT = 1;
	public static final int ENTITY_CERTIFICATE_TYPE_OTHER = 2;
	
	/*
	 * entity status definition
	 */
	public static final int ENTITY_STATUS_OK = 1;
	public static final int ENTITY_STATUS_FORBIDDEN = 2;
	public static final int ENTITY_STATUS_LOCK = 3;
	public static final int ENTITY_STATUS_CLOSE = 4;
	public static final int ENTITY_STATUS_NOSHENHE = 5;
	public static final int ENTITY_STATUS_NO = 6;
	public static final int ENTITY_STATUS_SHENHEZHONG = 7;
	public static final int ENTITY_STATUS_SHENHESHIBAN = 8;
	
	/*
	 * 短信发送接口相关参数定义
	 */
	public static final int SMS_MAX_TOTAL = 180;
	public static final int SMS_MAX_BYTES_EN = 140;
	public static final int SMS_MAX_BYTES_CH = 69;
	public static final int SMS_MAX_COUNT = 50;
	public static final String SMS_SIG_DEFAULT = "[天润融通]";
	
	/*
	 * cookie name used in jboss definition
	 */
	public static final String CCIC_LOGIN_USERNAME_COOKIE = "ccic_login_username_cookie";
	public static final String JBOSS_LOGIN_USERNAME_COOKIE = "jboss_login_username_cookie";
	public static final String JBOSS_LOGIN_USER_COOKIE_ID = "jboss_login_user_cookie_id";
	
	/*
	 * jboss system_setting name definition
	 */
	public static final String SYSTEM_SETTING_NAME_LOCK_LOGIN_TIME = "lock_login_time";
	public static final String SYSTEM_SETTING_NAME_LOGIN_FAILED_WHEN_EMAIL_TIMES = "login_failed_when_email_times";
	public static final String SYSTEM_SETTING_NAME_LOGIN_FAILED_FORBIDDEN_TIMES = "login_failed_forbidden_times";
	public static final String SYSTEM_SETTING_NAME_ANTIHARASS_UNIT_TIME = "antiharass_unit_time";
	public static final String SYSTEM_SETTING_NAME_INTERFACE_AUTH = "interface_auth";
	public static final String SYSTEM_SETTING_NAME_RECEIVE_CRONTAB_EMAIL_ADDRESS = "receive_crontab_email_address";
	public static final String SYSTEM_SETTING_NAME_RECEIVE_CRONTAB_EMAIL_ADDRESS_CS = "receive_test_email_address_cs";
	public static final String SYSTEM_SETTING_NAME_SSO_LOGIN_URL = "sso_login_url";
	public static final String SYSTEM_SETTING_NAME_SSO_LOGOUT_URL = "sso_logout_url";
	public static final String SYSTEM_SETTING_NAME_SSO_CHECK_TICKET_URL = "sso_check_ticket_url";
	public static final String SYSTEM_SETTING_NAME_BOSS_JSP_TITLE = "boss_jsp_title";
	public static final String SYSTEM_SETTING_NAME_FAXC_URL = "faxc_url";
	public static final String SYSTEM_SETTING_NAME_NMC_URL = "nmc_url";
	public static final String SYSTEM_SETTING_NAME_PRE_RECHARGE_FREQUENCY_TIME_UNIT = "pre-recharge_frequency";
	public static final String SYSTEM_SETTING_NAME_SMSC_URL = "smsc_url";
	public static final String SYSTEM_SETTING_NAME_UNICOM_SEGMENT = "unicom_segment";
	public static final String SYSTEM_SETTING_NAME_TELECOM_SEGMENT = "telecom_segment";
	public static final String SYSTEM_SETTING_NAME_MOBILE_SEGMENT = "mobile_segment";
	public static final String SYSTEM_SETTING_NAME_NEW_PLATFORM_VERSION = "new_platform_version";
	public static final String SYSTEM_SETTING_NAME_NEW_PREDICTIVE_OUT_CALL_MODE = "predictive_out_call_mode";
	public static final String SYSTEM_SETTING_NAME_NMC_INTERFACE_TOKEN = "nmc_interface_token";
	
	
	public static final int SYSTEM_SETTING_TEST_ALERT_THRESHOLD = 2;
	
	 // ccic system_setting name definition 
	 	
	public static final String SYSTEM_SETTING_NAME_LOCK_IP_TIME = "lock_ip_time";
	public static final String SYSTEM_SETTING_NAME_LOCK_COOKIENAME_TIME = "lock_cookiename_time";
	public static final String SYSTEM_SETTING_NAME_LOCK_TEL_TIME = "lock_tel_time";
	public static final String SYSTEM_SETTING_NAME_CALL_FAILURE_MAX_TIMES = "call_failure_max_times";
	public static final String SYSTEM_SETTING_NAME_CALL_MAX_TIMES = "call_max_times";
	public static final String SYSTEM_SETTING_NAME_SMS_MAX_TIMES = "sms_max_times";
	public static final String SYSTEM_SETTING_NAME_MONEY_CALL_ALERT_THRESHOLD = "money_call_alert_threshold";
	
	public static final String SYSTEM_SETTING_NAME_RADIUS_SERVER_AUTH = "radius_server_auth";
	public static final String SYSTEM_SETTING_NAME_RADIUS_SERVER_ACCT = "radius_server_acct";
	public static final String SYSTEM_SETTING_NAME_AGI_SERVER_PORT = "agi_server_port";
	
	public static final String SYSTEM_SETTING_NAME_DEFAULT_ROUTER = "default_router";
	public static final String SYSTEM_SETTING_NAME_IB_CALL_REMEMBER_TIME = "ib_call_remember_time";
	public static final String SYSTEM_SETTING_NAME_OB_CALL_REMEMBER_TIME = "ob_call_remember_time";
	public static final String SYSTEM_SETTING_NAME_AMI_RESPONSE_TIMEOUT = "ami_response_timeout";
	
	public static final String SYSTEM_SETTING_NAME_BOSS_DB_INFO = "boss_db_info";
	public static final String SYSTEM_SETTING_NAME_BOSS_URL_INFO = "boss_url_info";
	public static final String SYSTEM_SETTING_NAME_BOSS_INTERFACE_URL = "boss_interface_url";
	public static final String SYSTEM_SETTING_NAME_BOSS_CONN_POOL = "boss_conn_pool";
	public static final String SYSTEM_SETTING_NAME_TIMED_TASK_EMAIL_ADDRESS = "timed_task_email_address";
	public static final String SYSTEM_SETTING_NAME_MOD_AGI = "mod_agi";
	public static final String SYSTEM_SETTING_NAME_MOD_AMI = "mod_ami";
	public static final String SYSTEM_SETTING_NAME_MOD_RADIUS = "mod_radius";
	public static final String SYSTEM_SETTING_NAME_MOD_WEBSOCKET = "mod_websocket";
	public static final String SYSTEM_SETTING_NAME_MOD_MONITOR = "mod_monitor";
	public static final String SYSTEM_SETTING_NAME_MOD_MONITOR_QUEUE = "mod_monitor_queue";
	public static final String SYSTEM_SETTING_NAME_MOD_MONITOR_IVR = "mod_monitor_ivr";
	public static final String SYSTEM_SETTING_NAME_MOD_MONITOR_TRUNK = "mod_monitor_trunk";
	public static final String SYSTEM_SETTING_NAME_MOD_AGENDA = "mod_agenda";
	public static final String SYSTEM_SETTING_NAME_SERVER_MONITOR_INTERVAL = "server_monitor_interval";
	public static final String SYSTEM_SETTING_NAME_SERVICE_LEVEL = "service_level";
	public static final String SYSTEM_SETTING_NAME_CNO_LENGTH = "cno_length";
	public static final String SYSTEM_SETTING_NAME_QNO_LENGTH = "qno_length";
	public static final String SYSTEM_SETTING_NAME_EXTEN_LENGTH = "exten_length";
	public static final String SYSTEM_SETTING_NAME_SMS_URL = "sms_url";
	
	/*
	 * system_setting name definition 数据过期时间 
	 */
	public static final String SYSTEM_SETTING_NAME_CDR_EXPIRE_MONTH = "cdr_expire_month";
	public static final String SYSTEM_SETTING_NAME_CDR_SMS_EXPIRE_MONTH = "cdr_sms_expire_month";
	public static final String SYSTEM_SETTING_NAME_LOG_ACTION_EXPIRE_MONTH = "log_action_expire_month";
	public static final String SYSTEM_SETTING_NAME_LOG_CLIENT_EXPIRE_MONTH = "log_client_expire_month";
	public static final String SYSTEM_SETTING_NAME_LOG_CRONTAB_EXPIRE_MONTH = "log_crontab_expire_month";
	public static final String SYSTEM_SETTING_NAME_LOG_EMAIL_EXPIRE_MONTH = "log_email_expire_month";
	public static final String SYSTEM_SETTING_NAME_LOG_LOGIN_EXPIRE_MONTH = "log_login_expire_month";
	public static final String SYSTEM_SETTING_NAME_LOG_WEB400_CALL_EXPIRE_MONTH = "log_web400_call_expire_month";
	public static final String SYSTEM_SETTING_NAME_REPORT_DAY_EXPIRE_MONTH = "report_day_expire_month";
	public static final String SYSTEM_SETTING_NAME_REPORT_MONTH_EXPIRE_MONTH = "report_month_expire_month";
	public static final String SYSTEM_SETTING_NAME_REPORT_WEEK_EXPIRE_MONTH = "report_week_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_CALL_IB_DAY_EXPIRE_MONTH = "statistic_call_ib_day_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_CALL_IB_DAY_AREA_EXPIRE_MONTH = "statistic_call_ib_day_area_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_CALL_OB_DAY_EXPIRE_MONTH = "statistic_call_ob_day_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_CLIENT_WORKLOAD_DAY_EXPIRE_MONTH = "statistic_client_workload_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_INVESTIGATION_DAY_EXPIRE_MONTH = "statistic_investigation_day_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_IVR_DAY_EXPIRE_MONTH = "statistic_ivr_day_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_QUEUE_HOUR_EXPIRE_MONTH = "statistic_queue_hour_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_SMS_DAY_EXPIRE_MONTH = "statistic_sms_day_expire_month";
	public static final String SYSTEM_SETTING_NAME_STATISTIC_WEB400_DAY_EXPIRE_MONTH = "statistic_web400_day_expire_month";
	public static final String SYSTEM_SETTING_NAME_WEB400_VISITOR_EXPIRE_MONTH = "web400_visitor_expire_month";
	public static final String SYSTEM_SETTING_NAME_MONITOR_SERVER_EXPIRE_MONTH = "monitor_server_expire_month";
	public static final String SYSTEM_SETTING_NAME_CASE_LIB_EXPIRE_MONTH = "case_lib_expire_month";
	public static final String SYSTEM_SETTING_NAME_CCIC_JSP_TITLE = "ccic_jsp_title";
	public static final String SYSTEM_SETTING_NAME_PREDICTIVE_CHANNEL_LIMIT = "predictive_channel_limit";
	public static final String SYSTEM_SETTING_NAME_OUTBOUND_PROXY = "outbound_proxy";
	/*
	 * CCIC platform table system_setting name definition
	 */
	public static final String CCIC_SYSTEM_SETTING_NAME_DEFAULT_ROUTER = "default_router";
	public static final String CCIC_SYSTEM_SETTING_NAME_INTERFACE_AUTH = "interface_auth";
	
	/*
	 * CCIC platform table gateway calllimit definition
	 */
	public static final int GATEWAY_CALLLIMIT = 300;
	
	/*
	 * system_setting property definition
	 */
	public static final String SYSTEM_SETTING_PROPERTY_YEAR = "year";
	public static final String SYSTEM_SETTING_PROPERTY_MONTH = "month";
	public static final String SYSTEM_SETTING_PROPERTY_HOUR = "hour";
	public static final String SYSTEM_SETTING_PROPERTY_MINUTE = "minute";
	public static final String SYSTEM_SETTING_PROPERTY_SECOND = "second";
	public static final String SYSTEM_SETTING_PROPERTY_TIMES = "times";
	
	/*
	 * ccic active definition
	 */
	public static final int CCIC_ACTIVE_UPGRADING = 2;
	public static final int CCIC_ACTIVE_RUNNING = 1;
	public static final int CCIC_ACTIVE_STOP = 0;
	
	/*
	 * number status definition
	 */
	public static final int NUMBER_STATUS_SELLING = 1;
	public static final int NUMBER_STATUS_SOLD = 2;
	public static final int NUMBER_STATUS_FREEZE = 3;
	public static final int NUMBER_STATUS_RESERVE_FOREVER = 4;
	public static final int NUMBER_STATUS_ONRESERVE = 5;
	public static final int NUMBER_STATUS_INVALID = 6;
	public static final int NUMBER_STATUS_BINDING = 10;
	
	/*
	 * number price definition
	 */
	public static final Double NUMBER_PRICE_30 = 0.3;
	public static final Double NUMBER_PRICE_25 = 0.25; 
	public static final Double NUMBER_PRICE_24 = 0.24;
	public static final Double NUMBER_PRICE_23 = 0.23;
	public static final Double NUMBER_PRICE_22 = 0.22;
	public static final Double NUMBER_PRICE_20 = 0.20;
	public static final Double NUMBER_PRICE_18 = 0.18;
	public static final Double NUMBER_PRICE_15 = 0.15;
	public static final Double NUMBER_PRICE_12 = 0.12;
	
	
	
	public static final BigDecimal NUMBER_PRICE_50000 = new BigDecimal(50000);
	public static final BigDecimal NUMBER_PRICE_20000 = new BigDecimal(20000);
	public static final BigDecimal NUMBER_PRICE_3000 = new BigDecimal(3000);
	public static final BigDecimal NUMBER_PRICE_2000 = new BigDecimal(2000);
	public static final BigDecimal NUMBER_PRICE_1000 = new BigDecimal(1000);
	public static final BigDecimal NUMBER_PRICE_800 = new BigDecimal(800);
	public static final BigDecimal NUMBER_PRICE_500 = new BigDecimal(500);
	public static final BigDecimal NUMBER_PRICE_300 = new BigDecimal(300);
	public static final BigDecimal NUMBER_PRICE_200 = new BigDecimal(200);
	public static final BigDecimal NUMBER_PRICE_100 = new BigDecimal(100);
	public static final BigDecimal NUMBER_PRICE_50 = new BigDecimal(50);
	
	/*
	 * number hyphenation definition
	 */
	
	public static final int NUMBER_HYPHENATION_334 = 1;
	public static final int NUMBER_HYPHENATION_433 = 2;
	public static final int NUMBER_HYPHENATION_55 = 3;
	public static final int NUMBER_HYPHENATION_OTHER = 4;
	
	/*
	 * number trade definetion
	 */
	public static final int NUMBER_TRADE_TRAVEL = 1;
	public static final int NUMBER_TRADE_CATERING = 2;
	public static final int NUMBER_TRADE_EDUCATION = 3;
	public static final int NUMBER_TRADE_LOVE = 4;
	public static final int NUMBER_TRADE_MEDICAL = 5;
	public static final int NUMBER_TRADE_LIQUOR = 6;
	public static final int NUMBER_TRADE_FOOD = 7;
	public static final int NUMBER_TRADE_SERVE = 8;
	public static final int NUMBER_TRADE_COMMON = 9;
	
	/*
	 * number easy_number difinetion
	 */
	public static final int NUMBER_EASY_NUMBER_COMMON = 1;
	public static final int NUMBER_EASY_NUMBER_YEARS = 2; 
	public static final int NUMBER_EASY_NUMBER_MULTIPLY = 3; 
	public static final int NUMBER_EASY_NUMBER_AREACODE = 4; 
	
	/*
	 * number divination difinetion
	 */
	public static final int NUMBER_DIVINATION_DEFAULT = 1;
	
	
	/*
	 * number active_type definition
	 */
	public static final int NUMBER_ACTIVE_TYPE_IMMEDIATE = 1;
	public static final int NUMBER_ACTIVE_TYPE_RESERVE = 2;
	public static final int NUMBER_ACTIVE_TYPE_DIRECT= 3; // 直销靓号
	
	/*
	 * trunk trunk_type,status definition
	 */
	public static final int TRUNK_TRUNK_TYPE_GENERAL = 1;
	public static final int TRUNK_TRUNK_TYPE_FAX = 2;
	
	public static final int TRUNK_STATUS_UNUSED  = 1;
	public static final int TRUNK_STATUS_INUSE = 2;
	
	/*
	 * number_reserved reserve_status definition
	 */
	public static final int NUMBER_RESERVED_RESERVE_STATUS_UNRESERVE = 1;
	public static final int NUMBER_RESERVED_RESERVE_STATUS_ONRESERVE = 2;
	public static final int NUMBER_RESERVED_RESERVE_STATUS_SUCCESS_RESERVE = 3;
	public static final int NUMBER_RESERVED_RESERVE_STATUS_FAIL_RESERVE = 4;
	public static final int NUMBER_RESERVED_RESERVE_STATUS_BIND_CUSTOMER = 5;
	
	/*
	 * entity_setting name definition
	 */
	public static final String ENTITY_SETTING_NAME_MAX_NUMBER_RESERVED_AMOUNT = "max_number_reserved_amount";
	public static final String ENTITY_SETTING_NAME_ALERT_TEMPLATE_TEAM_SELECT_MAIL = "alert_template_team_select_mail";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_MAIL_AGENT = "is_send_alert_mail_agent";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_MAIL_ENTERPRISE = "is_send_alert_mail_enterprise";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_MAIL_CS = "is_send_alert_mail_cs";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_MAIL_CASHIER = "is_send_alert_mail_cashier";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_MAIL_DEPUTY_GENERAL = "is_send_alert_mail_deputy_general";
	public static final String ENTITY_SETTING_NAME_ALERT_TEMPLATE_TEAM_SELECT_SMS = "alert_template_team_select_sms";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_SMS_AGENT = "is_send_alert_sms_agent";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_SMS_ENTERPRISE = "is_send_alert_sms_enterprise";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_SMS_CS = "is_send_alert_sms_cs";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_SMS_CASHIER = "is_send_alert_sms_cashier";
	public static final String ENTITY_SETTING_NAME_IS_SEND_ALERT_SMS_DEPUTY_GENERAL = "is_send_alert_sms_deputy_general";
	public static final String ENTITY_SETTING_NAME_IS_ALLOW_LOGIN_CCIC = "is_allow_login_ccic";
	public static final String ENTITY_SETTING_NAME_CHARGE_APPROVAL_LIMIT = "charge_approval_limit";
	public static final String ENTITY_SETTING_NAME_CREDIT_DAY_LIMIT = "credit_day_limit";
	
	/*
	 * entity_setting value definition
	 */
	public static final String ENTITY_SETTING_VALUE_SERVICE_OPEN = "1";
	public static final String ENTITY_SETTING_VALUE_SERVICE_CLOSE = "0";
	
	/*
	 * 代理商预占号码的默认保留天数
	 */
	public static final int RESERVED_NUMBER_DEFAULT_HOLD_DAYS = 7;
	
	/*
	 * 资金账户默认值
	 */
	public static final BigDecimal ACCOUNT_MONEY = new BigDecimal("0.0");
	public static final BigDecimal ACCOUNT_CREDIT = new BigDecimal("0.0");
	public static final int ACCOUNT_ACCOUNT_TYPE_CALL = 1;
	public static final int ACCOUNT_ACCOUNT_TYPE_FUNCTION = 2;
	public static final int ACCOUNT_ACCOUNT_TYPE_OUTBOUND = 3;
	public static final int ACCOUNT_ACCOUNT_TYPE_SMS = 4;
	public static final int ACCOUNT_ACCOUNT_TYPE_CASH = 0;
	
	public static final int ACCOUNT_PAY_TYPE_PRE = 1;
	public static final int ACCOUNT_PAY_TYPE_POST = 2;
	public static final int ACCOUNT_ACTIVE_OK = 1;
	public static final int ACCOUNT_ACTIVE_STOP = 2;
	public static final int ACCOUNT_ACTIVE_CLOSE = 3;
	
	/*
	 * enterprise_temp status,client_css,crm_type,popscreen_type definition
	 */
	public static final int ENTERPRISE_TEMP_STATUS_NO_SERVICE = 0;
	public static final int ENTERPRISE_TEMP_STATUS_OK = 1;
	public static final int ENTERPRISE_TEMP_STATUS_PAUSED = 2;
	public static final int ENTERPRISE_TEMP_STATUS_STOP = 3;
	public static final int ENTERPRISE_TEMP_STATUS_CLOSE = 4;
	
	public static final String ENTERPRISE_TEMP_CLIENT_CSS_SMOOTHNESS = "smoothness";
	public static final String ENTERPRISE_TEMP_CLIENT_CSS_REDMOND = "redmond";
	
	public static final int ENTERPRISE_TEMP_CRM_TYPE_HOSTED = 0;
	public static final int ENTERPRISE_TEMP_CRM_TYPE_SELF_DEFINITION = 1;
	
	public static final int ENTERPRISE_TEMP_POPSCREEN_TYPE_DEFAULT = 0;
	public static final int ENTERPRISE_TEMP_POPSCREEN_TYPE_IFRAME = 1;
	
	/*
	 * enterprise_temp callClidType definition
	 * 企业透传号码类型定义:1:中继 2:发起呼叫者 3:设定的固定值
	 */
	public static final int ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK = 1;
	public static final int ENTERPRISE_TEMP_CALL_CLID_TYPE_CALLER = 2;
	public static final int ENTERPRISE_TEMP_CALL_CLID_TYPE_FIXED_NUMBER = 3;
	public static final int ENTERPRISE_TEMP_CALL_CLID_TYPE_HOTLINE = 4;
	
	/*
	 * enterprise_temp template_type,period definition
	 * 企业模板 1:自定义 2:彩铃 3:分组 4:总机分机 5:地区
	 */
	public static final int ENTERPRISE_TEMP_TEMPLATE_TYPE_USER_DEFINED = 1;
	public static final int ENTERPRISE_TEMP_TEMPLATE_TYPE_RING_TONE = 2;
	public static final int ENTERPRISE_TEMP_TEMPLATE_TYPE_GROUP = 3;
	public static final int ENTERPRISE_TEMP_TEMPLATE_TYPE_EXTEN = 4;
	public static final int ENTERPRISE_TEMP_TEMPLATE_TYPE_AREA = 5;
	
	/* 
	 * enterprise_temp is_test, is_record, is_call_restrict, is_remember 
	 * is_leave_message, is_inbound_analysis, is_fax, is_call_out definition
	 */
	public static final int ENTERPRISE_TEMP_IS_TEST_NO = 0;
	public static final int ENTERPRISE_TEMP_IS_TEST_YES = 1;
	public static final int ENTERPRISE_TEMP_IS_RECORD_OFF = 0;
	public static final int ENTERPRISE_TEMP_IS_RECORD_ON = 1;
	public static final int ENTERPRISE_TEMP_IS_CALL_RESTRICT_OFF = 1;
	public static final int ENTERPRISE_TEMP_IS_CALL_RESTRICT_BLACK_LIST = 2;
	public static final int ENTERPRISE_TEMP_IS_CALL_RESTRICT_WHITE_LIST = 3;
	public static final int ENTERPRISE_TEMP_IS_REMEMBER_OFF = 0;
	public static final int ENTERPRISE_TEMP_IS_REMEMBER_ON = 1;
	public static final int ENTERPRISE_TEMP_IS_LEAVE_MESSAGE_OFF = 0;
	public static final int ENTERPRISE_TEMP_IS_LEAVE_MESSAGE_ON = 1;
	public static final int ENTERPRISE_TEMP_IS_INBOUND_ANALYSIS_OFF = 0;
	public static final int ENTERPRISE_TEMP_IS_INBOUND_ANALYSIS_ON = 1;
	public static final int ENTERPRISE_TEMP_IS_FAX_OFF = 0;
	public static final int ENTERPRISE_TEMP_IS_FAX_ON = 1;
	public static final int ENTERPRISE_TEMP_IS_CALL_OUT_OFF = 0;
	public static final int ENTERPRISE_TEMP_IS_CALL_OUT_ON = 0;
	
	public static final int ENTERPRISE_TEMP_IS_IDD_OFF = 0;
	public static final int ENTERPRISE_TEMP_IS_IDD_ON = 1;
	
	/*
	 * enterprise_hotline is_master,period,type definiation
	 */
	public static final int ENTERPRISE_HOTLINE_IS_MASTER_NO = 0;
	public static final int ENTERPRISE_HOTLINE_IS_MASTER_YES = 1;
	public static final int ENTERPRISE_HOTLINE_PERIOD_MONTH = 1;
	public static final int ENTERPRISE_HOTLINE_PERIOD_YEAR = 2; 
	
	public static final int ENTERPRISE_HOTLINE_TYPE_400_NUMBER = 1;
	public static final int ENTERPRISE_HOTLINE_TYPE_DIRECT_NUMBER = 2; 
	public static final int ENTERPRISE_HOTLINE_TYPE_MOBILE_VIRTUAL = 3;
	
//	public static final int ENTERPRISE_HOTLINE_TYPE_LOWEST_COST_MODE_IB = 0;
//	public static final int ENTERPRISE_HOTLINE_TYPE_LOWEST_COST_MODE_IBOB = 1;
	public static final int ENTERPRISE_HOTLINE_LOWEST_COST_MODE_IB = 0;
	public static final int ENTERPRISE_HOTLINE_LOWEST_COST_MODE_IBOB = 1;
	
	/*
	 * enterprise_number type definiation
	 */
	public static final int ENTERPRISE_NUMBER_TYPE_400_NUMBER = 1;
	public static final int ENTERPRISE_NUMBER_TYPE_TRUNK = 2; 
	public static final int ENTERPRISE_NUMBER_TYPE_MOBILE_VIRTUAL = 3;
	public static final int ENTERPRISE_NUMBER_TYPE_ENTERPRISE_OWNED = 4;
	
	/*
	 * enterprise_number flag definiation
	 */
	public static final int ENTERPRISE_NUMBER_FLAG_NO = 0;
	public static final int ENTERPRISE_NUMBER_FLAG_YES = 1; 
	
	/*
	 * enterprise_number status definiation
	 */
	public static final int ENTERPRISE_NUMBER_STATUS_UNUSE = 1;
	public static final int ENTERPRISE_NUMBER_STATUS_INUSE = 2; 
	
	/*
	 * enterprise_trunk is_billing,type definiation
	 * 
	 * '目的码类型：0--未绑定400或1010号码 1--绑定400或1010号码  2--手机虚拟号码';
	 */
	public static final int ENTERPRISE_TRUNK_IS_BILLING_NO = 0;
	public static final int ENTERPRISE_TRUNK_IS_BILLING_YES = 1;
	
	public static final int ENTERPRISE_TRUNK_TYPE_NOT_BIND = 0;
	public static final int ENTERPRISE_TRUNK_TYPE_BIND = 1;
	public static final int ENTERPRISE_TRUNK_TYPE_MOBILE_VIRTUAL = 2;
	
	/*
	 * trunk carrier definition 1-联通 2-电信 3-移动 4-其他';
	 */
	public static final int TRUNK_CARRIER_CUCC = 1; // china unicom
	public static final int TRUNK_CARRIER_CTTC = 2; // china tietong
	public static final int TRUNK_CARRIER_CTCC = 3; // china telecom
	public static final int TRUNK_CARRIER_CMCC = 4; // china mobile
	public static final int TRUNK_CARRIER_OTHER = 5;
	
	/*
	 * mobile carrier definition 1-联通 2-电信 3-移动 4-其他';
	 */
	public static final int MOBILE_CARRIER_CUCC = 1;
	public static final int MOBILE_CARRIER_CTCC = 2;
	public static final int MOBILE_CARRIER_CMCC = 3;
	public static final int MOBILE_CARRIER_OTHER = 4;
	
	/*
	 * subPlatForm: some columns of table business definition
	 */
	public static final int BUSINESS_IS_CALLOUT_ON = 1;
	public static final int BUSINESS_IS_CALLOUT_OFF = 0;
	public static final int BUSINESS_IS_FAX_ON = 1;
	public static final int BUSINESS_IS_FAX_OFF = 0;
	public static final int BUSINESS_IS_RINGTONE_ON = 1;
	public static final int BUSINESS_IS_RINGTONE_OFF = 0;
	public static final int BUSINESS_IS_INBOUND_ANALYSIS_ON = 1;
	public static final int BUSINESS_IS_INBOUND_ANALYSIS_OFF = 0;
	public static final int BUSINESS_IS_LEAVE_MESSAGE_ON = 1;
	public static final int BUSINESS_IS_LEAVE_MESSAGE_OFF = 0;
	
	/*
	 * CCIC: enterprise business status definition
	 */
	public static final int BUSINESS_STATUS_NO_SERVICE = 0;
	public static final int BUSINESS_STATUS_OK = 1;
	public static final int BUSINESS_STATUS_PAUSED = 2;
	public static final int BUSINESS_STATUS_STOP = 3;
	public static final int BUSINESS_STATUS_CLOSE = 4;
	
	/*
	 * enterprise_temp audit_status definition
	 1=未提交，2=未审核，3=审核中，4=审核失败，5=审核通过，6=未审批，7=审批中，8=审批失败，9=审批通过
	 */
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_UNCOMMITTED = 1;
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_UNAUDITED = 2;
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_ONAUDIT = 3;
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_FAIL_AUDIT = 4;
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_AUDIT_PASS = 5;
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_UNAPPROVED = 6;
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_APPROVEING = 7;
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_APPROVEFALL = 8;
	public static final int ENTERPRISE_TEMP_AUDIT_STATUS_APPROVEPASS = 9;
	
	/**
	 * SuperHotline -> 企业语音库 审核状态: 1--未审核（上传完时的状态），2--审核中（客服点击审核），3--审核通过，4--审核失败
	 */
	
	public static final int ENTERPRISE_VOICE_AUDIT_STATUS_UNAUDITED = 1;
	public static final int ENTERPRISE_VOICE_AUDIT_STATUS_AUDITING = 2;
	public static final int ENTERPRISE_VOICE_AUDIT_STATUS_AUDITPASS = 3;
	public static final int ENTERPRISE_VOICE_AUDIT_STATUS_AUDITFAIL = 4;
	
	
	/*
	 * function type && interval definition
	 */
	public static final int FUNCTION_TYPE_BASE = 1;
	public static final int FUNCTION_TYPE_EXTRA = 2;
	public static final int FUNCTION_INTERVAL_MONTH = 1;
	public static final int FUNCTION_INTERVAL_YEAR = 2;
	
	/*
	 * accessory accesory_type && operator_type definition
	 */
	public static final int ACCESSORY_ACCESORY_TYPE_CONTRACT = 1;
	public static final int ACCESSORY_ACCESORY_TYPE_MSO_ACCEPTANCE_FORM = 2;
	public static final int ACCESSORY_ACCESORY_TYPE_BUSINESS_LICENSE  = 3;
	public static final int ACCESSORY_ACCESORY_TYPE_OPERATOR_ID_CARD = 4;
	public static final int ACCESSORY_ACCESORY_TYPE_TINET_ACCEPTANCE_FORM = 5;
	public static final int ACCESSORY_ACCESORY_TYPE_LEGAL_PERSON_ID_CARD = 6;
	public static final int ACCESSORY_ACCESORY_TYPE_SPECIAL_NUMBER_APPLICATION_FORM = 7;
	public static final int ACCESSORY_ACCESORY_TYPE_ORG_CODE = 8;
	public static final int ACCESSORY_ACCESORY_TYPE_CLID_AGREEMENT = 13; // 号码传送服务知情书，透传知情书
	
	public static final int ACCESSORY_ACCESORY_TYPE_DOC_SMART400 = 9;
	public static final int ACCESSORY_ACCESORY_TYPE_DOC_SUP_HOTLINE = 10;
	public static final int ACCESSORY_ACCESORY_TYPE_DOC_CALL_CENTER = 11;
	public static final int ACCESSORY_ACCESORY_TYPE_DOC_OTHERS = 12;
	
	public static final int ACCESSORY_OPERATOR_TYPE_AGENT1 = 1;
	public static final int ACCESSORY_OPERATOR_TYPE_CUSTOMER_SERVICE = 2;
	
	public static final String ACCESSORY_ENTERPRISE_PATH = "/upload/";
	
	public static final String FILE_DOWNLOAD_PATH = "/download/";
	
	/*
	 * cti active definition 
	 */
	public static final int CTI_ACTIVE_RUNNING = 1;
	public static final int CTI_ACTIVE_STOP = 0;
	
	/*
	 * ivr_profile ivr_name and ivr_type definition
	 */
	public static final String IVR_PROFILE_IVR_NAME_WORKING_HOURS = "上班时间";
	public static final String IVR_PROFILE_IVR_NAME_REST_HOURS = "下班时间";
	
	public static final int IVR_PROFILE_IVR_TYPE_IVR = 1;
	public static final int IVR_PROFILE_IVR_TYPE_RINGTONE = 2;
	
	public static final String IVR_PROFILE_IVR_NAME_RINGTONE_DEFAULT = "彩铃";
	public static final String ENTERPRISE_VOICE_VOICE_NAME_RINGTONE_DEFAULT="默认彩铃";
	public static final String ENTERPRISE_IVR_FILE_PATH_RINGTONE_DEFAULT="public_transfer.wav";
	/*
	 * enterprise_iv op_path and op_action and file_path definition
	 */
	public static final String ENTERPRISE_IVR_OP_PATH_WELCOME = "1"; 
	public static final String ENTERPRISE_IVR_OP_PATH_VOICEMAIL = "default_voice_mail"; 
	public static final String ENTERPRISE_IVR_FILE_PATH_VOICEMAIL="default_voice_mail.wav";
	public static final String ENTERPRISE_IVR_FILE_PATH_QUEUE="default_transfer.wav";
	public static final String ENTERPRISE_IVR_FILE_PATH_GROUP_ON_DUTY = "on_duty.wav";
	public static final String ENTERPRISE_IVR_FILE_PATH_EXTEN_ON_DUTY = "tel_exten_on_duty.wav";
	public static final String ENTERPRISE_IVR_FILE_PATH_AREA = "public_transfer.wav";
	
	public static final int ENTERPRISE_IVR_OP_ACTION_EXTEN = 0;
	public static final int ENTERPRISE_IVR_OP_ACTION_SELECT = 1;
	public static final int ENTERPRISE_IVR_OP_ACTION_PLAY = 2; 
	public static final int ENTERPRISE_IVR_OP_ACTION_VOICEMAIL = 3;
	public static final int ENTERPRISE_IVR_OP_ACTION_QUEUE = 4;
	public static final int ENTERPRISE_IVR_OP_ACTION_DIRECT_SEAT = 5;
	public static final int ENTERPRISE_IVR_OP_ACTION_DIRECT_QUEUE = 6;
	public static final int ENTERPRISE_IVR_OP_ACTION_RECEIVE_FAX = 9;
	
	
	public static final int ENTERPRISE_TIME_TYPE_WEEK = 1;
	public static final int ENTERPRISE_TIME_TYPE_DAY = 2;
	public static final String ENTERPRISE_TIME_DAYOFWEEK_ALL = "%";
	public static final int ENTERPRISE_TIME_PRIORITY_DEFAULT = 999;
	
	public static final String ENTERPRISE_AREA_AREA_CODE_ALL = "%"; 
	
	public static final String QUEUE_MUSIC_CLASS_DEFAULT_FILE_NAME = "moh_default.wav";
	public static final String QUEUE_MUSIC_CLASS_DEFAULT = "default";
	public static final int QUEUE_QUEUE_TIMEOUT_DEFAULT = 60;
	public static final boolean QUEUE_SAY_AGENTNO_DEFAULT = false;
	public static final String QUEUE_CONTEXT_DEFAULT = "default";
	public static final int QUEUE_MEMBER_TIMEOUT_DEFAULT = 30;
	public static final int QUEUE_RETRY_DEFAULT = 0;
	public static final int QUEUE_WRAPUP_TIME_DEFAULT = 5;
	public static final int QUEUE_MAX_LEN_DEFAULT = 5;
	public static final String QUEUE_STRATEGY_DEFAULT = "rrmemory";
	public static final int QUEUE_SET_INTERFACEVAR_DEFAULT = 1;
	public static final String QUEUE_QUEUE_THANKYOU_DEFAULT = "";
	public static final String QUEUE_ANNOUNCE_DEFAULT = "";
	public static final int QUEUE_ANNOUNCE_FREQUENCY_DEFAULT = 0;
	public static final String QUEUE_ANNOUNCE_POSITION_DEFAULT = "no";
	public static final String QUEUE_ANNOUNCE_HOLDTIME_DEFAULT = "no";
	public static final int QUEUE_ANNOUNCE_ROUND_SECONDS_DEFAULT = 10;
	public static final int QUEUE_MIN_ANNOUNCE_FREQUENCY_DEFAULT = 15;
	public static final String QUEUE_JOIN_EMPTY_DEFAULT = "no";
	public static final int QUEUE_WEIGHT_DEFAULT = 0;
	public static final String QUEUE_LEAVE_WHEN_EMPTY_DEFAULT = "no";
	public static final String QUEUE_QNO_RINGTONE_DEFAULT = "000";
	
	/**
	 * queue prefix of qno in groupTemplate
	 */
	public static final String QUEUE_QNO_PREFIX_WORKING_HOURS = "0";
	public static final String QUEUE_QNO_PREFIX_REST_HOURS = "1";
	
	/*
	 * enterprise_ivr_router active and router_type definition
	 */
	public static final int ENTERPRISE_IVR_ROUTER_ACTIVE_ON = 1;
	public static final int ENTERPRISE_IVR_ROUTER_ROUTER_TYPE_IVR = 1;
	
	/*
	 * web400_config
	 */
	public static final String WEB400_CONFIG_WELCOME_MSG_DEFAULT = "您好！很高兴为你服务";
	public static final String WEB400_CONFIG_CUSTOMIZE_MSG_DEFAULT = "网上400是一款精良的网络营销工具，它可以有效地发现有意向的客户，并主动邀请这些潜在客户更多的了解企业的产品和服务信息，促进潜在客户向正式客户转化。从而真正做到把访问量变成客户量。";
	public static final String WEB400_CONFIG_AD_FILE_DEFAULT ="top_ad.gif";
	
	/*
	 * ccic:enterprise_setting name definition
	 */
	/** 测试账户的过期时间 **/
	public static final String ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_EXPIRY_DATE = "enterprise_test_expiry_date";
	/** 测试账户的使用状态 **/
	public static final String ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_STATUS = "enterprise_test_status";
	/**   使用接口：0=不使用接口 1=使用接口 **/
	public static final String ENTERPRISE_SETTING_NAME_IS_USE_ITF = "is_use_itf";
	/**   客户电话接入方式：1=直线 2=IAD  3=无线话机   4=软电话**/
	public static final String ENTERPRISE_SETTING_NAME_ACCESS_MODE = "access_mode";
	
	/** 是否全程录音  0=否   1=是 **/
	public static final String ENTERPRISE_SETTING_NAME_IS_FULL_RECORD="is_full_record";
	
	/** 企业录音试听方式 **/
	public static final String ENTERPRISE_SETTING_IS_AWS_RECORD = "is_aws_record";
	/** S3目录 **/
	public static final String ENTERPRISE_SETTING_AWS_RECORD_DIRECTORY = "aws_record_directory";
	
	/**外呼黑名单开关***/
	public static final String ENTERPRISE_SETTING_OUT_CALL_SWITCH="out_call_switch";
	
	/** 企业对客户资料的管理模式 **/
	public static final String ENTERPRISE_SETTING_VALUE_SHARE_MODE_ALLSHARE = "1";
	/** 短信小号 **/
	public static final String ENTERPRISE_SETTING_NAME_SMS_CELL = "sms_cell";
	/** 短信签名 **/
	public static final String ENTERPRISE_SETTING_NAME_SMS_SIGN = "sms_sign";
	/** 淘宝对接 */
	public static final String ENTERPRISE_SETTING_NAME_TAOBAO_NICK = "taobao_nick";
	public static final String ENTERPRISE_SETTING_NAME_TAOBAO_STAT = "taobao_stat";
	public static final String ENTERPRISE_SETTING_NAME_TAOBAO_STAT_UPDATE_TIME = "taobao_stat_update_time";
	public static final String ENTERPRISE_SETTING_NAME_ENTERPRISE_IS_PREDICTIVE_OPEN = "is_ob_predictive";
	public static final String ENTERPRISE_SETTING_NAME_OB_PREDICTIVE_WORK_TIME = "ob_predictive_work_time";
	public static final String ENTERPRISE_SETTING_NAME_TOTAL_COST = "total_cost";
	/**号码状态识别选项 */
	public static final String ENTERPRISE_SETTING_TEL_STATUS_IDENTIFICATION = "tel_status_identification";
	/**是否开启呼叫手机录音 */
	public static final String ENTERPRISE_SETTING_IS_RECORD_TEL = "is_record_tel";
	/**预测外呼企业并发限制 */
	public static final String ENTERPRISE_SETTING_PREDICTIVE_CALL_LIMIT = "predictive_call_limit";
	/**预测外呼企业并发资费*/
	public static final String ENTERPRISE_SETTING_PREDICTIVE_CALL_FEE = "predictive_call_fee";
	public static final String ENTGERPRISE_SETTING_IS_IVR_LOCK =  "is_ivr_lock";
	public static final String ENTGERPRISE_SETTING_CURL_LEVEL =  "curl_level";
	public static final String ENTGERPRISE_SETTING_IS_CRBT =  "is_crbt";
	public static final String TAOBAO_APP_SECRET= "613d247967bb964dc069ca1bec71cc79";
	public static final String TAOBAO_APP_KEY = "21535371";
	public static final String TAOBAO_CN = "cntaobao";
	public static final Integer TAOBAO_NUMBER_STAT_SERVICE = 0;
	public static final Integer TAOBAO_NUMBER_STAT_UN_SERVICE = 1;
	public static final Integer TAOBAO_NUMBER_STAT_UN_ACTIVE = 2;
	public static final Integer TAOBAO_NUMBER_STAT_ACTIVE = 3;
	
	/*
	 * business_item type,balance_type definition
 	 */
	public static final int BUSINESS_ITEM_TYPE_INBOUND = 1;
	public static final int BUSINESS_ITEM_TYPE_OUTBOUND = 2;
	public static final int BUSINESS_ITEM_TYPE_CLIENT_RENT = 3;
	public static final int BUSINESS_ITEM_TYPE_FUNCTION = 4;
	public static final int BUSINESS_ITEM_TYPE_FEE = 5;
	public static final int BUSINESS_ITEM_TYPE_SMS = 6;
	public static final int BUSINESS_ITEM_BALANCE_TYPE_RATE = 1;
	public static final int BUSINESS_ITEM_BALANCE_TYPE_DISCOUNT = 2;
	
	/* 
	 * log_charge account_type definition
	 */
	public static final int LOG_CHARGE_ACCOUNT_TYPE_CALL_IB = 1;
	public static final int LOG_CHARGE_ACCOUNT_TYPE_FUNCTION = 2;
	public static final int LOG_CHARGE_ACCOUNT_TYPE_CALL_OB = 3;
	public static final int LOG_CHARGE_ACCOUNT_TYPE_SMS = 4;
	public static final int LOG_CHARGE_ACCOUNT_TYPE_CASH = 0;
	public static final int LOG_CHARGE_ACCOUNT_TYPE_AGENT = 11;
	
	/* 
	 * log_charge charge_type definition
	 */
	public static Map<Integer, String> TABLE_CHARGE_TYPE ;
    static {
    	TABLE_CHARGE_TYPE = new HashMap<Integer, String>();
    	TABLE_CHARGE_TYPE.put(0, "测试账户开户，还原或初始化系统自动充值");
    	TABLE_CHARGE_TYPE.put(1, "出纳给一级代理商充值");
    	TABLE_CHARGE_TYPE.put(2, "出纳给一级代理商冲正");
    	TABLE_CHARGE_TYPE.put(3, "一级代理商给二级代理商充值一级代理商账户扣钱");
    	TABLE_CHARGE_TYPE.put(4, "一级代理商给二级代理商充值二级代理商账户加钱");
    	TABLE_CHARGE_TYPE.put(5, "一级代理商给客户充值二级代理商扣钱");
    	TABLE_CHARGE_TYPE.put(6, "一级代理商给客户充值客户账户加钱");
    	TABLE_CHARGE_TYPE.put(7, "充值返点给二级代理商");
    	TABLE_CHARGE_TYPE.put(8, "充值返点给一级代理商");
    	TABLE_CHARGE_TYPE.put(9, "客服扣二级代理商一次性费用");
    	TABLE_CHARGE_TYPE.put(10, "扣一次性费用给一级代理商返点");
    	TABLE_CHARGE_TYPE.put(11, "客服直接给客户充值客户账户加钱");
    	TABLE_CHARGE_TYPE.put(12, "一级代理商给二级代理商冲正二级代理商账户扣钱");
    	TABLE_CHARGE_TYPE.put(13, "一级代理商给二级代理商冲正一级代理商账户加钱");
    	TABLE_CHARGE_TYPE.put(14, "一级代理商给客户冲正客户账户扣钱");
    	TABLE_CHARGE_TYPE.put(15, "一级代理商给客户冲正二级代理商加钱");
    	TABLE_CHARGE_TYPE.put(16, "一级代理商给客户冲正扣除二级代理商返点");
    	TABLE_CHARGE_TYPE.put(17, "一级代理商给客户冲正扣除一级代理商返点");
    	TABLE_CHARGE_TYPE.put(18, "出纳直接给客户充值客户账户加钱");
    	TABLE_CHARGE_TYPE.put(19, "客服给客户冲正客户账户扣钱");
    	TABLE_CHARGE_TYPE.put(20, "出纳给客户冲正客户账户扣钱");
    	TABLE_CHARGE_TYPE.put(21, "充值预报充值");
    	TABLE_CHARGE_TYPE.put(22, "预充值充值");
    	TABLE_CHARGE_TYPE.put(23, "赠送充值");
    	TABLE_CHARGE_TYPE.put(24, "转移充值");
    	TABLE_CHARGE_TYPE.put(31, "在线充值");
    	TABLE_CHARGE_TYPE.put(32, "在线充值-赠送");
    	TABLE_CHARGE_TYPE.put(33, "线下充值");
    	TABLE_CHARGE_TYPE.put(34, "线下充值-赠送");
    	//V210
    	TABLE_CHARGE_TYPE.put(35, "充值预报充值");
    	TABLE_CHARGE_TYPE.put(36, "赠送充值");
    	TABLE_CHARGE_TYPE.put(37, "在线支付");
    }
    
	 public static Map<Integer, String> JSP_CHARGE_TYPE ;
	    static {
	    	JSP_CHARGE_TYPE = new HashMap<Integer, String>();
	    	JSP_CHARGE_TYPE.put(1, "一级代理商充值");
	    	JSP_CHARGE_TYPE.put(2, "一级代理商冲正");
	    	JSP_CHARGE_TYPE.put(3, "充值给二级代理商");
	    	JSP_CHARGE_TYPE.put(4, "二级代理商充值");
	    	JSP_CHARGE_TYPE.put(5, "充值给客户");
	    	JSP_CHARGE_TYPE.put(6, "充值给客户");
	    	JSP_CHARGE_TYPE.put(7, "充值返点给二代");
	    	JSP_CHARGE_TYPE.put(8, "充值返点给一代");
	    	JSP_CHARGE_TYPE.put(9, "扣一次性费用");
	    	JSP_CHARGE_TYPE.put(10, "扣一次性费用返点");
	    	JSP_CHARGE_TYPE.put(11, "充值给客户");
	    	JSP_CHARGE_TYPE.put(12, "二级代理商冲正");
	    	JSP_CHARGE_TYPE.put(13, "冲正给二级代理商");
	    	JSP_CHARGE_TYPE.put(14, "冲正给客户");
	    	JSP_CHARGE_TYPE.put(15, "冲正给客户");
	    	JSP_CHARGE_TYPE.put(16, "冲正扣除二级代理商返点");
	    	JSP_CHARGE_TYPE.put(17, "冲正扣除一级代理商返点");
	    	JSP_CHARGE_TYPE.put(18, "充值给客户");
	    	JSP_CHARGE_TYPE.put(19, "冲正给客户");
	    	JSP_CHARGE_TYPE.put(20, "冲正给客户");
	    	JSP_CHARGE_TYPE.put(21, "充值预报充值");
	    	JSP_CHARGE_TYPE.put(22, "预充值充值");
	    	JSP_CHARGE_TYPE.put(23, "赠送充值");
	    	JSP_CHARGE_TYPE.put(24, "转移充值");
	    	JSP_CHARGE_TYPE.put(31, "在线充值");
	    	JSP_CHARGE_TYPE.put(32, "在线充值-赠送");
	    	JSP_CHARGE_TYPE.put(33, "线下充值");
	    	JSP_CHARGE_TYPE.put(34, "线下充值-赠送");
	    	JSP_CHARGE_TYPE.put(35, "充值预报充值");
	    	JSP_CHARGE_TYPE.put(36, "赠送充值");
	    	JSP_CHARGE_TYPE.put(37, "在线支付");
	    }
	
	    public static Map<Integer, String> MONEY_FLOW ;
	    static {
	    	MONEY_FLOW = new HashMap<Integer, String>();
	    	MONEY_FLOW.put(0, "系统 => 客户");
	    	MONEY_FLOW.put(1, "系统 => 一代");
	    	MONEY_FLOW.put(2, "一代 => 系统");
	    	MONEY_FLOW.put(3, "一代 => 二代");
	    	MONEY_FLOW.put(4, "一代 => 二代");
	    	MONEY_FLOW.put(5, "二代 => 客户");
	    	MONEY_FLOW.put(6, "二代 => 客户");
	    	MONEY_FLOW.put(7, "系统 => 二代");
	    	MONEY_FLOW.put(8, "系统 => 一代");
	    	MONEY_FLOW.put(9, "二代 => 系统");
	    	MONEY_FLOW.put(10, "系统 => 一代");
	    	MONEY_FLOW.put(11, "系统 => 客户");
	    	MONEY_FLOW.put(12, "二代 => 一代");
	    	MONEY_FLOW.put(13, "二代 => 一代");
	    	MONEY_FLOW.put(14, "客户 => 二代");
	    	MONEY_FLOW.put(15, "客户 => 二代");
	    	MONEY_FLOW.put(16, "二代 => 系统");
	    	MONEY_FLOW.put(17, "一代 => 系统");
	    	MONEY_FLOW.put(18, "系统 => 客户");
	    	MONEY_FLOW.put(19, "客户 => 系统");
	    	MONEY_FLOW.put(20, "客户 => 系统");
	    	MONEY_FLOW.put(21, "系统 => 客户");
	    	MONEY_FLOW.put(22, "系统 => 客户");
	    	MONEY_FLOW.put(23, "系统 => 客户");
	    	MONEY_FLOW.put(24, "系统 => 客户");
	    	MONEY_FLOW.put(31, "系统 => 客户");
	    	MONEY_FLOW.put(32, "系统 => 客户");
	    	MONEY_FLOW.put(33, "系统 => 客户");
	    	MONEY_FLOW.put(34, "系统 => 客户");
	    	MONEY_FLOW.put(35, "系统 => 客户");
	    	MONEY_FLOW.put(36, "系统 => 客户");
	    	MONEY_FLOW.put(37, "系统 => 客户");
	    }
	    
	    //对账明细 类型定义
	    public static final int CHECK_LIST_TYPE_AGENT1_CHARGE =1;
	    public static final int CHECK_LIST_TYPE_AGENT1_CHARGE_BACK_OUT =2;
	    public static final int CHECK_LIST_TYPE_AGENT2_CHARGE =3;
	    public static final int CHECK_LIST_TYPE_AGENT2_CHARGE_BACK_OUT =4;
	    public static final int CHECK_LIST_TYPE_ENTERPRISE_CHARGE =5;
	    public static final int CHECK_LIST_TYPE_ENTERPRISE_CHARGE_BACK_OUT =6;
	    public static final int CHECK_LIST_TYPE_ONE_TIME_CHARGE =7;
	    
	    //声音文件路径
	    public static final String SOUNDS_IVR_VOICE_PATH = "/voices/ivr_voice/";
	    
	    /*
	     * 公共语音库 publicvoice
	     */
	    public static final String SOUNDS_MOH_ABS_PATH = "/voices/moh/";
	    public static final String SOUNDS_IVR_VOICE_ABS_PATH = "/voices/ivr_voice/";
	    
	    /*
	     * 企业语音库 
	     */
	    public static final String SOUNDS_ENTERPRISE_MOH_ABS_PATH = "/var/www/html/voices/moh/";
	    
	    /*
	     * 企业实体状态:1正常，2暂停,3锁定
	     */
	    public static final int ENTERPRISE_STATUS_NORMAL = 1;
	    public static final int ENTERPRISE_STATUS_SUSPEND = 2;
	    public static final int ENTERPRISE_STATUS_LOCK = 3;
	    
	    /*
	     * 企业业务状态:0:未开通 1:正常 2:欠费 3:停机 4:注销
	     */
	    public static final int ENTERPRISE_BUSINESS_STATUS_UNTUTORED = 0;
	    public static final int ENTERPRISE_BUSINESS_STATUS_NORMAL = 1;
	    public static final int ENTERPRISE_BUSINESS_STATUS_SUSPEND = 2;
	    public static final int ENTERPRISE_BUSINESS_STATUS_STOP = 3;
	    public static final int ENTERPRISE_BUSINESS_STATUS_LOGOUT = 4;
	    
	    /*
	     *
	     * 企业类型: 0--直销客户或测试账户 1--代理商的非测试客户 2--直销客户 3--测试客户 4--代理商的所有客户 5--所有客户
	     */
	    public static final int ENTERPRISE_TYPE_DIRECT_OR_TEST = 0;
	    public static final int ENTERPRISE_TYPE_AGENT = 1;
	    public static final int ENTERPRISE_TYPE_DIRECT = 2;
	    public static final int ENTERPRISE_TYPE_TEST = 3;
	    public static final int ENTERPRISE_TYPE_AGENT_ALL = 4;
	    public static final int ENTERPRISE_TYPE_ALL = 5;
	    
	    /*
	     * log_email: type definition
	     */
	    public static final Integer LOG_EMAIL_TYPE_REPORT_WEEK = 1;
	    public static final Integer LOG_EMAIL_TYPE_REPORT_MONTH = 2;
	    public static final Integer LOG_EMAIL_TYPE_SYSTEM_REMIND = 3;
	    public static final Integer LOG_EMAIL_TYPE_SYSTEM_ALERT = 4;
	    public static final Integer LOG_EMAIL_TYPE_CRONTAB = 5;
	    public static final Integer LOG_EMAIL_TYPE_FIND_PASSWORD = 6;
	    
	    /*
	     * 系统自动发送邮件提醒的发件人地址
	     */
	    public static final String SYSTEM_EMAIL_ADDRESS = "system_email_address";
	    public static final String SYSTEM_EMAIL_PWD = "system_email_pwd";
	    
	    /*
	     * 北京联通超级总机网站
	     */
	    public static final String BEI_JING_UNICOM_SUPER_HOTLINE_WEBSITE = "http://www.hostpbx.cn";
	    
	    /*
	     * 充值，直充与冲正定义
	     */
	    public static final Integer CHARGE_CHARGE_TYPE_CHARGE = 1;
	    public static final Integer CHARGE_CHARGE_TYPE_DIRECT_CHARGE = 2;
	    public static final Integer CHARGE_CHARGE_TYPE_BACKOUT = 3;
	    public static final Integer CHARGE_CHARGE_TYPE_PRE_CHARGE = 4;
	    
	    /*
	     * 日报表/月报表
	     */
	    public static final Integer REPORT_MONTH = 1;
	    public static final Integer REPORT_DAY = 2;
	    
	    /*
	     * 汇总显示,分类显示
	     */
	    public static final Integer REPORT_VIEW_TYPE_COLLECT = 1;
	    public static final Integer REPORT_VIEW_TYPE_CLASSIFY = 2;
	    
	    /*
	     * log_deduction fee_type definition 1开头的为手工扣费
	     */
	    public static final int LOG_DEDUCTION_FEE_TYPE_LOWEST_COST = 1;
	    public static final int LOG_DEDUCTION_FEE_TYPE_FUNCTION_FEE = 2;
	    public static final int LOG_DEDUCTION_FEE_TYPE_CLIENT_RENT = 3;
	    public static final int LOG_DEDUCTION_FEE_TYPE_ONCE_CHARGE = 4;
	    public static final int LOG_DEDUCTION_FEE_TYPE_OB_COMBO_FEE = 5;
	    public static final int LOG_DEDUCTION_FEE_TYPE_TRUNK_RENT = 6;
	    public static final int LOG_DEDUCTION_FEE_TYPE_PREDICTIVE_CALL_FEE = 7;
	    public static final int LOG_DEDUCTION_FEE_TYPE_CALL_ACCOUNT_FEE = 11;
	    public static final int LOG_DEDUCTION_FEE_TYPE_OB_ACCOUNT_FEE = 12;
	    public static final int LOG_DEDUCTION_FEE_TYPE_CLIENT_RENT_FEE = 13;
	    public static final int LOG_DEDUCTION_FEE_TYPE_NUMBER_FEE_HAND = 16;
		public static final int LOG_DEDUCTION_FEE_TYPE_TRUNK_USE_FEE_HAND = 17;
	    
	    
	    /*
	     * 测试账户开户默认充值金额
	     */
	    public static final BigDecimal TEST_ENTERPRISE_ACCOUNT_MONEY_DEFAULT = new BigDecimal(100);
	    
	    /*
	     * 天润默认公司名称及联系电话
	     */
	    public static final String TINET_NAME_DEFAULT = "北京天润融通科技有限公司";
	    public static final String TINET_TEL_DEFAULT = "01059222999";
	    
	    /*
	     * product id definition
	     */
	    public static final Integer PRODUCT_ID_SMART400 = 10;
	    public static final Integer PRODUCT_ID_SUPERHOTLINE = 11;
	    public static final Integer PRODUCT_ID_CALL_CENTER = 12;
	    
	    /*
	     * notice is_publish definition
	     */
	    public static final Integer NOTICE_IS_PUBLISH_NO = 0;
	    public static final Integer NOTICE_IS_PUBLISH_YES = 1;
	    
	    /*
	     * alert_template_eamil language,template_role definition
	     */
	    public static final String ALERT_TEMPLATE_EAMIL_LANGUAGE_DEFAULT = "utf-8";
	    
	    /*
	     * alert_template_email event_type,template_role definition
	     */
	    public static Map<Integer, String> ALERT_EVENT_TYPE ;
	    static {
	    	ALERT_EVENT_TYPE = new HashMap<Integer, String>();
	    	ALERT_EVENT_TYPE.put(1000, "客户管理");
	    	ALERT_EVENT_TYPE.put(1111, "开户");
	    	ALERT_EVENT_TYPE.put(1112, "业务开通");
	    	ALERT_EVENT_TYPE.put(1113, "客户3个月未登录");
	    	ALERT_EVENT_TYPE.put(1114, "合同到期");
	    	ALERT_EVENT_TYPE.put(1115, "密码过期");
	    	ALERT_EVENT_TYPE.put(1116, "开户申请");
	    	ALERT_EVENT_TYPE.put(1117, "申请预充值开户");//接受者：直销部门
	    	ALERT_EVENT_TYPE.put(1118, "申请预充值开户");//接受者：副总
	    	ALERT_EVENT_TYPE.put(1119, "客户业务信息变更");//接受者：客户，直销经理
	    	
	    	ALERT_EVENT_TYPE.put(1120, "请审核");
	    	ALERT_EVENT_TYPE.put(1121, "审核失败");
	    	ALERT_EVENT_TYPE.put(1122, "申请开户");//接受者：客服
	    	ALERT_EVENT_TYPE.put(1123, "开户申请失败");//接受者：销售
	    	ALERT_EVENT_TYPE.put(1124, "未开通");//接受者：销售
	    	
	    	ALERT_EVENT_TYPE.put(2000, "号码管理");
	    	ALERT_EVENT_TYPE.put(2111, "号码预占");
	    	ALERT_EVENT_TYPE.put(2112, "预占号码释放");
	    	ALERT_EVENT_TYPE.put(2113, "号码预占申请");
	    	ALERT_EVENT_TYPE.put(2114, "号码预占成功");
	    	ALERT_EVENT_TYPE.put(2115, "号码预占失败");
	    	
	    	ALERT_EVENT_TYPE.put(3000, "交易");
	    	ALERT_EVENT_TYPE.put(3111, "申请充值");//接受者：出纳
	    	ALERT_EVENT_TYPE.put(3118, "申请充值");//接受者：直销部门
	    	ALERT_EVENT_TYPE.put(3119, "申请充值");//接受者：直销副总
	    	ALERT_EVENT_TYPE.put(3112, "扣费");
	    	ALERT_EVENT_TYPE.put(3113, "充值已到账");//接受者：销售
	    	ALERT_EVENT_TYPE.put(3114, "其他项目充值");//接受者：客服
	    	ALERT_EVENT_TYPE.put(3115, "申请赠送充值");//接受者：客服
	    	ALERT_EVENT_TYPE.put(3116, "充值失败");//接受者：销售
	    	ALERT_EVENT_TYPE.put(3117, "充值审核失败");//接受者：销售
//	    	ALERT_EVENT_TYPE.put(3120, "补充赠送充值");//接受者：销售
	    	ALERT_EVENT_TYPE.put(3121, "信用额度申请");//接受者：部门
	    	ALERT_EVENT_TYPE.put(3122, "信用额度申请");//接受者：副总
	    	ALERT_EVENT_TYPE.put(3123, "信用额度申请失败");//接受者：经理
	    	ALERT_EVENT_TYPE.put(3124, "信用额度申请成功");//接受者：销售
	    	
	    	ALERT_EVENT_TYPE.put(4000, "计费");
	    	ALERT_EVENT_TYPE.put(4100, "呼入余额较低");
	    	ALERT_EVENT_TYPE.put(4110, "呼入话费余额较低");
	    	ALERT_EVENT_TYPE.put(4111, "呼入话费余额不足下月最低消费");
	    	ALERT_EVENT_TYPE.put(4112, "呼入话费余额不足下年最低消费");
	    	ALERT_EVENT_TYPE.put(4113, "呼入话费余额不足200元");
	    	ALERT_EVENT_TYPE.put(4114, "呼入话费余额不足100元");
	    	ALERT_EVENT_TYPE.put(4115, "呼入话费余额不足50元");
	    	ALERT_EVENT_TYPE.put(4116, "呼入话费余额不足月最低消费1/5");
	    	ALERT_EVENT_TYPE.put(4117, "呼入话费余额不足月最低消费1/10");
	    	
	    	ALERT_EVENT_TYPE.put(4130, "功能费余额较低");
	    	ALERT_EVENT_TYPE.put(4131, "座席费余额不足下月座席费");
	    	ALERT_EVENT_TYPE.put(4132, "功能费余额不足下年功能费");
	    	
	    	ALERT_EVENT_TYPE.put(4140, "座席费余额较低");
	    	ALERT_EVENT_TYPE.put(4150, "短信费余额较低");
	    	
	    	ALERT_EVENT_TYPE.put(4160, "外呼话费余额不足1000元");
	    	ALERT_EVENT_TYPE.put(4161, "外呼话费余额较低");
	    	ALERT_EVENT_TYPE.put(4162, "外呼话费余额不足500元");
	    	ALERT_EVENT_TYPE.put(4163, "外呼话费余额不足200元");
	    	ALERT_EVENT_TYPE.put(4164, "外呼话费余额不足100元");
	    	ALERT_EVENT_TYPE.put(4165, "外呼话费余额不足50元");
	    	ALERT_EVENT_TYPE.put(4166, "外呼话费余额不足10元");
	    	ALERT_EVENT_TYPE.put(4167, "外呼话费余额不足0元");
	    	
	    	/*-- start 合并账户后新定义的提醒事件类型   --*/
	    	ALERT_EVENT_TYPE.put(4120, "现金账户余额不足0元");
	    	ALERT_EVENT_TYPE.put(4121, "现金账户余额不足下月固定费用20%");
	    	ALERT_EVENT_TYPE.put(4122, "现金账户余额不足下月固定费用");
	    	ALERT_EVENT_TYPE.put(4123, "现金账户余额不足上月总消费20%");
	    	
	    	ALERT_EVENT_TYPE.put(4271, "账户欠费,业务暂停");
	    	/*-- end --*/
	    	
	    	ALERT_EVENT_TYPE.put(4200, "欠费");
	    	ALERT_EVENT_TYPE.put(4211, "呼入话费不足欠费");
	    	ALERT_EVENT_TYPE.put(4231, "功能费不足欠费");
	    	ALERT_EVENT_TYPE.put(4241, "座席费不足欠费");
	    	ALERT_EVENT_TYPE.put(4251, "短信费不足欠费");
	    	ALERT_EVENT_TYPE.put(4261, "外呼话费不足欠费");
	    	
	    	ALERT_EVENT_TYPE.put(5000, "故障告警");
	    	ALERT_EVENT_TYPE.put(5111, "系统告警");
	    	ALERT_EVENT_TYPE.put(5112, "网络告警");
	    	ALERT_EVENT_TYPE.put(5113, "网关告警");
	    	ALERT_EVENT_TYPE.put(5114, "电路告警");
	    	
	    	ALERT_EVENT_TYPE.put(6000, "测试账号相关");
	    	ALERT_EVENT_TYPE.put(6111, "申请测试账号成功");
	    	ALERT_EVENT_TYPE.put(6112, "测试账号数量过小");
	    	ALERT_EVENT_TYPE.put(6113, "测试账号初始化成功");
	    }
	    public static final int ALERT_TEMPLATE_TEMPLATE_ROLE_AGENT_DIRECT = 1;//模板接收者的角色 1--直销/代理商  2--客户  3--客服 4--直销部门 5--直销经理  6--代理商 12--出纳  --17直销副总  
	    public static final int ALERT_TEMPLATE_TEMPLATE_ROLE_ENTERPRISE = 2;
	    public static final int ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE = 3;
	    public static final int ALERT_TEMPLATE_TEMPLATE_ROLE_CASHIER = 12;
	    public static final int ALERT_TEMPLATE_TEMPLATE_ROLE_DEPUTY_GENERAL = 17;
	    public static final int ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_SECTION = 4;
	    public static final int ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER = 5;
	    public static final int ALERT_TEMPLATE_TEMPLATE_ROLE_AGENT = 6;
	    
	    /*
	     * default alert templates of email or sms templateTeam 
	     */
	    public static final Map<Integer, Integer[]> eventTypeAndRoleMap = new HashMap<Integer, Integer[]>();
	    static {
	    	eventTypeAndRoleMap.put(1111, new Integer[]{Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE,
	    			Const.ALERT_TEMPLATE_TEMPLATE_ROLE_ENTERPRISE,Const.ALERT_TEMPLATE_TEMPLATE_ROLE_AGENT_DIRECT });
	    	eventTypeAndRoleMap.put(1116, new Integer[]{Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE});
	    	eventTypeAndRoleMap.put(2111, new Integer[]{Const.ALERT_TEMPLATE_TEMPLATE_ROLE_AGENT,
	    			Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE});
	    	eventTypeAndRoleMap.put(2113, new Integer[]{Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE});
	    	eventTypeAndRoleMap.put(2114, new Integer[]{Const.ALERT_TEMPLATE_TEMPLATE_ROLE_AGENT,
	    			Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE});
	    	eventTypeAndRoleMap.put(2115, new Integer[]{Const.ALERT_TEMPLATE_TEMPLATE_ROLE_AGENT,
	    			Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE});
	    	eventTypeAndRoleMap.put(4100, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE,
	    			Const.ALERT_TEMPLATE_TEMPLATE_ROLE_ENTERPRISE,Const.ALERT_TEMPLATE_TEMPLATE_ROLE_AGENT_DIRECT});
	    	eventTypeAndRoleMap.put(4200, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE,
	    			Const.ALERT_TEMPLATE_TEMPLATE_ROLE_ENTERPRISE, Const.ALERT_TEMPLATE_TEMPLATE_ROLE_AGENT_DIRECT });
	    	
	    	eventTypeAndRoleMap.put(1120, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE});
	    	eventTypeAndRoleMap.put(1121, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
	    	eventTypeAndRoleMap.put(1124, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
	    	eventTypeAndRoleMap.put(1117, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_SECTION});
	    	eventTypeAndRoleMap.put(1118, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DEPUTY_GENERAL});
	    	eventTypeAndRoleMap.put(3111, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CASHIER});
	    	eventTypeAndRoleMap.put(3113, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
	    	eventTypeAndRoleMap.put(3114, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE});
//	    	eventTypeAndRoleMap.put(3120, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
	    	eventTypeAndRoleMap.put(3116, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
	    	eventTypeAndRoleMap.put(3118, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_SECTION});
	    	eventTypeAndRoleMap.put(3119, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DEPUTY_GENERAL});
	    	eventTypeAndRoleMap.put(6111, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
//	    	eventTypeAndRoleMap.put(6113, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
//	    	eventTypeAndRoleMap.put(6112, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_CUSTOMER_SERVICE});
	    	
	    	eventTypeAndRoleMap.put(3121, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_SECTION});
	    	eventTypeAndRoleMap.put(3122, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DEPUTY_GENERAL});
	    	eventTypeAndRoleMap.put(3123, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
	    	eventTypeAndRoleMap.put(3124, new Integer[]{ Const.ALERT_TEMPLATE_TEMPLATE_ROLE_DIRECT_MANAGER});
	    	
	    	
	    }
	    
	    /*
		 * log_crontab: result && name definition
		 */
		public static final int LOG_CRONTAB_RESULT_FAILURE = 0;
		public static final int LOG_CRONTAB_RESULT_SUCCESS = 1;
		public static final String LOG_CRONTAB_NAME_CREATE_TABLE = "createTable";
		public static final String LOG_CRONTAB_NAME_VACUUM_ANALYZE_DB = "vacuumAnalyzeDb";
		public static final String LOG_CRONTAB_NAME_REMOVE_EXPIRED_NUMBER = "removeExpiredNumber";
		public static final String LOG_CRONTAB_NAME_EXECUTE_SENT_ALERT_EVENT = "executeSentAlertEvent";
		public static final String LOG_CRONTAB_NAME_EXECUTE_SENT_MONEY_ALERT_EVENT = "executeMoneySentAlertEvent";
		public static final String LOG_CRONTAB_NAME_EXECUTE_SENT_TEST_LITTLE_ALERT_EVENT = "executeTestLittleAlertEvent";
		public static final String LOG_CRONTAB_NAME_EXECUTE_UPDATE_ENTERPRISE_TO_BOSS2 = "syncEnterprise2BOSS2";
		public static final String LOG_CRONTAB_NAME_EXECUTE_INVALID_ORDERS = "executeInvalidOrders";
		public static final String LOG_CRONTAB_NAME_CHECK_TIMEOUT_TRANS = "checkTimeoutTrans";
		public static final String LOG_CRONTAB_NAME_UPDATE_TOTAL_COST = "updateTotalCost";
		public static final String LOG_CRONTAB_NAME_EXECUTE_CLIENT_NUM_VALID = "executeClientNumValid";
		public static final String LOG_CRONTAB_NAME_EXECUTE_UPDATE_NUMBERS_TO_BOSS2 = "syncNumbers2BOSS2";
		
		/*
		 * alert_event: send_flag,send_time_type,send_method,tag_type definition
		 */
		public static final int ALERT_EVENT_SEND_FLAG_UNSENT = 0;//发送标志: 0--未发送,1--已发送
		public static final int ALERT_EVENT_SEND_FLAG_SENT = 1;
		public static final int ALERT_EVENT_SEND_TIME_TYPE_AT_ONCE = 0;//发送时间类型:0--实时发送 1--定时发送
		public static final int ALERT_EVENT_SEND_TIME_TYPE_TIMED = 1;
		public static final int ALERT_EVENT_SEND_METHOD_NONE = 0;// 发送方式: 0--无,1--邮件,2--短信,3--邮件/短信
		public static final int ALERT_EVENT_SEND_METHOD_EMAIL = 1;
		public static final int ALERT_EVENT_SEND_METHOD_SMS = 2;
		public static final int ALERT_EVENT_SEND_METHOD_BOTH = 3;
		public static final int ALERT_EVENT_TAG_TYPE_UNDISPOSED = 1;//标记: 1--未处理，2--待后续，3--已处理
		public static final int ALERT_EVENT_TAG_TYPE_WAITING = 2;
		public static final int ALERT_EVENT_TAG_TYPE_HANDLED = 3;
		/*
		 * enterprise_setting: alert_threshold 标记: 1~6分别表示不同级别 0元、50元、100元、200元、500元、1000元
		 */
		public static final String ENTERPRISE_SETTING_ALERT_ALERT_THRESHOLD0 = "alert_threshold1";
		public static final String ENTERPRISE_SETTING_ALERT_ALERT_THRESHOLD50 = "alert_threshold2";
		public static final String ENTERPRISE_SETTING_ALERT_ALERT_THRESHOLD100 = "alert_threshold3";
		public static final String ENTERPRISE_SETTING_ALERT_ALERT_THRESHOLD200 = "alert_threshold4";
		public static final String ENTERPRISE_SETTING_ALERT_ALERT_THRESHOLD500 = "alert_threshold5";
		public static final String ENTERPRISE_SETTING_ALERT_ALERT_THRESHOLD1000 = "alert_threshold6";
		
		/*
		 * enterprise_setting: 最低充值金额 标记: 
		 */
		public static final String ENTERPRISE_SETTING_MIN_CHARGE_MONEY_IB = "min_money_ib";
		public static final String ENTERPRISE_SETTING_MIN_CHARGE_MONEY_OB = "min_money_ob";
		public static final String ENTERPRISE_SETTING_MIN_CHARGE_MONEY_FUNC = "min_money_func";
		public static final String ENTERPRISE_SETTING_MIN_CHARGE_MONEY_SMS = "min_money_sms";
		/*
		 * enterprise_setting: test_status 标记:0表示空闲、1表示已用
		 */
		public static final int ENTERPRISE_SETTING_TEST_STATUS_FREE = 0;
		public static final int ENTERPRISE_SETTING_TEST_STATUS_USED = 1;
		/*
		 * alert_send_list: send_method,receiver_type definition
		 */
		public static final int ALERT_SEND_LIST_SEND_METHOD_EMAIL = 1;//发送类型：1--邮件 2--短信
		public static final int ALERT_SEND_LIST_SEND_METHOD_SMS = 2;
		public static final int ALERT_SEND_LIST_RECEIVER_TYPE_AGENT1 = 1;//接收方类型 1--一代 2--二代 3--客户 4--直销部门，5--销售经理，13--客服 ，同entity的entity_type
		public static final int  ALERT_SEND_LIST_RECEIVER_TYPE_AGENT2 = 2;
		public static final int  ALERT_SEND_LIST_RECEIVER_TYPE_ENTERPRISE = 3;
		public static final int  ALERT_SEND_LIST_RECEIVER_TYPE_DIRECT_SECTION = 4;
		public static final int  ALERT_SEND_LIST_RECEIVER_TYPE_DIRECT_MANAGER = 5;
		public static final int  ALERT_SEND_LIST_RECEIVER_TYPE_CUSTOMER_SERVICE = 13;
		public static final int  ALERT_SEND_LIST_RECEIVER_TYPE_CASHIER = 12;
		public static final int  ALERT_SEND_LIST_RECEIVER_TYPE_DEPUTY_GENERAL = 17;
		
		/*
		 * cdr_sms: sms_type definition
		 */
		public static final int CDR_SMS_SMS_TYPE_REMINDING = 1;
		public static final int CDR_SMS_SMS_TYPE_WARNING = 2;
		
		/*
		 * recharge_order: type definition 1：充值预报，2：预充值，3：赠送-客户充值，4：转移，5:赠送-充抵欠款,6：在线充值，7：在线充值-赠送,8：线下充值，9线下充值-赠送，10：使用临时信用额度开户;
		 */
		public static final int RECHARGE_ORDER_TYPE_FORECAST = 1;
		public static final int RECHARGE_ORDER_TYPE_PRECHARGE = 2;
		public static final int RECHARGE_ORDER_TYPE_GIVE_CHARGED = 3;
		public static final int RECHARGE_ORDER_TYPE_DIVERT = 4;
		public static final int RECHARGE_ORDER_TYPE_GIVE_UNCHARGED = 5;
		public static final int RECHARGE_ORDER_TYPE_ONLINE_CHARGED = 6;
		public static final int RECHARGE_ORDER_TYPE_ONLINE_GIVE = 7;
		public static final int RECHARGE_ORDER_TYPE_UNDERLINE_CHARGED = 8;
		public static final int RECHARGE_ORDER_TYPE_UNDERLINE_GIVE = 9;
		public static final int RECHARGE_ORDER_TYPE_CREDIT_ORDER = 10;
		
		/*
		 * recharge_order: status definition 状态:1=未审批、2=审批中、3=审批失败、4=审批通过、5=未审核、6=审核中、7=审核失败、8=审核通过、9=已到账、10=已充值、11=已取消、12=未提交。status=1,2,3,4对type=2,3,4生效；status=5,6,7,8对type=1,3生效。status=9对所有type生效;
		 */
		public static final int RECHARGE_ORDER_STATUS_UNAPPROVED= 1;
		public static final int RECHARGE_ORDER_STATUS_APPROVEING = 2;
		public static final int RECHARGE_ORDER_STATUS_APPROVEFALL = 3;
		public static final int  RECHARGE_ORDER_STATUS_APPROVEPASS = 4;
		public static final int RECHARGE_ORDER_STATUS_UNAUDITED= 5;
		public static final int RECHARGE_ORDER_STATUS_AUDITING = 6;
		public static final int RECHARGE_ORDER_STATUS_AUDITFALL = 7;
		public static final int RECHARGE_ORDER_STATUS_AUDITPASS = 8;
		public static final int RECHARGE_ORDER_STATUS_ISPAYED = 9;
		public static final int  RECHARGE_ORDER_STATUS_CHARGED = 10;//在线充值和线下充值同样用到-已充值
		public static final int RECHARGE_ORDER_STATUS_ISCANCLE = 11;
		public static final int RECHARGE_ORDER_STATUS_UNCOMMITTED = 12;
		public static final int RECHARGE_ORDER_STATUS_UNPAID = 13;//在线充值和线下充值专用-未付款
		public static final int RECHARGE_ORDER_STATUS_COMMITTED = 14;//在线充值和线下充值专用-已提交
		public static final int RECHARGE_ORDER_STATUS_INVALID = 15;//在线充值和线下充值专用-已失效
		public static final int RECHARGE_ORDER_STATUS_PAYFAIL = 16;//在线充值和线下充值专用-充值失败
		public static final int RECHARGE_ORDER_STATUS_ACTIVE_CANCLE = 17;//在线充值和线下充值专用-手动取消
		/*
		 * recharge_order: is_arrears definition 0：是欠款，1：不是欠款;
		 */
		public static final int RECHARGE_ORDER_IS_ARREARS_TRUE = 1;
		public static final int RECHARGE_ORDER_IS_ARREARS_FALSE = 0;
		
		/*
		 * recharge_order_new : status definition 
		 * 开户状态使用1,未提交;2,开户直销经理提交之后到客服审核通过之前,3客服审核通过,直销经理提交审核（出纳待查到帐），4 客服审核失败
		 * 5.出纳查到账，6出纳打回
		 * 赠送充值 7 未审批，8审批中，9审批失败，10审批通过
		 */
		public static final int RECHARGE_ORDER_NEW_STATUS_UNCOMMIT = 1;
		public static final int RECHARGE_ORDER_NEW_STATUS_AUDITING = 2;
		public static final int RECHARGE_ORDER_NEW_STATUS_AUDITPASS = 3;
		public static final int RECHARGE_ORDER_NEW_STATUS_AUDITFAIL = 4;
		
		public static final int RECHARGE_ORDER_NEW_STATUS_SUCCESS = 5;
		public static final int RECHARGE_ORDER_NEW_STATUS_FAIL = 6;
		
		public static final int RECHARGE_ORDER_NEW_STATUS_UNAPPROVAL = 7;
		public static final int RECHARGE_ORDER_NEW_STATUS_APPROVALING = 8;
		public static final int RECHARGE_ORDER_NEW_STATUS_APPROVALFAIL = 9;
		public static final int RECHARGE_ORDER_NEW_STATUS_APPROVALSUCCESS = 10;
		
		public static final int RECHARGE_ORDER_NEW_STATUS_UNCOMMITTED = 12;
		public static final int RECHARGE_ORDER_NEW_STATUS_UNPAID = 13;//在线充值和线下充值专用-未付款
		public static final int RECHARGE_ORDER_NEW_STATUS_UNDER_LINE_UNCHARGE = 14;
		public static final int RECHARGE_ORDER_NEW_STATUS_INVALID = 15;//在线充值和线下充值专用-已失效
		public static final int RECHARGE_ORDER_NEW_STATUS_PAYFAIL = 16;//在线充值和线下充值专用-充值失败
		public static final int RECHARGE_ORDER_NEW_STATUS_ACTIVE_CANCLE = 17;//在线充值和线下充值专用-手动取消
		
		
		
		
		public static final int RECHARGE_ORDER_NEW_TYPE_FORCAST = 1;
		public static final int RECHARGE_ORDER_NEW_TYPE_GIVE = 2;
		public static final int RECHARGE_ORDER_NEW_TYPE_ONLINE = 6;
		public static final int RECHARGE_ORDER_NEW_TYPE_ONLINE_GIVE = 7;
		public static final int RECHARGE_ORDER_NEW_TYPE_UNDERLINE = 8;
		
		/*
		 * log_charge: is_cancel definition 0：交易未被取消，1：交易取消产生的新记录 2：交易取消后的原记录;
		 */
		public static final int TRADE_IS_NOT_CANCEL = 0;
		public static final int TRAND_CANCEL_NEW_LOG = 1;
		public static final int TRAND_CANCEL_FORMER_LOG =2;
		/*
		 * recharge_order: enterpriseFlag definition 0：表示新开户，1：表示续费;
		 */
		public static final int RECHARGE_ORDER_ENTERPRISE_FLAG_NEWCHARGED = 0;
		public static final int RECHARGE_ORDER_ENTERPRISE_FLAG_OLDCHARGED = 1;
		/*
		 * recharge_order: 是否充抵欠款 0：表示直接向C2充值，1：表示充抵欠款;
		 */
		public static final int RECHARGE_ORDER_IS_UPDATE_ARREARS_NO = 0;
		public static final int RECHARGE_ORDER_IS_UPDATE_ARREARS_YES = 1;
		/*
		 * trunk trunk_status definition
		 */
		public static final int TRUNK_TRUNK_STATUS_UNBOUND = 1;
		public static final int TRUNK_TRUNK_STATUS_BOUND = 2;
		public static final int TRUNK_TRUNK_STATUS_INUSE = 3;
		public static final int TRUNK_TRUNK_STATUS_PREEMPTION = 4; // 客服手工预占
		public static final int TRUNK_TRUNK_STATUS_FREEZE = 5; // 冻结
		public static final int TRUNK_TRUNK_STATUS_RESERVE = 6; // 项目保留
		
		/*
		 * enterprise_arrears type definition
		 */
		public static final int ENTERPRISE_ARREARS_TYPE_ARREARS = 1;
		public static final int ENTERPRISE_ARREARS_TYPE_REPAY = 2;
		
		/*
		 * enterprise_arrears is_cancle definition
		 */
		public static final int ENTERPRISE_ARREARS_IS_CANCLE_NO = 0;
		public static final int ENTERPRISE_ARREARS_IS_CANCLE_YES = 1;
		/*
		 * 客户管理返回页面标志
		 */
		public static final String ENTERPRISE_RETURN_FLAG_DIRECT = "direct";
		public static final String ENTERPRISE_RETURN_FLAG_TEST = "test";
		public static final String ENTERPRISE_RETURN_FLAG_QUICK = "quick";
		
		public static final String[] ENTITY_COLUMN = new String[]{
			"entityName", "entityType", "entityParent", "entitySn", "fullName", "areaCode", "principal", "sex", "sexTitle", "mobile", "tel"
			, "email", "fax", "address", "post", "status", "legalPerson", "trade", "businessNo", "question", "answer"
			, "website", "certificateType", "certificateId", "certificateTime", "comment", "country", "state", "city", "language"
		};
		public static final String[] ENTERPRISE_TEMP_COLUMN = new String[]{
			"ccic", "allNumber", "allTrunk", "masterHotline", "clientWeb", "clientTel", "rateObLeftName", "rateIbLeftName", "rateObRightName", "rateObR" +
					"rateSms"
			, "ibRouterRightName", "obPreviewRouterLeftName"
		};

		/*折扣状态 1表示激活，0表示未激活*/
		public static final int DISCOUNT_ACTIVE = 1;
		public static final int DISCOUNT_UN_ACTIVE = 0;
		/*支付接口返回状态，200表示正确，400表示没有数据，500表示服务器错误，900表示未知异常*/
		public static final String SERVER_STATUS_OK ="200";
		public static final String SERVER_STATUS_AUTH_FAIL ="300";
		public static final String SERVER_STATUS_NO_RESPONSE ="400";
		public static final String SERVER_STATUS_ERROR ="500";
		public static final String SERVER_STATUS_UNKNOW_ERROR ="900";
		/*是否开发票 1表示开，0表示不开*/
		public static final int HAS_INVOICE = 1;
		public static final int HAS_NO_INVOICE = 0;
		/*延期15天*/
		public static final int ENTERPRISE_TEST_EXPIRY_DATE_ADD15 = 15;
		/*在线支付固定值*/
		public static final String ONLINE_PAY_BUY ="Buy";// 在线支付请求，固定值 ”Buy”
		public static final String ONLINE_PAY_CNY ="CNY";// 交易币种
		public static final String ONLINE_PAY_GOODS_NAME ="tinet";// 商品名称
		public static final String ONLINE_PAY_GOODS_TYPE ="tinet";// 商品种类
		public static final String ONLINE_PAY_GOODS_DESC ="tinet";// 商品描述
//		public static final String ONLINE_PAY_GOODS_NAME ="托管型呼叫中心";// 商品名称
//		public static final String ONLINE_PAY_GOODS_TYPE ="呼叫中心";// 商品种类
//		public static final String ONLINE_PAY_GOODS_DESC ="托管型呼叫中心";// 商品描述
		public static final String ONLINE_PAY_CALLBACK_URL ="/app/yeepay/callback.jsp";// 商户接收支付成功数据的地址
		public static final String ONLINE_PAY_CALLBACK_URL_NEW ="/app/yeepay/callbackNew.jsp";// 商户接收支付成功数据的地址
		public static final String ONLINE_PAY_SAF ="0";// 需要填写送货信息 0：不需要  1:需要
		public static final String ONLINE_PAY_MP ="CCIC2";// 商户扩展信息
		public static final String ONLINE_PAY_FRPID ="";// 支付通道编码
		public static final int INVOICE_STATUS_YES = 1;
		public static final int INVOICE_STATUS_NO = 0;
		
		/*
		 * INVOICE: TYPE 1、托管型呼叫中心服务费2、智能400服务费3、信息服务费4、服务费
		 */
		public static final int INVOICE_TYPE_1 = 1;
		public static final int INVOICE_TYPE_2 = 2;
		public static final int INVOICE_TYPE_3 = 3;
		public static final int INVOICE_TYPE_4 = 4;
		public static final String ONLINE_PAY_FEE_RATE_B2C ="0.0007";// 手续费率
		public static final String ONLINE_PAY_FEE_MONEY_B2B ="10";// 手续费率
		public static final BigDecimal ON_LINE_SERVICE_CHARGE = new BigDecimal(0.007);
		/*
		 * enterprise_is_predictive_open: 1、开启0、关闭
		 */
		public static final int IS_PREDICTIVE_OPEN_ON = 1;
		public static final int IS_PREDICTIVE_OPEN_OFF = 0;
		
		/*
		 * log_change type : 1、座席变更  2、新开户  3，销户
		 * 4-座席月租，5-号码最低消费，6-客户呼入费率，7-外呼客户费率变更，8-短信费率，9-短信签名。10-虚拟号码变更
		 */
		public static final int LOG_CHANGE_TYPE_BIZ_CHANGE = 1;
		public static final int LOG_CHANGE_TYPE_OPEN_BIZ = 2;
		public static final int LOG_CHANGE_TYPE_LOGOUT = 3;
		public static final int LOG_CHANGE_TYPE_CLIENT_WEB_RENT = 4;
		public static final int LOG_CHANGE_TYPE_LOWEST_COST = 5;
		public static final int LOG_CHANGE_TYPE_IB_RATE = 6;
		public static final int LOG_CHANGE_TYPE_OB_RATE = 7;
		public static final int LOG_CHANGE_TYPE_SMS_RATE = 8;
		public static final int LOG_CHANGE_TYPE_SMS_SIGN = 9;
		public static final int LOG_CHANGE_TYPE_49 = 49;
		public static final int LOG_CHANGE_TYPE_10 = 10;
		 
		 /*
		 * 1-已变更  2-未提交  3-未审批  4-审批中  5-审批失败  6-等待生效
		 */
		public static final int LOG_CHANGE_STATUS_CHANGED = 1;
		public static final int LOG_CHANGE_STATUS_UNCOMMIT = 2;
		public static final int LOG_CHANGE_STATUS_UNAPPROVAL = 3;
		public static final int LOG_CHANGE_STATUS_APPROVALING = 4;
		public static final int LOG_CHANGE_STATUS_APPROVAL_FAIL = 5;
		public static final int LOG_CHANGE_STATUS_WAIT_VALID = 6;
		
		 /*
		  * auth_white_ip type
		 * 1-1-C2后台充值接口IP验证，2-营帐登录IP验证;
		 */
		public static final int AUTH_WHITE_IP_TYPE_CCIC2 = 1;
		public static final int AUTH_WHITE_IP_TYPE_LOGIN_BOSS = 2;
		
		public static final String[] TITLE_BIZ_CHANGE = new String[]{"编号","变更日期","公司名称","热线号码","目的码","原电脑座席数","变更后电脑座席数","座席增减",
			"原座席单价","变更后座席单价","平台","业务状态","所属销售"};
		
		public static final String[] CONTENT_BIZ_CHANGE = new String[]{"strUpdateTime","enterpriseName","hotline","trunk","strOldValue","strNewValue","strClientChange",
			"oldRent","newRent","ccicName","bizStatusDesc","directManager"};
		
		public static final String[] TITLE_OPEN_BIZ = new String[]{"编号","开户日期","公司名称","热线号码","目的码",
			"座席数","座席单价","平台","业务状态","所属销售"};
		
		public static final String[] CONTENT_OPEN_BIZ = new String[]{"strCreateTime","enterpriseName","hotline","trunk",
			"newClient","newRent","ccicName","bizStatusDesc","directManager"};
		
		public static final String[] TITLE_LOGOUT = new String[]{"编号","销户日期","开户日期","公司名称","热线号码","目的码",
			"座席数","座席单价","平台","所属销售","销户原因"};
		
		public static final String[] CONTENT_LOGOUT = new String[]{"strUpdateTime","openBizTime","enterpriseName","hotline","trunk",
			"oldClient","oldRent","ccicName","directManager","comment"};
		
		public static final String[] TITLE_BUSINESSCHANGE = new String[]{"编号","变更日期","公司名称","热线号码","目的码",
			"变更项","变更前值","变更后值","平台","业务状态","所属销售"};
		
		public static final String[] CONTENT_BUSINESSCHANGE = new String[]{"strUpdateTime","enterpriseName","hotline","trunk",
			"typeDesc","oldValue","newValue","ccicName","bizStatusDesc","directManager"};
		
		// 导出充值单的表头充值单信息对应字段
		public static final String[] TITLE_CHARGEORDER = new String[]{"序号","客户名称","主热线号码","充值单类型","流动金额（元）", 
			"状态", "充值类型", "备注", "直销经理", "提交时间", "充值时间", "发票抬头", "发票联系人","联系电话","email","邮寄地址"};
		
		public static final String[] CONTENT_CHARGEORDER = new String[]{"enterpriseName","enterpriseHotline","rechargeName","money",
			"statusDesc","enterpriseFlagDesc","comment","directManagerName","createTime","updateTime"};
		
		public static final String[] CONTENT_CHARGEORDER_NEW = new String[]{"fullName","hotline","typeDesc","money",
			"statusDesc","enterpriseFlagDesc","remark","entityName","createTime","updateTime"};
		
		// 导出充值单的表头发票信息对应字段
		public static final String[] CONTENT_INVOICE = new String[]{"invoiceName", "company", "tel", "email", "sendAddress"};
		
		// 导出外显号码信息对应表头及字段
		public static final String[] TITLE_ENTERPRISE_NUMBER = new String[]{"序号","企业ID","客户名称","主热线号码","所属部门","所属销售","外显号码",
			"号码类型", "号码状态", "座席数量", "座席所在地", "所属行业", "所属平台", "运营商", "法人代表", "联系地址", "负责人姓名/手机/邮箱", "付款人姓名/手机/邮箱"};
		
		public static final String[] CONTENT_ENTERPRISE_NUMBER = new String[]{"enterpriseId", "fullName", "masterHotline", "directSectionName", "entityParentName",
			"entireNumber", "typeDesc", "statusDesc", "clientCount", "clientArea", "trade", "ccicName", "carrier", "legalPerson", "address", "principalInfo", "payerInfo"};
		
		/*
		 * Announce type definition
		 */
		public static final int ANNOUNCE_TYPE_NOTICE = 1;
		public static final int ANNOUNCE_TYPE_FAQ = 2;
		
		public static final int ENTITY_READONLY_TRUE = 1;
		public static final int ENTITY_READONLY_FALSE = 0;
		/*
		 * min_rate type definition
		 * 类型 1.月租，2外呼，3呼入,4最低消费,5 短信费率
		 */
		public static final int MIN_RATE_TYPE_WEB_RENT = 1;
		public static final int MIN_RATE_TYPE_OB = 2;
		public static final int MIN_RATE_TYPE_IB = 3;
		public static final int MIN_RATE_TYPE_LOWEST_COST = 4;
		public static final int MIN_RATE_TYPE_SMS = 5;
		
		public static final String ACCESSORY_UPLOAD_EXTENSION = ".jpg.JPG.Jpg.jpeg.pdf.PDF.png.gif.GIF.tif.xls.xlsx.doc.docx.zip.rar";
		
		/*
		 * credit_order type definition
		 * 类型 0.临时信用 1.固定信用
		 */
		public static final int CREDIT_TYPE_TEMP = 0;
		public static final int CREDIT_TYPE_FIXED = 1;
		/*
		 * credit_order status definition
		 * 类型 1,未提交,2,未审批,3审批中,4审批失败，5审批通过
		 */
		public static final int CREDIT_ORDER_STATUS_UNCOMMIT = 1;
		public static final int CREDIT_ORDER_STATUS_UNAPPROVAL = 2;
		public static final int CREDIT_ORDER_STATUS_APPROVALING = 3;
		public static final int CREDIT_ORDER_STATUS_APPROVALFAIL = 4;
		public static final int CREDIT_ORDER_STATUS_APPROVALSUCCESS = 5;
		
		
		public static final int ENTERPRISE_FLAG_NEW = 0;
		public static final int ENTERPRISE_FLAG_OLD = 1;
		
		public static final int COMOBO_PERIOD_MONTH = 1;
		public static final int COMOBO_PERIOD_YEAR = 2;
		
		public static final int COMOBO_VALID_OFF = 0;
		public static final int COMOBO_VALID_ON = 1;
		
		public static final int NUMBER_INUSE_NUMBER400 = 1;
		public static final int NUMBER_INUSE_DIRECT = 2;
		/*
		 * 手机虚拟号码状态，1-未使用 2-使用中
		 */
		public static final int MOBILE_STATUS_UNUSE = 1;
		public static final int MOBILE_STATUS_INUSE = 2;
		/*
		 * 手机虚拟是否被标记，0-未标记  1-已标记
		 */
		public static final int MOBILE_ISSIGN_NO = 0;
		public static final int MOBILE_ISSIGN_YES = 1;
		
		/*
		 * business.productType，0-未标记  1-已标记
		 */
		public static final int BUSINESS_PRODUCT_TYPE_NORMAL = 1;
		public static final int BUSINESS_PRODUCT_TYPE_400 = 2;
		public static final int BUSINESS_PRODUCT_TYPE_OBCOMBO = 3;
		public static final int BUSINESS_PRODUCT_TYPE_ZFJ = 4;
		public static final int BUSINESS_PRODUCT_TYPE_OTHER = 5;
		
		public static final int BUSINESS_IS_IDD_YES = 1;
		public static final int BUSINESS_IS_IDD_NO = 0;
		
		public static final String NO_ENTERPRISE_TEMP_HOTLINE = "none";
		
		public static final String LANDLINE_VALIDATION_3 = "^010\\d{8}$|^02\\d{9}$";
		public static final String LANDLINE_VALIDATION_4 = "^0[3,4,5,6,7,8,9][0-9]\\d{8,9}$";
		
		public static Map<String, String> APP_KEY;
	    static {
	    	APP_KEY = new HashMap<String, String>();
	    	APP_KEY.put("SDK-TINET-0001", "724b15c6-3018-4f7c-9fc0-604ac81966d4");
	    }
}
