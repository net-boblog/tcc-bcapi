package com.tinet.api.boss2.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinet.api.boss2.dao.BusinessDao;
import com.tinet.api.boss2.dao.ClientDao;
import com.tinet.api.boss2.dao.CommonDao;
import com.tinet.api.boss2.dao.EnterpriseDao;
import com.tinet.api.boss2.dao.EnterpriseSettingDao;
import com.tinet.api.boss2.dao.EnterpriseUserDao;
import com.tinet.api.boss2.dao.EntityDao;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.core.jdbc.DatabaseContextHolder;

/**
 * 客户管理业务逻辑处理层
 * <p>
 *  FileName： EnterpriseService.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@Service("enterpriseService")
public class EnterpriseService {
	@Autowired
	private EntityDao entityDao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private EnterpriseUserDao enterpriseUserDao;
	@Autowired
	private EnterpriseSettingDao enterpriseSettingDao;
	@Autowired
	private CommonDao commonDao;
	
	
	/**
	 * 获取企业基本信息
	 * @author louxue
	 * @param ccicId 平台id
	 * @param enterpriseId 企业id
	 * @return
	 */
	public Enterprise getEntBaseInfo(String ccicId, Integer enterpriseId){
		DatabaseContextHolder.setDBType(ccicId); // 切换数据源id
		Enterprise enterprise = enterpriseDao.getEntBaseInfo(enterpriseId);
		enterprise.setCcicId(Integer.parseInt(ccicId));
		return enterprise;
	}
	
	/**
	 * 获取企业全部信息
	 * @author louxue
	 * @param ccicId 平台id
	 * @param enterpriseId 企业id
	 * @return
	 */
	public Enterprise getEntAllInfo(String ccicId, Integer enterpriseId){
		DatabaseContextHolder.setDBType(ccicId); // 切换数据源id
		Enterprise enterprise = enterpriseDao.getEntAllInfo(enterpriseId);
		enterprise.setCcicId(Integer.parseInt(ccicId));
		return enterprise;
	}
	
	/**
	 * 查询直销经理可做业务变更的企业信息
	 * @param ccicId 平台ID 
	 * @param enterpriseId 企业ID
	 * @param entityParent 直销经理ID
	 * @return
	 */
	public Map<String, Object> getEntInfoForBizChange(String ccicId, Integer enterpriseId, Integer entityParent){
		DatabaseContextHolder.setDBType(ccicId); // 切换数据源id
		return enterpriseDao.getEntForBizChange(enterpriseId, entityParent);
	}
	
	/**
	 * 直销客户转移直销部门和直销经理
	 * @author louxue
	 * @param ccicIds 平台id串，英文逗号分隔，与enterpriseIds一一对应
	 * @param enterpriseIds 企业id串，英文逗号分隔，与ccicIds一一对应
	 * @param entityParentId 转移所属上级id
	 * @param entityType 转移所属上级类型
	 * @param comment 转移备注
	 * @return
	 */
	public void updateEntParent(String ccicIds, String enterpriseIds, int entityParentId, Integer entityType,
			String comment) {
		String[] ccicIdArry = ccicIds.split(",");
		String[] enterpriseIdArry = enterpriseIds.split(",");
		for (int i = 0; i < ccicIdArry.length; i++) {
			String entityComment = "";
			if (comment != null && !"".equals(comment)) {
				entityComment = " 转移备注:" + comment;
			}
			DatabaseContextHolder.setDBType(ccicIdArry[i]); // 切换数据源id
			entityDao.updateEntParent(enterpriseIdArry[i], entityParentId, entityType, entityComment);
		}
	}

	/**
	 * 测试账户转正
	 * @author louxue
	 * @param ccicSet 涉及的所有企业的所属平台id set
	 * @param enterpriseIds 所有转正的企业id串，英文逗号分隔。
	 * @return
	 */
	public void executeTest2Direct(Set<Integer> ccicSet, String enterpriseIds) {
		for (Iterator<Integer> iterator = ccicSet.iterator(); iterator.hasNext();) {
			Integer ccicId = iterator.next();
			DatabaseContextHolder.setDBType(ccicId.toString()); // 切换数据源id
			businessDao.executeTest2Direct(enterpriseIds);
		}
	}

	/**
	 * 测试账户延期
	 * @author louxue
	 * @param ccicSet 涉及的所有企业的所属平台id set
	 * @param enterpriseIds 所有延期企业id串，英文逗号分隔。
	 * @return
	 */
	public void executeExtend15Days(Set<Integer> ccicSet, String enterpriseIds) {
		for (Iterator<Integer> iterator = ccicSet.iterator(); iterator.hasNext();) {
			Integer ccicId = iterator.next();
			DatabaseContextHolder.setDBType(ccicId.toString()); // 切换数据源id
			enterpriseSettingDao.executeExtend15Days(enterpriseIds);
		}
	}

	/**
	 * 重置企业所有座席密码
	 * @author louxue
	 * @param ccicId
	 * @param enterpriseId
	 * @return  返回企业所有座席号及其密码字符串
	 */
	public String getEntCnosPwdStr(String ccicId, Integer enterpriseId) {
		DatabaseContextHolder.setDBType(ccicId);
		return clientDao.updateCnoPwd(enterpriseId);
	}
	
	/**
	 * 重置企业后台admin登录密码
	 * @author louxue
	 * @param ccicId C2平台id
	 * @param enterpriseId 企业id
	 * @param creq 数据签名对象
	 * @return 返回对admin重置后的明文密码
	 */
	public String resetEntUserAdminPwd(String ccicId, Integer enterpriseId) {
		DatabaseContextHolder.setDBType(ccicId);
		return enterpriseUserDao.resetEntAdminPwd(enterpriseId);
	}
	
	/**
	 * 随机获取平台ccicId上的一个空闲测试账户，获取成功修改其使用状态，有效期和所属经理信息。
	 * @author louxue
	 * @param ccicId 平台id
	 * @param entityParentId 申请者id
	 * @param entityParentType 申请者类型
	 * @return
	 */
	public Enterprise executeApplyTestEnt(String ccicId, Integer entityParentId, Integer entityParentType) {
		DatabaseContextHolder.setDBType(ccicId);
		return enterpriseDao.executeApplyTestEnt(entityParentId, entityParentType);
	}
}
