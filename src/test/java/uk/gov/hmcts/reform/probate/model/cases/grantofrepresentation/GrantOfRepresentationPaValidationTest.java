package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.validation.groups.crossfieldcheck.PaCrossFieldCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.fieldcheck.PaFieldCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.nullcheck.PaNullCheck;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class GrantOfRepresentationPaValidationTest {

    private static final Class[] PA_VALIDATION = {PaNullCheck.class, PaFieldCheck.class, PaCrossFieldCheck.class};

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldValidateGrantOfRepresentation() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(0);
    }

    @Test
    void shouldFailWhenPrimaryApplicantForenamesIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPrimaryApplicantForenames(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("primaryApplicantForenames", "must not be blank"));
    }

    @Test
    void shouldFailWhenPrimaryApplicantSurnameIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPrimaryApplicantSurname(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("primaryApplicantSurname", "must not be blank"));
    }

    @Test
    void shouldFailWhenPrimaryApplicantEmailAddressIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPrimaryApplicantEmailAddress(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("primaryApplicantEmailAddress", "must not be blank"));
    }

    @Test
    void shouldFailWhenPrimaryApplicantEmailAddressIsNotValid() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPrimaryApplicantEmailAddress("ssdfdsfd");

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(
                tuple("primaryApplicantEmailAddress", "must be a well-formed email address"));
    }

    @Test
    void shouldFailWhenDeceasedForenamesIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeceasedForenames(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("deceasedForenames", "must not be blank"));
    }

    @Test
    void shouldFailWhenDeceasedSurnameIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeceasedSurname(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("deceasedSurname", "must not be blank"));
    }


    @Test
    void shouldFailWhenDeceasedDateOfBirthIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeceasedDateOfBirth(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(2)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(
                tuple("deceasedDateOfBirth", "must not be null"),
                tuple("deceasedDateOfBirthBeforeDeceasedDateOfDeath",
                    "deceasedDateOfBirth must be on or before deceasedDateOfDeath"));
    }

    @Test
    void shouldFailWhenDeceasedDateOfDeathIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeceasedDateOfDeath(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(2)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(
                tuple("deceasedDateOfDeath", "must not be null"),
                tuple("deceasedDateOfBirthBeforeDeceasedDateOfDeath",
                    "deceasedDateOfBirth must be on or before deceasedDateOfDeath"));
    }

    @Test
    void shouldFailWhenDeceasedDateOfDeathIsBeforeDeceasedDateOfBirth() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeceasedDateOfDeath(LocalDate.of(2000, 1, 1));
        caseData.setDeceasedDateOfBirth(LocalDate.of(2019, 1, 1));

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(
                tuple("deceasedDateOfBirthBeforeDeceasedDateOfDeath",
                    "deceasedDateOfBirth must be on or before deceasedDateOfDeath"));
    }

    @Test
    void shouldPassWhenDeceasedDateOfDeathEqualsDeceasedDateOfDeath() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeceasedDateOfDeath(LocalDate.of(2000, 1, 1));
        caseData.setDeceasedDateOfBirth(LocalDate.of(2000, 1, 1));

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenApplicationTypeIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setApplicationType(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("applicationType", "must not be null"));
    }

    @Test
    void shouldFailWhenDeceasedAddressIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeceasedAddress(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("deceasedAddress", "must not be null"));
    }

    @Test
    void shouldFailWhenDeceasedAddressDoesNotContainAddressLine1AndPostcode() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeceasedAddress(Address.builder().build());

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(2)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("deceasedAddress.addressLine1", "must not be blank"),
                tuple("deceasedAddress.postCode", "must not be blank"));
    }

    @Test
    void shouldFailWhenPrimaryApplicantAddressIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPrimaryApplicantAddress(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("primaryApplicantAddress", "must not be null"));
    }

    @Test
    void shouldFailWhenPrimaryApplicantAddressDoesNotContainAddressLine1AndPostcode() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPrimaryApplicantAddress(Address.builder().build());

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(2)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("primaryApplicantAddress.addressLine1", "must not be blank"),
                tuple("primaryApplicantAddress.postCode", "must not be blank"));
    }

    @Test
    void shouldFailWhenIhtNetValueIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setIhtNetValue(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("ihtNetValue", "must not be null"));
    }

    @Test
    void shouldFailWhenIhtGrossValueIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setIhtGrossValue(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("ihtGrossValue", "must not be null"));
    }

    @Test
    void shouldFailWhenPrimaryApplicantPhoneNumberIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPrimaryApplicantPhoneNumber(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("primaryApplicantPhoneNumber", "must not be blank"));
    }

    @Test
    void shouldFailWhenWillHasCodicilsIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setWillHasCodicils(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("willHasCodicils", "must not be null"));
    }

    @Test
    void shouldFailWhenNumberOfExecutorsIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setNumberOfExecutors(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("numberOfExecutors", "must not be null"));
    }

    @Test
    void shouldFailWhenNumberOfExecutorsIsLessThan1() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setNumberOfExecutors(0L);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("numberOfExecutors", "must be greater than or equal to 1"));
    }

    @Test
    void shouldFailWhenNumberOfApplicantsIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setNumberOfApplicants(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("numberOfApplicants", "must not be null"));
    }

    @Test
    void shouldFailWhenNumberOfApplicantsIsLessThan1() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setNumberOfApplicants(0L);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("numberOfApplicants", "must be greater than or equal to 1"));
    }

    @Test
    void shouldFailWhenGrantTypeIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setGrantType(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).hasSize(1)
            .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(tuple("grantType", "must not be null"));
    }

    @Test
    void shouldNotFailWhenIhtGrossValueFieldIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setIhtGrossValueField(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldNotFailWhenIhtNetValueFieldIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setIhtNetValueField(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_VALIDATION);

        assertThat(violations).isEmpty();

    }
}
