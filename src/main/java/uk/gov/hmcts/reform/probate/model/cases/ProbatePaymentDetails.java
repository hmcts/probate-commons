package uk.gov.hmcts.reform.probate.model.cases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProbatePaymentDetails {

    private CaseType caseType;

    private CasePayment payment;
}
