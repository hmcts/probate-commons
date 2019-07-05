package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.validation.groups.crossfieldcheck.PaCrossFieldCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.fieldcheck.PaFieldCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.nullcheck.PaNullCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.submission.PaSubmission;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class GrantOfRepresentationPaSubmissionTest {

    private static final Class[] PA_SUBMISSION =
        {PaNullCheck.class, PaFieldCheck.class, PaCrossFieldCheck.class, PaSubmission.class};

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldValidateGrantOfRepresentation() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(0);
    }

    @Test
    public void shouldFailWhenApplicationSubmittedIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setApplicationSubmittedDate(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(1)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(tuple("applicationSubmittedDate", "must not be null"));
    }

    @Test
    public void shouldFailWhenDeclarationCheckboxIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeclarationCheckbox(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(1)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(tuple("declarationCheckbox", "must not be null"));
    }

    @Test
    public void shouldFailWhenDeclarationCheckboxIsFalse() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeclarationCheckbox(false);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(1)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(tuple("declarationCheckbox", "must be true"));
    }

    @Test
    public void shouldFailWhenDeclarationInNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeclaration(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(1)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(tuple("declaration", "must not be null"));
    }

    @Test
    public void shouldFailWhenDeclarationIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setDeclaration(Declaration.builder().build());

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(10)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        tuple("declaration.confirmItem1", "must not be blank"),
                        tuple("declaration.requests", "must not be blank"),
                        tuple("declaration.confirm", "must not be blank"),
                        tuple("declaration.requestsItem1", "must not be blank"),
                        tuple("declaration.requestsItem2", "must not be blank"),
                        tuple("declaration.confirmItem2", "must not be blank"),
                        tuple("declaration.understandItem1", "must not be blank"),
                        tuple("declaration.understandItem2", "must not be blank"),
                        tuple("declaration.confirmItem3", "must not be blank"),
                        tuple("declaration.accept", "must not be blank"));
    }

    @Test
    public void shouldFailWhenLegalStatementIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setLegalStatement(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(1)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(tuple("legalStatement", "must not be null"));
    }

    @Test
    public void shouldFailWhenLegalStatementIsBlank() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setLegalStatement(LegalStatement.builder().build());

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(5)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        tuple("legalStatement.deceasedEstateLand", "must not be blank"),
                        tuple("legalStatement.intro", "must not be blank"),
                        tuple("legalStatement.applicant", "must not be blank"),
                        tuple("legalStatement.deceased", "must not be blank"),
                        tuple("legalStatement.executorsApplying", "must not be null"));
    }

    @Test
    public void shouldFailWhenPaymentsIsNull() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPayments(null);

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(1)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(tuple("payments", "must not be null"));
    }

    @Test
    public void shouldFailWhenPaymentsSizeIsZero() {
        GrantOfRepresentationData caseData = GrantOfRepresentationCreator.createProbateCase();
        caseData.setPayments(Lists.newArrayList());

        Set<ConstraintViolation<CaseData>> violations = validator.validate(caseData, PA_SUBMISSION);

        assertThat(violations).hasSize(1)
                .extracting(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(tuple("payments", "size must be between 1 and 2147483647"));
    }
}
