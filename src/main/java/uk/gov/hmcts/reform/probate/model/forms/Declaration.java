package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public abstract class Declaration {

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty(value = "declarationCheckbox")
    private Boolean declarationAgreement;
}
