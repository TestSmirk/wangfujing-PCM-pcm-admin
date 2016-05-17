package com.wangfj.product.core.controller.support;

/**
 * 字典表
 * 
 * @author niu
 * 
 */
public class PcmDictPara {

	private Long sid;

	private String code;

	private String name;
	/**
	 * 父sid
	 */
	private Long pid;
	/**
	 * 顺序
	 */
	private String sort;

	private String status;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
