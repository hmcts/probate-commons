package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.Relationship.Constants.ADOPTED_CHILD_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.Relationship.Constants.CHILD_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.Relationship.Constants.OTHER_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.Relationship.Constants.SPOUSE_VALUE;

@RequiredArgsConstructor
public enum Relationship {

    @JsonProperty(SPOUSE_VALUE) SPOUSE("Spouse"),
    @JsonProperty(CHILD_VALUE) CHILD("Child"),
    @JsonProperty(ADOPTED_CHILD_VALUE) ADOPTED_CHILD("Adopted Child"),
    @JsonProperty(OTHER_VALUE) OTHER("Other");

    private final String description;

    public static class Constants {

        public static final String SPOUSE_VALUE = "partner";
        public static final String CHILD_VALUE = "child";
        public static final String ADOPTED_CHILD_VALUE = "adoptedChild";
        public static final String OTHER_VALUE = "other";

        private Constants() {
        }
    }
}
