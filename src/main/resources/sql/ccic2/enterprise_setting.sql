INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'customer_share_mode','1','企业对客户资料权限的管理模式');
	
INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'is_call_failed_msg_send','0','未接来电短信提醒');
	
INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'is_call_success_msg_send','0','已接来电短信提醒');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'is_tail_msg_send','0','客户挂机留存信息');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'auto_investigation','0','是否开启自动满意度调查');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'logout_with_tel', '0', '是否开启座席退出时同时退出电话');
	
INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'ob_missed_call_alert', '0', '是否开启未接来电提示框');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'ob_twice_confirm', '0', '是否开启外呼2次确认');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'last_tel_info', '0','是否开启最近电话信息');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'ob_knowledge_sidebar_show', '0','是否开启外呼知识库侧边栏显示');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'auto_login_tel', '0','是否开启座席自动登录电话');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'is_ivr_lock', '0','ivr锁定 0不锁 1锁');

INSERT INTO enterprise_setting(enterprise_id, name, value, property)
	VALUES (enterprise_id_template,'curl_level', '0','curl级别');
	