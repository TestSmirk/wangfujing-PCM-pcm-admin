package com.wangfj.product.core.controller.support;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 分页查询集团品牌Controller参数
 * 
 * @Class Name SelectPcmBrandGroupPagePara
 * @Author wangx
 * @Create In 2015-8-3
 */
public class SelectPcmBrandGroupPagePara extends BasePara {

	private Long sid;

	private String brandSid;// 集团品牌编码

	private String brandName;// 中文名称

	private String brandNameSpell;// 中文拼音

	private String brandNameAlias;// 别名

	private String brandNameEn;// 英文名称

	private Integer currentPage = 1;// 当前页数

	private Integer pageSize = 10;// 每页大小

	/** 当前页的起始索引,从1开始 */
	private int start = 1;

	/** mysql 分页 */
	private int limit = 10;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandNameSpell() {
		return brandNameSpell;
	}

	public void setBrandNameSpell(String brandNameSpell) {
		this.brandNameSpell = brandNameSpell;
	}

	public String getBrandNameAlias() {
		return brandNameAlias;
	}

	public void setBrandNameAlias(String brandNameAlias) {
		this.brandNameAlias = brandNameAlias;
	}

	public String getBrandNameEn() {
		return brandNameEn;
	}

	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn;
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "SelectPcmBrandGroupPagePara [sid=" + sid + ", brandSid=" + brandSid
				+ ", brandName=" + brandName + ", brandNameSpell=" + brandNameSpell
				+ ", brandNameAlias=" + brandNameAlias + ", brandNameEn=" + brandNameEn
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", start=" + start
				+ ", limit=" + limit + "]";
	}

}
