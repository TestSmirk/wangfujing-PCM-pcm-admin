package com.wangfj.product.core.controller.support;

/**
 * 查询门店品牌参数
 * 
 * @Class Name SelectPcmBrandPara
 * @Author wangx
 * @Create In 2015-8-11
 */
public class SelectPcmBrandPara {

	private Long sid;

	private String brandSid;// 门店品牌编码

	private String brandName;// 品牌名称

	private String spell;// 拼音

	private String brandNameSecond;// 品牌第二个名字

	private String brandcorp;// 品牌公司

	private Long optUserSid;// 操作人sid

	private String optRealName;// 操作人

	private Integer shopType;// 门店类型(0 北京，1 外埠 ，2 电商erp)默认为0

	private Integer brandType;// 品牌类型 0 集团品牌 1 门店品牌 （默认为0）

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid == null ? null : brandSid.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell == null ? null : spell.trim();
	}

	public String getBrandNameSecond() {
		return brandNameSecond;
	}

	public void setBrandNameSecond(String brandNameSecond) {
		this.brandNameSecond = brandNameSecond == null ? null : brandNameSecond.trim();
	}

	public String getBrandcorp() {
		return brandcorp;
	}

	public void setBrandcorp(String brandcorp) {
		this.brandcorp = brandcorp == null ? null : brandcorp.trim();
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public String getOptRealName() {
		return optRealName;
	}

	public void setOptRealName(String optRealName) {
		this.optRealName = optRealName == null ? null : optRealName.trim();
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public Integer getBrandType() {
		return brandType;
	}

	public void setBrandType(Integer brandType) {
		this.brandType = brandType;
	}

}
