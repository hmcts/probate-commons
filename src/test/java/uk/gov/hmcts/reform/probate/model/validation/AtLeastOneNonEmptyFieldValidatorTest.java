package uk.gov.hmcts.reform.probate.model.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class AtLeastOneNonEmptyFieldValidatorTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void shouldFailWhenNoFieldIsSet() {
        CaseData caseData = new GrantOfRepresentationData();
        assertThat(validator.validate(caseData), hasSize(1));
    }

    @Test
    void shouldSucceedWhenAtLeastFieldIsSet() {
        CaseData caseData = new GrantOfRepresentationData();
        ((GrantOfRepresentationData) caseData).setDeceasedSurname("Stark");
        assertThat(validator.validate(caseData), hasSize(0));
    }
}
