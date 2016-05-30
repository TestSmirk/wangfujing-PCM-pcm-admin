package com.wangfj.product.core.controller.support;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.wangfj.product.maindata.domain.vo.BarcodeDto;

/**
 * 新增专柜商品PARA
 * 
 * @Class Name SaveShoppeProductParaDs
 * @Author 张学祎
 * @Create In 2015年10月30日
 */
public class SaveShoppeProductParaDs {
	@NotNull(message = "{SaveShoppeProductParaDs.skuSid.isNotNull}")
	private String skuSid;/* skuSID */
	@NotNull(message = "{SaveShoppeProductParaDs.skuName.isNotNull}")
	private String skuName;/* SKU标准品名 */
	@NotNull(message = "{SaveShoppeProductParaDs.supplierCode.isNotNull}")
	private String supplierCode;/* 供应商SID */
	@NotNull(message = "{SaveShoppeProductParaDs.counterCode.isNotNull}")
	private String counterCode; /* 专柜SID */
	@NotNull(message = "{SaveShoppeProductParaDs.shopCode.isNotNull}")
	private String shopCode; /* 门店编码 */
	private String erpProductCode;/* 商品大码（商品对应的门店ERP编码） */
	private String brandSid;/* 门店品牌编码 */
	private String productName;/* 商品名称/超市商品为注册商标名 */
	private String productAbbr;/* 商品简称(最多18个中文字符)。 */
	@NotNull(message = "{SaveShoppeProductParaDs.unitCode.isNotNull}")
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
	@NotNull(message = "{SaveShoppeProductParaDs.marketPrice.isNotNull}")
	private String marketPrice;/* 吊牌价（原价） */
	private String salePrice;/* 售价（现价） */
	private String remarks;/* 备注 */
	private String offerNumber;/* 要约号 */
	private String entryNumber;/* 录入人员编号 */
	private String procurementPersonnelNumber;/* 采购人员编号 */
	//* @NotNull(message = "{SaveShoppeProductParaDs.manageCateGory.isNotNull}") **//
	private String manageCateGory;/* 管理分类SID */
	@NotNull(message = "统计分类不能为空")
	private String finalClassiFicationCode;/* 末级统计分类SID */
	private String isAdjustPrice;/* 是否允许ERP调价 */
	private String isPromotion;/* 是否允许ERP促销 */
	private String inventory;/* 库存 */
	private String modelNum;/* 货号 */
	@NotNull(message = "{SaveShoppeProductParaDs.type.isNotNull}")
	private String type;/* 类型（0百货，1超市) */
	private List<BarcodeDto> barcodes;/* 条码 */
	private String supplyProductCode;/* 供应商商品编码 */
	private String tmsParam;/* 物流类型 1.液体 2.易碎 3.液体与易碎 4.粉末 */
	private String isCod;/* 是否支持货到付款 0支持；1不支持 */
	private String isPacking;/* 可包装 0是，1否,默认(0) */
	private String isCard;/* 可贺卡 0是，1否,默认(1) */
	private String originCountry;/* 原产国 */
	private String stockMode;/* 虚库标记 1.ZK 自库 2.XK 虚库 */
	private String isOriginPackage;/* 是否有原厂包装 0是，1否,默认(1) */
	private String isGift;/*
						 * 赠品范围（缺省值为0。 0表示正常商品， 1表示可以单独销售也可以作为本专柜内的赠品，
						 * 2表示可以单独销售也可以作为本门店内的赠品， 3表示可以单独销售也可以作为全渠道的赠品，
						 * 4表示不可单独销售但是可以作为本专柜内的赠品， 5表示不可单独销售但可以作为本门店内的赠品，
						 * 6表示不可单独销售但可以作为全渠道的赠品。）电商用
						 */
	private String xxhcFlag;/* 是否先销后采 0是，1否,默认(1) */
	private String baseUnitCode;/* 是否有原厂包装 0是，1否,默认(1) */
	
	private String zzColorCode;//电商商品颜色码
	private String zzSizeCode;//电商商品尺寸码
	private String supplyOriginLand;//货源地
	private String shelfLife;//总货架寿命
	private String remainShelLife;//剩余货架寿命
	private String launchDate;//上市日期
	private String season;//季节
	private String applicablePeople;//适应人群
	private String shoppeProType;//电商商品类型

	public String getShoppeProType() {
		return shoppeProType;
	}

	public void setShoppeProType(String shoppeProType) {
		this.shoppeProType = shoppeProType;
	}

	public String getZzColorCode() {
		return zzColorCode;
	}

	public void setZzColorCode(String zzColorCode) {
		this.zzColorCode = zzColorCode;
	}

	public String getZzSizeCode() {
		return zzSizeCode;
	}

	public void setZzSizeCode(String zzSizeCode) {
		this.zzSizeCode = zzSizeCode;
	}

	public String getSupplyOriginLand() {
		return supplyOriginLand;
	}

	public void setSupplyOriginLand(String supplyOriginLand) {
		this.supplyOriginLand = supplyOriginLand;
	}

	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	public String getRemainShelLife() {
		return remainShelLife;
	}

	public void setRemainShelLife(String remainShelLife) {
		this.remainShelLife = remainShelLife;
	}

	public String getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getApplicablePeople() {
		return applicablePeople;
	}

	public void setApplicablePeople(String applicablePeople) {
		this.applicablePeople = applicablePeople;
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

	public String getSupplyProductCode() {
		return supplyProductCode;
	}

	public void setSupplyProductCode(String supplyProductCode) {
		this.supplyProductCode = supplyProductCode;
	}

	public String getTmsParam() {
		return tmsParam;
	}

	public void setTmsParam(String tmsParam) {
		this.tmsParam = tmsParam;
	}

	public String getIsCod() {
		return isCod;
	}

	public void setIsCod(String isCod) {
		this.isCod = isCod;
	}

	public String getIsPacking() {
		return isPacking;
	}

	public void setIsPacking(String isPacking) {
		this.isPacking = isPacking;
	}

	public String getIsCard() {
		return isCard;
	}

	public void setIsCard(String isCard) {
		this.isCard = isCard;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getStockMode() {
		return stockMode;
	}

	public void setStockMode(String stockMode) {
		this.stockMode = stockMode;
	}

	public String getIsOriginPackage() {
		return isOriginPackage;
	}

	public void setIsOriginPackage(String isOriginPackage) {
		this.isOriginPackage = isOriginPackage;
	}

	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public String getXxhcFlag() {
		return xxhcFlag;
	}

	public void setXxhcFlag(String xxhcFlag) {
		this.xxhcFlag = xxhcFlag;
	}

	public String getBaseUnitCode() {
		return baseUnitCode;
	}

	public void setBaseUnitCode(String baseUnitCode) {
		this.baseUnitCode = baseUnitCode;
	}

	public String getFinalClassiFicationCode() {
		return finalClassiFicationCode;
	}

	public void setFinalClassiFicationCode(String finalClassiFicationCode) {
		this.finalClassiFicationCode = finalClassiFicationCode;
	}

	@Override
	public String toString() {
		return "SaveShoppeProductParaDs [skuSid=" + skuSid + ", skuName="
				+ skuName + ", supplierCode=" + supplierCode + ", counterCode="
				+ counterCode + ", shopCode=" + shopCode + ", erpProductCode="
				+ erpProductCode + ", brandSid=" + brandSid + ", productName="
				+ productName + ", productAbbr=" + productAbbr + ", unitCode="
				+ unitCode + ", inputTax=" + inputTax + ", outputTax="
				+ outputTax + ", consumptionTax=" + consumptionTax + ", rate="
				+ rate + ", rate_price=" + rate_price
				+ ", purchasePrice_taxRebate=" + purchasePrice_taxRebate
				+ ", discountLimit=" + discountLimit + ", placeOfOrigin="
				+ placeOfOrigin + ", countryOfOrigin=" + countryOfOrigin
				+ ", processingType=" + processingType + ", marketPrice="
				+ marketPrice + ", salePrice=" + salePrice + ", remarks="
				+ remarks + ", offerNumber=" + offerNumber + ", entryNumber="
				+ entryNumber + ", procurementPersonnelNumber="
				+ procurementPersonnelNumber + ", manageCateGory="
				+ manageCateGory + ", finalClassiFicationCode="
				+ finalClassiFicationCode + ", isAdjustPrice=" + isAdjustPrice
				+ ", isPromotion=" + isPromotion + ", inventory=" + inventory
				+ ", modelNum=" + modelNum + ", type=" + type + ", barcodes="
				+ barcodes + ", supplyProductCode=" + supplyProductCode
				+ ", tmsParam=" + tmsParam + ", isCod=" + isCod
				+ ", isPacking=" + isPacking + ", isCard=" + isCard
				+ ", originCountry=" + originCountry + ", stockMode="
				+ stockMode + ", isOriginPackage=" + isOriginPackage
				+ ", isGift=" + isGift + ", xxhcFlag=" + xxhcFlag
				+ ", baseUnitCode=" + baseUnitCode + ", zzColorCode="
				+ zzColorCode + ", zzSizeCode=" + zzSizeCode
				+ ", supplyOriginLand=" + supplyOriginLand + ", shelfLife="
				+ shelfLife + ", remainShelLife=" + remainShelLife
				+ ", launchDate=" + launchDate + ", season=" + season
				+ ", applicablePeople=" + applicablePeople + ", shoppeProType="
				+ shoppeProType + "]";
	}

}
