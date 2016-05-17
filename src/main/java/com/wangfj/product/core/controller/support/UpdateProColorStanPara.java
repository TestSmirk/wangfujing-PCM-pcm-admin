package com.wangfj.product.core.controller.support;

public class UpdateProColorStanPara {
	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;
	/**
	 * 商品表SKU编码
	 */
	private String productDetailSid;
	/**
	 * 特性
	 */
	private String features;
	/**
	 * 色码
	 */
	private String proColorName;
	/**
	 * 规码
	 */
	private String proStanSid;
	/**
	 * 款码
	 */
	private String productSku;
	/**
	 * 主属性
	 */
	private String primaryAttr;
	/**
	 * 色系
	 */
	private String proColorSid;

	public String getProColorSid() {
		return proColorSid;
	}

	public void setProColorSid(String proColorSid) {
		this.proColorSid = proColorSid;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getProductDetailSid() {
		return productDetailSid;
	}

	public void setProductDetailSid(String productDetailSid) {
		this.productDetailSid = productDetailSid;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getProColorName() {
		return proColorName;
	}

	public void setProColorName(String proColorName) {
		this.proColorName = proColorName;
	}

	public String getProStanSid() {
		return proStanSid;
	}

	public void setProStanSid(String proStanSid) {
		this.proStanSid = proStanSid;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getPrimaryAttr() {
		return primaryAttr;
	}

	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
	}

	@Override
	public String toString() {
		return "UpdateProColorStanPara [shoppeProSid=" + shoppeProSid + ", productDetailSid="
				+ productDetailSid + ", features=" + features + ", proColorName=" + proColorName
				+ ", proStanSid=" + proStanSid + ", productSku=" + productSku + ", primaryAttr="
				+ primaryAttr + "]";
	}

}
