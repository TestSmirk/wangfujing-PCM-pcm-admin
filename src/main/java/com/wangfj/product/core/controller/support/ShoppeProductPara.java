package com.wangfj.product.core.controller.support;

/**
 * 专柜商品表
 * 
 * @Class Name PcmShoppeProduct
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class ShoppeProductPara {
	private Long sid;
	private String productCode;// 专柜商品编码
	private String productName;// 专柜商品名称
	private String productAbbr;// 专柜商品简称
	private String skuSid;// 商品表SKUSID
	private String storeCode;// 门店编码
	private String storeName;// 门店名称
	private String counterSid;// 专柜SID
	private String counterCode;// 专柜编码
	private String counterName;// 专柜名
	private String supplierSid;// 供应商SID
	private String supplierCode;// 供应商编码
	private String supplierName;// 供应商名称
	private String brandCode;// 门店品牌编码
	private String brandName;// 门店品牌名称
	private String glCategoryName;// 管理分类编码
	private String manageCategory;// 管理分类编码
	private String isSale;// 专柜商品可售状态
	private String industryCondition;// 业态
	private String fiels2;
	private Integer currentPage;// 当前页
	private Integer pageSize;// 单页行数
	private Integer start;
	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAbbr() {
		return productAbbr;
	}

	public void setProductAbbr(String productAbbr) {
		this.productAbbr = productAbbr;
	}

	public String getSkuSid() {
		return skuSid;
	}

	public void setSkuSid(String skuSid) {
		this.skuSid = skuSid;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCounterSid() {
		return counterSid;
	}

	public void setCounterSid(String counterSid) {
		this.counterSid = counterSid;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public String getSupplierSid() {
		return supplierSid;
	}

	public void setSupplierSid(String supplierSid) {
		this.supplierSid = supplierSid;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getGlCategoryName() {
		return glCategoryName;
	}

	public void setGlCategoryName(String glCategoryName) {
		this.glCategoryName = glCategoryName;
	}

	public String getManageCategory() {
		return manageCategory;
	}

	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getFiels2() {
		return fiels2;
	}

	public void setFiels2(String fiels2) {
		this.fiels2 = fiels2;
	}

	public String getIndustryCondition() {
		return industryCondition;
	}

	public void setIndustryCondition(String industryCondition) {
		this.industryCondition = industryCondition;
	}

	@Override
	public String toString() {
		return "ShoppeProductDto [sid=" + sid + ", productCode=" + productCode + ", productName="
				+ productName + ", productAbbr=" + productAbbr + ", skuSid=" + skuSid
				+ ", storeCode=" + storeCode + ", storeName=" + storeName + ", counterSid="
				+ counterSid + ", counterCode=" + counterCode + ", counterName=" + counterName
				+ ", supplierSid=" + supplierSid + ", supplierCode=" + supplierCode
				+ ", supplierName=" + supplierName + ", brandCode=" + brandCode + ", brandName="
				+ brandName + ", glCategoryName=" + glCategoryName + ", manageCategory="
				+ manageCategory + ", isSale=" + isSale + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit + "]";
	}

}