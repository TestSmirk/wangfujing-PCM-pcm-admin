package com.wangfj.product.core.controller.support;

public class PcmOrgPara {

	private Long sid;

	private String parentSid;/* 所属上级sid */

	private Long groupSid;/* 所属集团sid */

	private String organizationCode;/* 机构编码 */

	private String organizationName;/* 机构名称 */

	private Integer organizationType;/* 机构类别 */

	private Integer organizationStatus;/* 机构状态 */

	private Integer storeType;/* 门店类型 */

	private String shippingPoint;/* 集货地点编码 */

	private String areaCode;/* 门店所属城市在省市区字典中的编码 */

	private String createName; /* 创建人 */

	private String updateName; /* 修改人 */

	private String actionCode;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	public Long getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(Long groupSid) {
		this.groupSid = groupSid;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	@Override
	public String toString() {
		return "PcmOrgPara [sid=" + sid + ", parentSid=" + parentSid + ", groupSid=" + groupSid
				+ ", organizationCode=" + organizationCode + ", organizationName="
				+ organizationName + ", organizationType=" + organizationType
				+ ", organizationStatus=" + organizationStatus + ", storeType=" + storeType
				+ ", shippingPoint=" + shippingPoint + ", areaCode=" + areaCode + ", createName="
				+ createName + ", updateName=" + updateName + ", actionCode=" + actionCode + "]";
	}

}
