package com.wangfj.product.core.controller.support;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 分页查找供应商信息参数
 * 
 * @Class Name SelectPcmSupplyInfoPagePara
 * @Author wangx
 * @Create In 2015-8-14
 */
public class SelectPcmSupplyInfoPagePara extends BasePara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 5679687212918770182L;

	private String sid;

	private String supplyName; // 供应商名称

	private String shopSid_;// 门店的sid

	private String shopSid;// 门店sid(组织结构编码)

	private String supplyCode; // 供应商编码

	private String supplyType;// 供应商类型:0，门店供应商；1，电商供应商；2，集团供应商（默认0）

	private String status;// 供应商状态：Y正常；T未批准；N终止；L待审批；3淘汰；4停货；5停款；6冻结。

	private String bizCertificateNo; // 营业执照号

	private String orgCode;// 企业代码

	private String keySupplier;// 重点供应商 1：是 0：不是

	private String currentPage = "1";// 当前页数

	private String pageSize = "10";// 每页大小

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName == null ? null : supplyName.trim();
	}

	public String getShopSid_() {
		return shopSid_;
	}

	public void setShopSid_(String shopSid_) {
		this.shopSid_ = shopSid_ == null ? null : shopSid_.trim();
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid == null ? null : shopSid.trim();
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode == null ? null : supplyCode.trim();
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType == null ? null : supplyType.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getBizCertificateNo() {
		return bizCertificateNo;
	}

	public void setBizCertificateNo(String bizCertificateNo) {
		this.bizCertificateNo = bizCertificateNo == null ? null : bizCertificateNo.trim();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	public String getKeySupplier() {
		return keySupplier;
	}

	public void setKeySupplier(String keySupplier) {
		this.keySupplier = keySupplier == null ? null : keySupplier.trim();
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage == null ? null : currentPage.trim();
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize == null ? null : pageSize.trim();
	}

}
