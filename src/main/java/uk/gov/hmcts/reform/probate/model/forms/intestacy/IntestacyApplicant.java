package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.OptionYesNo;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.ProbateApplicant;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IntestacyApplicant extends ProbateApplicant {

    @ApiModelProperty(value = "Relationship to the deceased")
    private String relationshipToDeceased;

    @ApiModelProperty(value = "Was adoption in England or Wales",
        allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("adoptionPlace")
    private Boolean adoptionInEnglandOrWales;

    @ApiModelProperty(value = "Reason spouse not applying")
    private String spouseNotApplyingReason;

    @Builder
    public IntestacyApplicant(String firstName, String lastName, String alias, String aliasReason, Address address,
                              String postcode, String postcodeAddress, Boolean addressFound,
                              List<Map<String, Object>> addresses, String phoneNumber, Boolean nameAsOnTheWill,
                              String otherReason, String relationshipToDeceased,
                              Boolean adoptionInEnglandOrWales, String spouseNotApplyingReason) {
        super(firstName, lastName, alias, aliasReason, address, postcode, postcodeAddress, addressFound, addresses,
            phoneNumber, nameAsOnTheWill, otherReason);
        this.relationshipToDeceased = relationshipToDeceased;
        this.adoptionInEnglandOrWales = adoptionInEnglandOrWales;
        this.spouseNotApplyingReason = spouseNotApplyingReason;
    }
}
