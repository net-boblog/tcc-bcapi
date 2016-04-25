CREATE TABLE task_inventory_template
(
  id integer NOT NULL,
  task_id integer NOT NULL,
  file_id integer,
  customer_name character varying, 
  customer_id integer,
  tel character varying, 
  tel_type integer,
  tel_area_code character varying,
  owner character varying, 
  status integer DEFAULT 0, 
  retry integer DEFAULT 0,
  cno character varying,
  client_name character varying,
  client_crm_id character varying,
  start_time timestamp with time zone,
  end_time timestamp with time zone,
  bridge_duration integer NOT NULL, 
  main_unique_id character varying, 
  business_result character varying,
  is_valid integer,
  num_status integer,
  description character varying,
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
  create_time timestamp with time zone DEFAULT now(),
  ivr_id integer,
  tel_id character varying,
  CONSTRAINT task_inventory_template_pkey PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE task_inventory_template OWNER TO postgres;
COMMENT ON TABLE task_inventory_template IS '外呼任务明细表';
COMMENT ON COLUMN task_inventory_template.id IS 'id标识';
COMMENT ON COLUMN task_inventory_template.task_id IS '关联任务id';
COMMENT ON COLUMN task_inventory_template.file_id IS '对应task_file的id';
COMMENT ON COLUMN task_inventory_template.customer_name IS '客户名称';
COMMENT ON COLUMN task_inventory_template.customer_id IS '电话对应customer表中客户id';
COMMENT ON COLUMN task_inventory_template.tel_area_code IS '电话所属区号';
COMMENT ON COLUMN task_inventory_template.tel IS '电话号码';
COMMENT ON COLUMN task_inventory_template.tel_type IS '电话类型1固话 2手机';
COMMENT ON COLUMN task_inventory_template.tel_area_code IS '电话所在地区';
COMMENT ON COLUMN task_inventory_template.owner IS '预览外呼分配的座席号';
COMMENT ON COLUMN task_inventory_template.status IS '呼叫结果：0-未执行，1,5,6,7,8,9-忙/拒接/空号/关机/暂时无法接听/停机(预览外呼 客户空号/拥塞|预测外呼客户空号/拥塞)，2-超时未应答(预览外呼 客户未应答|预测外呼 客户未应答)，3-已应答(预测外呼 客户已应答)，4-已通话(预览外呼 客户已和座席通话|预测外呼 客户已和座席通话),10-应答机已应答(预测外呼 客户已应答)';
COMMENT ON COLUMN task_inventory_template.retry IS '此号码呼叫次数';
COMMENT ON COLUMN task_inventory_template.cno IS '座席工号';
COMMENT ON COLUMN task_inventory_template.client_name IS '座席名';
COMMENT ON COLUMN task_inventory_template.client_crm_id IS '座席crmid';
COMMENT ON COLUMN task_inventory_template.start_time IS '呼叫开始时间';
COMMENT ON COLUMN task_inventory_template.end_time IS '结束时间';
COMMENT ON COLUMN task_inventory_template.bridge_duration IS '通话时长';
COMMENT ON COLUMN task_inventory_template.main_unique_id IS '如果接通对应的cdr表中unique_id';
COMMENT ON COLUMN task_inventory_template.business_result IS '业务结果，企业可自定义取值，初始化取值：0-无人接，1-不感兴趣，2-感兴趣，3-已成交。';
COMMENT ON COLUMN task_inventory_template.is_valid IS '是否有效，1：有效，0：无效';
COMMENT ON COLUMN task_inventory_template.num_status IS '号码状态：0-未处理，1-待后续，2-完成';
COMMENT ON COLUMN task_inventory_template.description IS '备注';
COMMENT ON COLUMN task_inventory_template.user_field1 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field2 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field3 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field4 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field5 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field6 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field7 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field8 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field9 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.user_field10 IS '自定义字段，字段类型为select时，取值为value_info的record_value';
COMMENT ON COLUMN task_inventory_template.create_time IS '任务生成时间';
COMMENT ON COLUMN task_inventory_template.ivr_id IS '是呼叫这个号码后要转接到哪个ivr的id';
COMMENT ON COLUMN task_inventory_template.tel_id IS '是新增的每个电话号码的唯一标识';


CREATE INDEX task_inventory_template_task_id_index
  ON task_inventory_template
  USING btree
  (task_id);



CREATE INDEX task_inventory_template_tel_index
  ON task_inventory_template
  USING btree
  (tel);




CREATE INDEX task_inventory_template_start_time_index
  ON task_inventory_template
  USING btree
  (start_time);



CREATE INDEX task_inventory_template_main_unique_id_index
  ON task_inventory_template
  USING btree
  (main_unique_id);
  
CREATE INDEX task_inventory_template_cno_index
  ON task_inventory_template
  USING btree
  (cno);