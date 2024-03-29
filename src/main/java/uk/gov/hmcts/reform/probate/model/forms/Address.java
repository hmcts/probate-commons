package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    public Address(String address) {
    }

    @NotNull
    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String county;

    private String postTown;

    private String postCode;

    private String country;

    private String formattedAddress;
}
