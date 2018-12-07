package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

class AssetsOverseasMandatoryValidatorTest {

    AssetsOverseasMandatoryValidator assetsOverseasMandatoryValidator = new AssetsOverseasMandatoryValidator();

    @Test
    void shouldVReturnFalseIfDeceasedHasOtherNamesAndAliasListIsNull() {
        GrantOfRepresentation grantOfRepresentation = GrantOfRepresentation.builder().ihtNetValue(250000L).build();
        boolean result = assetsOverseasMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    void shouldVReturnTrueIfDeceasedHasOtherNamesAndAliasListPopulated() {
        GrantOfRepresentation grantOfRepresentation = GrantOfRepresentation.builder().ihtNetValue(250000L).assetsOverseas(Boolean.FALSE).build();
        boolean result = assetsOverseasMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}