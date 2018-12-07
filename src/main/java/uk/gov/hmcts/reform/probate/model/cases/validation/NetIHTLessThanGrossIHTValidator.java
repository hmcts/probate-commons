package uk.gov.hmcts.reform.probate.model.cases.validation;

import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NetIHTLessThanGrossIHTValidator implements ConstraintValidator<NetIHTLessThanGrossIHT, GrantOfRepresentation> {

    @Override
    public boolean isValid(GrantOfRepresentation grantOfRepresentation, ConstraintValidatorContext constraintValidatorContext) {
        Long netIhtValue = grantOfRepresentation.getIhtNetValue();
        Long grossIhtValue = grantOfRepresentation.getIhtGrossValue();
        return !(netIhtValue > grossIhtValue);
    }

}
