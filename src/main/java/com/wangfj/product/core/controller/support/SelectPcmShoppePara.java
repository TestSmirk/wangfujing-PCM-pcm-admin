package com.wangfj.product.core.controller.support;

/**
 * 查询参数
 * 
 * @Class Name SelectPcmShoppePara
 * @Author wangxuan
 * @Create In 2015-8-24
 */
public class SelectPcmShoppePara {

	private String sid;

	/* 所属门店SID */
	private String shopSid;

	/* 楼层SID */
	private String floorSid;

	/* 专柜所属业态SID */
	private String industryConditionSid;

	/* 专柜编码 */
	private String shoppeCode;

	/* 专柜名称 */
	private String shoppeName;

	/* 状态 */
	private String shoppeStatus;

	/* 商品管理类型 */
	private String goodsManageType;

	/* 经营方式 */
	private String businessTypeSid;

	/* 最后一次修改人 */
	private String optUser;

	/* 创建人 */
	private String createName;

	/* 柜组 */
	private String shoppeGroup;

	/* 专柜类型 */
	private String shoppeType;

	/* 集货地点 */
	private String shoppeShippingPoint;

	/* 参考奥莱专柜 */
	private String refCounter;

	/* 是否负库存销售 */
	private String negativeStock;

	/* 当前页数 */
	private Integer currentPage;

	/* 每页显示数量 */
	private Integer pageSize;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid == null ? null : shopSid.trim();
	}

	public String getFloorSid() {
		return floorSid;
	}

	public void setFloorSid(String floorSid) {
		this.floorSid = floorSid == null ? null : floorSid.trim();
	}

	public String getIndustryConditionSid() {
		return industryConditionSid;
	}

	public void setIndustryConditionSid(String industryConditionSid) {
		this.industryConditionSid = industryConditionSid == null ? null : industryConditionSid
				.trim();
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode == null ? null : shoppeCode.trim();
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName == null ? null : shoppeName.trim();
	}

	public String getShoppeStatus() {
		return shoppeStatus;
	}

	public void setShoppeStatus(String shoppeStatus) {
		this.shoppeStatus = shoppeStatus == null ? null : shoppeStatus.trim();
	}

	public String getGoodsManageType() {
		return goodsManageType;
	}

	public void setGoodsManageType(String goodsManageType) {
		this.goodsManageType = goodsManageType;
	}

	public String getBusinessTypeSid() {
		return businessTypeSid;
	}

	public void setBusinessTypeSid(String businessTypeSid) {
		this.businessTypeSid = businessTypeSid;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser == null ? null : optUser.trim();
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName == null ? null : createName.trim();
	}

	public String getShoppeGroup() {
		return shoppeGroup;
	}

	public void setShoppeGroup(String shoppeGroup) {
		this.shoppeGroup = shoppeGroup == null ? null : shoppeGroup.trim();
	}

	public String getShoppeType() {
		return shoppeType;
	}

	public void setShoppeType(String shoppeType) {
		this.shoppeType = shoppeType == null ? null : shoppeType.trim();
	}

	public String getShoppeShippingPoint() {
		return shoppeShippingPoint;
	}

	public void setShoppeShippingPoint(String shoppeShippingPoint) {
		this.shoppeShippingPoint = shoppeShippingPoint == null ? null : shoppeShippingPoint.trim();
	}

	public String getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter == null ? null : refCounter.trim();
	}

	public String getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(String negativeStock) {
		this.negativeStock = negativeStock;
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

}
