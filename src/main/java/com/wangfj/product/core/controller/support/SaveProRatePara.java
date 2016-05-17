package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

public class SaveProRatePara {
	/**
	 * 专柜商品SID
	 */
	@NotNull(message = "{SaveProRatePara.shoppeProSid.isNotNull}")
	private String shoppeProSid;
	/**
	 * 促销扣率码
	 */
	@NotNull(message = "{SaveProRatePara.promotionRateCodeSid.isNotNull}")
	private String promotionRateCodeSid;
	/**
	 * 开始时间
	 */
	@NotNull(message = "{SaveProRatePara.beginTime.isNotNull}")
	private String beginTime;
	/**
	 * 结束时间
	 */
	@NotNull(message = "{SaveProRatePara.endTime.isNotNull}")
	private String endTime;
	/**
	 * 操作人
	 */
	@NotNull(message = "{SaveProRatePara.optUserSid.isNotNull}")
	private Long optUserSid;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getPromotionRateCodeSid() {
		return promotionRateCodeSid;
	}

	public void setPromotionRateCodeSid(String promotionRateCodeSid) {
		this.promotionRateCodeSid = promotionRateCodeSid;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

}
