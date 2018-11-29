package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegalStatementExecutorsNotApplying {

    private final LegalStatementExecutorNotApplying value;

    private final String id;

}
