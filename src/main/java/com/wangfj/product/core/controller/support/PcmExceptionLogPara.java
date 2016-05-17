package com.wangfj.product.core.controller.support;

import java.util.Date;
/**
 * 
 * @Comment
 * @Class Name PcmExceptionLogPara
 * @Author chenhu
 * @Create In 2015-9-9
 */

public class PcmExceptionLogPara {
	private Long sid;

	private String interfaceName;

	private String exceptionType;

	private Date createTime;

	private String resolveby;

	private Integer processStatus;

	private Date updateTime;

	private String errorMessage;

	private String dataContent;

	private String uuid;
	private int currentPage=1;
	private int pageSize=10;
	
	private String startTimeStr;
	private String endTimeStr;
	
	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getResolveby() {
		return resolveby;
	}

	public void setResolveby(String resolveby) {
		this.resolveby = resolveby;
	}

	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @Return the String uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @Param String uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}