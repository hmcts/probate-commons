package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.YesNo;
import uk.gov.hmcts.reform.probate.model.forms.Applicant;
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

}
