package uk.gov.hmcts.reform.probate.model.cases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProbateCalculatedFees {

    private String status;

    private BigDecimal applicationFee;

    private BigDecimal ukCopiesFee;

    private BigDecimal overseasCopiesFee;

    private BigDecimal total;

}
