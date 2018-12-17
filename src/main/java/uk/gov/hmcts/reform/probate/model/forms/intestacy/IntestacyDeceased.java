package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.YesNo;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.Deceased;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.time.LocalDate;
import java.util.Map;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IntestacyDeceased extends Deceased {

    @ApiModelProperty(value = "Deceased marital status")
    private MaritalStatus maritalStatus;

    @ApiModelProperty(value = "Was Divorced in England or Wales?", allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean divorcedInEnglandOrWales;

    @ApiModelProperty(value = "Reason spouse not applying")
    private SpouseNotApplyingReason spouseNotApplyingReason;

    @ApiModelProperty(value = "Does the deceased have other children?",
            allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean otherChildren;

    @ApiModelProperty(value = "Are all the deceased's children over 18?",
            allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean allDeceasedChildrenOverEighteen;

    @ApiModelProperty(value = "Did any of the deceased's children die before the deceased?",
            allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyDeceasedChildrenDieBeforeDeceased;

    @ApiModelProperty(value = "Are any of the deceased children under 18?",
            allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyDeceasedGrandchildrenUnderEighteen;

    @ApiModelProperty(value = "Does the deceased have any children?",
            allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyChildren;

    @Builder
    public IntestacyDeceased(String firstName, String lastName, Boolean domiciledInEnglandOrWales,
                             LocalDate dateOfBirth, LocalDate dateOfDeath, Boolean addressFound, String address,
                             String freeTextAddress, Boolean alias, Map<String, AliasOtherNames> otherNames,
                             MaritalStatus maritalStatus, Boolean divorcedInEnglandOrWales,
                             SpouseNotApplyingReason spouseNotApplyingReason, Boolean otherChildren,
                             Boolean allDeceasedChildrenOverEighteen, Boolean anyDeceasedChildrenDieBeforeDeceased,
                             Boolean anyDeceasedGrandchildrenUnderEighteen, Boolean anyChildren) {
        super(firstName, lastName, domiciledInEnglandOrWales, dateOfBirth, dateOfDeath, addressFound, address,
                freeTextAddress, alias, otherNames);
        this.maritalStatus = maritalStatus;
        this.divorcedInEnglandOrWales = divorcedInEnglandOrWales;
        this.spouseNotApplyingReason = spouseNotApplyingReason;
        this.otherChildren = otherChildren;
        this.allDeceasedChildrenOverEighteen = allDeceasedChildrenOverEighteen;
        this.anyDeceasedChildrenDieBeforeDeceased = anyDeceasedChildrenDieBeforeDeceased;
        this.anyDeceasedGrandchildrenUnderEighteen = anyDeceasedGrandchildrenUnderEighteen;
        this.anyChildren = anyChildren;
    }
}
