/**
 * @Probject Name: pcm-core
 * @Path: com.wangfj.product.core.controller.supportCategoryPropsDictPara.java
 * @Create By duanzhaole
 * @Create In 2015年7月31日 上午10:21:41
 */
package com.wangfj.product.core.controller.support;

/**
 * 品类属性para参数
 * 
 * @Class Name CategoryPropsDictPara
 * @Author duanzhaole
 * @Create In 2015年7月31日
 */
public class CategoryPropsDictPara {

	/**
	 * 属性名
	 */
	private String propsName;
	/**
	 * 属性描述
	 */
	private String propsDesc;

	/**
	 * 是否erp属性
	 */
	private String isErpProp;
	/**
	 * ERP类型
	 */
	private String erpType;

	/**
	 * 是否关键属性
	 */
	private String isKeyProp;

	/**
	 * 状态：0 可用，1禁用 默认为0
	 */
	private String status;
	
	/**
	 * 属性类型
	 */
	private String isEnumProp;

	private String id;

	private String sid;

	private String channelSid;
	private String insert1;

	private String update1;

	private String delete1;
	private Integer limit = 20;
	private Integer start = 0;

	public String getPropsName() {
		return propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	public String getPropsDesc() {
		return propsDesc;
	}

	public void setPropsDesc(String propsDesc) {
		this.propsDesc = propsDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getInsert1() {
		return insert1;
	}

	public void setInsert1(String insert1) {
		this.insert1 = insert1;
	}

	public String getUpdate1() {
		return update1;
	}

	public void setUpdate1(String update1) {
		this.update1 = update1;
	}

	public String getDelete1() {
		return delete1;
	}

	public void setDelete1(String delete1) {
		this.delete1 = delete1;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getIsErpProp() {
		return isErpProp;
	}

	public void setIsErpProp(String isErpProp) {
		this.isErpProp = isErpProp;
	}

	public String getErpType() {
		return erpType;
	}

	public void setErpType(String erpType) {
		this.erpType = erpType;
	}

	public String getIsKeyProp() {
		return isKeyProp;
	}

	public void setIsKeyProp(String isKeyProp) {
		this.isKeyProp = isKeyProp;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getIsEnumProp() {
		return isEnumProp;
	}

	public void setIsEnumProp(String isEnumProp) {
		this.isEnumProp = isEnumProp;
	}

}
