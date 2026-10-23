package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.Predeceased.Constants.NO_VALUE;
import static uk.gov.hmcts.reform.probate.model.Predeceased.Constants.YES_ALL_VALUE;
import static uk.gov.hmcts.reform.probate.model.Predeceased.Constants.YES_SOME_VALUE;


@RequiredArgsConstructor
public enum Predeceased {

    @JsonProperty(YES_SOME_VALUE) SOME("optionYesSome"),
    @JsonProperty(YES_ALL_VALUE) ALL("optionYesAll"),
    @JsonProperty(NO_VALUE) NO("optionNo");

    @Getter
    private final String description;


    public static Predeceased fromString(String text) {
        for (Predeceased ms : Predeceased.values()) {
            if (text != null && ms.description.equalsIgnoreCase(text)) {
                return ms;
            }
        }
        return null;
    }

    public static class Constants {

        public static final String YES_SOME_VALUE = "YesSome";

        public static final String YES_ALL_VALUE = "YesAll";

        public static final String NO_VALUE = "No";

        private Constants() {
        }
    }
}
