
CREATE TABLE cdr_ib_detail_template
(
  id integer NOT NULL DEFAULT nextval('cdr_id_seq'),
  unique_id character varying, 
  main_unique_id character varying,
  callee_number character varying, 
  client_name character varying,
  cno character varying,
  exten character varying,
  start_time bigint,
  answer_time bigint,
  end_time bigint,
  bill_duration integer DEFAULT 0,
  total_duration integer DEFAULT 0,
  cost numeric(38,3) NOT NULL,
  call_type integer NOT NULL,
  status integer NOT NULL,
  gw_ip character varying,
  end_reason integer DEFAULT 0,
  sip_cause integer DEFAULT 0,
  mark character varying,
  record_file character varying,
  create_time timestamp with time zone DEFAULT now(),
  CONSTRAINT cdr_ib_detail_template_pkey PRIMARY KEY (id),
  CONSTRAINT cdr_ib_detail_template_unique_id_key UNIQUE (unique_id) 
)
WITHOUT OIDS;
ALTER TABLE cdr_ib_detail_template OWNER TO postgres;
COMMENT ON TABLE cdr_ib_detail_template IS '呼入通话记录详细表，记录呼转座席、咨询、转移、三方、监听、强插（拦截）、耳语';
COMMENT ON COLUMN cdr_ib_detail_template.id IS '流水号';
COMMENT ON COLUMN cdr_ib_detail_template.unique_id IS '电话唯一标示';
COMMENT ON COLUMN cdr_ib_detail_template.main_unique_id IS '主通道标识';
COMMENT ON COLUMN cdr_ib_detail_template.callee_number IS '呼叫的号码，可能是座席也可能是普通电话';
COMMENT ON COLUMN cdr_ib_detail_template.client_name IS '如果是座席此字段保存座席名称';
COMMENT ON COLUMN cdr_ib_detail_template.cno IS '如果是座席此字段保存座席号';
COMMENT ON COLUMN cdr_ib_detail_template.exten IS '如果呼叫了分机，此字段保存分机号';
COMMENT ON COLUMN cdr_ib_detail_template.start_time IS '开始呼叫时间';
COMMENT ON COLUMN cdr_ib_detail_template.answer_time IS '接听时间';
COMMENT ON COLUMN cdr_ib_detail_template.end_time IS '结束时间';
COMMENT ON COLUMN cdr_ib_detail_template.bill_duration IS '计费时长';
COMMENT ON COLUMN cdr_ib_detail_template.total_duration IS '总时长';
COMMENT ON COLUMN cdr_ib_detail_template.cost IS '费用';
COMMENT ON COLUMN cdr_ib_detail_template.call_type IS '101:呼转座席 102:转移 103:咨询 104:三方 105:监听 106:耳语 107:强插（拦截） 108:抢线';
COMMENT ON COLUMN cdr_ib_detail_template.status IS '1:呼叫失败 2:接听';
COMMENT ON COLUMN cdr_ib_detail_template.gw_ip IS '网关ip';
COMMENT ON COLUMN cdr_ib_detail_template.end_reason IS '接听之后的挂机原因 1000:客户挂机 1001:座席挂机 1002:被强拆 1003:系统挂机（没有bridge）1-127:其他挂机原因';
COMMENT ON COLUMN cdr_ib_detail_template.sip_cause IS 'SIP响应码：200 ~ 699';
COMMENT ON COLUMN cdr_ib_detail_template.mark IS '备注 用于存储信息，比如是否主叫记忆 1呼入主叫记忆 2外呼主叫记忆';
COMMENT ON COLUMN cdr_ib_detail_template.record_file IS '呼叫手机录音';
COMMENT ON COLUMN cdr_ib_detail_template.create_time IS '记录创建时间';

CREATE INDEX cdr_ib_detail_template_main_unique_id_index
  ON cdr_ib_detail_template
  USING btree
  (main_unique_id);



CREATE INDEX cdr_ib_detail_template_callee_number_index
  ON cdr_ib_detail_template
  USING btree
  (callee_number);



CREATE INDEX cdr_ib_detail_template_cno_index
  ON cdr_ib_detail_template
  USING btree
  (cno);



CREATE INDEX cdr_ib_detail_template_start_time_index
  ON cdr_ib_detail_template
  USING btree
  (start_time);
  