package com.wangfj.product.core.controller.support;

import java.util.Date;

/**
 * 品类信息DTO
 * 
 * @Class Name PcmCategory
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmSelectCategoryPara {

	private Long sid;

	/**
	 * 品类编码
	 */
	private String categorySid;

	/**
	 * 分类节点
	 */
	private String parentSid;
	/**
	 * 品类名称
	 */
	private String name;
	/**
	 * 是否是父节点
	 */
	private Integer isParent;
	/**
	 * 是否自建
	 */
	private Integer isSelfBuilt;
	/**
	 * 是否展示
	 */
	private Integer isDisplay;

	/**
	 * 是否末级
	 */
	private String isLeaf;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 根节点
	 */
	private Long rootSid;

	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 顺序
	 */
	private Integer sortOrder;

	/**
	 * 渠道sid(外键)
	 */
	private Long channelSid;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 搜索目录
	 */
	private String searchPath;
	/**
	 * 品类类型
	 */
	private Integer categoryType;

	/**
	 * 门店SID
	 */
	private String shopSid;
	/**
	 * 分类描述
	 */
	private String description;

	/**
	 * ERP类型
	 */
	private Integer erpType;

	/**
	 * 是否是超市，0百货，1超市，默认为0（只限管理分类）
	 */
	private String isMarket;
	/**
	 * 集团SID
	 */
	private String groupSid;
	/**
	 * 录入分类编码
	 */
	private String categoryCode;
	/**
	 * 生效时间
	 */
	private Date successTime;

	/*
	 * A(创建)，U(更新,如果不存在则创建)，D(删除)
	 */
	private String actionCode;

	/**
	 * 操作发起时间戳(yyyyMMdd.HHmmssZ)，例如”20141008.101830+0800”，可以为空。
	 */
	private String actionDate;

	/**
	 * 操作员标识(英文标识)。可以为空。
	 */
	private String actionPerson;

	/** 当前页的起始索引,从1开始 */
	protected int start = 0;

	/** mysql 分页 */
	protected int limit = 10;

	/** 当前页的起始索引,从1开始 */
	protected int currenPage = 1;

	/** mysql 分页 */
	protected int pageSize = 10;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public Integer getIsSelfBuilt() {
		return isSelfBuilt;
	}

	public void setIsSelfBuilt(Integer isSelfBuilt) {
		this.isSelfBuilt = isSelfBuilt;
	}

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRootSid() {
		return rootSid;
	}

	public void setRootSid(Long rootSid) {
		this.rootSid = rootSid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Long channelSid) {
		this.channelSid = channelSid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSearchPath() {
		return searchPath;
	}

	public void setSearchPath(String searchPath) {
		this.searchPath = searchPath;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getErpType() {
		return erpType;
	}

	public void setErpType(Integer erpType) {
		this.erpType = erpType;
	}

	public String getIsMarket() {
		return isMarket;
	}

	public void setIsMarket(String isMarket) {
		this.isMarket = isMarket;
	}

	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurrenPage() {
		return currenPage;
	}

	public void setCurrenPage(int currenPage) {
		this.currenPage = currenPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(String groupSid) {
		this.groupSid = groupSid;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}