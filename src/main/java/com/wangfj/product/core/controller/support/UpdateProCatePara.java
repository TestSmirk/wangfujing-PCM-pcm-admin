package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

public class UpdateProCatePara {
	/**
	 * 产品SPU编码
	 */
	@NotNull(message = "{UpdateProCatePara.productSid.isNotNull}")
	private String productSid;
	/**
	 * 工业分类编码
	 */
	@NotNull(message = "{UpdateProCatePara.categorySid.isNotNull}")
	private String categorySid;

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

}
