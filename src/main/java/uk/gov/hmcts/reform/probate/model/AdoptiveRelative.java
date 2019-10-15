package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdoptiveRelative {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "relationship")
    private String relationship;

    @JsonProperty(value = "adoptedInOrOut")
    private InOut adoptedInOrOut;

}
