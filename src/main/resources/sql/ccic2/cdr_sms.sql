
CREATE TABLE cdr_sms_template
(
  id integer NOT NULL DEFAULT nextval('cdr_sms_id_seq'),
  sms_type integer NOT NULL, 
  hotline character varying, 
  cno character varying,
  customer_name character varying,
  unique_id character varying,
  mobile character varying,
  content character varying,
  result integer NOT NULL,
  cost numeric(38,3) NOT NULL,
  create_time timestamp with time zone DEFAULT now(),
  CONSTRAINT cdr_sms_template_pkey PRIMARY KEY (id)
)
WITHOUT OIDS;
ALTER TABLE cdr_sms_template OWNER TO postgres;
GRANT ALL ON TABLE cdr_sms_template TO postgres;
COMMENT ON TABLE cdr_sms_template IS '短信cdr表';
COMMENT ON COLUMN cdr_sms_template.id IS 'id';
COMMENT ON COLUMN cdr_sms_template.sms_type IS '短信类型 1:网上400留言 2:企业未接电话 3:企业尾巴短信 4:企业周报 5:访客短信 6:提醒企业 7:告警企业 8:短信管理发送短信 9:企业已接短信 10:企业月报 11:企业接收短信 12:座席发送短信';
COMMENT ON COLUMN cdr_sms_template.hotline IS '企业400号码';
COMMENT ON COLUMN cdr_sms_template.cno IS '座席号';
COMMENT ON COLUMN cdr_sms_template.customer_name IS '手机号对应人 sms_type=11时无效';
COMMENT ON COLUMN cdr_sms_template.unique_id IS '对应cdr表中unique_id字段，sms_type=2,3,9中索引到对应cdr sms_type=11时无效';
COMMENT ON COLUMN cdr_sms_template.mobile IS '手机号码';
COMMENT ON COLUMN cdr_sms_template.content IS '短信内容';
COMMENT ON COLUMN cdr_sms_template.result IS '发送结果 sms_type=11时无效';
COMMENT ON COLUMN cdr_sms_template.cost IS '费用 sms_type=11时无效';
COMMENT ON COLUMN cdr_sms_template.create_time IS '创建时间';


CREATE INDEX cdr_sms_template_unique_id_index
  ON cdr_sms_template
  USING btree
  (unique_id);



CREATE INDEX cdr_sms_template_mobile_index
  ON cdr_sms_template
  USING btree
  (mobile);



CREATE INDEX cdr_sms_template_create_time_index
  ON cdr_sms_template
  USING btree
  (create_time);