package be.rochus.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import be.rochus.domain.validator.annotation.FieldsNotEqual;

public class FieldsNotEqualValidator implements ConstraintValidator<FieldsNotEqual, Object> {

	private String firstFieldName;
	private String secondFieldName;

	public void initialize(final FieldsNotEqual constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
	}

	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
			
			if(firstObj == null || secondObj == null) {
				return false;
			}
			return !firstObj.equals(secondObj);
		} catch (final Exception e) {
			return false;
		}
	}
	
}
