package com.wangfj.product.core.controller.support;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.wangfj.product.maindata.domain.vo.ParametersDto;
import com.wangfj.product.maindata.domain.vo.SkuPropDto;

/**
 * 插入一条商品基本信息(SKU) para
 * 
 * @Class Name SaveSkuPara
 * @Author zhangxy
 * @Create In 2015年8月24日
 */
public class SaveSkuPara {
	@Pattern(regexp = "\\w{0,20}", message = "{SaveSkuPara.brandSid.patternError}")
	private String brandSid;/* 门店品牌编码。 */
	private String productNum;/* 款号（吊牌或者外包装上的款号） */
	private List<SkuPropDto> skuProps;/* skuDto */
	// private String colorCode;/* 色码（吊牌或者外包装上的色码对应的字典编码,如果没有，必须传空） */
	// private String colorName;/* 颜色描述（一般情况下颜色描述=色码） */
	// private String proColor;/* 色系 */
	// private String features;/* 特性 */
	// private String sizeCode;/* 尺码/规格 */
	// private String modelNum;/* 货号(吊牌或者外包装的货号，一般情况下货号是到款色规的编码，或者货号=款号) */
	private String prodCategoryCode;/* 末级工业分类SID */
	private String finalClassiFicationCode;/* 末级统计分类SID */
	@Pattern(regexp = "\\d{0,8}", message = "{SaveSkuPara.yearToMarket.patternError}")
	private String yearToMarket;/* 上市年份(yyyy) */
	private String seasonCode;/* 季节表SID */
	private String crowdUser;/* 适用人群 */
	@Pattern(regexp = "[\\w\\u4e00-\\u9fa5]{0,20}", message = "{SaveSkuPara.mainAttribute.patternError}")
	private String mainAttribute;/* 主属性 */
	@Pattern(regexp = "[\\w\\W]{0,200}", message = "{SaveSkuPara.categoryName.patternError}")
	private String categoryName;/* 分类名 */
	private List<ParametersDto> parameters;/* 属性/属性值 */
	private String type;/* 类型（0百货，1超市) */
	private String proTypeSid;/* 商品类型Sid */

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public List<SkuPropDto> getSkuProps() {
		return skuProps;
	}

	public void setSkuProps(List<SkuPropDto> skuProps) {
		this.skuProps = skuProps;
	}

	public String getProdCategoryCode() {
		return prodCategoryCode;
	}

	public void setProdCategoryCode(String prodCategoryCode) {
		this.prodCategoryCode = prodCategoryCode;
	}

	public String getFinalClassiFicationCode() {
		return finalClassiFicationCode;
	}

	public void setFinalClassiFicationCode(String finalClassiFicationCode) {
		this.finalClassiFicationCode = finalClassiFicationCode;
	}

	public String getYearToMarket() {
		return yearToMarket;
	}

	public void setYearToMarket(String yearToMarket) {
		this.yearToMarket = yearToMarket;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getCrowdUser() {
		return crowdUser;
	}

	public void setCrowdUser(String crowdUser) {
		this.crowdUser = crowdUser;
	}

	public String getMainAttribute() {
		return mainAttribute;
	}

	public void setMainAttribute(String mainAttribute) {
		this.mainAttribute = mainAttribute;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ParametersDto> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParametersDto> parameters) {
		this.parameters = parameters;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProTypeSid() {
		return proTypeSid;
	}

	public void setProTypeSid(String proTypeSid) {
		this.proTypeSid = proTypeSid;
	}

	@Override
	public String toString() {
		return "SaveSkuPara [brandSid=" + brandSid + ", productNum=" + productNum + ", skuProps="
				+ skuProps + ", prodCategoryCode=" + prodCategoryCode
				+ ", finalClassiFicationCode=" + finalClassiFicationCode + ", yearToMarket="
				+ yearToMarket + ", seasonCode=" + seasonCode + ", crowdUser=" + crowdUser
				+ ", mainAttribute=" + mainAttribute + ", categoryName=" + categoryName
				+ ", parameters=" + parameters + ", type=" + type + ", proTypeSid=" + proTypeSid
				+ "]";
	}

}
