package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.YesNo;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.ProbateApplicant;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IntestacyApplicant extends ProbateApplicant {

    @ApiModelProperty(value = "Relationship to the deceased")
    private Relationship relationshipToDeceased;

    @ApiModelProperty(value = "Was adoption in England or Wales", allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean adoptionInEnglandOrWales;

    @Builder
    public IntestacyApplicant(String firstName, String lastName, String alias, String aliasReason, Address address,
                              String postcode, String postcodeAddress, Boolean addressFound,
                              List<Map<String, Object>> addresses, String phoneNumber, Boolean nameAsOnTheWill,
                              String otherReason, Relationship relationshipToDeceased,
                              Boolean adoptionInEnglandOrWales) {
        super(firstName, lastName, alias, aliasReason, address, postcode, postcodeAddress, addressFound, addresses,
                phoneNumber, nameAsOnTheWill, otherReason);
        this.relationshipToDeceased = relationshipToDeceased;
        this.adoptionInEnglandOrWales = adoptionInEnglandOrWales;
    }
}
