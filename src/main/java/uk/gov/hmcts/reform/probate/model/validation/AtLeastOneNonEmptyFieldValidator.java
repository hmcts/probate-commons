package uk.gov.hmcts.reform.probate.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.beans.Transient;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AtLeastOneNonEmptyFieldValidator implements ConstraintValidator<AtLeastOneNonEmptyField, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(object.getClass().getMethods())
            .filter(method -> filterValuesPresent(method, object))
            .findAny().isPresent();
    }

    private boolean filterValuesPresent(Method method, Object object) {
        return method.getName().startsWith("get")
            && !method.getName().equals("getType")
            && !method.getName().equals("getClass")
            && method.getAnnotation(Transient.class) == null
            && !isEmpty(method, object);
    }

    private boolean isEmpty(Method method, Object object) {
        try {
            Object obj = MethodUtils.invokeMethod(object, method.getName());
            if (obj instanceof String) {
                return StringUtils.isBlank((String) obj);
            }
            return obj == null;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
