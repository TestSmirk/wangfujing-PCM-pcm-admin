package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

/**
 * 商品更改属性
 * 
 * @Class Name ChangeProductStatusPara
 * @Author liuhp
 * @Create In 2015-8-5
 */
public class ChangeProductPara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 3025542529964584867L;

	@NotNull(message = "{专柜商品sid不能为null!}")
	private Long sid;

	private String supplySid;// 供应商编码

	private String shopPuductSid;// 专柜商品编码

	private String actionCode;

	private String actionDate;

	private String actionPerson;

	private Integer saleStatus; // 销售状态

	private String brandSid; // 集团品牌

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public String getShopPuductSid() {
		return shopPuductSid;
	}

	public void setShopPuductSid(String shopPuductSid) {
		this.shopPuductSid = shopPuductSid;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

	public Integer getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

}
