package uk.gov.hmcts.probate.model.cases.grantofrepresentation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Declaration {

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
