package uk.gov.hmcts.probate.model.cases;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Data
@Builder
@ApiModel(value = "DraftRequest", description = "Draft request which contains specific case data to save on CCD")
public class DraftRequest {

    @ApiModelProperty(value = "Case data to send to CCD, which will be of a specific type eg. GrantOfRepresentation")
    @Valid
    private CaseData caseData;
}
