package uk.gov.hmcts.reform.probate.model.forms.caveat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFee;
import uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFeeNotIncludedReason;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.Applicant;
import uk.gov.hmcts.reform.probate.model.forms.Language;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
public class CaveatApplicant extends Applicant {

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private Address address;

    private String caveatorPhoneNumber;

    private ProbateFee probateFee;

    private ProbateFeeNotIncludedReason probateFeeNotIncludedReason;

    private String helpWithFeesReference;

    private String probateFeeNotIncludedExplanation;

    private String probateFeeAccountNumber;

    private String probateFeeAccountReference;

    private Language bilingualCorrespondenceRequested;

}
