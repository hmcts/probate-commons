package uk.gov.hmcts.reform.probate.model.validation;

import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class AtLeastOneNonEmptyFieldValidatorTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldFailWhenNoFieldIsSet() {
        CaseData caseData = new GrantOfRepresentation();
        assertThat(validator.validate(caseData), hasSize(1));
    }

    @Test
    public void shouldSucceedWhenAtLeastFieldIsSet() {
        CaseData caseData = new GrantOfRepresentation();
        ((GrantOfRepresentation) caseData).setDeceasedSurname("Stark");
        assertThat(validator.validate(caseData), hasSize(0));
    }
}
