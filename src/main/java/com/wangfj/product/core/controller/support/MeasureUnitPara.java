package com.wangfj.product.core.controller.support;

import javax.validation.constraints.Pattern;

/**
 * 计量单位Dto
 * 
 * @Class Name MeasureUnitDto
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
public class MeasureUnitPara {
	private Long sid;
	/**
	 * 计量单位
	 */
	@Pattern(regexp = "[\\w\\u4e00-\\u9fa5]{0,50}", message = "{MeasureUnitPara.unitName.patternError}")
	private String unitName;
	/**
	 * 计量描述
	 */
	@Pattern(regexp = "[\\w\\u4e00-\\u9fa5]{0,50}", message = "{MeasureUnitPara.unitDesc.patternError}")
	private String unitDesc;
	private Integer currentPage;
	private Integer pageSize;
	private Integer start;
	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName == null ? null : unitName.trim();
	}

	public String getUnitDesc() {
		return unitDesc;
	}

	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc == null ? null : unitDesc.trim();
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
		return "MeasureUnitDto [sid=" + sid + ", unitName=" + unitName + ", unitDesc=" + unitDesc
				+ "]";
	}

}
