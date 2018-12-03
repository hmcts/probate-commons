package uk.gov.hmcts.reform.probate.model.forms;

import lombok.Data;



@Data
//@Builder
public abstract class Applicant {

    private String applicantEmail;

    private String firstName;

    private String lastName;

    private String postCode;

    private String phoneNumber;

    private String address;

    private Boolean addressFound;

    private String freeTextAddress;
}
