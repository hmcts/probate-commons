package uk.gov.hmcts.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum YesNo {

    @JsonProperty("Yes") YES,
    @JsonProperty("No") NO
}
