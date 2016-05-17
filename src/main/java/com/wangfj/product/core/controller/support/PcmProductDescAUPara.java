package com.wangfj.product.core.controller.support;

/**
 * Created by wangxuan on 2016-02-16 0016.
 */
public class PcmProductDescAUPara {

	private Long sid;

	private String color;// 色系

	private String productSid;// 产品编码

	private String contents;// 商品描述

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "PcmProductDescAUPara{" + "sid=" + sid + ", color='" + color + '\''
				+ ", productSid='" + productSid + '\'' + ", contents='" + contents + '\'' + '}';
	}
}
