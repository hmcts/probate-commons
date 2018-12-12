package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Applicant {

    private String email;

    private String firstName;

    private String lastName;

    private String postCode;

    private String phoneNumber;

    private String address;

    @JsonSerialize(using = ToStringSerializer.class)
    private Boolean addressFound;

    private String freeTextAddress;
}
