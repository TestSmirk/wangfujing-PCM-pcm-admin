package com.wangfj.product.core.controller.support;

/**
 * 查询集团品牌的参数
 * 
 * @Class Name SelectPcmBrandGroupPara
 * @Author wangx
 * @Create In 2015-8-11
 */
public class SelectPcmBrandGroupPara {

	private Long sid;

	private String brandSid; // 集团品牌编码

	private String brandName;// 中文名称

	private String brandNameSpell;// 中文拼音

	private String brandNameAlias;// 别名

	private String brandNameEn;// 英文名称

	private String brandCreateCountry;// 创建国家

	private Long awesome;// 点赞数量

	private Long isDisplay;// 是否展示

	private Integer status = 0;// 有效标记：0有效，1无效（默认为0）

	private String optRealName;// 操作人

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

	public String getBrandNameSpell() {
		return brandNameSpell;
	}

	public void setBrandNameSpell(String brandNameSpell) {
		this.brandNameSpell = brandNameSpell == null ? null : brandNameSpell.trim();
	}

	public String getBrandNameAlias() {
		return brandNameAlias;
	}

	public void setBrandNameAlias(String brandNameAlias) {
		this.brandNameAlias = brandNameAlias == null ? null : brandNameAlias.trim();
	}

	public String getBrandNameEn() {
		return brandNameEn;
	}

	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn == null ? null : brandNameEn.trim();
	}

	public String getBrandCreateCountry() {
		return brandCreateCountry;
	}

	public void setBrandCreateCountry(String brandCreateCountry) {
		this.brandCreateCountry = brandCreateCountry == null ? null : brandCreateCountry.trim();
	}

	public Long getAwesome() {
		return awesome;
	}

	public void setAwesome(Long awesome) {
		this.awesome = awesome;
	}

	public Long getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Long isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getOptRealName() {
		return optRealName;
	}

	public void setOptRealName(String optRealName) {
		this.optRealName = optRealName == null ? null : optRealName.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}