package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "AliasName", description = "Represents alias name in CCD")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliasName {

    @JsonProperty(value = "Forenames")
    private String forenames;

    @JsonProperty(value = "LastName")
    private String lastName;

    @JsonProperty(value = "AppearOnGrant")
    private String appearOnGrant;

}
