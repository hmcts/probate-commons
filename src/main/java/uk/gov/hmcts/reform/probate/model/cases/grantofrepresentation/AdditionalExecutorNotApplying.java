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
public class AdditionalExecutorNotApplying {

    private String notApplyingExecutorName;

    private String notApplyingExecutorNameOnWill;

    private String notApplyingExecutorNameDifferenceComment;

    private String notApplyingExecutorReason;

    private String notApplyingExecutorNotified;
}
