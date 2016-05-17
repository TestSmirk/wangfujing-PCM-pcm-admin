package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;
/**
 * 
 * @Class Name UpdateStudentsPara
 * @Author wuxiong
 * @Create In 2015年7月3日
 */
public class UpdateStudentsPara {
	@NotNull(message = "{UpdateStudentsPara.sid.isNotNull}")
	private Integer sid;
	private String name;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
