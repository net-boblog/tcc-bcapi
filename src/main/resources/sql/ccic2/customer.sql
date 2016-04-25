
CREATE TABLE customer_template
(
  id serial NOT NULL,
  name character varying DEFAULT ''::character varying,
  crm_id character varying,
  ib_count integer NOT NULL DEFAULT 0,
  ob_count integer NOT NULL DEFAULT 0,
  sex integer,
  birth character varying,
  department character varying,
  trade character varying,
  position character varying,
  mobile1 character varying,
  mobile2 character varying,
  tel1 character varying,
  tel2 character varying,
  ptel character varying,
  other_tel character varying,
  email character varying,
  fax character varying,
  im character varying,
  company character varying,
  country character varying,
  post character varying,
  province character varying,
  city character varying,
  address character varying,
  create_time timestamp with time zone DEFAULT now(),
  modify_time timestamp with time zone DEFAULT now(),
  last_mender character varying,
  creator character varying,
  description character varying,
  level integer NOT NULL DEFAULT 0,
  status character varying,
  share_type integer DEFAULT 1,
  owner character varying,
  company_url character varying,
  customer_src character varying,
  user_field1 character varying,
  user_field2 character varying,
  user_field3 character varying,
  user_field4 character varying,
  user_field5 character varying,
  user_field6 character varying,
  user_field7 character varying,
  user_field8 character varying,
  user_field9 character varying,
  user_field10 character varying,
  CONSTRAINT customer_template_pkey PRIMARY KEY (id)
 )
WITHOUT OIDS;
ALTER TABLE customer_template OWNER TO postgres;
COMMENT ON TABLE customer_template IS '客户信息表';
COMMENT ON COLUMN customer_template.id IS 'id标识';
COMMENT ON COLUMN customer_template.name IS '姓名';
COMMENT ON COLUMN customer_template.crm_id IS '对应第三方系统crm_id';
COMMENT ON COLUMN customer_template.ib_count IS '呼入次数';
COMMENT ON COLUMN customer_template.ob_count IS '呼出次数';
COMMENT ON COLUMN customer_template.sex IS '客户性别';
COMMENT ON COLUMN customer_template.birth IS '生日';
COMMENT ON COLUMN customer_template.department IS '部门';
COMMENT ON COLUMN customer_template.trade IS '所属行业';
COMMENT ON COLUMN customer_template.position IS '职务';
COMMENT ON COLUMN customer_template.mobile1 IS '手机1';
COMMENT ON COLUMN customer_template.mobile2 IS '手机2';
COMMENT ON COLUMN customer_template.tel1 IS '电话1';
COMMENT ON COLUMN customer_template.tel2 IS '电话2';
COMMENT ON COLUMN customer_template.ptel IS '家庭电话';
COMMENT ON COLUMN customer_template.other_tel IS '其他联系方式';
COMMENT ON COLUMN customer_template.email IS 'Email 多个以逗号分隔';
COMMENT ON COLUMN customer_template.fax IS '传真';
COMMENT ON COLUMN customer_template.im IS '客户IMQQMSN';
COMMENT ON COLUMN customer_template.company IS '公司名称';
COMMENT ON COLUMN customer_template.country IS '所在国家';
COMMENT ON COLUMN customer_template.post IS '邮编';
COMMENT ON COLUMN customer_template.province IS '所在省市';
COMMENT ON COLUMN customer_template.city IS '所在城市';
COMMENT ON COLUMN customer_template.address IS '家庭地址';
COMMENT ON COLUMN customer_template.create_time IS '加入时间';
COMMENT ON COLUMN customer_template.modify_time IS '最近修改时间';
COMMENT ON COLUMN customer_template.last_mender IS '修改人';
COMMENT ON COLUMN customer_template.creator IS '创建人';
COMMENT ON COLUMN customer_template.description IS '备注';
COMMENT ON COLUMN customer_template.level IS '客户等级 打开队列VIP功能会按照等级排队';
COMMENT ON COLUMN customer_template.status IS '客户分类';
COMMENT ON COLUMN customer_template.share_type IS '共享类型 1:共享 2:组内共享 3:座席独享';
COMMENT ON COLUMN customer_template.owner IS '客户所有人 share_type=1此字段无意义 share_type=2此字段为qno share_type=3此字段为cno';
COMMENT ON COLUMN customer_template.company_url IS '公司网址';
COMMENT ON COLUMN customer_template.company_url IS '公司网址';
COMMENT ON COLUMN customer_template.customer_src IS '电话来源 电视/网络/广播/报刊/其他';
COMMENT ON COLUMN customer_template.user_field1 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field2 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field3 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field4 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field5 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field6 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field7 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field8 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field9 IS '自定义字段';
COMMENT ON COLUMN customer_template.user_field10 IS '自定义字段';

CREATE INDEX customer_template_mobile1_index
  ON customer_template
  USING btree
  (mobile1 varchar_pattern_ops);

CREATE INDEX customer_template_reverse_mobile1_index
  ON customer_template
  USING btree
  (reverse(mobile1::text) varchar_pattern_ops);

CREATE INDEX customer_template_tel1_index
  ON customer_template
  USING btree
  (tel1 varchar_pattern_ops);

CREATE INDEX customer_template_reverse_tel1_index
  ON customer_template
  USING btree
  (reverse(tel1::text) varchar_pattern_ops);

CREATE INDEX customer_template_create_time_index
  ON customer_template
  USING btree
  (create_time);
  
  CREATE INDEX customer_template_modify_time_index
  ON customer_template
  USING btree
  (modify_time);
  
  CREATE INDEX customer_template_mobile2_index
  ON customer_template
  USING btree
  (mobile2 COLLATE pg_catalog."default" varchar_pattern_ops);
  
  CREATE INDEX customer_template_reverse_mobile2_index
  ON customer_template
  USING btree
  (reverse(mobile2::text) COLLATE pg_catalog."default" varchar_pattern_ops);
  
  CREATE INDEX customer_template_other_tel_index
  ON customer_template
  USING btree
  (other_tel COLLATE pg_catalog."default" varchar_pattern_ops);
  
  CREATE INDEX customer_template_reverse_other_tel_index
  ON customer_template
  USING btree
  (reverse(other_tel::text) COLLATE pg_catalog."default" varchar_pattern_ops);
  
  CREATE INDEX customer_template_ptel_index
  ON customer_template
  USING btree
  (ptel COLLATE pg_catalog."default" varchar_pattern_ops);
  
  CREATE INDEX customer_template_reverse_ptel_index
  ON customer_template
  USING btree
  (reverse(ptel::text) COLLATE pg_catalog."default" varchar_pattern_ops);
  
  CREATE INDEX customer_template_tel2_index
  ON customer_template
  USING btree
  (tel2 COLLATE pg_catalog."default" varchar_pattern_ops);
  
  CREATE INDEX customer_template_reverse_tel2_index
  ON customer_template
  USING btree
  (reverse(tel2::text) COLLATE pg_catalog."default" varchar_pattern_ops);