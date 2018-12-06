package uk.gov.hmcts.reform.probate.model.forms;

import lombok.Data;

@Data
public class Registry {

    private String name;

    private String email;

    private String address;

    private Long sequenceNumber;

}
