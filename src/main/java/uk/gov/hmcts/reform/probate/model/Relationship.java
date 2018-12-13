package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.ADOPTED_CHILD_DESC;
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.CHILD_DESC;
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.OTHER_DESC;
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.SPOUSE_DESC;

@RequiredArgsConstructor
public enum Relationship {

    @JsonProperty(SPOUSE_DESC) SPOUSE(SPOUSE_DESC),
    @JsonProperty(CHILD_DESC) CHILD(CHILD_DESC),
    @JsonProperty(ADOPTED_CHILD_DESC) ADOPTED_CHILD(ADOPTED_CHILD_DESC),
    @JsonProperty(OTHER_DESC) OTHER(OTHER_DESC);

    @Getter
    private final String description;

    public static class Constants {

        public static final String SPOUSE_DESC = "spouse";
        public static final String CHILD_DESC = "child";
        public static final String ADOPTED_CHILD_DESC = "adoptedChild";
        public static final String OTHER_DESC = "other";

        private Constants() {
        }
    }
}
