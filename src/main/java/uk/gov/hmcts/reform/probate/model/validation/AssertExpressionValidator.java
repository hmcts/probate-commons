package uk.gov.hmcts.reform.probate.model.validation;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.CollectionUtils;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class AssertExpressionValidator implements ConstraintValidator<AssertExpression, Object> {

    private Expression expression;

    @Override
    public void initialize(AssertExpression constraintAnnotation) {
        ExpressionParser parser = new SpelExpressionParser();
        expression = parser.parseExpression(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        EvaluationContext evaluationContext = createEvaluationContext(object);
        return expression.getValue(evaluationContext, Boolean.class);
    }

    private StandardEvaluationContext createEvaluationContext(Object object) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setRootObject(object);
        try {
            context.registerFunction("isTrue", BooleanUtils.class.getDeclaredMethod("isTrue", Boolean.class));
            context.registerFunction("isEmpty", CollectionUtils.class.getDeclaredMethod("isEmpty", Collection.class));
            context.registerFunction("L", ValidationUtils.class.getDeclaredMethod("defaultNullToZero", Long.class));
            context.registerFunction("isSpouse",
                    ValidationUtils.class.getDeclaredMethod("isSpouse", Relationship.class));
            context.registerFunction("R", ValidationUtils.class.getDeclaredMethod("getName", Relationship.class));
            context.registerFunction("MS", ValidationUtils.class.getDeclaredMethod("getName", MaritalStatus.class));
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
        return context;
    }
}
