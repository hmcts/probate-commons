package uk.gov.hmcts.reform.probate.model.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fees {

    private String status;

    private BigDecimal applicationFee;

    private BigDecimal applicationValue;

    private Long ukCopies;

    private BigDecimal ukCopiesFee;

    private Long overseasCopies;

    private BigDecimal overseasCopiesFee;

    private BigDecimal total;

}
