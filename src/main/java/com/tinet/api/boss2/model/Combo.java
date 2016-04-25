package com.tinet.api.boss2.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tinet.common.inc.Const;
public class Combo extends BaseStandardEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 3554947089508101859L;
	
	private String name;	// 套餐名称
	private BigDecimal rent;	// 套餐资费
	private Integer threshold;	// 计时阈值。业务计时阈值（业务允许的时长），单位(分钟)，指单个座席的分钟数
	private Integer period;	// 计费周期: 1-自然月	2-自然年
	private Integer valid;	// 是否有效: 0-无效	1-有效
	private String remark;	// 备注
	private Date createTime; // 创建时间
	
	public Combo(){
		this.period = Const.COMOBO_PERIOD_MONTH;
		this.valid = Const.COMOBO_VALID_ON;
		this.createTime = new Date();
	}
	
	public Combo(int id) {
		this.setId(id);
	}
	
	
	// Property accessors
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getRent() {
		return rent;
	}
	
	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public Integer getThreshold() {
		return threshold;
	}
	
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Integer getPeriod() {
		return period;
	}
	
	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getValid() {
		return valid;
	}
	
	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
