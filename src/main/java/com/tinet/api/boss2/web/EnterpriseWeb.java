package com.tinet.api.boss2.web;

import java.util.Set;

import javax.jws.WebService;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
/**
 * 客户管理业务接口
 * <p>
 *  FileName： EnterpriseWeb.java
 * <p>
 * Copyright (c) 2006-2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 * @see com.tinet.api.boss2.web.imp.EnterpriseWebImp
 */
@WebService 
public interface EnterpriseWeb {
	
	/**
	 * 获取企业基本信息
	 * @author louxue
	 * @param ccicId 平台ID 
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名对象
	 * @return
	 */
	public EnterpriseResponse getEntBaseInfo(String ccicId, Integer enterpriseId, CommonRequest creq);
	
	/**
	 * 获取企业全部信息
	 * @author louxue
	 * @param ccicId 平台ID 
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名对象
	 * @return
	 */
	public EnterpriseResponse getEntAllInfo(String ccicId, Integer enterpriseId, CommonRequest creq);
	
	/**
	 * 查询直销经理可做业务变更的企业信息
	 * @author louxue
	 * @param ccicId 平台ID 
	 * @param enterpriseId 企业ID
	 * @param entityParent 直销经理ID
	 * @param creq 数据签名对象
	 * @return
	 */
	public EnterpriseResponse getEntInfoForBizChange(String ccicId, Integer enterpriseId, Integer entityParent, CommonRequest creq);
	
	/**
	 * 直销客户转移直销部门和直销经理
	 * @author louxue
	 * @param ccicIds 平台id串，英文逗号分隔，与enterpriseIds一一对应
	 * @param enterpriseIds 企业id串，英文逗号分隔，与ccicIds一一对应
	 * @param entityParentId 转移所属上级id
	 * @param entityType 转移所属上级类型
	 * @param comment 转移备注
	 * @param creq 数据签名对象
	 * @return
	 */
   public CommonResponse updateEntParent(String ccicIds, String enterpriseIds, Integer entityParentId,
			Integer entityType, String comment, CommonRequest creq); 
   
   /**
	* 测试账户转正
	* @author louxue
	* @param ccicSet 涉及的所有企业的所属平台id set
	* @param enterpriseIds 所有转正的企业id串，英文逗号分隔。
	* @param creq 数据签名对象
	* @return
	*/
   public CommonResponse updateEntTest2Direct(Set<Integer> ccicSet, String enterpriseIds, CommonRequest creq);
   
   /**
	* 测试账户有效期延期15天
	* @author louxue
	* @param ccicSet 涉及的所有企业的所属平台id set
	* @param enterpriseIds 所有延期企业id串，英文逗号分隔。
	* @param creq 数据签名对象
	* @return
	*/
  public CommonResponse updateEntTestExtend15Days(Set<Integer> ccicSet, String enterpriseIds, CommonRequest creq);
  
  /**
   * 重置企业所有座席密码
   * @author louxue
   * @param ccicId C2平台id
   * @param enterpriseId 企业id
   * @param creq 数据签名对象
   * @return 返回企业所有座席号及其密码字符串
   */
  public StringDataResponse resetAndGetEntCnosPwdStr(String ccicId, Integer enterpriseId, CommonRequest creq);
  
  /**
	* 重置企业后台admin登录密码
	* @author louxue
	* @param ccicId C2平台id
	* @param enterpriseId 企业id
	* @param creq 数据签名对象
	* @return 返回对admin重置后的密码，该密码为可逆加密密文
	*/
  public StringDataResponse resetEntUserAdminPwd(String ccicId, Integer enterpriseId, CommonRequest creq);
  
  /**
   * 直销经理在某平台上随机申请一个测试账户
   * @author louxue
   * @param ccicId 平台id
   * @param entityParentId 申请者id
   * @param entityParentType 申请者类型
   * @param creq 数据签名对象
   * @return
   */
  public EnterpriseResponse applyTestEnt(String ccicId, Integer entityParentId, Integer entityParentType, CommonRequest creq);
} 
