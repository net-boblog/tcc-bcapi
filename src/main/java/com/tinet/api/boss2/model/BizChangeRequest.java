package com.tinet.api.boss2.model;

import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class BizChangeRequest extends BaseStandardEntity implements java.io.Serializable{
	// 企业对象
	private Enterprise enterprise;
	// 企业开通功能名称集合
	private String[] functionNameSet;
	// 目的码及其区号map
	private Map<String, String> trunkWithAreaCode;
	// 新增的热线号码
	private List<EnterpriseHotline> entHotlineAdd;
	// 删除的热线号码
	private List<EnterpriseHotline> entHotlineDel;
	// 新增的直线号码
	private List<Trunk> entTrunkAdd;
	// 删除的直线号码
	private List<Trunk> entTrunkDel;
	// 新增的热线号码的目的码
	private List<Trunk> entHotlineTrunkAdd;
	// 删除的热线号码的目的码
	private List<Trunk> entHotlineTrunkDel;
	// 新增手机虚拟号
	private List<MobileVirtual> mobileVirtualAdd;
	// 删除手机虚拟号
	private List<MobileVirtual> mobileVirtualDel;
	// 目的码有变化的热线号码
	private List<EnterpriseHotline> entHotlineTrunkChange;
	
	public Enterprise getEnterprise() {
		return enterprise;
	}
	
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	public String[] getFunctionNameSet() {
		return functionNameSet;
	}

	public void setFunctionNameSet(String[] functionNameSet) {
		this.functionNameSet = functionNameSet;
	}

	public Map<String, String> getTrunkWithAreaCode() {
		return trunkWithAreaCode;
	}
	
	public void setTrunkWithAreaCode(Map<String, String> trunkWithAreaCode) {
		this.trunkWithAreaCode = trunkWithAreaCode;
	}
	
	public List<EnterpriseHotline> getEntHotlineAdd() {
		return entHotlineAdd;
	}
	
	public void setEntHotlineAdd(List<EnterpriseHotline> entHotlineAdd) {
		this.entHotlineAdd = entHotlineAdd;
	}
	
	public List<EnterpriseHotline> getEntHotlineDel() {
		return entHotlineDel;
	}
	
	public void setEntHotlineDel(List<EnterpriseHotline> entHotlineDel) {
		this.entHotlineDel = entHotlineDel;
	}
	
	public List<Trunk> getEntTrunkAdd() {
		return entTrunkAdd;
	}
	
	public void setEntTrunkAdd(List<Trunk> entTrunkAdd) {
		this.entTrunkAdd = entTrunkAdd;
	}
	
	public List<Trunk> getEntTrunkDel() {
		return entTrunkDel;
	}
	
	public void setEntTrunkDel(List<Trunk> entTrunkDel) {
		this.entTrunkDel = entTrunkDel;
	}
	
	public List<Trunk> getEntHotlineTrunkAdd() {
		return entHotlineTrunkAdd;
	}
	public void setEntHotlineTrunkAdd(List<Trunk> entHotlineTrunkAdd) {
		this.entHotlineTrunkAdd = entHotlineTrunkAdd;
	}
	
	public List<Trunk> getEntHotlineTrunkDel() {
		return entHotlineTrunkDel;
	}
	
	public void setEntHotlineTrunkDel(List<Trunk> entHotlineTrunkDel) {
		this.entHotlineTrunkDel = entHotlineTrunkDel;
	}
	
	public List<MobileVirtual> getMobileVirtualAdd() {
		return mobileVirtualAdd;
	}
	public void setMobileVirtualAdd(List<MobileVirtual> mobileVirtualAdd) {
		this.mobileVirtualAdd = mobileVirtualAdd;
	}
	
	public List<MobileVirtual> getMobileVirtualDel() {
		return mobileVirtualDel;
	}
	
	public void setMobileVirtualDel(List<MobileVirtual> mobileVirtualDel) {
		this.mobileVirtualDel = mobileVirtualDel;
	}
	
	public List<EnterpriseHotline> getEntHotlineTrunkChange() {
		return entHotlineTrunkChange;
	}
	
	public void setEntHotlineTrunkChange(List<EnterpriseHotline> entHotlineTrunkChange) {
		this.entHotlineTrunkChange = entHotlineTrunkChange;
	}
	
	
}
