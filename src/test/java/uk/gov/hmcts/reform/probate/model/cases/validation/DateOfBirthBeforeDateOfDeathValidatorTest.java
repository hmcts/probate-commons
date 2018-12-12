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

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setDeceasedDateOfBirth(afterDate);
        grantOfRepresentation.setDeceasedDateOfDeath(beforeDate);
        boolean result = dateOfBirthBeforeDateOfDeathValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void shouldReturnTrueIfDodAfterDob() {

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setDeceasedDateOfBirth(beforeDate);
        grantOfRepresentation.setDeceasedDateOfDeath(afterDate);
        boolean result = dateOfBirthBeforeDateOfDeathValidator.isValid(grantOfRepresentation, null);
        Assert.assertThat(result, Matchers.equalTo(Boolean.TRUE));
    }
}
