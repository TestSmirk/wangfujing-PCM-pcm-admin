package com.wangfj.product.core.controller.support;

/**
 * Created by wangxuan on 2016-03-07 0007.
 */
public class PcmShoppeProductQueryPara {

	private String productCode;//专柜商品编码

	private String storeCode;// 门店编码

	private String supplierCode;// 供应商编码

	private String manageCategory;// 管理分类sid

	private String counterCode;// 专柜编码

	private String brandCode;// 门店品牌编码

	private String brandSid;// 门店品牌sid

	private String isAddTag;// 是否已加入标签

	private String tagSid;// 标签的sid

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getManageCategory() {
		return manageCategory;
	}

	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
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

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	@Override
	public String toString() {
		return "PcmShoppeProductQueryPara{" +
				"productCode='" + productCode + '\'' +
				", storeCode='" + storeCode + '\'' +
				", supplierCode='" + supplierCode + '\'' +
				", manageCategory='" + manageCategory + '\'' +
				", counterCode='" + counterCode + '\'' +
				", brandCode='" + brandCode + '\'' +
				", brandSid='" + brandSid + '\'' +
				", isAddTag='" + isAddTag + '\'' +
				", tagSid='" + tagSid + '\'' +
				'}';
	}
}
