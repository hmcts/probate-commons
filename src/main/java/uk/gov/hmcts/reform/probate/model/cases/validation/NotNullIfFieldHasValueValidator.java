package uk.gov.hmcts.reform.probate.model.cases.validation;


import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * Implementation of {@link NotNullIfFieldHasValueValidator} validator.
 **/
public class NotNullIfFieldHasValueValidator
        implements ConstraintValidator<NotNullIfFieldHasValue, Object> {

    private String fieldName;
    private String expectedFieldValue;
    private String dependFieldName;

    @Override
    public void initialize(NotNullIfFieldHasValue annotation) {
        fieldName = annotation.fieldName();
        expectedFieldValue = annotation.fieldValue();
        dependFieldName    = annotation.dependFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {

        if (value == null) {
            return true;
        }
        boolean validCheck = true;
        try {
                String fieldValue       = BeanUtils.getProperty(value, fieldName);
                String dependFieldValue = BeanUtils.getProperty(value, dependFieldName);

                if (expectedFieldValue.equals(fieldValue) && dependFieldValue == null) {
                    validCheck = false;
                }


        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        if(!validCheck){
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(dependFieldName)
                    .addConstraintViolation();
        }

        return validCheck;
    }

}
