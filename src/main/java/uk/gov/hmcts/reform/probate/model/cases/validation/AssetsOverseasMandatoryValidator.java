package uk.gov.hmcts.reform.probate.model.cases.validation;

import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AssetsOverseasMandatoryValidator implements
    ConstraintValidator<AssetsOverseasMandatory, GrantOfRepresentation> {

    @Override
    public boolean isValid(GrantOfRepresentation grantOfRepresentation,
                           ConstraintValidatorContext constraintValidatorContext) {
        return !(grantOfRepresentation.getIhtNetValue() != null
            && grantOfRepresentation.getIhtNetValue() <= 250000
            && grantOfRepresentation.getAssetsOverseas() == null);
    }
}
