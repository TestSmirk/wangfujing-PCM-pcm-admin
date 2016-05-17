package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

public class PcmContractLogPara {
	private Long sid;
	/**
	 * 要约号
	 */
	private String contractCode;
	/**
	 * 门店编码
	 */
	@NotNull(message = "{storeCode.isNotNull}")
	private String storeCode;
	/**
	 * 供应商编码
	 */
	@NotNull(message = "{supplyCode.isNotNull}")
	private String supplyCode;
	/**
	 * 经营方式
	 */
	// @NotNull(message = "{manageType.isNotNull}")
	private String manageType;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	@Override
	public String toString() {
		return "PcmContractLogPara [sid=" + sid + ", contractCode=" + contractCode + ", storeCode="
				+ storeCode + ", supplyCode=" + supplyCode + ", manageType=" + manageType + "]";
	}

}
