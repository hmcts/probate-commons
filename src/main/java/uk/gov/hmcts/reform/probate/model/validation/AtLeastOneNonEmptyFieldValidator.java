package uk.gov.hmcts.reform.probate.model.validation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AtLeastOneNonEmptyFieldValidator implements ConstraintValidator<AtLeastOneNonEmptyField, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> !isEmpty(field, object))
                .findAny().isPresent();
    }

    private boolean isEmpty(Field field, Object object) {
        try {
            Class<?> type = field.getType();
            Object obj = MethodUtils.invokeMethod(object, "get" + StringUtils.capitalize(field.getName()));
            if (type.equals(String.class)) {
                return StringUtils.isBlank((String) obj);
            }
            return obj == null;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
