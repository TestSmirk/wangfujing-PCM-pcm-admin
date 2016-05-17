package com.wangfj.product.core.controller.support;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.wangfj.product.core.controller.support.base.para.BasePara;

public class PcmShoppeAUPara extends BasePara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 173309107825808879L;

	private Long sid;

	@NotNull(message = "{PcmShoppeAUPara.groupSid.notNull}")
	private Long groupSid;// 所属集团sid

	@NotNull(message = "{PcmShoppeAUPara.shopSid.notNull}")
	private Long shopSid;/* 所属门店SID */

	private Long floorSid;/* 楼层SID */

	private Integer industryConditionSid;/* 专柜所属业态SID */

	@NotNull(message = "{PcmShoppeAUPara.shoppeName.notNull}")
	private String shoppeName;/* 专柜名称 */

	private Integer shoppeStatus;/* 状态 */

	private Integer goodsManageType;/* 先销后采(0是，1否） */

	private Integer businessTypeSid;/* 经营方式 */

	private String optUser;/* 最后一次修改人 */

	private String createName;/* 创建人 */

	private String shoppeGroup;/* 柜组 */

	private String shoppeType;/* 专柜类型 */

	private String shoppeShippingPoint;/* 集货地点 */

	private String refCounter;/* 参考奥莱专柜 */

	private Integer negativeStock;/* 是否负库存销售 */

	private Integer isShippingPoint;/* 是否为集货地点 */

	private String supplySid;// 供应商的sid

	private String brandSid;// 品牌的sid

	private List<PcmChannelSaleConfigPara> channelSaleConfigParaList = new ArrayList<PcmChannelSaleConfigPara>();

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(Long groupSid) {
		this.groupSid = groupSid;
	}

	public Long getShopSid() {
		return shopSid;
	}

	public void setShopSid(Long shopSid) {
		this.shopSid = shopSid;
	}

	public Long getFloorSid() {
		return floorSid;
	}

	public void setFloorSid(Long floorSid) {
		this.floorSid = floorSid;
	}

	public Integer getIndustryConditionSid() {
		return industryConditionSid;
	}

	public void setIndustryConditionSid(Integer industryConditionSid) {
		this.industryConditionSid = industryConditionSid;
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
	}

	public Integer getShoppeStatus() {
		return shoppeStatus;
	}

	public void setShoppeStatus(Integer shoppeStatus) {
		this.shoppeStatus = shoppeStatus;
	}

	public Integer getGoodsManageType() {
		return goodsManageType;
	}

	public void setGoodsManageType(Integer goodsManageType) {
		this.goodsManageType = goodsManageType;
	}

	public Integer getBusinessTypeSid() {
		return businessTypeSid;
	}

	public void setBusinessTypeSid(Integer businessTypeSid) {
		this.businessTypeSid = businessTypeSid;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getShoppeGroup() {
		return shoppeGroup;
	}

	public void setShoppeGroup(String shoppeGroup) {
		this.shoppeGroup = shoppeGroup;
	}

	public String getShoppeType() {
		return shoppeType;
	}

	public void setShoppeType(String shoppeType) {
		this.shoppeType = shoppeType;
	}

	public String getShoppeShippingPoint() {
		return shoppeShippingPoint;
	}

	public void setShoppeShippingPoint(String shoppeShippingPoint) {
		this.shoppeShippingPoint = shoppeShippingPoint;
	}

	public String getRefCounter() {
		return refCounter;
	}

	public void setRefCounter(String refCounter) {
		this.refCounter = refCounter;
	}

	public Integer getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(Integer negativeStock) {
		this.negativeStock = negativeStock;
	}

	public Integer getIsShippingPoint() {
		return isShippingPoint;
	}

	public void setIsShippingPoint(Integer isShippingPoint) {
		this.isShippingPoint = isShippingPoint;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public List<PcmChannelSaleConfigPara> getChannelSaleConfigParaList() {
		return channelSaleConfigParaList;
	}

	public void setChannelSaleConfigParaList(
			List<PcmChannelSaleConfigPara> channelSaleConfigParaList) {
		this.channelSaleConfigParaList = channelSaleConfigParaList;
	}

	@Override
	public String toString() {
		return "PcmShoppeAUPara [sid=" + sid + ", groupSid=" + groupSid + ", shopSid=" + shopSid
				+ ", floorSid=" + floorSid + ", industryConditionSid=" + industryConditionSid
				+ ", shoppeName=" + shoppeName + ", shoppeStatus=" + shoppeStatus
				+ ", goodsManageType=" + goodsManageType + ", businessTypeSid=" + businessTypeSid
				+ ", optUser=" + optUser + ", createName=" + createName + ", shoppeGroup="
				+ shoppeGroup + ", shoppeType=" + shoppeType + ", shoppeShippingPoint="
				+ shoppeShippingPoint + ", refCounter=" + refCounter + ", negativeStock="
				+ negativeStock + ", isShippingPoint=" + isShippingPoint + ", supplySid="
				+ supplySid + ", brandSid=" + brandSid + ", channelSaleConfigParaList="
				+ channelSaleConfigParaList + "]";
	}

}
