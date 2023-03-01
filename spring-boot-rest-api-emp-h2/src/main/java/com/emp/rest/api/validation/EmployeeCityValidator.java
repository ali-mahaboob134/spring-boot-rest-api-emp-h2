package com.emp.rest.api.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeCityValidator implements ConstraintValidator<ValidateCity, String>{

	@Override
	public boolean isValid(String city, ConstraintValidatorContext context) {
		List<String> cityList = Arrays.asList("Austin", "San Fransisco", "Chicago");
		return cityList.contains(city);
	}

}
