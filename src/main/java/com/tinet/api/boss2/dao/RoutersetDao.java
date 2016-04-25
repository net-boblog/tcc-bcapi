package com.tinet.api.boss2.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Routerset;
import com.tinet.common.inc.Const;

/**
 * routerset表读写
 * <p>
 *  FileName： RoutersetDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("routersetDao")
public class RoutersetDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 获取所有企业路由组
	 * @author louxue
	 * @return
	 */
	public List<Routerset> getAllRouterset(){
		List<Routerset> routersetList = new ArrayList<Routerset>();
		StringBuffer getRouersetSql = new StringBuffer("");
		getRouersetSql.append("select r.id as id, r.name as name, r.description as description, ")
			.append(" (case when s.value=cast(r.id as character varying) then true else false end)as defaultRouter ")
			.append(" from routerset r,system_setting s")
			.append(" where s.name= ? order by r.id;");
		List<Map<String, Object>> routersetMapList = jdbcTemplate.queryForList(getRouersetSql.toString(), Const.CCIC_SYSTEM_SETTING_NAME_DEFAULT_ROUTER);
		for (Map<String, Object> map : routersetMapList) {
			Routerset routerset = new Routerset();
			routerset.setId((Integer)map.get("id"));
			routerset.setName(map.get("name").toString());
			routerset.setDescription(map.get("description").toString());
			routerset.setDefaultRouter((Boolean)map.get("defaultRouter"));
			routersetList.add(routerset);
		}
		return routersetList;
	}
	
	/**
	 * routerset表新增一条数据
	 * @param name 路由组名称
	 * @param description 路由组描述
	 */
	@Transactional
	public void insert(Integer id, String name, String description) {
		if (id!=null) {
			String insterSql = "insert into routerset(id, name,description) values(?,?,?)" ;
			jdbcTemplate.update(insterSql, id, name, description);
		} else {
			String insterSql = "insert into routerset(name,description) values(?,?)" ;
			jdbcTemplate.update(insterSql, name, description);
		}
	}
	
	/**
	 * 修改一条routerset数据
	 * @param id 路由组id
	 * @param name 路由组名称
	 * @param description 路由组描述
	 */
	@Transactional
	public void update(Integer id, String name, String description) {
		String updateSql = "update routerset set name=?,description=? where id=?" ;
		jdbcTemplate.update(updateSql, name, description, id);
	}
	
	/**
	 * routerset表删除一条数据
	 * @param id 路由组id
	 */
	@Transactional
	public String delete(Integer id) {
		// 查询是否有企业使用
		String qurySql = "select count(*) as num from enterprise_router where ib_router_right=? or ob_preview_router_left=? or ob_predictive_router_left=?";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qurySql, id, id, id);
		if (rSet.next()) {
			int existNum = rSet.getInt("num");
			if (existNum!=0) {
				return "存在企业在使用该路由组，不能删除！";
			}
		}
		String delSql = "delete from router where routerset_id=?;delete from routerset where id=?;" ;
		jdbcTemplate.update(delSql, id, id);
		return null;
	}
	
	/**
	 * 获取路由组名称
	 * @param id 路由组id
	 * @return
	 */
	public String getRoutersetName(Integer id) {
		String qrySql = "select name from routerset where id=?";
		String name = "";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qrySql, id);
		if (rSet.next()) {
			name = rSet.getString("name");
		}
		return name;
	}
	
	/**
	 * 获取routerset表下一个序列值
	 * @return
	 */
	public Integer getNextSeq() {
		String qrySql = "select nextval('routerset_id_seq') as id;";
		SqlRowSet rSet = jdbcTemplate.queryForRowSet(qrySql);
		if (rSet.next()) {
			return rSet.getInt("id");
		} 
		return -1;
	}
}
