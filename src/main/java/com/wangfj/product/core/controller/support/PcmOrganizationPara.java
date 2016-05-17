package com.wangfj.product.core.controller.support;

import java.util.Date;

public class PcmOrganizationPara {
	Long sid;
	String code; /* 专柜编码 */
	String name; /* 专柜名称 */
	String storeCode; /* 门店编码 */
	String supplierCode; /**/
	String supplierErpCode; /* 供应商编码 */
	String floorCode; /* 楼层编码 */
	String floorName; /* 楼层名称 */
	String counterGroup; /* 柜组 */
	char businessType; /* 业态类型 */
	String counterType; /* 专柜类型 */
	String counterInventoryType; /* 专柜库存管理类型 */
	String counterShippingPoint; /* 专柜集货地点 */
	String refCounter; /* 参考奥莱专柜 */
	char counterStatus; /* 专柜状态 */
	char isNegInventory; /* 是否负库存 */
	private Integer pageSize;
	private Integer currentPage;

	private String parentSid; /* 所属上级编码 */

	private String organizationName; /* 机构名称 */

	private String organizationCode; /* 机构编码 */

	private Integer organizationType; /* 机构类别 */

	private Integer organizationStatus; /* 机构状态 */

	private String createName; /* 创建人 */

	private String updateName; /* 修改人 */

	private Date createTimes; /* 创建时间 */

	private Date updateTimes; /* 修改时间 */

	/*
	 * 门店类型： 1电商 2北京 3其它门店（指明门店类型） 4 集货仓 5 门店物流室
	 */
	private Integer storeType;

	/*
	 * 集货地点编码(对于门店非空，表明对应的集货仓地点编码， 例如电商对应电商百子湾集货仓的组织机构编码。 如果门店没有集货仓，则传自己的组织机构编码)
	 */
	private String shippingPoint;
	/*
	 * 门店所属城市在省市区字典中的编码 （业务要求配置到城市一级， 但是并不需要系统限制和校验）
	 */
	private Integer start;

	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierErpCode() {
		return supplierErpCode;
	}

	public void setSupplierErpCode(String supplierErpCode) {
		this.supplierErpCode = supplierErpCode;
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

	public String getCounterGroup() {
		return counterGroup;
	}

	public void setCounterGroup(String counterGroup) {
		this.counterGroup = counterGroup;
	}

	public char getBusinessType() {
		return businessType;
	}

	public void setBusinessType(char businessType) {
		this.businessType = businessType;
	}

	public String getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}

	public String getCounterInventoryType() {
		return counterInventoryType;
	}

	public void setCounterInventoryType(String counterInventoryType) {
		this.counterInventoryType = counterInventoryType;
	}

	public String getCounterShippingPoint() {
		return counterShippingPoint;
	}

	public void setCounterShippingPoint(String counterShippingPoint) {
		this.counterShippingPoint = counterShippingPoint;
	}

	public String getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter;
	}

	public char getCounterStatus() {
		return counterStatus;
	}

	public void setCounterStatus(char counterStatus) {
		this.counterStatus = counterStatus;
	}

	public char getIsNegInventory() {
		return isNegInventory;
	}

	public void setIsNegInventory(char isNegInventory) {
		this.isNegInventory = isNegInventory;
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

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public Integer getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(Integer organizationType) {
		this.organizationType = organizationType;
	}

	public Integer getOrganizationStatus() {
		return organizationStatus;
	}

	public void setOrganizationStatus(Integer organizationStatus) {
		this.organizationStatus = organizationStatus;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Integer getStoreType() {
		return storeType;
	}

	public void setStoreType(Integer storeType) {
		this.storeType = storeType;
	}

	public String getShippingPoint() {
		return shippingPoint;
	}

	public void setShippingPoint(String shippingPoint) {
		this.shippingPoint = shippingPoint;
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

	public Date getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(Date createTimes) {
		this.createTimes = createTimes;
	}

	public Date getUpdateTimes() {
		return updateTimes;
	}

	public void setUpdateTimes(Date updateTimes) {
		this.updateTimes = updateTimes;
	}

	@Override
	public String toString() {
		return "PcmOrganizationPara [sid=" + sid + ", code=" + code + ", name=" + name
				+ ", storeCode=" + storeCode + ", supplierCode=" + supplierCode
				+ ", supplierErpCode=" + supplierErpCode + ", floorCode=" + floorCode
				+ ", floorName=" + floorName + ", counterGroup=" + counterGroup + ", businessType="
				+ businessType + ", counterType=" + counterType + ", counterInventoryType="
				+ counterInventoryType + ", counterShippingPoint=" + counterShippingPoint
				+ ", refCounter=" + refCounter + ", counterStatus=" + counterStatus
				+ ", isNegInventory=" + isNegInventory + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", parentSid=" + parentSid
				+ ", organizationName=" + organizationName + ", organizationCode="
				+ organizationCode + ", organizationType=" + organizationType
				+ ", organizationStatus=" + organizationStatus + ", createName=" + createName
				+ ", updateName=" + updateName + ", createTimes=" + createTimes + ", updateTimes="
				+ updateTimes + ", storeType=" + storeType + ", shippingPoint=" + shippingPoint
				+ ", start=" + start + ", limit=" + limit + "]";
	}

}
