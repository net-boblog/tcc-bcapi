
CREATE TABLE cdr_ib_template
(
  id integer NOT NULL DEFAULT nextval('cdr_id_seq'),
  unique_id character varying, 
  hotline character varying, 
  number_trunk character varying,
  customer_number character varying,
  customer_number_type integer,
  customer_area_code character varying, 
  customer_province character varying,
  customer_crm_id character varying,
  customer_city character varying,
  customer_vip integer DEFAULT 0,
  client_number character varying, 
  client_area_code character varying, 
  client_name character varying,
  client_crm_id character varying,
  cno character varying,
  exten character varying,
  start_time bigint,
  answer_time bigint,
  join_queue_time bigint,
  bridge_time bigint,
  end_time bigint,
  bill_duration integer DEFAULT 0,
  bridge_duration integer DEFAULT 0,
  total_duration integer DEFAULT 0,
  cost numeric(38,3) NOT NULL,
  ivr_id integer,
  ivr_name character varying,
  ivr_flow character varying,
  queue_name character varying, 
  record_file character varying, 
  score integer DEFAULT 0,
  score_comment character varying,
  in_case_lib integer DEFAULT 0,
  call_type integer NOT NULL,
  status integer NOT NULL,
  mark integer DEFAULT 0,
  mark_data character varying,
  end_reason integer DEFAULT 0,
  gw_ip character varying, 
  processed integer DEFAULT 0,
  process_comment character varying,
  user_field character varying,
  sip_cause integer DEFAULT 0,
  create_time timestamp with time zone DEFAULT now(),
  CONSTRAINT cdr_ib_template_pkey PRIMARY KEY (id),
  CONSTRAINT cdr_ib_template_unique_id_key UNIQUE (unique_id) 
)
WITHOUT OIDS;
ALTER TABLE cdr_ib_template OWNER TO postgres;
COMMENT ON TABLE cdr_ib_template IS '通话记录表';
COMMENT ON COLUMN cdr_ib_template.id IS '流水号';
COMMENT ON COLUMN cdr_ib_template.unique_id IS '电话唯一标示';
COMMENT ON COLUMN cdr_ib_template.hotline IS '热线号码';
COMMENT ON COLUMN cdr_ib_template.number_trunk IS '中继号码';
COMMENT ON COLUMN cdr_ib_template.customer_number IS '主叫号码';
COMMENT ON COLUMN cdr_ib_template.customer_number_type IS '主叫号码类型1固话 2手机';
COMMENT ON COLUMN cdr_ib_template.customer_area_code IS '主叫地区';
COMMENT ON COLUMN cdr_ib_template.customer_province IS '主叫省份';
COMMENT ON COLUMN cdr_ib_template.customer_city IS '主叫地区城市';
COMMENT ON COLUMN cdr_ib_template.customer_crm_id IS '主叫在crm中的id';
COMMENT ON COLUMN cdr_ib_template.customer_vip IS '客户vip级别 大于0为vip';
COMMENT ON COLUMN cdr_ib_template.client_number IS '座席号码';
COMMENT ON COLUMN cdr_ib_template.client_area_code IS '座席区号';
COMMENT ON COLUMN cdr_ib_template.client_name IS '座席名称';
COMMENT ON COLUMN cdr_ib_template.client_crm_id IS '座席在crm中的id';
COMMENT ON COLUMN cdr_ib_template.start_time IS '客户进入系统时间';
COMMENT ON COLUMN cdr_ib_template.answer_time IS '系统接听时间';
COMMENT ON COLUMN cdr_ib_template.join_queue_time IS '进入queue时间';
COMMENT ON COLUMN cdr_ib_template.bridge_time IS '座席应答时间';
COMMENT ON COLUMN cdr_ib_template.end_time IS '结束时间';
COMMENT ON COLUMN cdr_ib_template.bill_duration IS '计费时长';
COMMENT ON COLUMN cdr_ib_template.bridge_duration IS '座席接听时长';
COMMENT ON COLUMN cdr_ib_template.total_duration IS '总通话时长';
COMMENT ON COLUMN cdr_ib_template.cost IS '费用';
COMMENT ON COLUMN cdr_ib_template.ivr_id IS 'IVR资源id';
COMMENT ON COLUMN cdr_ib_template.ivr_name IS 'IVR资源名称';
COMMENT ON COLUMN cdr_ib_template.ivr_flow IS 'IVR流程格式为 ivrId1|path1|action1|start_time1|end_time1,ivrId2|path2|action2|start_time2|end_time2 系统设计最多经过ivr节点数为100次';
COMMENT ON COLUMN cdr_ib_template.queue_name IS '队列名';
COMMENT ON COLUMN cdr_ib_template.cno IS '座席名称';
COMMENT ON COLUMN cdr_ib_template.exten IS '如果呼叫的分机，存入分机号';
COMMENT ON COLUMN cdr_ib_template.record_file IS '录音文件，文件名保存相对路径';
COMMENT ON COLUMN cdr_ib_template.score IS '录音质检打分 0为未打分';
COMMENT ON COLUMN cdr_ib_template.score_comment IS '打分备注';
COMMENT ON COLUMN cdr_ib_template.in_case_lib IS '是否在案例库中0:不在 1:在';
COMMENT ON COLUMN cdr_ib_template.call_type IS '1:呼入 2:web400呼入';
COMMENT ON COLUMN cdr_ib_template.status IS '1:座席接听 2:已呼叫座席，座席未接听 3:系统接听 4:系统未接-IVR配置错误 5:系统未接-停机 6:系统未接-欠费 7:系统未接-黑名单 8:系统未接-未注册 9:系统未接-彩铃 10:网上400未接受 11:系统未接-呼叫超出营帐中设置的最大限制 12:系统未接-客户呼入系统后在系统未应答前挂机 13:其他错误';
COMMENT ON COLUMN cdr_ib_template.mark IS '标记 1:留言 2:转移 3:咨询 4:三方 5:传真接收 6:会议 7:交互 8:AMD 9:已进入IVR 10:未进入IVR 11:队列中放弃  12:队列中溢出 13:预约  14 满意度调查';
COMMENT ON COLUMN cdr_ib_template.mark_data IS '标记的数据：留言箱id;转移的数据0,电话号码/1,座席号/2,分机号/3,ivrId_nodePath;咨询的数据0,电话号码/1,座席号/2,分机号。支持多组用逗号分隔;传真接收结果1成功2失败;交互的数据ivrId_nodePath';
COMMENT ON COLUMN cdr_ib_template.gw_ip IS '网关ip';
COMMENT ON COLUMN cdr_ib_template.end_reason IS '接听之后的挂机原因 1000:客户挂机 1001:座席挂机 1002:被强拆 1003:系统挂机（没有bridge）1-127:其他挂机原因';
COMMENT ON COLUMN cdr_ib_template.processed IS '是否处理过 0:未处理 1:已处理';
COMMENT ON COLUMN cdr_ib_template.process_comment IS '处理注释';
COMMENT ON COLUMN cdr_ib_template.user_field IS '用户自定义字段';
COMMENT ON COLUMN cdr_ib_template.sip_cause IS 'SIP响应码：200 ~ 699';
COMMENT ON COLUMN cdr_ib_template.create_time IS '记录创建时间';


CREATE INDEX cdr_ib_template_hotline_index
  ON cdr_ib_template
  USING btree
  (hotline);



CREATE INDEX cdr_ib_template_customer_number_index
  ON cdr_ib_template
  USING btree
  (customer_number varchar_pattern_ops);



CREATE INDEX cdr_ib_template_cno_index
  ON cdr_ib_template
  USING btree
  (cno);


CREATE INDEX cdr_ib_template_start_time_index
  ON cdr_ib_template
  USING btree
  (start_time);



CREATE INDEX cdr_ib_template_ivr_id_index
  ON cdr_ib_template
  USING btree
  (ivr_id);

CREATE INDEX cdr_ib_template_queue_name_index
  ON cdr_ib_template
  USING btree
  (queue_name);
  
