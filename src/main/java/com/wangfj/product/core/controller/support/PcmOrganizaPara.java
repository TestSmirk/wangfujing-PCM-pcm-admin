package com.wangfj.product.core.controller.support;

/**
 * @Class Name PcmOrganizaPara
 * @Author wuxiong
 * @Create In 2015年7月28日
 */
public class PcmOrganizaPara {

	private Long sid;

	private String code;/* 机构编码 */

	private String name;/* 机构名字 */

	private String type;/* 级别类型 */

	private String superCode;/* 上级机构编码 */

	private String storeType;/* 门店类型 */

	private String shippingPoint;/* 集货地点编码 */

	private String storeCode;/* 门店编码 */

	private String areaCode;/* 门店所属城市在省市区字典中的编码 */

	private String actionCode;

	private String updateTimes;/* 修改时间 */

	private String parentSid; /* 所属上级编码 */

	private String organizationName; /* 机构名称 */

	private String organizationCode; /* 机构编码 */

	private Integer organizationType; /* 机构类别 */

	private Integer organizationStatus; /* 机构状态 */

	private String createName; /* 创建人 */

	private String updateName; /* 修改人 */

	private String createTimes; /* 创建时间 */
	private Integer start;
	private Integer limit;

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

	public String getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}

	public String getUpdateTimes() {
		return updateTimes;
	}

	public void setUpdateTimes(String updateTimes) {
		this.updateTimes = updateTimes;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getShippingPoint() {
		return shippingPoint;
	}

	public void setShippingPoint(String shippingPoint) {
		this.shippingPoint = shippingPoint;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
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

	@Override
	public String toString() {
		return "PcmOrganizaPara [sid=" + sid + ", code=" + code + ", name=" + name + ", type="
				+ type + ", superCode=" + superCode + ", storeType=" + storeType
				+ ", shippingPoint=" + shippingPoint + ", storeCode=" + storeCode + ", areaCode="
				+ areaCode + ", actionCode=" + actionCode + ", updateTimes=" + updateTimes
				+ ", parentSid=" + parentSid + ", organizationName=" + organizationName
				+ ", organizationCode=" + organizationCode + ", organizationType="
				+ organizationType + ", organizationStatus=" + organizationStatus + ", createName="
				+ createName + ", updateName=" + updateName + ", createTimes=" + createTimes
				+ ", start=" + start + ", limit=" + limit + "]";
	}

}
