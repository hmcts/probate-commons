package uk.gov.hmcts.reform.probate.model.forms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CopiesPaymentDetail {

    private BigDecimal cost;

    private Long number;
}
