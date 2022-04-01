package uk.gov.hmcts.reform.probate.model.cases;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CaseTypeTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetCaseType() {
        CaseData caseData = new GrantOfRepresentationData();
        CaseType caseType = CaseType.getCaseType(caseData);
        assertEquals(CaseType.GRANT_OF_REPRESENTATION, caseType);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCaseDataDoesNotHaveCaseType() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Cannot find case type associated with class: RandomCase");

        CaseData caseData = new RandomCaseData();
        CaseType.getCaseType(caseData);
    }

    public class RandomCaseData extends CaseData {

        @Override
        public List<CollectionMember<CasePayment>> getPayments() {
            return null;
        }

        @Override
        public void setPayments(List<CollectionMember<CasePayment>> payments) {

        }

        @Override
        public void setRegistryLocation(RegistryLocation registryLocation) {

        }

        @Override
        public Boolean getPaperForm() {
            return null;
        }

        @Override
        public void setPaperForm(Boolean paperForm) {

        }
    }

}
