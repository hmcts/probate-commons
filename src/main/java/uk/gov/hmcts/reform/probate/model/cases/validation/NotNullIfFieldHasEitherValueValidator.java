package uk.gov.hmcts.reform.probate.model.cases.validation;


import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * Implementation of {@link NotNullIfFieldHasEitherValue} validator.
 **/
public class NotNullIfFieldHasEitherValueValidator
        implements ConstraintValidator<NotNullIfFieldHasEitherValue, Object> {

    private String fieldName;
    private String[]  expectedFieldValues;
    private String dependFieldName;

    @Override
    public void initialize(NotNullIfFieldHasEitherValue annotation) {
        fieldName = annotation.fieldName();
        expectedFieldValues = annotation.fieldValues();
        dependFieldName    = annotation.dependFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {

        if (value == null) {
            return true;
        }

        try {
            for (int i = 0; i < expectedFieldValues.length; i++) {
                String fieldValue       = BeanUtils.getProperty(value, fieldName);
                String dependFieldValue = BeanUtils.getProperty(value, dependFieldName);

                if (expectedFieldValues[i].equals(fieldValue) && dependFieldValue == null) {
                    ctx.disableDefaultConstraintViolation();
                    ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                            .addPropertyNode(dependFieldName)
                            .addConstraintViolation();
                    return false;
                }
            }


        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }

}
