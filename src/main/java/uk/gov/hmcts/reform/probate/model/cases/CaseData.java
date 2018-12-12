package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.validation.AtLeastOneNonEmptyField;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CaseData", description = "Abstract base model for all case types", discriminator = "type",
        subTypes = {GrantOfRepresentation.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = GrantOfRepresentation.class,
        name = CaseType.Constants.GRANT_OF_REPRESENTATION_NAME)})
@Data
//@AtLeastOneNonEmptyField
public abstract class CaseData {

    private CaseType type;

    private ProbateType applicationType;

    private List<CollectionMember<Payment>> payments;

}
