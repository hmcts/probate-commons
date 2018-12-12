package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Declaration {

    @JsonSerialize(using = ToStringSerializer.class)
    private Boolean declarationCheckbox;

    private String accept;

    private String confirm;

    private String requests;

    private String understand;

    private String confirmItem1;

    private String confirmItem2;

    private String confirmItem3;

    private String requestsItem1;

    private String requestsItem2;

    private String understandItem1;

    private String understandItem2;

}
