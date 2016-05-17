package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 
 * @Comment
 * @Class Name UserPara
 * @Author kongxs
 * @Create In 2015年6月25日
 */
public class SelectUserPagePara extends BasePara{
//	@IsLongOrInteger(message = "{currentPage.name.isNotInteger}")
	@NotNull(message = "{userPara.name.isNotNull}")
	private String name;
	@NotNull(message = "{currentPage.isNotNull}")
	private Integer currentPage;/* 当前页 */
	@NotNull(message = "{pageSize.isNotNull}")
	private Integer pageSize;/* 显示条数 */
	/**
	 * @Return the String name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @Param String name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @Return the Integer currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @Param Integer currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @Return the Integer pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @Param Integer pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
