package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.validation.AtLeastOneNonEmptyField;


@ApiModel(value = "CaseData", description = "Abstract base model for all case types", discriminator = "type",
        subTypes = {GrantOfRepresentation.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = GrantOfRepresentation.class,
        name = CaseType.Constants.GRANT_OF_REPRESENTATION_NAME)})
@Data
@AtLeastOneNonEmptyField
public abstract class CaseData {

    @ApiModelProperty(value = "Type of case that will be submitted to CCD",
            allowableValues = "GrantOfRepresentation,Caveat")
    private CaseType type;
}
