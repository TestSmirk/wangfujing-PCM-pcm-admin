package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.wangfj.product.core.controller.support.base.para.BasePara;

public class PcmRegionPara extends BasePara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -4620143525650160939L;

	private Long sid;

	private String regionCode;// 区域邮编

	private String regionInnerCode;// 行政区域编码

	@NotNull(message = "{PcmRegionPara.regionName.notNull}")
	private String regionName;// 区域名称

	private Long parentId;// 所属上级sid

	private Integer regionLevel;// 等级（0 省，1 市，2 区，3 县，4 镇 ）

	private Integer regionOrder;// 顺序

	private String regionNameEn;// 区域名称拼音

	private String regionShortnameEn;// 区域名称拼音简称

	private String actionCode;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}

	public Integer getRegionOrder() {
		return regionOrder;
	}

	public void setRegionOrder(Integer regionOrder) {
		this.regionOrder = regionOrder;
	}

	public String getRegionNameEn() {
		return regionNameEn;
	}

	public void setRegionNameEn(String regionNameEn) {
		this.regionNameEn = regionNameEn;
	}

	public String getRegionShortnameEn() {
		return regionShortnameEn;
	}

	public void setRegionShortnameEn(String regionShortnameEn) {
		this.regionShortnameEn = regionShortnameEn;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getRegionInnerCode() {
		return regionInnerCode;
	}

	public void setRegionInnerCode(String regionInnerCode) {
		this.regionInnerCode = regionInnerCode;
	}

	@Override public String toString() {
		return "PcmRegionPara{" +
				"sid=" + sid +
				", regionCode='" + regionCode + '\'' +
				", regionInnerCode='" + regionInnerCode + '\'' +
				", regionName='" + regionName + '\'' +
				", parentId=" + parentId +
				", regionLevel=" + regionLevel +
				", regionOrder=" + regionOrder +
				", regionNameEn='" + regionNameEn + '\'' +
				", regionShortnameEn='" + regionShortnameEn + '\'' +
				", actionCode='" + actionCode + '\'' +
				'}';
	}
}
