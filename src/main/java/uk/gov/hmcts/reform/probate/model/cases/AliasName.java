package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "AliasName", description = "Represents alias name in CCD")
public class AliasName {

    @JsonProperty(value = "Forenames")
    private String forenames;

    @JsonProperty(value = "LastName")
    private String lastName;

    @JsonProperty(value = "AppearOnGrant")
    private String appearOnGrant;

}
