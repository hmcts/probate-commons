package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uk.gov.hmcts.reform.probate.model.cases.Address;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdditionalExecutorApplying {

    private String applyingExecutorName;

    private String applyingExecutorPhoneNumber;

    private String applyingExecutorEmail;

    private Address applyingExecutorAddress;

    private String applyingExecutorOtherNames;

    private String applyingExecutorOtherNamesReason;

    private String applyingExecutorOtherReason;
}
