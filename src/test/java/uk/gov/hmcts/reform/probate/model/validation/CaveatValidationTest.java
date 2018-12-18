package uk.gov.hmcts.reform.probate.model.validation;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.CaveatCreator;
import uk.gov.hmcts.reform.probate.model.cases.caveat.Caveat;
import uk.gov.hmcts.reform.probate.model.validation.groups.SubmissionGroup;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CaveatValidationTest {


    private Validator validator;

    private Caveat caveat;

    private LocalDate dod = LocalDate.of(1910, 4, 4);

    private final String MIN_CHARS = "size must be between 2 and 2147483647";
    private final String NULL_VALIDATION = "must not be null";
    private final String DOD_IS_BEFORE_DECEASED_DOB = "deceasedDateOfBirth.isBefore(deceasedDateOfDeath) is false";

    @Before
    public void setUp() {
        caveat = CaveatCreator.createCaveatCase();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void shouldEnsureThatDeceasedFornamesIsLongerThan2Chars() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setDeceasedForenames("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    public void shouldEnsureThatDeceasedFornamesIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setDeceasedForenames(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatDeceasedSurnameIsLongerThan2Chars() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setDeceasedSurname("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    public void shouldEnsureThatDeceasedSurnameIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setDeceasedSurname(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatDeceasedDateOfDeathIsNotBeforeDateOfBirth() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setDeceasedDateOfDeath(dod);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(DOD_IS_BEFORE_DECEASED_DOB)));
    }

    @Test
    public void shouldEnsureThatDeceasedDateOfDeathIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setDeceasedDateOfDeath(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatDeceasedAnyOtherNamesIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setDeceasedAnyOtherNames(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatCaveatorFornamesIsLongerThan2Chars() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setCaveatorForenames("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    public void shouldEnsureThatCaveatorFornamesIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setCaveatorForenames(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatCavetorSurnameIsLongerThan2Chars() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setCaveatorSurname("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    public void shouldEnsureThatCaveatorSurnameIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setCaveatorSurname(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatCaveatorEmailIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setCaveatorEmailAddress(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatCaveatorAddressIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setCaveatorAddress(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatExpiryDateIsNotNull() {
        Set<ConstraintViolation<Caveat>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveat.setExpiryDate(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    private Set<ConstraintViolation<Caveat>> executeCaveatValidator() {
        return validator.validate(caveat, Default.class);
    }

}
