package com.wangfj.product.core.controller.support;

public class QueryPaymentTypePara {
	/**
	 * 中台门店编码
	 */
	private String storeCode;

	/**
	 * 付款方式编码
	 */
	private String payCode;

	/**
	 * 付款方式名称
	 */
	private String name;

	/**
	 * 上一级编码
	 */
	private String parentCode;

	/**
	 * 银行识别码
	 */
	public String bankBIN;

	/**
	 * 当前页
	 */
	private Integer currentPage;

	/**
	 * 单页行数
	 */
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

	public String getBankBIN() {
		return bankBIN;
	}

	public void setBankBIN(String bankBIN) {
		this.bankBIN = bankBIN;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
}
