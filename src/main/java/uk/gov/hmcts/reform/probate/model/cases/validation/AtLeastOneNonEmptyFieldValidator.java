package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AtLeastOneNonEmptyFieldValidator implements ConstraintValidator<AtLeastOneNonEmptyField, CaseData> {

    @Override
    public boolean isValid(CaseData caseData, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(caseData.getClass().getDeclaredFields())
                .filter(field -> !isEmpty(field, caseData))
                .findAny().isPresent();
    }

    private boolean isEmpty(Field field, CaseData caseData) {
        try {
            Class<?> type = field.getType();
            Object obj = MethodUtils.invokeMethod(caseData, "get" + StringUtils.capitalize(field.getName()));
            if (type.equals(String.class)) {
                return StringUtils.isBlank((String) obj);
            }
            return obj == null;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
