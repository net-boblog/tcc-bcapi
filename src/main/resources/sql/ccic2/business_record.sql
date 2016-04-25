
CREATE TABLE business_record_template
(
  id serial NOT NULL, 
  unique_id character varying, 
  enterprise_id integer NOT NULL, 
  customer_id integer NOT NULL, 
  call_type integer,
  business_type character varying, 
  business_level character varying, 
  business_status character varying, 
  subject character varying, 
  description character varying, 
  relation character varying, 
  creator character varying, 
  create_time timestamp with time zone DEFAULT now(), 
  modify_time timestamp with time zone DEFAULT now(), 
  last_mender character varying,
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
  user_field11 character varying,
  user_field12 character varying,
  user_field13 character varying,
  user_field14 character varying,
  user_field15 character varying,
  wo_flag integer DEFAULT 0,
  CONSTRAINT business_record_template_pkey PRIMARY KEY (id)
)
WITHOUT OIDS;
ALTER TABLE business_record_template OWNER TO postgres;
COMMENT ON TABLE business_record_template IS '业务记录';
COMMENT ON COLUMN business_record_template.id IS 'id标识';
COMMENT ON COLUMN business_record_template.unique_id IS '对应呼叫惟一标识';
COMMENT ON COLUMN business_record_template.enterprise_id IS '企业id';
COMMENT ON COLUMN business_record_template.customer_id IS '客户编号';
COMMENT ON COLUMN business_record_template.call_type IS '业务记录对应通话记录类型 1:呼入. 2:呼出';
COMMENT ON COLUMN business_record_template.business_type IS '业务类型';
COMMENT ON COLUMN business_record_template.business_level IS '业务优先级';
COMMENT ON COLUMN business_record_template.business_status IS '业务处理结果状态';
COMMENT ON COLUMN business_record_template.subject IS '业务处理标题';
COMMENT ON COLUMN business_record_template.description IS '业务记录描述';
COMMENT ON COLUMN business_record_template.relation IS '业务记录关联　自定义';
COMMENT ON COLUMN business_record_template.creator IS '业务记录创建人';
COMMENT ON COLUMN business_record_template.create_time IS '加入时间';
COMMENT ON COLUMN business_record_template.modify_time IS '最近修改时间';
COMMENT ON COLUMN business_record_template.last_mender IS '修改人';
COMMENT ON COLUMN business_record_template.user_field1 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field2 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field3 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field4 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field5 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field6 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field7 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field8 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field9 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field10 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field11 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field12 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field13 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field14 IS '自定义字段';
COMMENT ON COLUMN business_record_template.user_field15 IS '自定义字段';
COMMENT ON COLUMN business_record_template.wo_flag IS '工单标识  0（默认）:非工单不流转  1：工单流转中 2：工单结束流转';


CREATE INDEX business_record_template_customer_id_index
  ON business_record_template
  USING btree
  (customer_id);

CREATE INDEX business_record_template_create_time_index
  ON business_record_template
  USING btree
  (create_time );
  
  CREATE INDEX business_record_template_modify_time_index
  ON business_record_template
  USING btree
  (modify_time );