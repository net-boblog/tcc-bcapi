package com.tinet.api.boss2.web.imp;

import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.tinet.api.boss2.model.CommonRequest;
import com.tinet.api.boss2.model.Enterprise;
import com.tinet.api.boss2.response.enity.CommonResponse;
import com.tinet.api.boss2.response.enity.EnterpriseResponse;
import com.tinet.api.boss2.response.enity.StringDataResponse;
import com.tinet.api.boss2.service.EnterpriseService;
import com.tinet.api.boss2.web.EnterpriseWeb;
import com.tinet.common.inc.Const;
import com.tinet.common.util.StringUtil;
import com.tinet.common.util.ThreeDesCrypt;

/**
 * 客户管理业务接口实现
 * <p>
 *  FileName： EnterpriseWebImp.java
 * <p>
 * Copyright (c) 2015 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author louxue
 * @since 1.0
 * @version 1.0
 */
@WebService(endpointInterface = "com.tinet.api.boss2.web.EnterpriseWeb")
public class EnterpriseWebImp implements EnterpriseWeb {

	@Autowired
	private EnterpriseService enterpriseService;

	/**
	 * 直销客户转移直销部门和直销经理
	 * @author louxue
	 * @param ccicIds 平台id串，英文逗号分隔，与enterpriseIds一一对应
	 * @param enterpriseIds 企业id串，英文逗号分隔，与ccicIds一一对应
	 * @param entityParentId 转移所属上级id
	 * @param entityType 转移所属上级类型
	 * @param comment 转移备注
	 * @param creq
	 * @return
	 */
	@Override
	public CommonResponse updateEntParent(String ccicIds, String enterpriseIds, Integer entityParentId,
			Integer entityType, String comment, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			enterpriseService.updateEntParent(ccicIds, enterpriseIds, entityParentId, entityType, comment);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			crpon.setReturnCode("101");
			crpon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			crpon.setReturnCode("999");
			crpon.setMsg("其他异常，请联系管理员！");
		}
		return crpon;
	}

	/**
	* 测试账户转正
	* @author louxue
	* @param ccicSet 涉及的所有企业的所属平台id set
	* @param enterpriseIds 所有转正的企业id串，英文逗号分隔。
	* @param creq 数据签名对象
	* @return
	*/
	@Override
	public CommonResponse updateEntTest2Direct(Set<Integer> ccicSet, String enterpriseIds, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			// 分平台更新数据
			enterpriseService.executeTest2Direct(ccicSet, enterpriseIds);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			crpon.setReturnCode("101");
			crpon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			crpon.setReturnCode("999");
			crpon.setMsg("其他异常，请联系管理员！");
		}
		return crpon;
	}

	/**
	 * 测试账户有效期延期15天
	 * @author louxue
	 * @param ccicSet 涉及的所有企业的所属平台id set
	 * @param enterpriseIds 所有延期企业id串，英文逗号分隔。
	 * @param creq 数据签名对象
	 * @return
	 */
	@Override
	public CommonResponse updateEntTestExtend15Days(Set<Integer> ccicSet, String enterpriseIds, CommonRequest creq) {
		CommonResponse crpon = new CommonResponse();
		crpon.setReturnCode("0");
		crpon.setMsg("success");
		try {
			// 分平台更新数据
			enterpriseService.executeExtend15Days(ccicSet, enterpriseIds);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			crpon.setReturnCode("101");
			crpon.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			crpon.setReturnCode("999");
			crpon.setMsg("其他异常，请联系管理员！");
		}
		return crpon;
	}

	/**
	 * 重置企业所有座席密码
	 * @author louxue
	 * @param ccicId C2平台id
	 * @param enterpriseId 企业id
	 * @param creq 数据签名对象
	 * @return 返回企业所有座席号及其密码字符串
	 */
	@Override
	public StringDataResponse resetAndGetEntCnosPwdStr(String ccicId, Integer enterpriseId, CommonRequest creq) {
		StringDataResponse resp = new StringDataResponse();
		resp.setReturnCode("0");
		resp.setMsg("success");
		try {
			String data = enterpriseService.getEntCnosPwdStr(ccicId, enterpriseId);
			resp.setData(data);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			resp.setReturnCode("101");
			resp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			resp.setReturnCode("999");
			resp.setMsg("其他异常，请联系管理员！");
		}
		return resp;
	}

	/**
	 * 直销经理在某平台上随机申请一个测试账户
	 * @author louxue
	 * @param ccicId 平台id
	 * @param entityParentId 申请者id
	 * @param entityParentType 申请者类型
	 * @param creq 数据签名对象
	 * @return
	 */
	@Override
	public EnterpriseResponse applyTestEnt(String ccicId, Integer entityParentId, Integer entityParentType, CommonRequest creq) {
		EnterpriseResponse entRresp = new EnterpriseResponse();
		entRresp.setReturnCode("0");
		entRresp.setMsg("success");
		try {
			Enterprise enterprise = enterpriseService.executeApplyTestEnt(ccicId, entityParentId, entityParentType);
			if (enterprise != null) {
				enterprise.setCcicId(Integer.parseInt(ccicId));
				entRresp.setEnterprise(enterprise);
			} else {
				entRresp.setReturnCode("301");
				entRresp.setMsg("当前无空闲测试账号可申请，请联系客服!");
			}
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entRresp.setReturnCode("101");
			entRresp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entRresp.setReturnCode("999");
			entRresp.setMsg("其他异常，请联系管理员！");
		}
		return entRresp;
	}

	/**
	 * 获取企业基本信息
	 * @author louxue
	 * @param ccicId 平台ID 
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名对象
	 * @return
	 */
	@Override
	public EnterpriseResponse getEntBaseInfo(String ccicId, Integer enterpriseId, CommonRequest creq) {
		EnterpriseResponse entRresp = new EnterpriseResponse();
		entRresp.setReturnCode("0");
		entRresp.setMsg("success");
		try {
			Enterprise enterprise = enterpriseService.getEntBaseInfo(ccicId, enterpriseId);
			entRresp.setEnterprise(enterprise);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entRresp.setReturnCode("101");
			entRresp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entRresp.setReturnCode("999");
			entRresp.setMsg("其他异常，请联系管理员！");
		}
		return entRresp;
	}

	/**
	 *获取企业全部信息
	 * @author louxue
	 * @param ccicId 平台ID 
	 * @param enterpriseId 企业ID
	 * @param creq 数据签名对象
	 * @return
	 */
	@Override
	public EnterpriseResponse getEntAllInfo(String ccicId, Integer enterpriseId, CommonRequest creq) {
		EnterpriseResponse entRresp = new EnterpriseResponse();
		entRresp.setReturnCode("0");
		entRresp.setMsg("success");
		try {
			Enterprise enterprise = enterpriseService.getEntAllInfo(ccicId, enterpriseId);
			entRresp.setEnterprise(enterprise);
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entRresp.setReturnCode("101");
			entRresp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entRresp.setReturnCode("999");
			entRresp.setMsg("其他异常，请联系管理员！");
		}
		return entRresp;
	}

	/**
	 * 查询直销经理可做业务变更的企业信息
	 * @author louxue
	 * @param ccicId 平台ID 
	 * @param enterpriseId 企业ID
	 * @param entityParent 直销经理ID
	 * @param creq 数据签名对象
	 * @return
	 */
	@Override
	public EnterpriseResponse getEntInfoForBizChange(String ccicId, Integer enterpriseId, Integer entityParent,
			CommonRequest creq) {
		EnterpriseResponse entRresp = new EnterpriseResponse();
		entRresp.setReturnCode("0");
		entRresp.setMsg("success");
		try {
			Map<String, Object> map = enterpriseService.getEntInfoForBizChange(ccicId, enterpriseId, entityParent);
			entRresp.setEnterprise((Enterprise) map.get("enterprise"));
			if (StringUtil.isNotEmpty((String)map.get("error"))) {
				entRresp.setReturnCode("201");
				entRresp.setMsg(map.get("error").toString());
			}
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			entRresp.setReturnCode("101");
			entRresp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			entRresp.setReturnCode("999");
			entRresp.setMsg("其他异常，请联系管理员！");
		}
		return entRresp;
	}

	/**
	 * 重置企业后台admin登录密码
	 * @author louxue
	 * @param ccicId C2平台id
	 * @param enterpriseId 企业id
	 * @param creq 数据签名对象
	 * @return 返回对admin重置后的密码，该密码为可逆加密密文
	 */
	@Override
	public StringDataResponse resetEntUserAdminPwd(String ccicId, Integer enterpriseId, CommonRequest creq) {
		StringDataResponse resp = new StringDataResponse();
		resp.setReturnCode("0");
		resp.setMsg("success");
		try {
			String data = enterpriseService.resetEntUserAdminPwd(ccicId, enterpriseId);
			// 可逆加密
			resp.setData(ThreeDesCrypt.encrypt(Const.APP_KEY.get(creq.getClientId()), data));
		} catch (DataAccessException dataEx) {
			dataEx.printStackTrace();
			resp.setReturnCode("101");
			resp.setMsg("数据库操作失败！");
		} catch (Exception e) {
			e.printStackTrace();
			resp.setReturnCode("999");
			resp.setMsg("其他异常，请联系管理员！");
		}
		return resp;
	}

}
