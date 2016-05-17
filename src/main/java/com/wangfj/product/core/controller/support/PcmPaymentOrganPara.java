package com.wangfj.product.core.controller.support;

public class PcmPaymentOrganPara {
	/**
	 * sid
	 */
	private String sid;

	/**
	 * 门店编号
	 */
	private String shopSid;

	/**
	 * 银行识别码
	 */
	private String bankBin;

	/**
	 * 支付方式编码
	 */
	private String code;

	/**
	 * 本条记录对应的操作 (A添加；U更新；D删除)
	 */
	private String actionCode;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBankBin() {
		return bankBin;
	}

	public void setBankBin(String bankBin) {
		this.bankBin = bankBin;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	@Override
	public String toString() {
		return "PcmPaymentOrganPara [sid=" + sid + ", shopSid=" + shopSid + ", bankBin=" + bankBin
				+ ", code=" + code + ", actionCode=" + actionCode + "]";
	}

}
