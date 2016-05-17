/**
 * 
 */
package com.wangfj.product.core.controller.support;

/**
 * 商品品类参数
 * 
 * @Class Name ProductCategoryPara
 * @Author duanzhaole
 * @Create In 2015年8月3日
 */
public class ProductCategoryPara {

	private Long pid;

	private String productSid;

	private String categorySid;
	
	private String id;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
