package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentStatus {

    @JsonProperty("Success") SUCCESS, @JsonProperty("Failed") FAILED, @JsonProperty("Initiated") INITIATED
}
