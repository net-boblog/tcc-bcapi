package com.tinet.api.boss2.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.model.EnterpriseNumber;
import com.tinet.common.inc.Const;

/**
 * enterprise_hotline，trunk，enterprise_clid表查询
 * <p>
 *  FileName： AccountDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseNumberDao")
public class EnterpriseNumberDao {
	
	private static Logger logger = LoggerFactory.getLogger(EnterpriseNumberDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<EnterpriseNumber> getAllNumbers(Set<String> numberSet) {
		List<EnterpriseNumber> entNumberList = new ArrayList<EnterpriseNumber>();
		StringBuffer hotlineQrySql = new StringBuffer();
		StringBuffer trunkQrySql = new StringBuffer();
		StringBuffer entOwnedNumQrySql = new StringBuffer();
		hotlineQrySql.append("select enterprise_id,hotline from enterprise_hotline where type = 1 order by enterprise_id;");
		trunkQrySql.append("select enterprise_id,area_code,number_trunk,type from trunk order by enterprise_id;");
		entOwnedNumQrySql.append("select enterprise_id,ib_clid_right_type,ib_clid_right_number,ob_preview_clid_left_type,ob_preview_clid_left_number,ob_preview_clid_right_type,ob_preview_clid_right_number,ob_predictive_clid_left_type,ob_predictive_clid_left_number,ob_predictive_clid_right_type,ob_predictive_clid_right_number ")
			.append(" from enterprise_clid ")
			.append(" where (ib_clid_right_type =3 or ob_preview_clid_left_type=3 or ob_preview_clid_right_type=3 or ob_predictive_clid_left_type=3 or ob_predictive_clid_right_type =3)")
			.append(" and enterprise_id in(select enterprise_id from business where status!=4) order by enterprise_id");

		// 查询所有400/1010
		SqlRowSet rs = jdbcTemplate.queryForRowSet(hotlineQrySql.toString());
		while (rs.next()) {
			int enterpriseId = rs.getInt("enterprise_id");
			EnterpriseNumber hotline = new EnterpriseNumber();
			hotline.setEnterpriseId(enterpriseId);
			hotline.setAreaCode("");
			hotline.setNumberInuse(rs.getString("hotline"));
			hotline.setType(Const.ENTERPRISE_NUMBER_TYPE_400_NUMBER);
			entNumberList.add(hotline);
			numberSet.add(enterpriseId+rs.getString("hotline"));
		}
		
		// 查询所有中继号码,手机虚拟号码
		rs = jdbcTemplate.queryForRowSet(trunkQrySql.toString());
		while (rs.next()) {
			int type = rs.getInt("type");
			int enterpriseId = rs.getInt("enterprise_id");
			EnterpriseNumber trunk = null;
			switch (type) {
				case Const.ENTERPRISE_TRUNK_TYPE_NOT_BIND:	// 直线号码，中继号码
				case Const.ENTERPRISE_TRUNK_TYPE_BIND:	// 绑定了400或1010号码的中继号码
					trunk = new EnterpriseNumber();
					trunk.setEnterpriseId(enterpriseId);
					trunk.setAreaCode(rs.getString("area_code"));
					trunk.setNumberInuse(rs.getString("number_trunk"));
					trunk.setType(Const.ENTERPRISE_NUMBER_TYPE_TRUNK);
					numberSet.add(enterpriseId+rs.getString("area_code")+rs.getString("number_trunk"));
					break;
				case Const.ENTERPRISE_TRUNK_TYPE_MOBILE_VIRTUAL:	// 手机虚拟号码
					trunk = new EnterpriseNumber();
					trunk.setEnterpriseId(enterpriseId);
					trunk.setAreaCode(rs.getString("area_code"));
					trunk.setNumberInuse(rs.getString("number_trunk"));
					trunk.setType(Const.ENTERPRISE_NUMBER_TYPE_MOBILE_VIRTUAL);
					numberSet.add(enterpriseId+rs.getString("number_trunk"));
					break;
				default:
					break;
			}
			if (trunk!=null) {
				entNumberList.add(trunk);
			}
		}
		
		// 查询该平台所有企业存在某一透传类型为“固定号码”对应的透传号码，筛选出客户自备号码
		rs = jdbcTemplate.queryForRowSet(entOwnedNumQrySql.toString());
		while (rs.next()) {
			int enterpriseId = rs.getInt("enterprise_id");
			if (rs.getInt("ib_clid_right_type")==3
					&& rs.getString("ib_clid_right_number")!=null
					&& !"".equals(rs.getString("ib_clid_right_number"))) {
				String[] clidNumberArry = rs.getString("ib_clid_right_number").split(",");
				for (int j = 0; j < clidNumberArry.length; j++) {
					String num = clidNumberArry[j];
					if (!numberSet.contains(enterpriseId+num)) {
						EnterpriseNumber entNumber = new EnterpriseNumber();
						entNumber.setEnterpriseId(enterpriseId);
						entNumber.setAreaCode("");
						entNumber.setNumberInuse(num);
						entNumber.setType(Const.ENTERPRISE_NUMBER_TYPE_ENTERPRISE_OWNED);
						numberSet.add(enterpriseId+num);
						entNumberList.add(entNumber);
					}
						
				}
			}
			if (rs.getInt("ob_preview_clid_left_type")==3
					&& rs.getString("ob_preview_clid_left_number")!=null
					&& !"".equals(rs.getString("ob_preview_clid_left_number"))) {
				String[] clidNumberArry = rs.getString("ob_preview_clid_left_number").split(",");
				for (int j = 0; j < clidNumberArry.length; j++) {
					String num = clidNumberArry[j];
					if (!numberSet.contains(enterpriseId+num)) {
						EnterpriseNumber entNumber = new EnterpriseNumber();
						entNumber.setEnterpriseId(enterpriseId);
						entNumber.setAreaCode("");
						entNumber.setNumberInuse(num);
						entNumber.setType(Const.ENTERPRISE_NUMBER_TYPE_ENTERPRISE_OWNED);
						numberSet.add(enterpriseId+num);
						entNumberList.add(entNumber);
					}
						
				}
			}
			if (rs.getInt("ob_preview_clid_right_type")==3
					&& rs.getString("ob_preview_clid_right_number")!=null
					&& !"".equals(rs.getString("ob_preview_clid_right_number"))) {
				String[] clidNumberArry = rs.getString("ob_preview_clid_right_number").split(",");
				for (int j = 0; j < clidNumberArry.length; j++) {
					String num = clidNumberArry[j];
					if (!numberSet.contains(enterpriseId+num)) {
						EnterpriseNumber entNumber = new EnterpriseNumber();
						entNumber.setEnterpriseId(enterpriseId);
						entNumber.setAreaCode("");
						entNumber.setNumberInuse(num);
						entNumber.setType(Const.ENTERPRISE_NUMBER_TYPE_ENTERPRISE_OWNED);
						numberSet.add(enterpriseId+num);
						entNumberList.add(entNumber);
					}
						
				}
			}
			if (rs.getInt("ob_predictive_clid_left_type")==3
					&& rs.getString("ob_predictive_clid_left_number")!=null
					&& !"".equals(rs.getString("ob_predictive_clid_left_number"))) {
				String[] clidNumberArry = rs.getString("ob_predictive_clid_left_number").split(",");
				for (int j = 0; j < clidNumberArry.length; j++) {
					String num = clidNumberArry[j];
					if (!numberSet.contains(enterpriseId+num)) {
						EnterpriseNumber entNumber = new EnterpriseNumber();
						entNumber.setEnterpriseId(enterpriseId);
						entNumber.setAreaCode("");
						entNumber.setNumberInuse(num);
						entNumber.setType(Const.ENTERPRISE_NUMBER_TYPE_ENTERPRISE_OWNED);
						numberSet.add(enterpriseId+num);
						entNumberList.add(entNumber);
					}
						
				}
			}
			if (rs.getInt("ob_predictive_clid_right_type")==3
					&& rs.getString("ob_predictive_clid_right_number")!=null
					&& !"".equals(rs.getString("ob_predictive_clid_right_number"))) {
				String[] clidNumberArry = rs.getString("ob_predictive_clid_right_number").split(",");
				for (int j = 0; j < clidNumberArry.length; j++) {
					String num = clidNumberArry[j];
					if (!numberSet.contains(enterpriseId+num)) {
						EnterpriseNumber entNumber = new EnterpriseNumber();
						entNumber.setEnterpriseId(enterpriseId);
						entNumber.setAreaCode("");
						entNumber.setNumberInuse(num);
						entNumber.setType(Const.ENTERPRISE_NUMBER_TYPE_ENTERPRISE_OWNED);
						numberSet.add(enterpriseId+num);
						entNumberList.add(entNumber);
					}
						
				}
			}
		}
		logger.error("总enterpriseId+num不同的个数："+numberSet.size());
		logger.error("总enterprise_number个数"+entNumberList.size());
		return entNumberList;
	}
}
