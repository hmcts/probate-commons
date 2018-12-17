package uk.gov.hmcts.reform.probate.model.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.IhtFormType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InheritanceTax {

    private IhtMethod method;

    private IhtFormType form;

    private String identifier;

    private BigDecimal grossValue;

    private BigDecimal netValue;
}
