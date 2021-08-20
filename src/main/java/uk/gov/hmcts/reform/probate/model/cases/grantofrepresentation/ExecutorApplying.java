package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.AliasReason;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutorApplying {

    private String applyingExecutorName;

    private String applyingExecutorFirstName;

    private String applyingExecutorLastName;

    private String applyingExecutorPhoneNumber;

    private String applyingExecutorEmail;

    private Address applyingExecutorAddress;

    private String applyingExecutorPostCode;

    private String applyingExecutorOtherNames;

    private AliasReason applyingExecutorOtherNamesReason;

    private String applyingExecutorOtherReason;

    private String applyingExecutorInvitationId;

    private String applyingExecutorLeadName;

    private String applyingExecutorType;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean applyingExecutorAgreed;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean applyingExecutorApplicant;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean applyingExecutorHasOtherName;

}
