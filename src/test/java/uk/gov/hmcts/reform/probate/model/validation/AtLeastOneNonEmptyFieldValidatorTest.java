package uk.gov.hmcts.reform.probate.model.validation;

import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertEquals;

public class AtLeastOneNonEmptyFieldValidatorTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldFailWhenNoFieldIsSet() {
        CaseData caseData = new GrantOfRepresentationData();
        assertEquals(1, validator.validate(caseData).size());
    }

    @Test
    public void shouldSucceedWhenAtLeastFieldIsSet() {
        CaseData caseData = new GrantOfRepresentationData();
        ((GrantOfRepresentationData) caseData).setDeceasedSurname("Stark");
        assertEquals(0, validator.validate(caseData).size());
    }
}
