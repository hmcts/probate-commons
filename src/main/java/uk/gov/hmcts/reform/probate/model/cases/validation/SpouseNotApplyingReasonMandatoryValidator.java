package uk.gov.hmcts.reform.probate.model.cases.validation;

import uk.gov.hmcts.reform.probate.model.cases.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SpouseNotApplyingReasonMandatoryValidator implements
    ConstraintValidator<SpouseNotApplyingReasonMandatory, GrantOfRepresentation> {
    @Override
    public boolean isValid(GrantOfRepresentation grantOfRepresentation,
                           ConstraintValidatorContext constraintValidatorContext) {
        return !(grantOfRepresentation.getIhtNetValue() != null
            && grantOfRepresentation.getIhtNetValue() > 250000
            && grantOfRepresentation.getRelationshipToDeceased() != null
            && !grantOfRepresentation.getRelationshipToDeceased().equals(Relationship.SPOUSE)
            && grantOfRepresentation.getSpouseNotApplyingReason() == null);
    }
}
