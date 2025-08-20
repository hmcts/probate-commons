package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

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
@ApiModel(value = "Alias", description = "Alias names for Death Record")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Alias {

    @JsonProperty("type")
    private String type;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty(value = "forenames")
    private String forenames;

    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty("suffix")
    private String suffix;
}