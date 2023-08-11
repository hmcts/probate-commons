package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.validation.groups.nullcheck.IntestacyNullCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.nullcheck.PaNullCheck;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Address", description = "Represents address in CCD")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
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

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @JsonProperty(value = "PostCode")
    private String postCode;

    @JsonProperty(value = "Country")
    private String country;
}
