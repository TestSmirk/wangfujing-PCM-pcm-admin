package com.wangfj.product.core.controller.support.base.constraintValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wangfj.product.core.controller.support.base.annotation.ValidateFromSystem;
import com.wangfj.product.core.controller.support.base.constants.CommonParamValidate;

public class ValidateFromSystemValidator implements ConstraintValidator<ValidateFromSystem, String> {

	@Override
	public void initialize(ValidateFromSystem arg0) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		if (value == null)
			return false;
		return CommonParamValidate.valiateResult(value.toString());
	}


}
