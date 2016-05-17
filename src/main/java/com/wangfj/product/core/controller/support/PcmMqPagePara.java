package com.wangfj.product.core.controller.support;

public class PcmMqPagePara {

	private String sid;

	private String messageid;

	private String desturl;

	private String serviceid;

	private String status;

	private String data;

	private String currentPage;

	private String pageSize;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid == null ? null : messageid.trim();
	}

	public String getDesturl() {
		return desturl;
	}

	public void setDesturl(String desturl) {
		this.desturl = desturl == null ? null : desturl.trim();
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid == null ? null : serviceid.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data == null ? null : data.trim();
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