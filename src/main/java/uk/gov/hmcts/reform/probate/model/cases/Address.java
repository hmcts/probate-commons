package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Address", description = "Represents address in CCD")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @NotNull
    @JsonProperty(value = "AddressLine1")
    private String addressLine1;

    @JsonProperty(value = "AddressLine2")
    private String addressLine2;

    @JsonProperty(value = "AddressLine3")
    private String addressLine3;

    @JsonProperty(value = "County")
    private String county;

    @JsonProperty(value = "PostTown")
    private String postTown;

    @NotNull
    @JsonProperty(value = "PostCode")
    private String postCode;

    @JsonProperty(value = "Country")
    private String country;
}
