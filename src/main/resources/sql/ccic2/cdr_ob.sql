
CREATE TABLE cdr_ob_template
(
  id integer NOT NULL DEFAULT nextval('cdr_id_seq'),
  unique_id character varying, 
  number_trunk character varying,
  customer_number character varying, 
  customer_number_type integer,
  customer_area_code character varying, 
  customer_province character varying,
  customer_city character varying,
  customer_crm_id character varying,
  customer_vip integer DEFAULT 0,
  client_number character varying,
  client_area_code character varying,
  cno character varying,
  exten character varying,
  client_name character varying,
  client_crm_id character varying,
  start_time bigint,
  answer_time bigint,
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
  task_id integer,
  task_name character varying,
  call_type integer NOT NULL,
  status integer NOT NULL,
  mark integer DEFAULT 0,
  mark_data character varying,
  end_reason integer DEFAULT 0,
  gw_ip character varying, 
  user_field character varying,
  sip_cause integer DEFAULT 0,
  combo_cost numeric(8, 0) DEFAULT 0, 
  is_billing integer DEFAULT 1,
  create_time timestamp with time zone DEFAULT now(),
  CONSTRAINT cdr_ob_template_pkey PRIMARY KEY (id),
  CONSTRAINT cdr_ob_template_unique_id_key UNIQUE (unique_id) 
)
WITHOUT OIDS;
ALTER TABLE cdr_ob_template OWNER TO postgres;
COMMENT ON TABLE cdr_ob_template IS '通话记录表';
COMMENT ON COLUMN cdr_ob_template.id IS '流水号';
COMMENT ON COLUMN cdr_ob_template.unique_id IS '电话唯一标示';
COMMENT ON COLUMN cdr_ob_template.number_trunk IS '中继号码';
COMMENT ON COLUMN cdr_ob_template.customer_number IS '客户号码';
COMMENT ON COLUMN cdr_ob_template.customer_number_type IS '客户号码类型 1固话 2手机';
COMMENT ON COLUMN cdr_ob_template.customer_area_code IS '客户地区';
COMMENT ON COLUMN cdr_ob_template.customer_province IS '客户省份';
COMMENT ON COLUMN cdr_ob_template.customer_city IS '客户城市';
COMMENT ON COLUMN cdr_ob_template.customer_crm_id IS '客户crmid';
COMMENT ON COLUMN cdr_ob_template.customer_vip IS '客户vip级别 大于0为vip';
COMMENT ON COLUMN cdr_ob_template.client_number IS '座席号码';
COMMENT ON COLUMN cdr_ob_template.client_area_code IS '座席区号';
COMMENT ON COLUMN cdr_ob_template.cno IS '座席号';
COMMENT ON COLUMN cdr_ob_template.exten IS '如果是分机保存分机号';
COMMENT ON COLUMN cdr_ob_template.client_name IS '座席名称';
COMMENT ON COLUMN cdr_ob_template.client_crm_id IS '座席crmid';
COMMENT ON COLUMN cdr_ob_template.start_time IS '会话开始时间';
COMMENT ON COLUMN cdr_ob_template.answer_time IS '系统接听时间';
COMMENT ON COLUMN cdr_ob_template.bridge_time IS '座席应答时间';
COMMENT ON COLUMN cdr_ob_template.end_time IS '结束时间';
COMMENT ON COLUMN cdr_ob_template.bill_duration IS '计费时长';
COMMENT ON COLUMN cdr_ob_template.bridge_duration IS '座席接听时长';
COMMENT ON COLUMN cdr_ob_template.total_duration IS '总通话时长';
COMMENT ON COLUMN cdr_ob_template.cost IS '费用';
COMMENT ON COLUMN cdr_ob_template.ivr_id IS 'IVR资源id';
COMMENT ON COLUMN cdr_ob_template.ivr_name IS 'IVR资源名称';
COMMENT ON COLUMN cdr_ob_template.ivr_flow IS 'IVR流程格式为 ivrId1|path1|action1|start_time1|end_time1,ivrId2|path2|action2|start_time2|end_time2 系统设计最多经过ivr节点数为100次';
COMMENT ON COLUMN cdr_ob_template.queue_name IS '队列名';
COMMENT ON COLUMN cdr_ob_template.cno IS '座席号';
COMMENT ON COLUMN cdr_ob_template.record_file IS '录音文件，文件名保存相对路径';
COMMENT ON COLUMN cdr_ob_template.score IS '录音质检打分 0为未打分';
COMMENT ON COLUMN cdr_ob_template.score_comment IS '打分备注';
COMMENT ON COLUMN cdr_ob_template.in_case_lib IS '是否在案例库中0:不在 1:在';
COMMENT ON COLUMN cdr_ob_template.task_id IS '如果在外呼任务中存储外呼任务对应id';
COMMENT ON COLUMN cdr_ob_template.task_name IS '如果在外呼任务中存储外呼任务名';
COMMENT ON COLUMN cdr_ob_template.call_type IS '3:点击外呼 4:预览外呼 5:预测外呼 6:主叫外呼 7:自助录音 8:发送传真 9:内部呼叫 10:预约回呼';
COMMENT ON COLUMN cdr_ob_template.status IS '21:（点击外呼、预览外呼时）座席接听，客户未接听(超时) 22:（点击外呼、预览外呼时）座席接听，客户未接听(空号拥塞) 23:（预测外呼时）客户接听，座席未接听 24:（点击外呼、预览外呼时）座席未接听 25:（预测外呼时）客户未接听(超时) 26:（预测外呼时）客户未接听(空号拥塞) 27:（直接外呼时）座席呼入后，呼叫客户未接听 28:双方接听 ';
COMMENT ON COLUMN cdr_ob_template.mark IS '标记 2:咨询 3:转移 7:交互 14:满意度调查';
COMMENT ON COLUMN cdr_ob_template.mark_data IS '转移的数据0,电话号码/1,座席号/2,分机号/3,ivrId_nodePath;咨询的数据0,电话号码/1,座席号/2,分机号。支持多组用逗号分隔;传真接收结果1成功2失败;交互的数据ivrId_nodePath';
COMMENT ON COLUMN cdr_ob_template.end_reason IS '接听之后的挂机原因 1000:客户挂机 1001:座席挂机 1002:被强拆 1003:系统挂机（没有bridge）1-127:其他挂机原因';
COMMENT ON COLUMN cdr_ob_template.gw_ip IS '网关ip';
COMMENT ON COLUMN cdr_ob_template.user_field IS '用户自定义字段';
COMMENT ON COLUMN cdr_ob_template.sip_cause IS 'SIP响应码：200 ~ 699';
COMMENT ON COLUMN cdr_ob_template.combo_cost IS '套餐计费，单位：分钟';
COMMENT ON COLUMN cdr_ob_template.is_billing IS '是否计费  1计费  0未计费';
COMMENT ON COLUMN cdr_ob_template.create_time IS '记录创建时间';



CREATE INDEX cdr_ob_template_number_trunk_index
  ON cdr_ob_template
  USING btree
  (number_trunk);




CREATE INDEX cdr_ob_template_customer_number_index
  ON cdr_ob_template
  USING btree
  (customer_number varchar_pattern_ops);




CREATE INDEX cdr_ob_template_cno_index
  ON cdr_ob_template
  USING btree
  (cno);




CREATE INDEX cdr_ob_template_start_time_index
  ON cdr_ob_template
  USING btree
  (start_time);



CREATE INDEX cdr_ob_template_task_id_index
  ON cdr_ob_template
  USING btree
  (task_id);
  
  
  CREATE INDEX cdr_ob_template_create_time_index
  ON cdr_ob_template
  USING btree
  (create_time);
  