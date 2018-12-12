package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

public class SpouseNotApplyingReasonMandatoryValidatorTest {

    SpouseNotApplyingReasonMandatoryValidator spouseNotApplyingReasonMandatoryValidator
            = new SpouseNotApplyingReasonMandatoryValidator();

    @Test
    public void shouldReturnFalseIfGrossIhtLessThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();

        grantOfRepresentation.setIhtNetValue(250001L);
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        boolean result = spouseNotApplyingReasonMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueIfGrossIhtMoreThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setAssetsOverseas(Boolean.TRUE);
        grantOfRepresentation.setAssetsOverseasNetValue(280000L);
        grantOfRepresentation.setDeceasedSpouseNotApplyingReason(SpouseNotApplyingReason.RENUNCIATED);
        boolean result = spouseNotApplyingReasonMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}
