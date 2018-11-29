package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DraftResponse {

    private CaseData caseData;

    private CaseInfo caseInfo;
}
