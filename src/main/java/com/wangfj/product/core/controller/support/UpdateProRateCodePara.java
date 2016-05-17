package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

public class UpdateProRateCodePara {
	/**
	 * 专柜商品编码
	 */
	@NotNull(message = "{UpdateProShoppePara.shoppeProSid.isNotNull}")
	private String shoppeProSid;
	/**
	 * 扣率码
	 */
	@NotNull(message = "{UpdateProShoppePara.rateCode.isNotNull}")
	private String rateCode;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

}
