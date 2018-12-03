package uk.gov.hmcts.reform.probate.model.forms;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InheritanceTax {

    private IhtMethod method;

    private IhtFormType form;

    private String ihtIdentifier;

    private Long grossValue;

    private Long netValue;
}
