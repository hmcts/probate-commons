package uk.gov.hmcts.reform.probate.model.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCopies {

    private PaymentCopiesForRegion uk;
    
    private PaymentCopiesForRegion overseas;
}
