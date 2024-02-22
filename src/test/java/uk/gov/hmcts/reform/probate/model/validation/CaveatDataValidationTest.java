package uk.gov.hmcts.reform.probate.model.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.probate.model.CaveatCreator;
import uk.gov.hmcts.reform.probate.model.cases.caveat.CaveatData;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.tuple;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CaveatDataValidationTest {

    private Validator validator;

    private CaveatData caveatData;

    private LocalDate dod = LocalDate.of(1910, 4, 4);

    private static final String MIN_CHARS = "size must be between 2 and 2147483647";
    private static final String NULL_VALIDATION = "must not be null";

    @BeforeEach
    public void setUp() {
        caveatData = CaveatCreator.createCaveatCase();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldEnsureThatDeceasedFornamesIsLongerThan2Chars() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedForenames("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    void shouldEnsureThatApplicationIdIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setApplicationId(null);
        violations = executeCaveatValidator();
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    void shouldEnsureThatDeceasedFornamesIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedForenames(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    void shouldEnsureThatDeceasedSurnameIsLongerThan2Chars() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedSurname("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    void shouldEnsureThatDeceasedSurnameIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedSurname(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    void shouldEnsureThatDeceasedDateOfDeathIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedDateOfDeath(null);
        violations = executeCaveatValidator();

        Assertions.assertThat(violations).hasSize(2)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("deceasedDateOfDeath", NULL_VALIDATION),
                tuple("deceasedDateOfBirthBeforeDeceasedDateOfDeath",
                    "deceasedDateOfBirth must be before deceasedDateOfDeath"));
    }

    @Test
    void shouldEnsureThatDeceasedAddressIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setDeceasedAddress(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }


    @Test
    void shouldEnsureThatCaveatorFornamesIsLongerThan2Chars() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorForenames("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    void shouldEnsureThatCaveatorFornamesIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorForenames(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    void shouldEnsureThatCavetorSurnameIsLongerThan2Chars() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorSurname("a");
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(MIN_CHARS)));
    }

    @Test
    void shouldEnsureThatCaveatorSurnameIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorSurname(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    void shouldEnsureThatCaveatorEmailIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorEmailAddress(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    @Test
    void shouldEnsureThatCaveatorAddressIsNotNull() {
        Set<ConstraintViolation<CaveatData>> violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(0));
        caveatData.setCaveatorAddress(null);
        violations = executeCaveatValidator();
        assertThat(violations, Matchers.hasSize(1));
        assertThat(violations.iterator().next().getMessage(), is(equalTo(NULL_VALIDATION)));
    }

    private Set<ConstraintViolation<CaveatData>> executeCaveatValidator() {
        return validator.validate(caveatData, Default.class);
    }

}
