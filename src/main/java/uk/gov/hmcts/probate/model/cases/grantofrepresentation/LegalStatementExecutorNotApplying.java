package uk.gov.hmcts.probate.model.cases.grantofrepresentation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegalStatementExecutorNotApplying {

    private final String executor;
}
