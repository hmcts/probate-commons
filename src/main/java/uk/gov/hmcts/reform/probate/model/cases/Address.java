package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@ApiModel(value = "Address", description = "Represents address in CCD")
public class Address implements Serializable {

    @JsonProperty(value = "AddressLine1")
    private final String addressLine1;

    @JsonProperty(value = "AddressLine2")
    private final String addressLine2;

    @JsonProperty(value = "AddressLine3")
    private final String addressLine3;

    @JsonProperty(value = "County")
    private final String county;

    @JsonProperty(value = "PostTown")
    private final String postTown;

    @JsonProperty(value = "PostCode")
    private final String postCode;

    @JsonProperty(value = "Country")
    private final String country;
}
