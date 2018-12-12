package uk.gov.hmcts.reform.probate.model.forms;

import lombok.Data;
import uk.gov.hmcts.reform.probate.model.IhtFormType;

import java.math.BigDecimal;

@Data
public class InheritanceTax {

    private IhtMethod method;

    private IhtFormType form;

    private String identifier;

    private BigDecimal grossValue;

    private BigDecimal netValue;
}
