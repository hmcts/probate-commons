package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutorApplying {

    private String applyingExecutorName;

    private String applyingExecutorPhoneNumber;

    private String applyingExecutorEmail;

    private Address applyingExecutorAddress;

    private String applyingExecutorOtherNames;

    private String applyingExecutorOtherNamesReason;

    private String applyingExecutorOtherReason;
}
