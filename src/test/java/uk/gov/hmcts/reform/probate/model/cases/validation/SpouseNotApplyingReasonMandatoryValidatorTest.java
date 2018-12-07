package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.cases.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

public class SpouseNotApplyingReasonMandatoryValidatorTest {

    SpouseNotApplyingReasonMandatoryValidator spouseNotApplyingReasonMandatoryValidator
        = new SpouseNotApplyingReasonMandatoryValidator();

    @Test
    public void shouldReturnFalseIfGrossIhtLessThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation = GrantOfRepresentation.builder().ihtNetValue(250001L)
            .relationshipToDeceased(Relationship.CHILD)
            .build();
        boolean result = spouseNotApplyingReasonMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueIfGrossIhtMoreThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation =
            GrantOfRepresentation.builder().assetsOverseas(Boolean.TRUE)
                .netValueAssestsOverseas(280000L).spouseNotApplyingReason(SpouseNotApplyingReason.RENUNCIATED).build();
        boolean result = spouseNotApplyingReasonMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}
