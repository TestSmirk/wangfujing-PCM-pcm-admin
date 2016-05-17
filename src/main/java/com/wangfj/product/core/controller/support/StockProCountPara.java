package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

/**
 * 库存接口商品Para
 * 
 * @Class Name StockProCountPara
 * @Author yedong
 * @Create In 2015年7月17日
 */
public class StockProCountPara {
	/*
	 * 销售明细编号
	 */
	@NotNull(message = "{StockProCountPara.salesItemNo.isNotNull}")
	private String salesItemNo;
	/*
	 * 销售数量
	 */
	@NotNull(message = "{StockProCountPara.saleSum.isNotNull}")
	private Integer saleSum;
	/*
	 * 专柜商品编号
	 */
	private String supplyProductNo;
	/*
	 * 大码
	 */
	private String erpProductNo;
	/*
	 * 渠道
	 */
	private String channelSid;
	/*
	 * 锁定/库存 类型
	 */
	@NotNull(message = "{StockProCountPara.stockType.isNotNull}")
	private Integer stockType;

	public String getSalesItemNo() {
		return salesItemNo;
	}

	public void setSalesItemNo(String salesItemNo) {
		this.salesItemNo = salesItemNo;
	}

	public String getSupplyProductNo() {
		return supplyProductNo;
	}

	public void setSupplyProductNo(String supplyProductNo) {
		this.supplyProductNo = supplyProductNo;
	}

	public Integer getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(Integer saleSum) {
		this.saleSum = saleSum;
	}

	public String getErpProductNo() {
		return erpProductNo;
	}

	public void setErpProductNo(String erpProductNo) {
		this.erpProductNo = erpProductNo;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}

	@Override
	public String toString() {
		return "StockProCountPara [salesItemNo=" + salesItemNo + ", saleSum=" + saleSum
				+ ", supplyProductNo=" + supplyProductNo + ", erpProductNo=" + erpProductNo
				+ ", channelSid=" + channelSid + ", stockType=" + stockType + "]";
	}

}
