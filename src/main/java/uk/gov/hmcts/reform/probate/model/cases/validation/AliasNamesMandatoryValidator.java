package uk.gov.hmcts.reform.probate.model.cases.validation;

import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AliasNamesMandatoryValidator implements ConstraintValidator<AliasNamesMandatory, GrantOfRepresentation> {
    @Override
    public boolean isValid(GrantOfRepresentation grantOfRepresentation,
                           ConstraintValidatorContext constraintValidatorContext) {
        return !(grantOfRepresentation.getDeceasedAnyOtherNames() != null
            && grantOfRepresentation.getDeceasedAnyOtherNames()
            && (grantOfRepresentation.getDeceasedAliasNameList() == null
            || grantOfRepresentation.getDeceasedAliasNameList().isEmpty()));
    }
}
