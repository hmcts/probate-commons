package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemovedRepresentative {

    private String organisationID;
    private String solicitorFirstName;
    private String solicitorLastName;
    private String solicitorEmail;
    private Organisation organisation;

}
