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
public class GetUsersPara extends BasePara{
	@NotNull(message = "{GetUsersPara.sid.isNotNull}")
	private Integer sid;
	
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
}
