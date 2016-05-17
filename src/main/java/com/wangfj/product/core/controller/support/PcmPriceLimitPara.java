package com.wangfj.product.core.controller.support;

import java.math.BigDecimal;

public class PcmPriceLimitPara {

	private BigDecimal upper;//上限阀值
	
	private int upperStatus;//上限阀值状态
	
	private BigDecimal lower;//下限阀值
	
	private int lowerStatus;//下限阀值状态
	
	private String shopSid;//门店号
	
	private String shopName;//门店名
	
	private String shopCode;//门店编号
	
	private String createName;//创建人
	
	private String updateName;//修改人
	
	private String actionCode;

	public BigDecimal getUpper() {
		return upper;
	}

	public void setUpper(BigDecimal upper) {
		this.upper = upper;
	}

	public int getUpperStatus() {
		return upperStatus;
	}

	public void setUpperStatus(int upperStatus) {
		this.upperStatus = upperStatus;
	}

	public BigDecimal getLower() {
		return lower;
	}

	public void setLower(BigDecimal lower) {
		this.lower = lower;
	}

	public int getLowerStatus() {
		return lowerStatus;
	}

	public void setLowerStatus(int lowerStatus) {
		this.lowerStatus = lowerStatus;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	@Override
	public String toString() {
		return "PcmPriceLimitPara [upper=" + upper + ", upperStatus=" + upperStatus + ", lower="
				+ lower + ", lowerStatus=" + lowerStatus + ", shopSid=" + shopSid + ", shopName="
				+ shopName + ", shopCode=" + shopCode + ", createName=" + createName
				+ ", updateName=" + updateName + ", actionCode=" + actionCode + "]";
	}
}
