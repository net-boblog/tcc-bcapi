insert into customize_field  (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'id','ID','customer','text',1,1,0,0,-1,'[]');

insert into customize_field  (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'name','姓名','customer','text',1,0,0,2,0,'[{"name":"姓名","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'crm_id','CRM序号','customer','text',1,1,0,3,31,'[{"name":"CRM序号","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'ib_count','呼入次数','customer','text',1,1,0,3,30,'[{"name":"呼入次数","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'ob_count','呼出次数','customer','text',1,1,0,3,31,'[{"name":"呼出次数","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'sex','客户性别','customer','select',1,1,2,1,1,'[{"name":"先生","is_default":"0","value":"0","config":[]},{"name":"女士","is_default":"0","value":"1","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'birth','生日','customer','text',1,1,0,3,31,'[{"name":"生日","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'department','部门','customer','text',1,1,0,3,31,'[{"name":"部门","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'trade','所属行业','customer','select',1,1,2,3,17,'[{"name":"IT","is_default":"1","value":"IT","config":[]},{"name":"机械","is_default":"0","value":"machine","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'position','职务','customer','text',1,1,0,3,31,'[{"name":"职位","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'mobile1','手机1','customer','text',1,1,0,2,3,'[{"name":"手机1","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'mobile2','手机2','customer','text',1,1,0,1,7,'[{"name":"手机2","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'tel1','电话1','customer','text',1,1,0,2,2,'[{"name":"电话1","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'tel2','电话2','customer','text',1,1,0,1,6,'[{"name":"电话2","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'ptel','家庭电话','customer','text',1,1,0,1,8,'[{"name":"家庭电话","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'other_tel','其他电话','customer','text',1,1,0,1,9,'[{"name":"其他电话","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'email','Email','customer','text',1,1,0,1,11,'[{"name":"Email","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'fax','传真','customer','text',1,1,0,1,10,'[{"name":"传真","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'im','即时通讯工具','customer','text',1,1,0,3,16,'[{"name":"即时通讯工具","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'company','公司名称','customer','text',1,1,0,3,31,'[{"name":"公司名称","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'country','所在国家','customer','text',1,1,0,3,31,'[{"name":"所在国家","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'post','邮编','customer','text',1,1,0,3,31,'[{"name":"邮编","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'province','所在省份','customer','text',1,1,0,3,31,'[{"name":"所在省份","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'city','所在城市','customer','text',1,1,0,3,31,'[{"name":"所在城市","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'address','家庭地址','customer','text',1,1,0,3,15,'[{"name":"家庭住址","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'create_time','创建时间','customer','text',1,1,0,1,4,'[{"name":"创建时间","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'modify_time','修改时间','customer','text',1,1,0,1,5,'[{"name":"修改时间","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'last_mender','修改人','customer','text',1,1,0,3,31,'[{"name":"修改人","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'creator','创建人','customer','text',1,1,0,3,31,'[{"name":"创建人","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'description','备注','customer','texts',1,1,0,2,14,'[{"name":"备注","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'level','客户等级','customer','select',1,1,0,3,13,'[{"name":"VIP","is_default":"1","value":"3","config":[]},{"name":"重要","is_default":"0","value":"2","config":[]},{"name":"普通","is_default":"0","value":"1","config":[]},{"name":"不重要","is_default":"0","value":"0","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'status','客户分类','customer','select',1,1,2,3,31,'[{"name":"已处理","is_default":"1","value":"yes","config":[]},{"name":"未处理","is_default":"0","value":"no","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'share_type','客户归属','customer','select',1,1,0,2,31,'[{"name":"全体共享","is_default":"1","value":"1","config":[]},{"name":"队列共享","is_default":"0","value":"2","config":[]},{"name":"座席私有","is_default":"0","value":"3","config":[]},{"name":"无归属","is_default":"0","value":"-1","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'owner','客户所有人','customer','text',1,1,0,0,-1,'[{"name":"客户所有人","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'company_url','公司网址','customer','text',1,1,0,3,31,'[{"name":"公司网址","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'customer_src','电话来源','customer','text',1,1,0,3,12,'[{"name":"电话来源","is_default":"1","value":"","config":[]}]');


insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'id','ID','business_record','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'unique_id','对应呼叫惟一标识','business_record','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'enterprise_id','企业id','business_record','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'customer_id','客户编号','business_record','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'call_type','通话类型','business_record','select',1,1,2,3,9,'[{"name":"呼入","is_default":"1","value":"1","config":[]},{"name":"呼出","is_default":"0","value":"2","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'business_type','业务类型','business_record','select',1,1,2,1,2,'[{"name":"咨询","is_default":"1","value":"consult","config":[]},{"name":"投诉","is_default":"0","value":"complain","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'business_level','优先级','business_record','select',1,1,2,1,4,'[{"name":"VIP","is_default":"1","value":"0","config":[]},{"name":"普通","is_default":"0","value":"1","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'business_status','业务状态','business_record','select',1,1,2,2,3,'[{"name":"已处理","is_default":"1","value":"yes","config":[]},{"name":"未处理","is_default":"0","value":"no","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'subject','主题','business_record','text',1,0,0,2,0,'[{"name":"主题","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'description','备注','business_record','texts',1,1,0,2,1,'[{"name":"备注","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'relation','业务记录关联','business_record','text',1,1,0,0,10,'[{"name":"业务记录关联","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'creator','创建人','business_record','text',1,1,0,1,5,'[{"name":"业务记录创建人","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'create_time','创建时间','business_record','text',1,1,0,1,6,'[{"name":"加入时间","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'modify_time','最近修改时间','business_record','text',1,1,0,1,8,'[{"name":"最近修改时间","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'last_mender','修改人','business_record','text',1,1,0,1,7,'[{"name":"修改人","is_default":"1","value":"","config":[]}]');


insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'id','id','task_inventory','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'task_id','任务名称','task_inventory','text',1,1,0,1,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'file_id','对应task_file的id','task_inventory','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'customer_id','电话对应customer表中客户id','task_inventory','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'customer_name','客户名称','task_inventory','text',1,1,0,2,1,'[{"name":"客户名称","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'cno','分配座席','task_inventory','select',1,1,0,2,2,'[{"name":"分配座席","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'tel','电话号码','task_inventory','text',1,1,0,2,3,'[{"name":"电话号码","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'tel_type','电话类型','task_inventory','select',1,1,0,3,4,'[{"name":"手机","is_default":"1","value":"2","config":[]},{"name":"固话","is_default":"0","value":"1","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'tel_area_code','区号','task_inventory','text',1,1,0,3,5,'[{"name":"区号","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'num_status','号码状态','task_inventory','select',1,1,0,3,6,'[{"name":"待后续","is_default":"0","value":"1","config":[]}]');
insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'status','呼叫结果','task_inventory','select',1,1,0,2,7,'[{"name":"未执行","is_default":"0","value":"1","config":[]},{"name":"已执行","is_default":"0","value":"2","config":[]}]');
insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'retry','呼叫次数','task_inventory','text',1,1,0,1,8,'[{"name":"呼叫次数","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'client_name','座席名','task_inventory','text',1,1,0,0,-1,'[{"name":"座席名","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'client_crm_id','座席crmid','task_inventory','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'start_time','最近呼叫时间','task_inventory','text',1,1,0,1,9,'[{"name":"最近呼叫时间","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'end_time','呼叫结束时间','task_inventory','text',1,1,0,0,-1,'[{"name":"呼叫结束时间","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'bridge_duration','通话时长','task_inventory','text',1,1,0,0,-1,'[{"name":"通话时长","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'owner','预览外呼分配的座席号','task_inventory','text',1,1,0,0,-1,'[{"name":"预览外呼分配座席号","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'main_unique_id','如果接通对应的cdr表中unique_id','task_inventory','text',1,1,0,0,-1,'[]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'business_result','业务结果','task_inventory','select',1,1,2,1,-1,'[{"name":"无人接","is_default":"1","value":"0","config":[]},{"name":"不感兴趣","is_default":"0","value":"1","config":[]},{"name":"感兴趣","is_default":"0","value":"2","config":[]},{"name":"已成交","is_default":"0","value":"3","config":[]},{"name":"无法接通","is_default":"0","value":"4","config":[]},{"name":"拒接","is_default":"0","value":"5","config":[]},{"name":"停机","is_default":"0","value":"6","config":[]},{"name":"空号","is_default":"0","value":"7","config":[]},{"name":"传真","is_default":"0","value":"8","config":[]},{"name":"总机","is_default":"0","value":"9","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'is_valid','是否有效','task_inventory','select',1,1,2,0,-1,'[{"name":"无效","is_default":"1","value":"0","config":[]},{"name":"有效","is_default":"0","value":"1","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'create_time','任务生成时间','task_inventory','text',1,1,0,1,-1,'[{"name":"任务生成时间","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'description','备注','task_inventory','texts',1,1,0,1,20,'[{"name":"备注","is_default":"1","value":"","config":[]}]');

insert into customize_field (id,enterprise_id,name,title_name,table_name,field_type,is_sys_default,is_optional,purview,status,priority,config) values (nextval('customize_field_id_seq'),enterprise_id_template,'upload_file','文件名称','task_inventory','text',1,1,0,3,-1,'[{"name":"文件名称","is_default":"1","value":"","config":[]}]');
