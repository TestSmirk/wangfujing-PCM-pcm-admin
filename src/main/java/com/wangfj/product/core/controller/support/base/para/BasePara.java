package com.wangfj.product.core.controller.support.base.para;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.wangfj.product.core.controller.support.base.annotation.ValidateFromSystem;


/**
 * 
 * @Comment
 * @Class Name UserPara
 * @Author kongxs
 * @Create In 2015年6月25日
 */
public class BasePara implements Serializable{
	
	@ValidateFromSystem(message = "{fromSystem.isError}")
	@NotNull(message = "{fromSystem.isNotNull}")
	private String fromSystem;

	/**
	 * @Return the String fromSystem
	 */
	public String getFromSystem() {
		return fromSystem;
	}

	/**
	 * @Param String fromSystem to set
	 */
	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}
	
}
