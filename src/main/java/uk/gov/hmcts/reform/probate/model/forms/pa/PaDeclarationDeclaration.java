package uk.gov.hmcts.reform.probate.model.forms.pa;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaDeclarationDeclaration {

    private String confirm;

    private String confirmItem1;

    private String confirmItem2;

    private String confirmItem3;

    private String requests;

    private String requestsItem1;

    private String requestsItem2;

    private String understand;

    private String understandItem1;

    private String understandItem2;

    private String accept;

    private String submitWarning;
}
