/**
 * 
 */
package com.wangfj.product.core.controller.support;

/**
 * 品类para参数
 * 
 * @Class Name CategoryPara
 * @Author duanzhaole
 * @Create In 2015年8月3日
 */
public class CategoryPara {

	private String id;

	private String channelSid;

	private String productSid;

	private String sid;

	private String name;
	private String status;

	/**
	 * 父节点
	 */
	private String parentSid;
	private int sortOrder;
	private Integer isDisplay;

	/**
	 * 拖拽方式
	 */
	private String moveType;
	/**
	 * 分类类型
	 */
	private String categoryType;

	private String targetSid;
	private String rootSid;
	/*
	 * 门店sid
	 */
	private String shopSid;

	private String isParent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getTargetSid() {
		return targetSid;
	}

	public void setTargetSid(String targetSid) {
		this.targetSid = targetSid;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getRootSid() {
		return rootSid;
	}

	public void setRootSid(String rootSid) {
		this.rootSid = rootSid;
	}

	@Override
	public String toString() {
		return "CategoryPara [id=" + id + ", channelSid=" + channelSid + ", productSid="
				+ productSid + ", sid=" + sid + ", name=" + name + ", status=" + status
				+ ", parentSid=" + parentSid + ", sortOrder=" + sortOrder + ", isDisplay="
				+ isDisplay + ", moveType=" + moveType + ", categoryType=" + categoryType
				+ ", shopSid=" + shopSid + "]";
	}

}
