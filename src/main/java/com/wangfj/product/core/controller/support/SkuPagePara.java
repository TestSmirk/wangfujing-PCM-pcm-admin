package com.wangfj.product.core.controller.support;

/**
 * 商品Para
 * 
 * @Class Name SkuPagePara
 * @Author zhangxy
 * @Create In 2015年8月13日
 */
public class SkuPagePara {
	private Long sid;
	private String spuSid;
	private String skuCode;// 商品明细表编码
	private String spuCode;// 产品表编码
	private String stanCode; // 规格编码
	private String stanName; // 规格名称
	private String colorCode;// 色码
	private String colorCodeName;// 色码名称
	private String memo;// 商品颜色为图片颜色
	private String barcode;// 二维码
	private String proWriTime;// 录入时间
	private String photoSaleCodeSid;// 送去拍照的销售编码编码
	private Integer photoStatus;// 拍照计划状态 : 0 未计划,1 已计划, 2 已拍照未上传店内,3
								// 已上传店内未到IDC,4 已上传至IDC 完成 5表示拍照部已计划导购未确认 默认 0
	private String planMaker;// 拍照计划制定人
	private String planTime;// 计划制定时间
	private String photoPlanSid;// 拍照计划表编码
	private Integer proActiveBit;// 是否启用 : 1 启用 0 未启用
	private String proType;// 商品类型 : 0 普通商品（实物类），1 赠品 ，2 礼品，3 虚拟商品（充值卡，购物卡），4
							// 服务类商品 （礼品包装，购物接送服务，停车服务）（注：礼品不可卖，赠品可卖）
	private String skuSale;// 上架状态
	private String searchKey;// 关键字
	private String keyWord;// 活动关键字
	private String sizePictureUrl;// 尺寸图片表路径
	private String features;// 特性
	private String articleNum;// 货号
	private String spuName;// 产品名(spu标准品名)
	private String skuName;// 商品名(sku标准品名)
	private String modelCode;// 款号
	private String primaryAttr;// 商品主属性
	private String brandGroupCode;// 集团品牌编码
	private String brandGroupName;// 集团品牌名称
	private String category;// 工业分类
	private String statCategory;// 统计分类
	private String spuSale;// spu上架状态
	private String seasonCode;// 季节
	private String season;// 季节
	private String onMarketDate;// 上市年份
	private String activityFlg;// 活动标记
	private String sexSid;// 适合性别
	private String awesome;// 点赞数量
	private String proSellingTime;// 上架时间
	private String shortDes;// 短描述
	private String editFlag;// 二次编辑标识
	private String specialDes;// 特别说明
	private String colorSid;// 色系编码
	private String colorName;// 色系名称
	private String industryCondition; // 业态类型(1百货,2电商,3超市)
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPhotoSaleCodeSid() {
		return photoSaleCodeSid;
	}

	public void setPhotoSaleCodeSid(String photoSaleCodeSid) {
		this.photoSaleCodeSid = photoSaleCodeSid;
	}

	public Integer getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(Integer photoStatus) {
		this.photoStatus = photoStatus;
	}

	public String getPlanMaker() {
		return planMaker;
	}

	public void setPlanMaker(String planMaker) {
		this.planMaker = planMaker;
	}

	public String getPhotoPlanSid() {
		return photoPlanSid;
	}

	public void setPhotoPlanSid(String photoPlanSid) {
		this.photoPlanSid = photoPlanSid;
	}

	public Integer getProActiveBit() {
		return proActiveBit;
	}

	public void setProActiveBit(Integer proActiveBit) {
		this.proActiveBit = proActiveBit;
	}

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

	public String getSizePictureUrl() {
		return sizePictureUrl;
	}

	public void setSizePictureUrl(String sizePictureUrl) {
		this.sizePictureUrl = sizePictureUrl;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
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

	public String getSpuName() {
		return spuName;
	}

	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
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

	public String getStanCode() {
		return stanCode;
	}

	public void setStanCode(String stanCode) {
		this.stanCode = stanCode;
	}

	public String getStanName() {
		return stanName;
	}

	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorCodeName() {
		return colorCodeName;
	}

	public void setColorCodeName(String colorCodeName) {
		this.colorCodeName = colorCodeName;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getPrimaryAttr() {
		return primaryAttr;
	}

	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatCategory() {
		return statCategory;
	}

	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

	public String getSpuSale() {
		return spuSale;
	}

	public void setSpuSale(String spuSale) {
		this.spuSale = spuSale;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getOnMarketDate() {
		return onMarketDate;
	}

	public void setOnMarketDate(String onMarketDate) {
		this.onMarketDate = onMarketDate;
	}

	public String getActivityFlg() {
		return activityFlg;
	}

	public void setActivityFlg(String activityFlg) {
		this.activityFlg = activityFlg;
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

	public String getProSellingTime() {
		return proSellingTime;
	}

	public void setProSellingTime(String proSellingTime) {
		this.proSellingTime = proSellingTime;
	}

	public String getShortDes() {
		return shortDes;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getSpecialDes() {
		return specialDes;
	}

	public void setSpecialDes(String specialDes) {
		this.specialDes = specialDes;
	}

	public String getColorSid() {
		return colorSid;
	}

	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getProWriTime() {
		return proWriTime;
	}

	public void setProWriTime(String proWriTime) {
		this.proWriTime = proWriTime;
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getSkuSale() {
		return skuSale;
	}

	public void setSkuSale(String skuSale) {
		this.skuSale = skuSale;
	}

	public String getIndustryCondition() {
		return industryCondition;
	}

	public void setIndustryCondition(String industryCondition) {
		this.industryCondition = industryCondition;
	}

	public String getSpuSid() {
		return spuSid;
	}

	public void setSpuSid(String spuSid) {
		this.spuSid = spuSid;
	}

	@Override
	public String toString() {
		return "SkuPagePara [sid=" + sid + ", spuSid=" + spuSid + ", skuCode=" + skuCode
				+ ", spuCode=" + spuCode + ", stanCode=" + stanCode + ", stanName=" + stanName
				+ ", colorCode=" + colorCode + ", colorCodeName=" + colorCodeName + ", memo="
				+ memo + ", barcode=" + barcode + ", proWriTime=" + proWriTime
				+ ", photoSaleCodeSid=" + photoSaleCodeSid + ", photoStatus=" + photoStatus
				+ ", planMaker=" + planMaker + ", planTime=" + planTime + ", photoPlanSid="
				+ photoPlanSid + ", proActiveBit=" + proActiveBit + ", proType=" + proType
				+ ", skuSale=" + skuSale + ", searchKey=" + searchKey + ", keyWord=" + keyWord
				+ ", sizePictureUrl=" + sizePictureUrl + ", features=" + features + ", articleNum="
				+ articleNum + ", spuName=" + spuName + ", skuName=" + skuName + ", modelCode="
				+ modelCode + ", primaryAttr=" + primaryAttr + ", brandGroupCode=" + brandGroupCode
				+ ", brandGroupName=" + brandGroupName + ", category=" + category
				+ ", statCategory=" + statCategory + ", spuSale=" + spuSale + ", seasonCode="
				+ seasonCode + ", season=" + season + ", onMarketDate=" + onMarketDate
				+ ", activityFlg=" + activityFlg + ", sexSid=" + sexSid + ", awesome=" + awesome
				+ ", proSellingTime=" + proSellingTime + ", shortDes=" + shortDes + ", editFlag="
				+ editFlag + ", specialDes=" + specialDes + ", colorSid=" + colorSid
				+ ", colorName=" + colorName + ", industryCondition=" + industryCondition
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", start=" + start
				+ ", limit=" + limit + "]";
	}

}