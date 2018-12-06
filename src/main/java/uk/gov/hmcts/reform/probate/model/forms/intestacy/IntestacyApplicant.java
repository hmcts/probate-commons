package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.reform.probate.model.YesNo;
import uk.gov.hmcts.reform.probate.model.forms.Applicant;
import uk.gov.hmcts.reform.probate.model.forms.Relationship;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntestacyApplicant extends Applicant {

    @ApiModelProperty(value = "Relationship to the deceased")
    private Relationship relationshipToDeceased;

    @ApiModelProperty(value = "Was adoption in England or Wales", allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean adoptionInEnglandOrWales;

    @ApiModelProperty(value = "Reason spouse not applying")
    private SpouseNotApplyingReason spouseNotApplyingReason;

    @ApiModelProperty(value = "Does the deceased have other children?",
            allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedOtherChildren;

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
    private Boolean deceasedAnyChildren;

}
