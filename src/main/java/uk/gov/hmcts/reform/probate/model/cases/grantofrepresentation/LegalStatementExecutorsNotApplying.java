package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegalStatementExecutorsNotApplying {

    private final LegalStatementExecutorNotApplying value;

    private final String id;

}
