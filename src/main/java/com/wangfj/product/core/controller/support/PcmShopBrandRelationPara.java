package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 门店与品牌关系Controller
 * 
 * @Class Name PcmShopBrandRelationPara
 * @Author wangxuan
 * @Create In 2015-8-20
 */
public class PcmShopBrandRelationPara extends BasePara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -5419940125175132531L;

	@NotNull
	@Pattern(regexp="^[1-9]\\d*$")
	private String shopCode;// 门店编码

	private String brandCode;// 门店品牌编码

	@NotNull
	@Pattern(regexp="^[1-9]\\d*$")
	private String brandGroupCode;// 集团品牌编码

	@NotNull
	private String brandName;// 品牌名称

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode == null ? null : shopCode.trim();
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode == null ? null : brandCode.trim();
	}

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode == null ? null : brandGroupCode.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

}
