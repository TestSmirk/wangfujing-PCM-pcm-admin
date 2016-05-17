package com.wangfj.product.core.controller.support;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class PcmAUTagPara {

	private Long sid;
	/**
	 * 标签
	 */
	@NotNull(message = "{PcmAUTagPara.tagName.notNull}")
	private String tagName;
	/**
	 * 标签编码
	 */
	private String tagCode;
	/**
	 * 标签类型
	 */
	private Integer tagType;
	/**
	 * 状态：0 有效 ，1 禁用 （默认为0）
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 操作人
	 */
	private Long optUserSid;
	/**
	 * 开始时间
	 */
	@NotNull(message = "{PcmAUTagPara.beginDate.notNull}")
	private String beginDate;
	/**
	 * 结束时间
	 */
	@NotNull(message = "{PcmAUTagPara.endDate.notNull}")
	private String endDate;
	/**
	 * 最后更改人
	 */
	private String operaterName;

	private String actionCode;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public Integer getTagType() {
		return tagType;
	}

	public void setTagType(Integer tagType) {
		this.tagType = tagType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	@Override
	public String toString() {
		return "PcmAUTagPara [sid=" + sid + ", tagName=" + tagName + ", tagCode=" + tagCode
				+ ", tagType=" + tagType + ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", optUserSid=" + optUserSid + ", beginDate="
				+ beginDate + ", endDate=" + endDate + ", operaterName=" + operaterName
				+ ", actionCode=" + actionCode + "]";
	}
}
