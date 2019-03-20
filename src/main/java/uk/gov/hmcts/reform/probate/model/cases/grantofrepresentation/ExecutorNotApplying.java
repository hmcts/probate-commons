package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutorNotApplying {

    private String notApplyingExecutorName;

    private String notApplyingExecutorNameOnWill;

    private String notApplyingExecutorNameDifferenceComment;

    private ExecutorNotApplyingReason notApplyingExecutorReason;

    private String notApplyingExecutorNotified;

}
