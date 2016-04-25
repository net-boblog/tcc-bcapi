INSERT INTO enterprise_pause_customize(enterprise_id, pause_status, is_rest, is_default, sort)
	VALUES(enterprise_id_template,'忙碌', 0, 1, 1);

INSERT INTO enterprise_pause_customize(enterprise_id, pause_status, is_rest, is_default, sort)
	VALUES(enterprise_id_template,'勿打扰', 0, 0, 2);

INSERT INTO enterprise_pause_customize(enterprise_id, pause_status, is_rest, is_default, sort)
	VALUES(enterprise_id_template,'小休', 1, 0, 3);

INSERT INTO enterprise_pause_customize(enterprise_id, pause_status, is_rest, is_default, sort)
	VALUES(enterprise_id_template,'午饭', 1, 0, 4);

INSERT INTO enterprise_pause_customize(enterprise_id, pause_status, is_rest, is_default, sort)
	VALUES(enterprise_id_template,'WC', 1, 0, 5);