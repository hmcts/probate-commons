package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.validation.groups.submission.PaSubmission;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegalStatement {

    @NotBlank(groups = {PaSubmission.class})
    private String intro;

    @NotBlank(groups = {PaSubmission.class})
    private String applicant;

    @NotBlank(groups = {PaSubmission.class})
    private String deceased;

    private String deceasedOtherNames;

    private String deceasedEstateValue;

    private String deceasedEstateValueExceptedEstateConfirmation;

    @NotBlank(groups = {PaSubmission.class})
    private String deceasedEstateLand;

    private List<CollectionMember<LegalStatementExecutorNotApplying>> executorsNotApplying;

    @NotNull(groups = {PaSubmission.class})
    @Size(min = 1, groups = {PaSubmission.class})
    private List<CollectionMember<LegalStatementExecutorApplying>> executorsApplying;

}
