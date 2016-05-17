package com.wangfj.product.core.controller.support;

public class PcmProDetailPara {
	private Long sid;
	/**
	 * 商品明细表编码
	 */
	private String productDetailSid;
	/**
	 * 产品表SID
	 */
	private String productSid;
	/**
	 * 规格
	 */
	private String proStanSid;
	/**
	 * 色系
	 */
	private Long proColorSid;
	/**
	 * 色码
	 */
	private String proColorName;
	/**
	 * 特性
	 */
	private String features;
	/**
	 * 操作类型(0未修改1修改2添加)
	 */
	private String type;
	private String searchKey;

	private String keyWord;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getProductDetailSid() {
		return productDetailSid;
	}

	public void setProductDetailSid(String productDetailSid) {
		this.productDetailSid = productDetailSid;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getProStanSid() {
		return proStanSid;
	}

	public void setProStanSid(String proStanSid) {
		this.proStanSid = proStanSid;
	}

	public Long getProColorSid() {
		return proColorSid;
	}

	public void setProColorSid(Long proColorSid) {
		this.proColorSid = proColorSid;
	}

	public String getProColorName() {
		return proColorName;
	}

	public void setProColorName(String proColorName) {
		this.proColorName = proColorName;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PcmProDetailPara [sid=" + sid + ", productDetailSid=" + productDetailSid
				+ ", productSid=" + productSid + ", proStanSid=" + proStanSid + ", proColorSid="
				+ proColorSid + ", proColorName=" + proColorName + ", features=" + features
				+ ", type=" + type + "]";
	}

}
