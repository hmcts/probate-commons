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
import uk.gov.hmcts.reform.probate.model.forms.Applicant;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class IntestacyApplicant extends Applicant {

    @NotNull
    private String email;

    private String firstName;

    private String lastName;

    private String postCode;

    private String phoneNumber;

    private Address address;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean addressFound;

    @ApiModelProperty(value = "Relationship to the deceased")
    private Relationship relationshipToDeceased;

    @ApiModelProperty(value = "Was adoption in England or Wales", allowableValues = YesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean adoptionInEnglandOrWales;

}
