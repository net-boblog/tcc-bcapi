package com.tinet.api.boss2.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.api.boss2.model.EnterpriseSetting;
import com.tinet.api.boss2.model.EnterpriseSettingAll;
import com.tinet.common.inc.Const;

/**
 * enterprise_setting表读写
 * <p>
 *  FileName： EnterpriseSettingDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseSettingDao")
public class EnterpriseSettingDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	/**
	 * 测试账户批量延期
	 * @author louxue
	 * @param enterpriseIds 所有延期企业id串，英文逗号分隔。
	 * @return
	 */
	@Transactional
	public Integer executeExtend15Days(String enterpriseIds) {
		String sql = "update enterprise_setting set value=cast((cast(value as date) + integer '15') as varchar) "
				+ " where name ='"+Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_TEST_EXPIRY_DATE+"' and enterprise_id in (" + enterpriseIds + ") ;";
		return jdbcTemplate.update(sql);
	}
	
	/**
	 * 获取企业enterprise_setting某name的value值
	 * @author louxue
	 * @param enterpriseId 企业id
	 * @param name 企业配置名
	 * @return
	 */
	public String getEntSettingValue(Integer enterpriseId, String name){
		String sql = "select value from enterprise_setting  where name = ? and enterprise_id = ?;";
		String value = null;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, name, enterpriseId);
		if (rs.next()) {
			value = rs.getString("value");
		} 
		return value;
	}
	
	/**
	 * 修改企业enterprise_setting某name的value值
	 * @author louxue
	 * @param enterpriseId 企业id
	 * @param name 企业配置名
	 * @param value 企业配置值
	 * @return
	 */
	@Transactional
	public Integer updateEntSetting(Integer enterpriseId, String name, String value, String property){
		String sql = "";
		// 是否更新property字段，依据参数property是否为null
		if (property!=null) {
			sql = "update enterprise_setting set value =?, property=? where name =? and enterprise_id =? ;";
			return jdbcTemplate.update(sql, value, property, name, enterpriseId);
		} else {
			sql = "update enterprise_setting set value =? where name =? and enterprise_id =? ;";
			return jdbcTemplate.update(sql, value, name, enterpriseId);
		}
	}
	
	/**
	 * 新增一条企业配置数据
	 * @param enterpriseId 企业ID
	 * @param name 配置名称
	 * @param value 配置值
	 * @param property 配置属性
	 * @return
	 */
	@Transactional
	public Integer insertEntSetting(Integer enterpriseId, String name, String value, String property) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into enterprise_setting (enterprise_id,name,value,property) values(?,?,?,?);");
		return jdbcTemplate.update(sql.toString(), enterpriseId, name, value, property);
	}
	
	/**
	 * 获取企业其他配置
	 * @param enterpriseId 企业ID
	 * @return
	 */
	public List<EnterpriseSetting> getOtherConf(Integer enterpriseId){
		List<EnterpriseSetting> entSettingList = new ArrayList<EnterpriseSetting>();
		String qrySql = 
			"select id,enterprise_id,name,value,property from Enterprise_Setting where enterprise_id = ?"
			+ " and name in( '" + Const.ENTERPRISE_SETTING_NAME_TAOBAO_NICK + "','"+
			Const.ENTERPRISE_SETTING_TEL_STATUS_IDENTIFICATION+"','"+
			Const.ENTGERPRISE_SETTING_IS_IVR_LOCK+"','"+
			Const.ENTGERPRISE_SETTING_CURL_LEVEL+"','"+
			Const.ENTERPRISE_SETTING_IS_RECORD_TEL + "','" +
			Const.ENTERPRISE_SETTING_NAME_OB_PREDICTIVE_WORK_TIME + "','" +
			Const.ENTGERPRISE_SETTING_IS_CRBT+"');";
		List<Map<String, Object>> entSettingMapList = jdbcTemplate.queryForList(qrySql, enterpriseId);
		for (Map<String, Object> map : entSettingMapList) {
			EnterpriseSetting e = new EnterpriseSetting();
			e.setId(Integer.parseInt(map.get("id").toString()));
			e.setName(map.get("name").toString());
			e.setValue(map.get("value").toString());
			e.setProperty(map.get("property").toString());
			e.setEnterpriseId(enterpriseId);
			entSettingList.add(e);
		}
		return entSettingList;
	}
	
	/**
	 * 更新企业配置
	 * @param entSetting 企业配置对象
	 */
	@Transactional
	public void instOrUpdateEntSetting(EnterpriseSetting entSetting){
		Integer enterpriseId = entSetting.getEnterpriseId();
		// 数据库中存在则更新，否则新增
		if(this.getEntSettingValue(enterpriseId, entSetting.getName()) != null) {
			this.updateEntSetting(enterpriseId, entSetting.getName(), entSetting.getValue(), entSetting.getProperty());
		} else {
			this.insertEntSetting(enterpriseId, entSetting.getName(), entSetting.getValue(), entSetting.getProperty());
		}
		
		// 若是关闭“呼叫手机录音”功能，则同时关闭“号码状态识别选项”功能
		if(entSetting.getName().equals(Const.ENTERPRISE_SETTING_IS_RECORD_TEL) && entSetting.getValue().equals("0")) {
			String value = this.getEntSettingValue(enterpriseId, Const.ENTERPRISE_SETTING_TEL_STATUS_IDENTIFICATION);
			if(value == null) {
				this.insertEntSetting(enterpriseId, Const.ENTERPRISE_SETTING_TEL_STATUS_IDENTIFICATION, "0", "号码状态识别选项");
			} else {
				this.updateEntSetting(enterpriseId, Const.ENTERPRISE_SETTING_TEL_STATUS_IDENTIFICATION, "0", null);
			}
		}
	}
	
	/**
	 * 更新预测外呼开关，预测外呼并发限制，每并发资费
	 * @param enterpriseId 	企业ID
	 * @param isPredictiveOpen 预测外呼开关开启标志
	 * @param predictiveCallLimitSetting 预测外呼并发限制配置对象
	 * @param predictiveCallFeeSetting 预测外呼每并发资费配置对象
	 */
	@Transactional
	public void updateIsPredictiveOpen(Integer enterpriseId, Integer isPredictiveOpen, 
			EnterpriseSetting predictiveCallLimitSetting, EnterpriseSetting predictiveCallFeeSetting) {
		
		// 更新预测外呼开关开启标志
		this.updateEntSetting(enterpriseId, Const.ENTERPRISE_SETTING_NAME_ENTERPRISE_IS_PREDICTIVE_OPEN, isPredictiveOpen.toString(), null);
		
		// 若预测外呼开关开启，同时更新企业外呼并发数和每并发资费
		if(isPredictiveOpen.equals(Const.IS_PREDICTIVE_OPEN_ON)) {
			this.instOrUpdateEntSetting(predictiveCallLimitSetting);
			
			this.instOrUpdateEntSetting(predictiveCallFeeSetting);
			
		}
	}
	
	/**
	 * 企业开通功能更新
	 * @param enterpriseId 企业ID
	 * @param settingName 开启的功能名称
	 */
	@Transactional
	public void updateOpenFuncs(Integer enterpriseId, String[] settingName) {
		// 界面可选择的几项功能：传真接收，点击外呼，呼入录音，ivr录音，短信发送
		String[] settingNames = new String[]{"is_fax_receive", "is_ob_click", "is_record_ib", "is_record_ivr", "is_sms_send"};
		
		// 先全部关闭
		for (int i = 0; i < settingNames.length; i++) {
			this.updateEntSetting(enterpriseId, settingNames[i], "0", null);
		}
		
		// 开启已选择的功能
		if (settingName != null) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < settingName.length; i++) {
				list.add(settingName[i]);
			}
			for (int i = 0; i < settingNames.length; i++) {
				if (list.contains(settingNames[i])) {
					this.updateEntSetting(enterpriseId, settingNames[i], "1", null);
				}
			}
		}
	}
	
	/**
	 * 查找同步到BOSS2的enterprise_setting信息
	 * @return
	 */
	public List<EnterpriseSettingAll> getEntSettingSync(List<EnterpriseSettingAll> entTestStatusList,
			List<EnterpriseSettingAll> entTestExpiryDateList) {
		List<EnterpriseSettingAll> entSettingList = new ArrayList<EnterpriseSettingAll>();
		StringBuffer sqlEntSetting = new StringBuffer();
		sqlEntSetting.append("select enterprise_id, name, value, property, create_time from enterprise_setting where name in('enterprise_test_status','enterprise_test_expiry_date');");
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlEntSetting.toString());
		while (rs.next()) {
			if (rs.getString("name").equals("enterprise_test_status")) {
				boolean isExist = false;
				for (EnterpriseSettingAll entSettingBoss2 : entTestStatusList) {
					if (rs.getInt("enterprise_id") == entSettingBoss2.getEnterpriseId().intValue()) {
						entSettingBoss2.setValue(rs.getString("value"));
						entSettingList.add(entSettingBoss2);
						isExist = true;
						break;
					}
				}
				EnterpriseSettingAll entSettingAll = new EnterpriseSettingAll();	
				if(!isExist){
					entSettingAll.setEnterpriseId(rs.getInt("enterprise_id"));
					entSettingAll.setName(rs.getString("name"));
					entSettingAll.setValue(rs.getString("value"));
					entSettingAll.setProperty(rs.getString("property"));
					entSettingAll.setCreateTime(rs.getDate("create_time"));
					entSettingList.add(entSettingAll);
				}
				
			} else if (rs.getString("name").equals("enterprise_test_expiry_date")) {
				boolean isExist = false;
				for (EnterpriseSettingAll entSettingBoss2 : entTestExpiryDateList) {
					if (rs.getInt("enterprise_id") == entSettingBoss2.getEnterpriseId().intValue()) {
						entSettingBoss2.setValue(rs.getString("value"));
						entSettingList.add(entSettingBoss2);
						isExist = true;
						break;
					}
				}
				EnterpriseSettingAll entSettingAll = new EnterpriseSettingAll();	
				if(!isExist){
					entSettingAll.setEnterpriseId(rs.getInt("enterprise_id"));
					entSettingAll.setName(rs.getString("name"));
					entSettingAll.setValue(rs.getString("value"));
					entSettingAll.setProperty(rs.getString("property"));
					entSettingAll.setCreateTime(rs.getDate("create_time"));
					entSettingList.add(entSettingAll);
				}
			} else {
				continue;
			}
		}
		return entSettingList;
	}
}
