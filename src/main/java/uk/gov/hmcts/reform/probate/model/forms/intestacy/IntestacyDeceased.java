package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.reform.probate.model.YesNo;
import uk.gov.hmcts.reform.probate.model.forms.Deceased;
import uk.gov.hmcts.reform.probate.model.forms.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;


@Data
@EqualsAndHashCode(callSuper = true)
public class IntestacyDeceased extends Deceased {

    @ApiModelProperty(value = "Deceased marital status")
    private MaritalStatus maritalStatus;

    @ApiModelProperty(value = "Was Divorced in England or Wales?", allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean divorcedInEnglandOrWales;

}
