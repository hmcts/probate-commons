package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

class AssetsOverseasMandatoryValidatorTest {

    AssetsOverseasMandatoryValidator assetsOverseasMandatoryValidator = new AssetsOverseasMandatoryValidator();

    @Test
    void shouldVReturnFalseIfDeceasedHasOtherNamesAndAliasListIsNull() {
        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setIhtNetValue(250000L);
        boolean result = assetsOverseasMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    void shouldVReturnTrueIfDeceasedHasOtherNamesAndAliasListPopulated() {
        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setIhtNetValue(250000L);
        grantOfRepresentation.setAssetsOverseas(Boolean.FALSE);
        boolean result = assetsOverseasMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}