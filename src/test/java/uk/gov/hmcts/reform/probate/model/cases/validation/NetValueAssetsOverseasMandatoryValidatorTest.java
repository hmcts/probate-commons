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

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setAssetsOverseas(Boolean.TRUE);
        boolean result = netValueAssetsOverseasMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueIfGrossIhtMoreThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setAssetsOverseas(Boolean.TRUE);
        grantOfRepresentation.setAssetsOverseasNetValue(200000L);
        boolean result = netValueAssetsOverseasMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}
