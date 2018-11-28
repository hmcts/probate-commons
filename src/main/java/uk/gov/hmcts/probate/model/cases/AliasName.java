package uk.gov.hmcts.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "AliasName", description = "Represents alias name in CCD")
public class AliasName {

    @JsonProperty(value = "Forenames")
    private final String forenames;

    @JsonProperty(value = "LastName")
    private final String lastName;

    @JsonProperty(value = "AppearOnGrant")
    private final String appearOnGrant;

}
