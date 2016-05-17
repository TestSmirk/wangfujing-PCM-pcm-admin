package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

public class UpdateProShoppePara {
	/**
	 * 专柜商品编码
	 */
	@NotNull(message = "{UpdateProShoppePara.shoppeProSid.isNotNull}")
	private String shoppeProSid;

	/**
	 * 专柜编码
	 */
	@NotNull(message = "{UpdateProCatePara.shoppeSid.isNotNull}")
	private String shoppeSid;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(String shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

}
