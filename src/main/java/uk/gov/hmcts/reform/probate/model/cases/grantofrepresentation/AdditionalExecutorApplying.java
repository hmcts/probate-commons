package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.probate.model.cases.Address;

@Data
@Builder
public class AdditionalExecutorApplying {

    private final String applyingExecutorName;

    private final String applyingExecutorPhoneNumber;

    private final String applyingExecutorEmail;

    private final Address applyingExecutorAddress;

    private String applyingExecutorOtherNames;

    private String applyingExecutorOtherNamesReason;

    private String applyingExecutorOtherReason;
}
