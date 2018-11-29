package uk.gov.hmcts.reform.probate.model.cases;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CaseTypeTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetCaseType() {
        CaseData caseData = GrantOfRepresentation.builder()
                .build();
        CaseType caseType = CaseType.getCaseType(caseData);
        assertThat(caseType, is(CaseType.GRANT_OF_REPRESENTATION));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCaseDataDoesNotHaveCaseType() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Cannot find case type associated with class: RandomCaseData");

        CaseData caseData = new RandomCaseData();
        CaseType.getCaseType(caseData);
    }

    public class RandomCaseData extends CaseData {
    }

}
