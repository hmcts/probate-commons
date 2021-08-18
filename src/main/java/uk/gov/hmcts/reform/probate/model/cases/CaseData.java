package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.reform.probate.model.cases.caveat.CaveatData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;
import uk.gov.hmcts.reform.probate.model.cases.standingsearch.StandingSearchData;
import uk.gov.hmcts.reform.probate.model.cases.willlodgement.WillLodgementData;
import uk.gov.hmcts.reform.probate.model.validation.AtLeastOneNonEmptyField;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CaseData", description = "Abstract base model for all case types", discriminator = "type",
    subTypes = {GrantOfRepresentationData.class, CaveatData.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = GrantOfRepresentationData.class,
        name = CaseType.Constants.GRANT_OF_REPRESENTATION_NAME),
    @JsonSubTypes.Type(value = CaveatData.class, name = CaseType.Constants.CAVEAT_NAME),
    @JsonSubTypes.Type(value = StandingSearchData.class, name = CaseType.Constants.STANDING_SEARCH_NAME),
    @JsonSubTypes.Type(value = WillLodgementData.class, name = CaseType.Constants.WILL_LODGEMENT_NAME)
})
@Data
@AllArgsConstructor
@AtLeastOneNonEmptyField
@EqualsAndHashCode
public abstract class CaseData {

    public abstract List<CollectionMember<CasePayment>> getPayments();

    public abstract void setPayments(List<CollectionMember<CasePayment>> payments);

    public abstract void setRegistryLocation(RegistryLocation registryLocation);

    public abstract Boolean getPaperForm();

    public abstract void setPaperForm(Boolean paperForm);

    public abstract String getSummary();
    
    public abstract void setSummary(String summary);

}
