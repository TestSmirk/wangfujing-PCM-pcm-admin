package com.wangfj.product.core.controller.support;

/**
 * 专柜商品分页查询参数
 * 
 * @Class Name ProductPagePara
 * @Author zhangxy
 * @Create In 2015年7月16日
 */
public class ProductPagePara {
	private Long sid;
	/**
	 * 专柜商品编码
	 */
	private String productCode;
	/**
	 * 商品表SKU 编码
	 */
	private String skuCode;
	/**
	 * 产品编SPU 编码
	 */
	private String spuCode;
	/**
	 * 扣率码
	 */
	private String discountCode;
	/**
	 * 大码
	 */
	private String erpProductCode;
	/**
	 * 款号
	 */
	private String modelCode;
	/**
	 * 供应商商品编码
	 */
	private String barCode;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 专柜商品名称
	 */
	private String productAbbr;
	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 门店品牌编码
	 */
	private String brandCode;
	/**
	 * 门店品牌名称
	 */
	private String brandName;
	private String brandGroupCode;// 集团品牌编码
	private String brandGroupName;// 集团品牌名称
	/**
	 * 专柜编码
	 */
	private String counterCode;
	/**
	 * 专柜编码
	 */
	private String counterName;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 管理分类编码
	 */
	private String manageCategory;
	/**
	 * 颜色编码
	 */
	private String colorCode;
	/**
	 * 颜色名称
	 */
	private String colorName;
	/**
	 * 规格编码
	 */
	private String sizeCode;
	/**
	 * 规格名称
	 */
	private String stanCode;
	/**
	 * 规格名称
	 */
	private String stanName;
	/**
	 * 专柜商品可售状态
	 */
	private String isSale;
	private String skuSale;// sku上架状态
	private String spuSale;// spu上架状态
	/**
	 * 管理类型
	 */
	private String manageType;
	/**
	 * 库存方式
	 */
	private String stockType;
	/**
	 * 货号
	 */
	private String materialNum;
	private String articleNum;
	/**
	 * 是否可打折( 0 是,1 否, 默认0)
	 */
	private String isDiscountable;
	/**
	 * 是否支持货到付款(0支持；1不支持)
	 */
	private String isCOD;
	/**
	 * 销售单位编码
	 */
	private String unitCode;
	/**
	 * 销售单位名
	 */
	private String unitName;
	/**
	 * 零售价
	 */
	private String salesPrice;
	/**
	 * 市场价
	 */
	private String marketPrice;
	/**
	 * 折扣底线
	 */
	private String maxDiscountRate;
	/**
	 * 商品状态0正常；1停售；2停货；3暂停使用；4已删除；5淘汰
	 */
	private String productStatus;
	/**
	 * 经营方式编码
	 */
	private String operateMode;
	/**
	 * 标准扣率
	 */
	private String commissionRate;
	/**
	 * 源系统销售单位
	 */
	private String originSalesUnit;
	/**
	 * 大码类型
	 */
	private String erpSkuType;
	/**
	 * 统计分类编码
	 */
	private String statCategory;
	/**
	 * 渠道sid
	 */
	private String channelSid;
	private String priceChannel;// 价格来源
	/**
	 * 商品类型 0 普通商品（实物类），1 赠品 ，2 礼品，3 虚拟商品（充值卡，购物卡），4 服务类商品
	 * （礼品包装，购物接送服务，停车服务）（注：礼品不可卖，赠品可卖）
	 */
	private String productType;
	/**
	 * 库存数
	 */
	private String stockSum;
	/**
	 * 物流属性
	 */
	private String tmsParam;
	/**
	 * 促销价
	 */
	private String promotionPrice;
	// private String proSelling;// 产品上架状态
	// private String rate;//倍率
	private String negativeStock;
	private String season;
	private String stockMode;// 库存方式
	private String processType;// 加工方式
	private String originLand;// 产地
	private String orderType;// 订货方式
	private String isPromotion;// 是否支持ERP促销
	private String isAdjustPrice;// 是否支持ERP变价
	private String primaryAttr;// 产品主属性
	private String features;// 商品特性
	private String sexSid;// 适合性别编号
	private String awesome;// 被赞次数
	private String supplierSid;// 供应商SID
	
	/**
	 * 当前页
	 */
	private Integer currentPage;
	/**
	 * 单页行数
	 */
	private Integer pageSize;
	private Integer start;
	private Integer limit;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSpuCode() {
		return spuCode;
	}

	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getManageCategory() {
		return manageCategory;
	}

	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

	public String getIsDiscountable() {
		return isDiscountable;
	}

	public void setIsDiscountable(String isDiscountable) {
		this.isDiscountable = isDiscountable;
	}

	public String getIsCOD() {
		return isCOD;
	}

	public void setIsCOD(String isCOD) {
		this.isCOD = isCOD;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getMaxDiscountRate() {
		return maxDiscountRate;
	}

	public void setMaxDiscountRate(String maxDiscountRate) {
		this.maxDiscountRate = maxDiscountRate;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}

	public String getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getOriginSalesUnit() {
		return originSalesUnit;
	}

	public void setOriginSalesUnit(String originSalesUnit) {
		this.originSalesUnit = originSalesUnit;
	}

	public String getErpSkuType() {
		return erpSkuType;
	}

	public void setErpSkuType(String erpSkuType) {
		this.erpSkuType = erpSkuType;
	}

	public String getStatCategory() {
		return statCategory;
	}

	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getStockSum() {
		return stockSum;
	}

	public void setStockSum(String stockSum) {
		this.stockSum = stockSum;
	}

	public String getTmsParam() {
		return tmsParam;
	}

	public void setTmsParam(String tmsParam) {
		this.tmsParam = tmsParam;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	// public String getProSelling() {
	// return proSelling;
	// }
	//
	// public void setProSelling(String proSelling) {
	// this.proSelling = proSelling;
	// }
	//
	// public String getRate() {
	// return rate;
	// }
	//
	// public void setRate(String rate) {
	// this.rate = rate;
	// }

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

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode;
	}

	public String getBrandGroupName() {
		return brandGroupName;
	}

	public void setBrandGroupName(String brandGroupName) {
		this.brandGroupName = brandGroupName;
	}

	public String getSkuSale() {
		return skuSale;
	}

	public void setSkuSale(String skuSale) {
		this.skuSale = skuSale;
	}

	public String getSpuSale() {
		return spuSale;
	}

	public void setSpuSale(String spuSale) {
		this.spuSale = spuSale;
	}

	public String getPriceChannel() {
		return priceChannel;
	}

	public void setPriceChannel(String priceChannel) {
		this.priceChannel = priceChannel;
	}

	public String getStanCode() {
		return stanCode;
	}

	public void setStanCode(String stanCode) {
		this.stanCode = stanCode;
	}

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(String negativeStock) {
		this.negativeStock = negativeStock;
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

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getStockMode() {
		return stockMode;
	}

	public void setStockMode(String stockMode) {
		this.stockMode = stockMode;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}

	public String getIsAdjustPrice() {
		return isAdjustPrice;
	}

	public void setIsAdjustPrice(String isAdjustPrice) {
		this.isAdjustPrice = isAdjustPrice;
	}

	public String getPrimaryAttr() {
		return primaryAttr;
	}

	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getSexSid() {
		return sexSid;
	}

	public void setSexSid(String sexSid) {
		this.sexSid = sexSid;
	}

	public String getAwesome() {
		return awesome;
	}

	public void setAwesome(String awesome) {
		this.awesome = awesome;
	}

	public String getSupplierSid() {
		return supplierSid;
	}

	public void setSupplierSid(String supplierSid) {
		this.supplierSid = supplierSid;
	}

	@Override
	public String toString() {
		return "ProductPagePara [sid=" + sid + ", productCode=" + productCode + ", skuCode="
				+ skuCode + ", spuCode=" + spuCode + ", discountCode=" + discountCode
				+ ", erpProductCode=" + erpProductCode + ", modelCode=" + modelCode + ", barCode="
				+ barCode + ", productName=" + productName + ", productAbbr=" + productAbbr
				+ ", supplierCode=" + supplierCode + ", brandCode=" + brandCode + ", brandName="
				+ brandName + ", brandGroupCode=" + brandGroupCode + ", brandGroupName="
				+ brandGroupName + ", counterCode=" + counterCode + ", counterName=" + counterName
				+ ", storeCode=" + storeCode + ", manageCategory=" + manageCategory
				+ ", colorCode=" + colorCode + ", colorName=" + colorName + ", sizeCode="
				+ sizeCode + ", stanCode=" + stanCode + ", stanName=" + stanName + ", isSale="
				+ isSale + ", skuSale=" + skuSale + ", spuSale=" + spuSale + ", manageType="
				+ manageType + ", stockType=" + stockType + ", materialNum=" + materialNum
				+ ", articleNum=" + articleNum + ", isDiscountable=" + isDiscountable + ", isCOD="
				+ isCOD + ", unitCode=" + unitCode + ", unitName=" + unitName + ", salesPrice="
				+ salesPrice + ", marketPrice=" + marketPrice + ", maxDiscountRate="
				+ maxDiscountRate + ", productStatus=" + productStatus + ", operateMode="
				+ operateMode + ", commissionRate=" + commissionRate + ", originSalesUnit="
				+ originSalesUnit + ", erpSkuType=" + erpSkuType + ", statCategory=" + statCategory
				+ ", channelSid=" + channelSid + ", priceChannel=" + priceChannel
				+ ", productType=" + productType + ", stockSum=" + stockSum + ", tmsParam="
				+ tmsParam + ", promotionPrice=" + promotionPrice + ", negativeStock="
				+ negativeStock + ", season=" + season + ", stockMode=" + stockMode
				+ ", processType=" + processType + ", originLand=" + originLand + ", orderType="
				+ orderType + ", isPromotion=" + isPromotion + ", isAdjustPrice=" + isAdjustPrice
				+ ", primaryAttr=" + primaryAttr + ", features=" + features + ", sexSid=" + sexSid
				+ ", awesome=" + awesome + ", currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", start=" + start + ", limit=" + limit + "]";
	}

}
