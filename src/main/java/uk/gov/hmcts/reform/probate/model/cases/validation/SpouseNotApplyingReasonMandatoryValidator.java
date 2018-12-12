package uk.gov.hmcts.reform.probate.model.cases.validation;

import uk.gov.hmcts.reform.probate.model.Relationship;
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
            && grantOfRepresentation.getPrimaryApplicantRelationshipToDeceased() != null
            && !grantOfRepresentation.getPrimaryApplicantRelationshipToDeceased().equals(Relationship.SPOUSE)
            && grantOfRepresentation.getDeceasedSpouseNotApplyingReason() == null);
    }
}
