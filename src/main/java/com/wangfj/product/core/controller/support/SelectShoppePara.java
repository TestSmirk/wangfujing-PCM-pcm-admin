/**
 * @Probject Name: pcm-syn
 * @Path: com.wangfj.product.business.controller.supportSelectShoppePara.java
 * @Create By wuxiong
 * @Create In 2015年7月30日 上午10:10:28
 * TODO
 */
package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;


/**
 * @Class Name SelectShoppePara
 * @Author wuxiong
 * @Create In 2015年7月30日
 */
public class SelectShoppePara {
	private Long sid;
	private String shoppeCode;/* 专柜编码 */
	private String shoppeName;/* 专柜名称 */
	private String floorCode; /* 楼层编码 */
	private String floorName; /* 楼层名称 */
	private Integer industrySid;// 专柜所属业态SID
	private String shoppeType; // 专柜类型(01 单品管理专柜 02 非单品管理专柜 03 部分单品管理专柜)
	private Integer goodManageType; // 专柜库存管理类型(01 门店专柜 02 电商自有专柜 03 电商奥莱 04
									// 电商供应商虚仓)
	private String shippingPoint; // 专柜集货地点
	private String refCounter; // 参考奥莱专柜
	private Integer shoppeStatus;// 1，正常；2，停用；3，撤柜（默认1）
	private Integer negIiveStock; // 是否负库存销售 ：0 允许，1不允许（默认为0）
	private String businessTypeSid;
	private String orgCode;/* 门店编码 */
	private String orgName;/* 门店名称 */
	private String isShippingPoint;//是否为集货地点（0是，1否）
	
	public String getIsShippingPoint() {
		return isShippingPoint;
	}

	public void setIsShippingPoint(String isShippingPoint) {
		this.isShippingPoint = isShippingPoint;
	}

	/** 当前页的起始索引,从1开始 */
	protected int start = 0;

	/** mysql 分页 */
	protected int limit = 10;

	@NotNull(message = "{currentPage.isNotNull}")
	private Integer currentPage;
	@NotNull(message = "{pageSize.isNotNull}")
	private Integer pageSize;

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

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public Integer getIndustrySid() {
		return industrySid;
	}

	public void setIndustrySid(Integer industrySid) {
		this.industrySid = industrySid;
	}

	public String getShoppeType() {
		return shoppeType;
	}

	public void setShoppeType(String shoppeType) {
		this.shoppeType = shoppeType;
	}

	public Integer getGoodManageType() {
		return goodManageType;
	}

	public void setGoodManageType(Integer goodManageType) {
		this.goodManageType = goodManageType;
	}

	public String getShippingPoint() {
		return shippingPoint;
	}

	public void setShippingPoint(String shippingPoint) {
		this.shippingPoint = shippingPoint;
	}

	public String getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter;
	}

	public Integer getShoppeStatus() {
		return shoppeStatus;
	}

	public void setShoppeStatus(Integer shoppeStatus) {
		this.shoppeStatus = shoppeStatus;
	}

	public Integer getNegIiveStock() {
		return negIiveStock;
	}

	public void setNegIiveStock(Integer negIiveStock) {
		this.negIiveStock = negIiveStock;
	}

	public String getBusinessTypeSid() {
		return businessTypeSid;
	}

	public void setBusinessTypeSid(String businessTypeSid) {
		this.businessTypeSid = businessTypeSid;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
