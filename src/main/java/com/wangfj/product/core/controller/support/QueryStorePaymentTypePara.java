package com.wangfj.product.core.controller.support;

public class QueryStorePaymentTypePara {
	/**
	 * 中台门店编码
	 */
	private String storeCode;

	/**
	 * 门店名称
	 */
	private String storeName;

	/**
	 * 二级支付方式编码
	 */
	private String code;

	/**
	 * 有效状态 0：有效；1:无效
	 */
	private String status;

	/**
	 * 当前页
	 */
	private Integer currentPage;

	/**
	 * 单页行数
	 */
	private Integer pageSize;

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
