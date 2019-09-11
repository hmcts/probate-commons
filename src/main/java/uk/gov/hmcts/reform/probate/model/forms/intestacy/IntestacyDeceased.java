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
import uk.gov.hmcts.reform.probate.model.YesNo;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.ProbateDeceased;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IntestacyDeceased extends ProbateDeceased {

    @ApiModelProperty(value = "Deceased marital status")
    private String maritalStatus;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean domiciledInEnglandOrWales;

    @ApiModelProperty(value = "Was Divorced in England or Wales?", allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    @JsonProperty("divorcePlace")
    private Boolean divorcedInEnglandOrWales;

    @ApiModelProperty(value = "Reason spouse not applying")
    private String spouseNotApplyingReason;

    @ApiModelProperty(value = "Does the deceased have other children?",
        allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    @JsonProperty("anyOtherChildren")
    private Boolean otherChildren;

    @ApiModelProperty(value = "Are all the deceased's children over 18?",
        allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    @JsonProperty("allChildrenOver18")
    private Boolean allDeceasedChildrenOverEighteen;

    @ApiModelProperty(value = "Did any of the deceased's children die before the deceased?",
        allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    @JsonProperty("anyDeceasedChildren")
    private Boolean anyDeceasedChildrenDieBeforeDeceased;

    @ApiModelProperty(value = "Are any of the deceased children under 18?",
        allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    @JsonProperty("anyGrandchildrenUnder18")
    private Boolean anyDeceasedGrandchildrenUnderEighteen;

    @ApiModelProperty(value = "Does the deceased have any children?",
        allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyChildren;

    @Builder
    public IntestacyDeceased(String firstName, String lastName, Boolean alias, Map<String, AliasOtherNames> otherNames,
                             Boolean married, Address address, String postcode, String postcodeAddress,
                             Boolean addressFound, List<Map<String, Object>> addresses, LocalDateTime dateOfBirth,
                             LocalDateTime dateOfDeath, String domicile, String maritalStatus,
                             Boolean domiciledInEnglandOrWales, Boolean divorcedInEnglandOrWales,
                             String spouseNotApplyingReason, Boolean otherChildren,
                             Boolean allDeceasedChildrenOverEighteen, Boolean anyDeceasedChildrenDieBeforeDeceased,
                             Boolean anyDeceasedGrandchildrenUnderEighteen, Boolean anyChildren) {
        super(firstName, lastName, alias, otherNames, married, address, postcode, postcodeAddress, addressFound,
            addresses, dateOfBirth, dateOfDeath, domicile);
        this.maritalStatus = maritalStatus;
        this.domiciledInEnglandOrWales = domiciledInEnglandOrWales;
        this.divorcedInEnglandOrWales = divorcedInEnglandOrWales;
        this.spouseNotApplyingReason = spouseNotApplyingReason;
        this.otherChildren = otherChildren;
        this.allDeceasedChildrenOverEighteen = allDeceasedChildrenOverEighteen;
        this.anyDeceasedChildrenDieBeforeDeceased = anyDeceasedChildrenDieBeforeDeceased;
        this.anyDeceasedGrandchildrenUnderEighteen = anyDeceasedGrandchildrenUnderEighteen;
        this.anyChildren = anyChildren;
    }
}
