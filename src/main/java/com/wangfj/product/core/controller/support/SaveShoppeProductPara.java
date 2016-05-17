package com.wangfj.product.core.controller.support;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.wangfj.product.maindata.domain.vo.BarcodeDto;

/**
 * 新增专柜商品PARA
 * 
 * @Class Name SaveShoppeProductDto.java
 * @Author wangsy
 * @Create In 2015年7月15日
 */
public class SaveShoppeProductPara {
	@NotNull(message = "{SaveShoppeProductPara.skuSid.isNotNull}")
	private String skuSid;/* skuSID */
	@NotNull(message = "{SaveShoppeProductPara.skuName.isNotNull}")
	private String skuName;/* SKU标准品名 */
	@NotNull(message = "{SaveShoppeProductPara.supplierCode.isNotNull}")
	private String supplierCode;/* 供应商SID */
	@NotNull(message = "{SaveShoppeProductPara.counterCode.isNotNull}")
	private String counterCode; /* 专柜SID */
	@NotNull(message = "{SaveShoppeProductPara.shopCode.isNotNull}")
	private String shopCode; /* 门店编码 */
	private String erpProductCode;/* 商品大码（商品对应的门店ERP编码） */
	private String brandSid;/* 门店品牌编码 */
	@NotNull(message = "统计分类不能为空")
	private String finalClassiFicationCode;/* 末级统计分类SID */
	private String productName;/* 商品名称/超市商品为注册商标名 */
	private String productAbbr;/* 商品简称(最多18个中文字符)。 */
	@NotNull(message = "{SaveShoppeProductPara.unitCode.isNotNull}")
	private String unitCode;/* 销售单位编码（单位字典中的编码）(销售单位) */
	private String inputTax;/* 进项税 */
	private String outputTax;/* 销项税 */
	private String consumptionTax;/* 消费税 */
	private String rate;/* 扣率 */
	private String rate_price;/* 扣率/进价 */
	private String purchasePrice_taxRebate;/* 扣率/含税进价 */
	private String discountLimit;/* 折扣底限 */
	private String placeOfOrigin;/* 产地 */
	private String countryOfOrigin;/* 原产地 */
	private String processingType;/* 加工类型 */
	@NotNull(message = "{SaveShoppeProductPara.marketPrice.isNotNull}")
	private String marketPrice;/* 吊牌价（原价） */
	private String salePrice;/* 售价（现价） */
	private String remarks;/* 备注 */
	private String offerNumber;/* 要约号 */
	private String entryNumber;/* 录入人员编号 */
	private String procurementPersonnelNumber;/* 采购人员编号 */
	@NotNull(message = "{SaveShoppeProductPara.manageCateGory.isNotNull}")
	private String manageCateGory;/* 管理分类SID */
	private String isAdjustPrice;/* 是否允许ERP调价 */
	private String isPromotion;/* 是否允许ERP促销 */
	private String inventory;/* 库存 */
	private String modelNum;/* 货号 */
	@NotNull(message = "{SaveShoppeProductPara.type.isNotNull}")
	private String type;/* 类型（0百货，1超市) */
	private List<BarcodeDto> barcodes;/* 条码 */

	public String getFinalClassiFicationCode() {
		return finalClassiFicationCode;
	}

	public void setFinalClassiFicationCode(String finalClassiFicationCode) {
		this.finalClassiFicationCode = finalClassiFicationCode;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getOfferNumber() {
		return offerNumber;
	}

	public void setOfferNumber(String offerNumber) {
		this.offerNumber = offerNumber;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
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

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getInputTax() {
		return inputTax;
	}

	public void setInputTax(String inputTax) {
		this.inputTax = inputTax;
	}

	public String getOutputTax() {
		return outputTax;
	}

	public void setOutputTax(String outputTax) {
		this.outputTax = outputTax;
	}

	public String getConsumptionTax() {
		return consumptionTax;
	}

	public void setConsumptionTax(String consumptionTax) {
		this.consumptionTax = consumptionTax;
	}

	public String getRate_price() {
		return rate_price;
	}

	public void setRate_price(String rate_price) {
		this.rate_price = rate_price;
	}

	public String getPurchasePrice_taxRebate() {
		return purchasePrice_taxRebate;
	}

	public void setPurchasePrice_taxRebate(String purchasePrice_taxRebate) {
		this.purchasePrice_taxRebate = purchasePrice_taxRebate;
	}

	public String getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(String discountLimit) {
		this.discountLimit = discountLimit;
	}

	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getProcessingType() {
		return processingType;
	}

	public void setProcessingType(String processingType) {
		this.processingType = processingType;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEntryNumber() {
		return entryNumber;
	}

	public void setEntryNumber(String entryNumber) {
		this.entryNumber = entryNumber;
	}

	public String getProcurementPersonnelNumber() {
		return procurementPersonnelNumber;
	}

	public void setProcurementPersonnelNumber(String procurementPersonnelNumber) {
		this.procurementPersonnelNumber = procurementPersonnelNumber;
	}

	public String getManageCateGory() {
		return manageCateGory;
	}

	public void setManageCateGory(String manageCateGory) {
		this.manageCateGory = manageCateGory;
	}

	public String getIsAdjustPrice() {
		return isAdjustPrice;
	}

	public void setIsAdjustPrice(String isAdjustPrice) {
		this.isAdjustPrice = isAdjustPrice;
	}

	public String getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getSkuSid() {
		return skuSid;
	}

	public void setSkuSid(String skuSid) {
		this.skuSid = skuSid;
	}

	public List<BarcodeDto> getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(List<BarcodeDto> barcodes) {
		this.barcodes = barcodes;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	@Override
	public String toString() {
		return "SaveShoppeProductPara [skuSid=" + skuSid + ", skuName=" + skuName
				+ ", supplierCode=" + supplierCode + ", counterCode=" + counterCode + ", shopCode="
				+ shopCode + ", erpProductCode=" + erpProductCode + ", brandSid=" + brandSid
				+ ", finalClassiFicationCode=" + finalClassiFicationCode + ", productName="
				+ productName + ", productAbbr=" + productAbbr + ", unitCode=" + unitCode
				+ ", inputTax=" + inputTax + ", outputTax=" + outputTax + ", consumptionTax="
				+ consumptionTax + ", rate=" + rate + ", rate_price=" + rate_price
				+ ", purchasePrice_taxRebate=" + purchasePrice_taxRebate + ", discountLimit="
				+ discountLimit + ", placeOfOrigin=" + placeOfOrigin + ", countryOfOrigin="
				+ countryOfOrigin + ", processingType=" + processingType + ", marketPrice="
				+ marketPrice + ", salePrice=" + salePrice + ", remarks=" + remarks
				+ ", offerNumber=" + offerNumber + ", entryNumber=" + entryNumber
				+ ", procurementPersonnelNumber=" + procurementPersonnelNumber
				+ ", manageCateGory=" + manageCateGory + ", isAdjustPrice=" + isAdjustPrice
				+ ", isPromotion=" + isPromotion + ", inventory=" + inventory + ", modelNum="
				+ modelNum + ", type=" + type + ", barcodes=" + barcodes + "]";
	}

}
