package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 集团品牌门店品牌的关系Para
 * 
 * @Class Name PcmBrandRelationPara
 * @Author wangx
 * @Create In 2015-8-11
 */
public class PcmBrandRelationPara extends BasePara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -169484283889791460L;

	@NotNull(message = "{PcmBrandRelationPara.sid.isNotNull}")
	private String sid;// 门店品牌sid

	@NotNull(message = "{PcmBrandRelationPara.parentSid.isNotNull}")
	private String parentSid;// 集团品牌sid

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid == null ? null : parentSid.trim();
	}

	@Override
	public String toString() {
		return "PcmBrandRelationPara [sid=" + sid + ", parentSid=" + parentSid + "]";
	}

}
