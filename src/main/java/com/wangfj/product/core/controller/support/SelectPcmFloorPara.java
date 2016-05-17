package com.wangfj.product.core.controller.support;

/**
 * 查询参数
 * 
 * @Class Name SelectPcmFloorPara
 * @Author wangxuan
 * @Create In 2015-8-25
 */
public class SelectPcmFloorPara {

	private String sid;

	private String shopSid; /* 所属门店 */

	private String floorName; /* 楼层名称 */

	private String floorCode; /* 楼层编码 */

	private String floorStatus;// 楼层状态 默认为0，可用， 1禁用

	private Integer currentPage;// 当前页数

	private Integer pageSize;// 每页大小

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

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName == null ? null : floorName.trim();
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode == null ? null : floorCode.trim();
	}

	public String getFloorStatus() {
		return floorStatus;
	}

	public void setFloorStatus(String floorStatus) {
		this.floorStatus = floorStatus == null ? null : floorStatus.trim();
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