package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import uk.gov.hmcts.reform.probate.model.forms.Applicant;
import uk.gov.hmcts.reform.probate.model.forms.Relationship;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

public class IntestacyApplicant extends Applicant {

    @ApiModelProperty(value = "Relationship to the deceased")
    private Relationship relationshipToDeceased;

    @ApiModelProperty(value = "Was adoption in England or Wales", allowableValues = "Yes,No")
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean adoptionInEnglandOrWales;

    @ApiModelProperty(value = "Reason spouse not applying")
    private SpouseNotApplyingReason spouseNotApplyingReason;

    @ApiModelProperty(value = "Does the deceased have other children?", allowableValues = "Yes,No")
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedOtherChildren;

    @ApiModelProperty(value = "Are all the deceased's children over 18?", allowableValues = "Yes,No")
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean allDeceasedChildrenOverEighteen;

    @ApiModelProperty(value = "Did any of the deceased's children die before the deceased?", allowableValues = "Yes,No")
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyDeceasedChildrenDieBeforeDeceased;

    @ApiModelProperty(value = "Are any of the deceased children under 18?", allowableValues = "Yes,No")
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyDeceasedGrandchildrenUnderEighteen;

    @ApiModelProperty(value = "Does the deceased have any children?", allowableValues = "Yes,No")
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyChildren;
}
