package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

public class NetValueAssetsOverseasMandatoryValidatorTest {

    NetValueAssetsOverseasMandatoryValidator netValueAssetsOverseasMandatoryValidator
        = new NetValueAssetsOverseasMandatoryValidator();

    @Test
    public void shouldReturnFalseIfGrossIhtLessThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation =
            GrantOfRepresentation.builder().assetsOverseas(Boolean.TRUE).build();
        boolean result = netValueAssetsOverseasMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueIfGrossIhtMoreThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation =
            GrantOfRepresentation.builder().assetsOverseas(Boolean.TRUE).netValueAssestsOverseas(200000L).build();
        boolean result = netValueAssetsOverseasMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}
