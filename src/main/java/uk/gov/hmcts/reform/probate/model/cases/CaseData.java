package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.cases.caveat.Caveat;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.validation.AtLeastOneNonEmptyField;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CaseData", description = "Abstract base model for all case types", discriminator = "type",
        subTypes = {GrantOfRepresentation.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GrantOfRepresentation.class, name = CaseType.Constants.GRANT_OF_REPRESENTATION_NAME),
        @JsonSubTypes.Type(value = Caveat.class, name = CaseType.Constants.CAVEAT_NAME)
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@AtLeastOneNonEmptyField
public abstract class CaseData {

    private ProbateType applicationType;

    private String primaryApplicantEmailAddress;

    private List<CollectionMember<CasePayment>> payments;

}
