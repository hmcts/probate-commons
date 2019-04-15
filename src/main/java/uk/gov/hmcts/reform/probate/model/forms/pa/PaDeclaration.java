package uk.gov.hmcts.reform.probate.model.forms.pa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaDeclaration {

    private Boolean softStop;

    private Boolean hasEmailChanged;

    private PaLegalStatement legalStatement;

    private PaDeclarationDeclaration declaration;

    @JsonSerialize(using = ToStringSerializer.class)
    private Boolean declarationCheckbox;
}
