package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 集团品牌参数
 * 
 * @Class Name PcmBrandGroupPara
 * @Author wangx
 * @Create In 2015-8-12
 */
public class PcmBrandGroupPara extends BasePara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -1818305816920199297L;

	private Long sid;

	@NotNull(message = "{PcmBrandGroupPara.brandSid.isNotNull}")
	private String brandSid;// 品牌编码

	@NotNull(message = "{PcmBrandGroupPara.brandName.isNotNull}")
	private String brandName;// 品牌名称

	@Pattern(regexp = "^[A-Za-z]+$", message = "{PcmBrandGroupPara.spell.isNotLetter}")
	private String spell;// 拼音

	private String pictureUrl;// 图片路径

	private String brandNameSecond;// 品牌第二个名字

	@Pattern(regexp = "^[A-Za-z]+$", message = "{PcmBrandGroupPara.brandNameEn.isNotLetter}")
	private String brandNameEn;// 英文名称

	private String brandcorp;// 品牌公司

	private String brandpic1;// 图片1

	private String brandpic2;// 图片2

	private Long photoBlacklistBit;// 照片黑名单标志

	private Long optUserSid;// 操作人sid

	private String optRealName;// 操作人

	private String shopSid;// 门店sid

	private Integer shopType;// 门店类型(0 北京，1 外埠 ，2 电商erp)默认为0

	@NotNull(message = "{PcmBrandGroupPara.brandType.isNotNull}")
	private Integer brandType;// 品牌类型 0 集团品牌 1 门店品牌 （默认为0）

	private Integer status = 0;// 有效标记：0有效，1无效（默认为0）

	private String brandDesc;// 品牌描述

	private String brandSpecialty;// 品牌特点

	private String brandSuitability;// 适合人群

	private Integer isDisplay = 0;// 是否展示（0：是，1：否，默认0）

	private Long brandActiveBit;//

	private String brandno;

	private String parentSid;// 门店品牌与集团品牌关联关系

	private Long endBit;

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

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
	}

	public String getBrandNameSecond() {
		return brandNameSecond;
	}

	public void setBrandNameSecond(String brandNameSecond) {
		this.brandNameSecond = brandNameSecond == null ? null : brandNameSecond.trim();
	}

	public Long getBrandActiveBit() {
		return brandActiveBit;
	}

	public void setBrandActiveBit(Long brandActiveBit) {
		this.brandActiveBit = brandActiveBit;
	}

	public String getBrandno() {
		return brandno;
	}

	public void setBrandno(String brandno) {
		this.brandno = brandno == null ? null : brandno.trim();
	}

	public String getBrandcorp() {
		return brandcorp;
	}

	public void setBrandcorp(String brandcorp) {
		this.brandcorp = brandcorp == null ? null : brandcorp.trim();
	}

	public String getBrandpic1() {
		return brandpic1;
	}

	public void setBrandpic1(String brandpic1) {
		this.brandpic1 = brandpic1 == null ? null : brandpic1.trim();
	}

	public String getBrandpic2() {
		return brandpic2;
	}

	public void setBrandpic2(String brandpic2) {
		this.brandpic2 = brandpic2 == null ? null : brandpic2.trim();
	}

	public Long getPhotoBlacklistBit() {
		return photoBlacklistBit;
	}

	public void setPhotoBlacklistBit(Long photoBlacklistBit) {
		this.photoBlacklistBit = photoBlacklistBit;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid == null ? null : parentSid.trim();
	}

	public Long getEndBit() {
		return endBit;
	}

	public void setEndBit(Long endBit) {
		this.endBit = endBit;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell == null ? null : spell.trim();
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

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc == null ? null : brandDesc.trim();
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid == null ? null : shopSid.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBrandNameEn() {
		return brandNameEn;
	}

	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn == null ? null : brandNameEn.trim();
	}

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getBrandSpecialty() {
		return brandSpecialty;
	}

	public void setBrandSpecialty(String brandSpecialty) {
		this.brandSpecialty = brandSpecialty == null ? null : brandSpecialty.trim();
	}

	public String getBrandSuitability() {
		return brandSuitability;
	}

	public void setBrandSuitability(String brandSuitability) {
		this.brandSuitability = brandSuitability == null ? null : brandSuitability.trim();
	}

}