package uk.gov.hmcts.reform.probate.model.cases.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {NetValueAssetsOverseasMandatoryValidator.class})
@Documented
public @interface NetValueAssetsOverseasMandatory {

    String message() default "NetValueAssetsOverseasMandatory";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
