/**
 * @Probject Name: pcm-core
 * @Path: com.wangfj.product.core.controller.supportCategoryPropsValuePara.java
 * @Create By duanzhaole
 * @Create In 2015年7月31日 下午1:53:19
 */
package com.wangfj.product.core.controller.support;

/**
 * @Class Name CategoryPropsValuePara
 * @Author duanzhaole
 * @Create In 2015年7月31日
 */
public class CategoryPropsValuePara {

	/**
	 * 分类SID
	 */
	private String categoryId;

	/**
	 * 属性名
	 */
	private String propsName;

	private String cid;

	private Integer start;

	private Integer limit;

	private String propsSid;

	private String page;

	private String rows;

	private String name;

	private String channelSid;
	
	private String notNull;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPropsName() {
		return propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getPropsSid() {
		return propsSid;
	}

	public void setPropsSid(String propsSid) {
		this.propsSid = propsSid;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getNotNull() {
		return notNull;
	}

	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}

}
