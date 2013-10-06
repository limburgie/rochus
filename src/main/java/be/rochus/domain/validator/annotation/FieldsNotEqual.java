package be.rochus.domain.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import be.rochus.domain.validator.FieldsNotEqualValidator;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsNotEqualValidator.class)
@Documented
public @interface FieldsNotEqual {
	String message() default "{constraints.fieldmatch}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * @return The first field
	 */
	String first();

	/**
	 * @return The second field
	 */
	String second();

	/**
	 * Defines several <code>@FieldMatch</code> annotations on the same element
	 * 
	 * @see FieldMatch
	 */
	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		FieldsNotEqual[] value();
	}
}
