package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdditionalExecutorNotApplying {

    private final String notApplyingExecutorName;

    private final String notApplyingExecutorNameOnWill;

    private final String notApplyingExecutorNameDifferenceComment;

    private final String notApplyingExecutorReason;

    private final String notApplyingExecutorNotified;
}
