package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.validation.groups.submission.PaSubmission;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Declaration {

    @NotBlank(groups = {PaSubmission.class})
    private String accept;

    @NotBlank(groups = {PaSubmission.class})
    private String confirm;

    @NotBlank(groups = {PaSubmission.class})
    private String requests;

    private String understand;

    @NotBlank(groups = {PaSubmission.class})
    private String confirmItem1;

    @NotBlank(groups = {PaSubmission.class})
    private String confirmItem2;

    @NotBlank(groups = {PaSubmission.class})
    private String confirmItem3;

    @NotBlank(groups = {PaSubmission.class})
    private String requestsItem1;

    @NotBlank(groups = {PaSubmission.class})
    private String requestsItem2;

    @NotBlank(groups = {PaSubmission.class})
    private String understandItem1;

    @NotBlank(groups = {PaSubmission.class})
    private String understandItem2;

}
