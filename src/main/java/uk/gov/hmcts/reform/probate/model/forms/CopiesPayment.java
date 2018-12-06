package uk.gov.hmcts.reform.probate.model.forms;

import lombok.Data;

@Data
public class CopiesPayment {

    private CopiesPaymentDetail uk;

    private CopiesPaymentDetail overseas;
}
