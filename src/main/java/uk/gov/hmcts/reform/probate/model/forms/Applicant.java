 package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
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
