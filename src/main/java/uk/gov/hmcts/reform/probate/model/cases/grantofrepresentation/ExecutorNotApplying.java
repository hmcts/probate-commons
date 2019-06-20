package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean notApplyingExecutorNotified;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean notApplyingExecutorIsDead;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean notApplyingExecutorDiedBefore;

}
