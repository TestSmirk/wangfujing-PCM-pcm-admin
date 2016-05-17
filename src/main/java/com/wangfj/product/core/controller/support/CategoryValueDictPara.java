/**
 * @Probject Name: pcm-core
 * @Path: com.wangfj.product.core.controller.supportCategoryValueDictPara.java
 * @Create By duanzhaole
 * @Create In 2015年7月31日 上午11:16:37
 * TODO
 */
package com.wangfj.product.core.controller.support;

/**
 * 属性值参数para
 * 
 * @Class Name CategoryValueDictPara
 * @Author duanzhaole
 * @Create In 2015年7月31日
 */
public class CategoryValueDictPara {

	/**
	 * 属性值
	 */
	private String valuesName;

	/**
	 * 属性值描述
	 */
	private String valuesDesc;

	/**
	 * 属性值code
	 */
	private String valuesCode;

	/**
	 * 状态
	 */
	private Long status;

	/**
	 * 渠道sid
	 */
	private Long channelSid;
	
	private String categorySid; 

	private String id;
	private String sid;
	private String page = "1";

	private String rows = "3";

	public String getValuesName() {
		return valuesName;
	}

	public void setValuesName(String valuesName) {
		this.valuesName = valuesName;
	}

	public String getValuesDesc() {
		return valuesDesc;
	}

	public void setValuesDesc(String valuesDesc) {
		this.valuesDesc = valuesDesc;
	}

	public String getValuesCode() {
		return valuesCode;
	}

	public void setValuesCode(String valuesCode) {
		this.valuesCode = valuesCode;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Long channelSid) {
		this.channelSid = channelSid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

}
