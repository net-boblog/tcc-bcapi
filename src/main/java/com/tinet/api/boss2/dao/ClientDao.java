package com.tinet.api.boss2.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinet.common.inc.Const;
import com.tinet.common.util.MD5Encoder;
import com.tinet.common.util.RandomPwd;
import com.tinet.common.util.StringUtil;

/**
 * client表读写
 * <p>
 *  FileName： ClientDao.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("clientDao")
public class ClientDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	/**
	 * 重置企业所有座席登录密码为8为长度字母数字组合
	 * @author louxue
	 * @param enterpriseId 企业id
	 * @return 返回企业所有座席号及其密码字符串
	 */
	@Transactional
	public String updateCnoPwd(Integer enterpriseId) {
		String clientAndPwd = "座席前端登录座席号及密码 <br/> 座席号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码 <br/> ";
		
		// 查询企业所有座席
		String qryCnoSql = "select cno from client where enterprise_id = ? order by cno";
		List<Map<String, Object>> cnoMapList = jdbcTemplate.queryForList(qryCnoSql, enterpriseId);
		
		// 更改每一个座席的登录密码
		String updatePwdSql = "update client set pwd = ? where enterprise_id = ? and cno=?";
		List<Object[]> argsList = new ArrayList<Object[]>();
		for (Map<String, Object> map : cnoMapList) {
			String cno =  map.get("cno").toString();
			String pwd = RandomPwd.randomString(8);
			argsList.add(new Object[]{MD5Encoder.encode(pwd), enterpriseId, cno});
			
			String temp = cno + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + pwd + "<br/>";
			clientAndPwd += temp;
		}
		int[] argsType = {Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
		jdbcTemplate.batchUpdate(updatePwdSql, argsList, argsType);
		return clientAndPwd;
	}
	
	/**
	 * 更新企业client表的ob_clid字段
	 * @param enterpriseId
	 * @param obPreviewClidLeftType
	 * @param obPreviewClidLeftNumber
	 * @param hotlineTrunk
	 * @param directNumber
	 * @param mobileVirtual
	 * @param trunkWithAreaCode
	 */
	@Transactional
	public void updateClientObCLid(Integer enterpriseId, Integer obPreviewClidLeftType, String obPreviewClidLeftNumber,
			String hotlineTrunk, String directNumber, String mobileVirtual, Map<String, String> trunkWithAreaCode) {
		
		String[] clidNumberArry = null;
		if(obPreviewClidLeftType == Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_TRUNK) { // 外显中继号码
			// 带区号的固话号码
			String clidNumberStr = "";
			if(StringUtil.isNotEmpty(hotlineTrunk)) {
				String[] hotlineTrunkArry = hotlineTrunk.split(",");
				for (int i = 0; i < hotlineTrunkArry.length; i++) {
					clidNumberStr += trunkWithAreaCode.get(hotlineTrunkArry[i]) + hotlineTrunkArry[i];
					clidNumberStr += ",";
				}
			}
			if(StringUtil.isNotEmpty(directNumber)) {
				String[] directNumberArry = directNumber.split(",");
				for (int i = 0; i < directNumberArry.length; i++) {
					clidNumberStr += trunkWithAreaCode.get(directNumberArry[i]) + directNumberArry[i];
					clidNumberStr += ",";
				}
			}
			if(StringUtil.isNotEmpty(mobileVirtual)) {
				String[] mobileVirtualArry = mobileVirtual.split(",");
				for (int i = 0; i < mobileVirtualArry.length; i++) {
					clidNumberStr += mobileVirtualArry[i];
					clidNumberStr += ",";
				}
			}
			
			if(StringUtil.isNotEmpty(clidNumberStr)) {
				clidNumberArry = clidNumberStr.substring(0, clidNumberStr.length() - 1).split(",");
			}
		} else if (obPreviewClidLeftType == Const.ENTERPRISE_TEMP_CALL_CLID_TYPE_FIXED_NUMBER) { // 外显固定号码
			if(StringUtil.isNotEmpty(obPreviewClidLeftNumber)){
				clidNumberArry = obPreviewClidLeftNumber.split(",");
			}
		}
		
		if(clidNumberArry != null){
			// 存放每个座席要更新为的新的ob_clid和ob_clid_left_assign_value
			List<Object[]> argsList = new ArrayList<Object[]>();
			String qryClientClidsql = "select cno,ob_clid,ob_clid_left_assign_value from client where enterprise_id = ?;";
			List<Map<String, Object>> clientList = jdbcTemplate.queryForList(qryClientClidsql, enterpriseId);
			for (Map<String, Object> map : clientList){
				String cno = map.get("cno").toString();
				String obClid = map.get("ob_clid")==null?"":map.get("ob_clid").toString();
				String obClidAssignValue = map.get("ob_clid_left_assign_value")==null?"":map.get("ob_clid_left_assign_value").toString();
				String clientClid = "";
				String clientAssignClid = "";
				if(clidNumberArry != null){
					for (int i = 0; i < clidNumberArry.length; i++) {
						if(obClid!=null){
							if(obClid.contains(clidNumberArry[i])){
								clientClid += clidNumberArry[i] + ",";
							}
						}
						if (obClidAssignValue!=null) {
							if(obClidAssignValue.contains(clidNumberArry[i])){
								clientAssignClid += clidNumberArry[i] + ",";
							}
						}
					}
				}
				if(StringUtil.isNotEmpty(clientClid)){
					clientClid = clientClid.substring(0, clientClid.length() - 1);
				}
				if(StringUtil.isNotEmpty(clientAssignClid)){
					clientAssignClid = clientAssignClid.substring(0, clientAssignClid.length() - 1);
				}
				argsList.add(new Object[]{clientClid, clientAssignClid ,enterpriseId, cno});
			}
			if (argsList.size() > 0) {
				String updateClientSql="update client set ob_clid=?, ob_clid_left_assign_value=? where enterprise_id=? and cno=?";
				int[] argsType = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
				jdbcTemplate.batchUpdate(updateClientSql, argsList, argsType);
			}
		}
	}
}
