package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

public class QueryPricePara {
	/**
	 * 专柜商品sid
	 */
	@NotNull(message = "{QueryPricePara.shoppeProSid.isNotNull}")
	private String shoppeProSid;

	/**
	 * 价格渠道(默认为0)
	 */
	private String channelSid;

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}
}
