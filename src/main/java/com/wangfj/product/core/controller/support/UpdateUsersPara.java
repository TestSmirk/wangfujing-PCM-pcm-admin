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
public class UpdateUsersPara extends BasePara{
	@NotNull(message = "{UpdateUsersPara.sid.isNotNull}")
	private Integer sid;
	private String name;
	
	/**
	 * @Return the Integer sid
	 */
	public Integer getSid() {
		return sid;
	}
	/**
	 * @Param Integer sid to set
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}
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
	
}
