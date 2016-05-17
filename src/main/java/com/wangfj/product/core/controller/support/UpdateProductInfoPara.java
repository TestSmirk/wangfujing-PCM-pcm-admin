package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UpdateProductInfoPara {
	@NotNull(message = "商品编码不能为空")
	private String productCode;
	@Pattern(regexp = "[\\w\\W]{0,20}", message = "商品名称长度不超过20位")
	private String productName;
	@Pattern(regexp = "[\\w\\W]{0,20}", message = "销售单位长度不超过20位")
	private String unit;
	@Pattern(regexp = "[\\w\\W]{0,20}", message = "产地长度不超过20位")
	private String originLand;
	@Pattern(regexp = "[\\w\\W]{0,20}", message = "货号长度不超过20位")
	private String articleNum;
	@Pattern(regexp = "[\\w\\W]{0,20}", message = "备注长度不超过20位")
	private String remark;
	private Integer status;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}

	@Override
	public String toString() {
		return "UpdateProductInfoPara [productCode=" + productCode + ", productName=" + productName
				+ ", unit=" + unit + ", originLand=" + originLand + ", articleNum=" + articleNum
				+ ", remark=" + remark + ", status=" + status + "]";
	}

}
