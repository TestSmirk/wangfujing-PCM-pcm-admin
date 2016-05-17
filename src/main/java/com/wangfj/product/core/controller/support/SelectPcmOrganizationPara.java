package com.wangfj.product.core.controller.support;

public class SelectPcmOrganizationPara {

	private String parentSid; /* 所属上级编码 */

	private String organizationName; /* 机构名称 */

	private String organizationCode; /* 机构编码 */

	private String organizationType; /* 机构类别 */

	private String storeType;/* 门店类型 */

	private String organizationStatus; /* 机构状态 0.可用；1禁用（默认0） */

	private String currentPage;// 当前页数

	private String pageSize;// 每页大小

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid == null ? null : parentSid.trim();
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName == null ? null : organizationName.trim();
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode == null ? null : organizationCode.trim();
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType == null ? null : organizationType.trim();
	}

	public String getOrganizationStatus() {
		return organizationStatus;
	}

	public void setOrganizationStatus(String organizationStatus) {
		this.organizationStatus = organizationStatus == null ? null : organizationStatus.trim();
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage == null ? null : currentPage.trim();
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize == null ? null : pageSize.trim();
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}


}
