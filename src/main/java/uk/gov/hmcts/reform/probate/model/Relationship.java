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
import static uk.gov.hmcts.reform.probate.model.Relationship.Constants.GRANDCHILD_DESC;

@RequiredArgsConstructor
public enum Relationship {

    @JsonProperty(PARTNER_DESC) PARTNER("optionSpousePartner"),
    @JsonProperty(CHILD_DESC) CHILD("optionChild"),
    @JsonProperty(ADOPTED_CHILD_DESC) ADOPTED_CHILD("optionAdoptedChild"),
    @JsonProperty(SIBLING_DESC) SIBLING(SIBLING_DESC),
    @JsonProperty(PARENT_DESC) PARENT(PARENT_DESC),
    @JsonProperty(OTHER_DESC) OTHER("optionOther"),
    @JsonProperty(GRANDCHILD_DESC) GRANDCHILD("optionGrandchild");

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
        public static final String GRANDCHILD_DESC = "grandchild";

        private Constants() {
        }
    }
}
