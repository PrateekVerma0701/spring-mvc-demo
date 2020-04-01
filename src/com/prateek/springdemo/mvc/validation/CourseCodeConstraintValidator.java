package com.prateek.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

	private String[] coursePrefix;

	@Override
	public void initialize(CourseCode constraintAnnotation) {
		coursePrefix = constraintAnnotation.value();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
		Boolean result = false;
		if (code != null) {
			// loop through course prefixes
			// check to see if code matches any of the courses prefixes
			for (String tempPrefix : coursePrefix) {
				result = code.startsWith(tempPrefix);
				if (result) {
					break;
				}
			}
		} else {
			result = true;
		}
		return result;
	}

}
