package com.wangfj.product.core.controller.support;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 库存接口Para
 * 
 * @Class Name StockProCountListPara
 * @Author yedong
 * @Create In 2015年7月17日
 */
public class StockProCountListPara {
	/*
	 * 销售单号
	 */
	@NotNull(message = "{StockProCountListPara.saleNo.isNotNull}")
	private String saleNo;
	/*
	 * 脱机标示（1：脱机 0：联网）
	 */
	@NotNull(message = "{StockProCountListPara.isOfferLine.isNotNull}")
	private Integer isOfferLine;
	/*
	 * 商品列表
	 */
	private List<StockProCountPara> products = new ArrayList<StockProCountPara>();

	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	public Integer getIsOfferLine() {
		return isOfferLine;
	}

	public void setIsOfferLine(Integer isOfferLine) {
		this.isOfferLine = isOfferLine;
	}

	public List<StockProCountPara> getProducts() {
		return products;
	}

	public void setProducts(List<StockProCountPara> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "StockProCountListPara [saleNo=" + saleNo + ", isOfferLine=" + isOfferLine
				+ ", products=" + products + "]";
	}


}
