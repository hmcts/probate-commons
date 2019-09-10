package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.ADOPTED_CHILD_DESC;
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.CHILD_DESC;
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.OTHER_DESC;
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.PARENT_DESC;
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.PARTNER_DESC;
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.SIBLING_DESC;

@RequiredArgsConstructor
public enum Relationship {

    @JsonProperty(PARTNER_DESC) PARTNER("Husband, wife or civil partner (this does not include common law partners "
        + "even if you lived together)"),
    @JsonProperty(CHILD_DESC) CHILD("Child (this does not include stepchildren)"),
    @JsonProperty(ADOPTED_CHILD_DESC) ADOPTED_CHILD("Child who was lawfully adopted"),
    @JsonProperty(SIBLING_DESC) SIBLING(SIBLING_DESC),
    @JsonProperty(PARENT_DESC) PARENT(PARENT_DESC),
    @JsonProperty(OTHER_DESC) OTHER("Other");

    @Getter
    private final String description;

    public static Relationship fromString(String text) {
        for (Relationship ms : Relationship.values()) {
            if (text != null && ms.description.equalsIgnoreCase(text)) {
                return ms;
            }
        }
        return null;
    }

    public static class Constants {

        public static final String PARTNER_DESC = "partner";
        public static final String CHILD_DESC = "child";
        public static final String ADOPTED_CHILD_DESC = "adoptedChild";
        public static final String SIBLING_DESC = "sibling";
        public static final String PARENT_DESC = "parent";
        public static final String OTHER_DESC = "other";

        private Constants() {
        }
    }
}
