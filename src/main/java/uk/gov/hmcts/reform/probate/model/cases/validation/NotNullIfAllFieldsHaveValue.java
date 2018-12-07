package uk.gov.hmcts.reform.probate.model.cases.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validates that field {@code dependFieldName} is not null if
 * field {@code fieldName} has value {@code fieldValue}.
 **/
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotNullIfAllFieldsHaveValueValidator.class)
@Documented
public @interface NotNullIfAllFieldsHaveValue {

    String[] fieldNames();
    String[] fieldValues();
    String dependFieldName();

    String message() default "NotNullIfAllFieldsHaveValueValidator";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotNullIfAllFieldsHaveValue[] value();
    }

}