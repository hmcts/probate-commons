package uk.gov.hmcts.probate.model.cases;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseInfo {

    private String caseId;

    private String state;

}
