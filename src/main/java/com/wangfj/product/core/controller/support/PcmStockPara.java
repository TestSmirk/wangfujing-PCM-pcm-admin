package com.wangfj.product.core.controller.support;

public class PcmStockPara {
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Long sid;
	/*
	 * sku
	 */
	private String sku;
	/*
	 * 供应商商品编码
	 */
	private String supplyProductId;
	/*
	 * 库存地点编码
	 */
	private String location;
	/*
	 * 出货主体编号
	 */
	private String locationOwnerId;
	/*
	 * 库存数
	 */
	private Integer inventory;
	/*
	 * 借用库存数
	 */
	private Integer borrowInventory;
	/*
	 * 残次品库存数
	 */
	private Integer defectiveInventory;
	/*
	 * 停售库存数
	 */
	private Integer stopsalesInventory;
	/*
	 * 安全库存数
	 */
	private Integer warningInventory;
	/*
	 * 改动类型
	 */
	private String type;
	/*
	 * 调用方
	 */
	private String source;
	/*
	 * 操作人
	 */
	private String operator;
	/**
	 * 渠道
	 */
	private Integer channelSid;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSupplyProductId() {
		return supplyProductId;
	}

	public void setSupplyProductId(String supplyProductId) {
		this.supplyProductId = supplyProductId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationOwnerId() {
		return locationOwnerId;
	}

	public void setLocationOwnerId(String locationOwnerId) {
		this.locationOwnerId = locationOwnerId;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getBorrowInventory() {
		return borrowInventory;
	}

	public void setBorrowInventory(Integer borrowInventory) {
		this.borrowInventory = borrowInventory;
	}

	public Integer getDefectiveInventory() {
		return defectiveInventory;
	}

	public void setDefectiveInventory(Integer defectiveInventory) {
		this.defectiveInventory = defectiveInventory;
	}

	public Integer getStopsalesInventory() {
		return stopsalesInventory;
	}

	public void setStopsalesInventory(Integer stopsalesInventory) {
		this.stopsalesInventory = stopsalesInventory;
	}

	public Integer getWarningInventory() {
		return warningInventory;
	}

	public void setWarningInventory(Integer warningInventory) {
		this.warningInventory = warningInventory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Integer channelSid) {
		this.channelSid = channelSid;
	}

	@Override
	public String toString() {
		return "PcmStockPara [sid=" + sid + ", sku=" + sku + ", supplyProductId=" + supplyProductId
				+ ", location=" + location + ", locationOwnerId=" + locationOwnerId + ", inventory="
				+ inventory + ", borrowInventory=" + borrowInventory + ", defectiveInventory="
				+ defectiveInventory + ", stopsalesInventory=" + stopsalesInventory
				+ ", warningInventory=" + warningInventory + ", type=" + type + ", source=" + source
				+ ", operator=" + operator + ", channelSid=" + channelSid + "]";
	}

}
