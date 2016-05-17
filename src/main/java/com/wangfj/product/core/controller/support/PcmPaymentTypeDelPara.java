package com.wangfj.product.core.controller.support;

public class PcmPaymentTypeDelPara {
	/**
	 * SID
	 */
	private String sid;
	/**
	 * 上一级编码
	 */
	public String parentCode;

	/**
	 * 最后修改人
	 */
	private String lastUpdBy;

	/**
	 * 备注
	 */
	private String remark;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getLastUpdBy() {
		return lastUpdBy;
	}

	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
