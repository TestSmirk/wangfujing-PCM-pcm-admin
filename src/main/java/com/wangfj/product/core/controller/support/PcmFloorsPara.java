package com.wangfj.product.core.controller.support;



/**
 * @Class Name PcmFloorsPara
 * @Author wuxiong
 * @Create In 2015年7月27日
 */
public class PcmFloorsPara {
	private Long sid;
	private String storeName;/* 门店名称 */
	private String storeCode;
	private String code;/* 楼层编码 */
	private String name;/* 楼层名称 */
	private Integer pageSize;
	private Integer currentPage;

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
