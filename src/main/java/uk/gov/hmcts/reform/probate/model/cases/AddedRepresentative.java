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
public class AddedRepresentative {

    private String organisationID;
    private String updatedBy;
    private String updatedVia;

}
