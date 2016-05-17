package com.wangfj.product.core.controller.support;

/**
 * 集团品牌下发(输出参数DTO)
 * 
 * @Class Name BrandDataDto
 * @Author wangx
 * @Create In 2015-8-13
 */
public class BrandDataOut {

	/*
	 * 集团商标(中台的主品牌统一编码) 集团品牌编码 不可空
	 */
	private String superCode;

	/*
	 * 门店品牌编码 （对于历史遗留数据，门店品牌编码和中台品牌编码是不一致的。但是对于中台新准入品牌，两者是相同的） 不可空
	 */
	private String code;

	/*
	 * 中台品牌名称 不可空
	 */
	private String name;

	/*
	 * 门店类型（0-全局，1-电商、2-北京，3-其他门店）0 为集团品牌 不可空
	 */
	private String storeType;

	/*
	 * 中台品牌第二名称 可以为空
	 */
	private String Name2;

	/*
	 * 本条记录对应的操作 (A添加；U更新；D删除) 不可空
	 */
	private String actionCode;

	/*
	 * 操作时间（格式为yyyyMMdd.HHmmssZ，结果形如” 20141008.101603+0800”） 可以为空
	 */
	private String actionDate;

	/*
	 * 操作人 可以为空
	 */
	private String actionPerson;

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode == null ? null : superCode.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType == null ? null : storeType.trim();
	}

	public String getName2() {
		return Name2;
	}

	public void setName2(String name2) {
		Name2 = name2 == null ? null : name2.trim();
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode == null ? null : actionCode.trim();
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate == null ? null : actionDate.trim();
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson == null ? null : actionPerson.trim();
	}

}
