package com.wangfj.product.core.controller.support;

public class ProductPhotoPara {
	/**
	 * 色系编码
	 */
	private String colorSid;
	/**
	 * 款号
	 */
	private String modelCode;
	/**
	 * 集团品牌编码
	 */
	private String brandCode;
	/**
	 * 工业分类编码
	 */
	private String category;

	public String getColorSid() {
		return colorSid;
	}

	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductPhotoPara [colorSid=" + colorSid + ", modelCode=" + modelCode
				+ ", brandCode=" + brandCode + ", category=" + category + "]";
	}

}
