package uk.gov.hmcts.reform.probate.model.cases.validation;


import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * Implementation of {@link NotNullIfAllFieldsHaveValueValidator} validator.
 **/
public class NotNullIfAllFieldsHaveValueValidator
        implements ConstraintValidator<NotNullIfAllFieldsHaveValue, Object> {

    private String[] fieldNames;
    private String[] expectedFieldValues;
    private String dependFieldName;

    @Override
    public void initialize(NotNullIfAllFieldsHaveValue annotation) {
        fieldNames = annotation.fieldNames();
        expectedFieldValues = annotation.fieldValues();
        dependFieldName    = annotation.dependFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {

        if (value == null) {
            return true;
        }
        boolean validCheck = true;
        try {
            for (int i = 0; i < fieldNames.length; i++) {
                String fieldValue       = BeanUtils.getProperty(value, fieldNames[i]);
                String dependFieldValue = BeanUtils.getProperty(value, dependFieldName);

                if (expectedFieldValues[i].equals(fieldValue) && dependFieldValue == null) {
                    validCheck = false;
                }
                else {
                    validCheck = true;
                }
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
