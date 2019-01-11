package uk.gov.hmcts.reform.probate.model.validation;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.CaveatCreator;
import uk.gov.hmcts.reform.probate.model.cases.caveat.CaveatData;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CaveatDataValidationTest {

    private Validator validator;

    private CaveatData caveatData;

    private LocalDate dod = LocalDate.of(1910, 4, 4);

    private static final String MIN_CHARS = "size must be between 2 and 2147483647";
    private static final String NULL_VALIDATION = "must not be null";
    private static final String DOD_IS_BEFORE_DECEASED_DOB =
            "deceasedDateOfBirth.isBefore(deceasedDateOfDeath) is false";

    @Before
    public void setUp() {
        caveatData = CaveatCreator.createCaveatCase();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void shouldEnsureThatDeceasedFornamesIsLongerThan2Chars() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedForenames("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    public void shouldEnsureThatDeceasedFornamesIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedForenames(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatDeceasedSurnameIsLongerThan2Chars() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedSurname("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    public void shouldEnsureThatDeceasedSurnameIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedSurname(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatDeceasedDateOfDeathIsNotBeforeDateOfBirth() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedDateOfDeath(dod);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(DOD_IS_BEFORE_DECEASED_DOB)));
    }

    @Test
    public void shouldEnsureThatDeceasedDateOfDeathIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedDateOfDeath(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatDeceasedAnyOtherNamesIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedAnyOtherNames(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatCaveatorFornamesIsLongerThan2Chars() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorForenames("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    public void shouldEnsureThatCaveatorFornamesIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorForenames(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatCavetorSurnameIsLongerThan2Chars() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorSurname("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    public void shouldEnsureThatCaveatorSurnameIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorSurname(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatCaveatorEmailIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorEmailAddress(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatCaveatorAddressIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorAddress(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    public void shouldEnsureThatExpiryDateIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setExpiryDate(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    private Set<ConstraintViolation<CaveatData>> executeCaveatValidator() {
        return validator.validate(caveatData, Default.class);
    }

}
