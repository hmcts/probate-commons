package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Builder;
import lombok.Data;

@Data
public class CaseInfo {

    private String caseId;

    private String state;

}
