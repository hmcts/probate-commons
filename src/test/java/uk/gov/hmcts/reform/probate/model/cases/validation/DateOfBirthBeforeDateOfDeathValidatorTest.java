package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import java.time.LocalDate;

public class DateOfBirthBeforeDateOfDeathValidatorTest {

    DateOfBirthBeforeDateOfDeathValidator dateOfBirthBeforeDateOfDeathValidator =
        new DateOfBirthBeforeDateOfDeathValidator();
    LocalDate afterDate = LocalDate.of(2018, 9, 12);
    LocalDate beforeDate = LocalDate.of(1954, 9, 18);

    @Test
    public void shouldReturnFalseIfDodB4Dob() {

        GrantOfRepresentation grantOfRepresentation =
            GrantOfRepresentation.builder().deceasedDateOfBirth(afterDate).deceasedDateOfDeath(beforeDate).build();
        boolean result = dateOfBirthBeforeDateOfDeathValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueIfDodAfterDob() {

        GrantOfRepresentation grantOfRepresentation =
            GrantOfRepresentation.builder().deceasedDateOfBirth(beforeDate).deceasedDateOfDeath(afterDate).build();
        boolean result = dateOfBirthBeforeDateOfDeathValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}
