package uk.gov.hmcts.reform.probate.model.cases;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CaseTypeTest {

    @Test
    void shouldGetCaseType() {
        CaseData caseData = new GrantOfRepresentationData();
        CaseType caseType = CaseType.getCaseType(caseData);
        assertThat(caseType, is(CaseType.GRANT_OF_REPRESENTATION));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCaseDataDoesNotHaveCaseType() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
            CaseData caseData = new RandomCaseData();
            CaseType.getCaseType(caseData);
        });
        assertThat(iae.getMessage(), is("Cannot find case type associated with class: RandomCaseData"));

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
