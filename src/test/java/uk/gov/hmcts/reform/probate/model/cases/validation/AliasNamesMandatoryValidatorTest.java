package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AliasNamesMandatoryValidatorTest {

    AliasNamesMandatoryValidator aliasNamesMandatoryValidator = new AliasNamesMandatoryValidator();

    @Test
    void shouldVReturnFalseIfDeceasedHasOtherNamesAndAliasListIsNull() {
        GrantOfRepresentation grantOfRepresentation = GrantOfRepresentation.builder().deceasedAnyOtherNames(Boolean.TRUE).build();
        boolean result = aliasNamesMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    void shouldVReturnTrueIfDeceasedHasOtherNamesAndAliasListPopulated() {
        GrantOfRepresentation grantOfRepresentation = GrantOfRepresentation.builder().deceasedAnyOtherNames(Boolean.FALSE)
                .build();
        boolean result = aliasNamesMandatoryValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}