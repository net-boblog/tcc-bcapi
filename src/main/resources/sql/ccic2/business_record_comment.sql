 CREATE TABLE business_record_comment_template
(
  id serial NOT NULL, 
  business_record_id integer NOT NULL,
  assignee character varying, 
  assignee_name character varying,
  comment character varying, 
  start_time timestamp without time zone, 
  end_time timestamp without time zone, 
  create_by character varying,
  action_flag integer,
  last_task_flag integer DEFAULT 0,
  create_time timestamp with time zone DEFAULT now(), 
  CONSTRAINT business_record_comment_template_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE business_record_comment_template OWNER TO postgres;
COMMENT ON TABLE business_record_comment_template IS '业务记录流转表';
COMMENT ON COLUMN business_record_comment_template.id IS '任务id,一个工单对应一个或多个处理任务';
COMMENT ON COLUMN business_record_comment_template.business_record_id IS '业务记录id';
COMMENT ON COLUMN business_record_comment_template.assignee IS '任务处理人(分配到任务的人)，对应座席工号';
COMMENT ON COLUMN business_record_comment_template.assignee_name IS '处理人姓名';
COMMENT ON COLUMN business_record_comment_template.comment IS '任务处理人（assignee）的处理意见、说明';
COMMENT ON COLUMN business_record_comment_template.start_time IS '任务开始时间';
COMMENT ON COLUMN business_record_comment_template.end_time IS '任务结束时间';
COMMENT ON COLUMN business_record_comment_template.create_by IS '任务发起人，对应座席工号';
COMMENT ON COLUMN business_record_comment_template.action_flag IS '任务发起人动作标识 ：1.创建  2.转移 3.完成 4，关闭';
COMMENT ON COLUMN business_record_comment_template.last_task_flag IS '最新任务标识：  0默认(历史任务)，1当前（最后一个）任务';
COMMENT ON COLUMN business_record_comment_template.create_time IS '记录创建时间';

CREATE INDEX business_record_comment_template_business_id_index
  ON business_record_comment_template
  USING btree
  (business_record_id);