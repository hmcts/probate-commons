package uk.gov.hmcts.reform.probate.model.cases.validation;

import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NetValueAssetsOverseasMandatoryValidator implements ConstraintValidator<NetValueAssetsOverseasMandatory, GrantOfRepresentation> {
    @Override
    public boolean isValid(GrantOfRepresentation grantOfRepresentation, ConstraintValidatorContext constraintValidatorContext) {
        return !(grantOfRepresentation.getAssetsOverseas() != null
                && grantOfRepresentation.getAssetsOverseas()
                && grantOfRepresentation.getNetValueAssestsOverseas() == null);
    }
}
