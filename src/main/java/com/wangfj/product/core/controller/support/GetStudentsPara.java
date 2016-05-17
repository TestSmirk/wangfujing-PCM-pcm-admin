package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

import com.wangfj.product.core.controller.support.base.para.BasePara;
/**
 * 
 * @Class Name GetStudentsPara
 * @Author wuxiong
 * @Create In 2015年7月3日
 */
public class GetStudentsPara extends BasePara{
	@NotNull(message = "{GetStudentsPara.sid.isNotNull}")
	private Integer sid;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	

}
