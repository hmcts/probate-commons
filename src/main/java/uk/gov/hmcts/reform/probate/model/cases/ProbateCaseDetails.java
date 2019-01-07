package uk.gov.hmcts.reform.probate.model.cases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProbateCaseDetails {

    @Valid
    private CaseData caseData;

    private CaseInfo caseInfo;
}
