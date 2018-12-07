package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

public class NetIhtLessThanGrossIhtValidatorTest {

    NetIhtLessThanGrossIhtValidator netIhtLessThanGrossIhtValidator = new NetIhtLessThanGrossIhtValidator();

    @Test
    public void shouldReturnFalseIfGrossIhtLessThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation =
            GrantOfRepresentation.builder().ihtNetValue(250000L).ihtGrossValue(240000L).build();
        boolean result = netIhtLessThanGrossIhtValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueIfGrossIhtMoreThanNetIhtValue() {

        GrantOfRepresentation grantOfRepresentation =
            GrantOfRepresentation.builder().ihtNetValue(20000L).ihtGrossValue(240000L).build();
        boolean result = netIhtLessThanGrossIhtValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}

