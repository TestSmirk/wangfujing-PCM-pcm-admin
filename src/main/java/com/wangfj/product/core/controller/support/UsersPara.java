package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 
 * @Comment
 * @Class Name UserPara
 * @Author kongxs
 * @Create In 2015年6月25日
 */
public class UsersPara extends BasePara{
	
	@NotNull(message = "{userPara.name.isNotNull}")
	private String name;
	@NotNull(message = "{userPara.age.isNotNull}")
	private Integer age;
	
	//@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}",message="{userPara.birthday.patternError}")
	private String birthdayStr;
	
	//@Pattern(regexp="/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/",message="{userPara.idcard.patternError}")
	private String idcard;

	/**
	 * @Return the String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @Param String name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @Return the Integer age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @Param Integer age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	
	

	/**
	 * @Return the String birthdayStr
	 */
	public String getBirthdayStr() {
		return birthdayStr;
	}

	/**
	 * @Param String birthdayStr to set
	 */
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	/**
	 * @Return the String idcard
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * @Param String idcard to set
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	
	
	
}
