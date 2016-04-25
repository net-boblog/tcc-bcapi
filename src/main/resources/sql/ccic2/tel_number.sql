CREATE TABLE tel_number_template
(
  id serial NOT NULL,
  tel character varying, 
  province character varying,
  areacode character varying,
  type integer, 
  is_valid integer, 
  recent_call timestamp with time zone,
  create_time timestamp with time zone DEFAULT now(),
  CONSTRAINT tel_number_template_pkey PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tel_number_template
  OWNER TO postgres;
COMMENT ON TABLE tel_number_template
  IS '号码管理表';
COMMENT ON COLUMN tel_number_template.tel IS '号码';
COMMENT ON COLUMN tel_number_template.province IS '省份';
COMMENT ON COLUMN tel_number_template.areacode IS '区号';
COMMENT ON COLUMN tel_number_template.type IS '号码类型  1座机 2手机';
COMMENT ON COLUMN tel_number_template.is_valid IS '号码是否有效 0无效 1有效';
COMMENT ON COLUMN tel_number_template.recent_call IS '最近呼叫时间';
COMMENT ON COLUMN tel_number_template.create_time IS '创建时间';

CREATE INDEX tel_number_template_create_time_index
  ON tel_number_template
  USING btree
  (create_time );

CREATE INDEX tel_number_template_tel_index
  ON tel_number_template
  USING btree
  (tel );
