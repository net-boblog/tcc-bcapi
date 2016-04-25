package com.tinet.api.boss2.model;

import java.math.BigDecimal;

import com.tinet.common.inc.Const;


public class MobileVirtual {

	private String mobile;
	private BigDecimal rent;
	private Integer status;
	private Integer isSign;
	private String areaCode;
	private Integer carrier;
	private String carrierDesc;
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}
	public BigDecimal getRent() {
		return rent;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}
	public Integer getIsSign() {
		return isSign;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public Integer getCarrier() {
		return carrier;
	}

	public void setCarrier(Integer carrier) {
		this.carrier = carrier;
	}
	
	public String getCarrierDesc() {
		if (this.getCarrier()!=null) {
			switch (this.getCarrier().intValue()) {
				case Const.MOBILE_CARRIER_CUCC:
					carrierDesc = "联通";
					break;
				case Const.MOBILE_CARRIER_CTCC:
					carrierDesc = "电信";
					break;
				case Const.MOBILE_CARRIER_CMCC:
					carrierDesc = "移动";
					break;
				case Const.MOBILE_CARRIER_OTHER:
					carrierDesc = "其他";
					break;
				default:
					break;
			}
		}
		return carrierDesc;
	}

	public void setCarrierDesc(String carrierDesc) {
		this.carrierDesc = carrierDesc;
	}
}
