package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

import com.wangfj.product.core.controller.support.base.para.BasePara;
	/**
	 * 
	 * @Class Name SelectStudentPagePara
	 * @Author wuxiong
	 * @Create In 2015年7月2日
	 */
public class SelectStudentPagePara extends BasePara{
	@NotNull(message = "{userPara.name.isNotNull}")
	private String name;
	@NotNull(message = "{currentPage.isNotNull}")
	private Integer currentPage;
	@NotNull(message = "{pageSize.isNotNull}")
	private Integer pageSize;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
