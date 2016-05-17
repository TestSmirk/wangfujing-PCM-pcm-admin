package com.wangfj.product.core.controller.support;

/**
 * Created by wangxuan on 2016-03-07 0007.
 */
public class PcmSkuQueryPara {

	private String brandGroupCode;// 集团品牌编码

	private String colorSid;// 色系

	private String modelCode;// 规格

	private String proType;// 商品类型

	private String skuCode;// sku编码

	private String skuName;// sku名称

	private String isAddTag;// 是否已加入关键字

	private String tagSid;// 关键字的sid

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode;
	}

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

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getIsAddTag() {
		return isAddTag;
	}

	public void setIsAddTag(String isAddTag) {
		this.isAddTag = isAddTag;
	}

	public String getTagSid() {
		return tagSid;
	}

	public void setTagSid(String tagSid) {
		this.tagSid = tagSid;
	}

	@Override
	public String toString() {
		return "PcmSkuQueryPara{" + "brandGroupCode='" + brandGroupCode + '\'' + ", colorSid='"
				+ colorSid + '\'' + ", modelCode='" + modelCode + '\'' + ", proType='" + proType
				+ '\'' + ", skuCode='" + skuCode + '\'' + ", skuName='" + skuName + '\''
				+ ", isAddTag='" + isAddTag + '\'' + ", tagSid='" + tagSid + '\'' + '}';
	}
}
