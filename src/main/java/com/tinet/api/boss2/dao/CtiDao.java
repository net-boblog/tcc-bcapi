package com.tinet.api.boss2.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.Cti;
import com.tinet.common.inc.Const;

/**
 * cti表读写
 * <p>
 *  FileName： CtiDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author zhancy
 * @since 1.0
 * @version 1.0
 */
@Service("ctiDao")
public class CtiDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 查询全部cti
	 * @author zhancy
	 * @return
	 */
	public List<Cti> getAllCti(){
		List<Cti> list = new ArrayList<Cti>();
		String sql = "select id, ip_addr, area_code, radius_pwd, ami_server," +
				"ami_server_port, manager_user, manager_pwd, " +
				"action_user, action_pwd, active, type, mac, webcall_caps, cti_real_ip from cti order by ip_addr";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while(rs.next()){
			Cti cti = new Cti();
			cti.setId(rs.getInt("id"));
			cti.setIpAddr(rs.getString("ip_addr"));
			cti.setAreaCode(rs.getString("area_code"));
			cti.setRadiusPwd(rs.getString("radius_pwd"));
			cti.setAmiServer(rs.getString("ami_server"));
			cti.setAmiServerPort(rs.getInt("ami_server_port"));
			cti.setManagerUser(rs.getString("manager_user"));
			cti.setManagerPwd(rs.getString("manager_pwd"));
			cti.setActionUser(rs.getString("action_user"));
			cti.setActionPwd(rs.getString("action_pwd"));
			cti.setActive(rs.getInt("active"));
			cti.setType(rs.getInt("type"));
			cti.setMac(rs.getString("mac"));
			cti.setWebcallCaps(rs.getInt("webcall_caps"));
			cti.setRealAddr(rs.getString("cti_real_ip"));
			list.add(cti);
		}
		return list;
	}
	
	/**
	 * 查询全部激活状态cti
	 * @author zhancy
	 * @return
	 */
	public List<Cti> getAll(){
		List<Cti> list = new ArrayList<Cti>();
		String sql = "select id,ip_addr as ipAddr,area_code as areaCode,radius_pwd as radiusPwd,ami_server as amiServer," +
				"ami_server_port as amiServerPort, manager_user as managerUser, manager_pwd as managerPwd, action_user as actionUser,action_pwd as actionPwd," +
				"type, mac, webcall_caps, cti_real_ip " + "from cti where active = "+Const.CTI_ACTIVE_RUNNING+" order by ip_addr";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while(rs.next()){
			Cti cti = new Cti();
			cti.setId(rs.getInt("id"));
			cti.setIpAddr(rs.getString("ipAddr"));
			cti.setAreaCode(rs.getString("areaCode"));
			cti.setRadiusPwd(rs.getString("radiusPwd"));
			cti.setAmiServer(rs.getString("amiServer"));
			cti.setAmiServerPort(rs.getInt("amiServerPort"));
			cti.setManagerUser(rs.getString("managerUser"));
			cti.setManagerPwd(rs.getString("managerPwd"));
			cti.setActionUser(rs.getString("actionUser"));
			cti.setActionPwd(rs.getString("actionPwd"));
			cti.setType(rs.getInt("type"));
			cti.setMac(rs.getString("mac"));
			cti.setWebcallCaps(rs.getInt("webcall_caps"));
			cti.setRealAddr(rs.getString("cti_real_ip"));
			list.add(cti);
		}
		return list;
	}
	
	/**
	 * 新增
	 * @author zhancy
	 */
	@Transactional
	public void insert(Cti cti){
		String sql = "insert into cti(ip_addr, area_code, radius_pwd, ami_server, ami_server_port,"
				+ "manager_user, manager_pwd, action_user, action_pwd, active, type, mac, webcall_caps, cti_real_ip) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, cti.getIpAddr(), cti.getAreaCode(), cti.getRadiusPwd(), cti.getAmiServer(), 
				cti.getAmiServerPort(), cti.getManagerUser(), cti.getManagerPwd(), cti.getActionUser(), 
				cti.getActionPwd(),cti.getActive(), cti.getType(), cti.getMac(), cti.getWebcallCaps(), cti.getRealAddr());
	}
	
	/**
	 * 更新
	 * @author zhancy
	 */
	@Transactional
	public void update(Cti cti){
		String sql = "update cti set ip_addr = ?, area_code = ?, radius_pwd = ?, ami_server = ?, "
				+ "ami_server_port = ?, manager_user = ?, manager_pwd = ?, action_user = ?, "
				+ "action_pwd = ?, active = ?, type=?, mac=?, webcall_caps=?, cti_real_ip=? where id =?";
		jdbcTemplate.update(sql, cti.getIpAddr(), cti.getAreaCode(), cti.getRadiusPwd(), cti.getAmiServer(),
				cti.getAmiServerPort(), cti.getManagerUser(), cti.getManagerPwd(), cti.getActionUser(),
				cti.getActionPwd(), cti.getActive(), cti.getType(), cti.getMac(), cti.getWebcallCaps(), cti.getRealAddr(), cti.getId());
	}
	
	/**
	 * 批量删除
	 * @author zhancy
	 */
	@Transactional
	public void batchDelete(List<Integer> ids){
		if (ids!=null && ids.size()>0) {
			// 构造批量删除sql
			List<Object[]> argsList = new ArrayList<Object[]>();
			for (Integer id : ids) {
				argsList.add(new Object[]{id});
			}
			// 执行删除
			String sql = "delete from cti where id =?";
			int[] argsTypes = {Types.INTEGER};
			jdbcTemplate.batchUpdate(sql, argsList, argsTypes);
		}
	}
	
}
