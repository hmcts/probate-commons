package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

public class NetIhtLessThanGrossIhtValidatorTest {

    NetIhtLessThanGrossIhtValidator netIhtLessThanGrossIhtValidator = new NetIhtLessThanGrossIhtValidator();

    @Test
    public void shouldReturnFalseIfGrossIhtLessThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setIhtNetValue(250000L);
        grantOfRepresentation.setIhtGrossValue(240000L);
        boolean result = netIhtLessThanGrossIhtValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueIfGrossIhtMoreThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setIhtNetValue(20000L);
        grantOfRepresentation.setIhtGrossValue(240000L);
        boolean result = netIhtLessThanGrossIhtValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}

