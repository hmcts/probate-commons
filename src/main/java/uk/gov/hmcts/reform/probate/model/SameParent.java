package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.SameParent.Constants.BOTH_PARENTS_SAME;
import static uk.gov.hmcts.reform.probate.model.SameParent.Constants.ONE_PARENTS_SAME;


@RequiredArgsConstructor
public enum SameParent {

    @JsonProperty(BOTH_PARENTS_SAME) BOTH("optionBothParentsSame"),
    @JsonProperty(ONE_PARENTS_SAME) ONE("optionOneParentsSame");

    @Getter
    private final String description;


    public static SameParent fromString(String text) {
        for (SameParent ms : SameParent.values()) {
            if (text != null && ms.description.equalsIgnoreCase(text)) {
                return ms;
            }
        }
        return null;
    }

    public static class Constants {

        public static final String BOTH_PARENTS_SAME = "bothParentsSame";

        public static final String ONE_PARENTS_SAME = "oneParentsSame";

        private Constants() {
        }
    }
}
