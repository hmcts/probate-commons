package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static uk.gov.hmcts.reform.probate.model.Predeceased.Constants.NO;
import static uk.gov.hmcts.reform.probate.model.Predeceased.Constants.YES_ALL;
import static uk.gov.hmcts.reform.probate.model.Predeceased.Constants.YES_SOME;


@RequiredArgsConstructor
public enum Predeceased {

    @JsonProperty(YES_SOME) optionYesSome(YES_SOME),
    @JsonProperty(YES_ALL) optionYesAll(Constants.YES_ALL),
    @JsonProperty(NO) optionNo(NO);

    @Getter
    private final String description;

    public static Predeceased fromString(String text) {
        for (Predeceased ihtFormType : Predeceased.values()) {
            if (text != null && ihtFormType.description.equalsIgnoreCase(text)) {
                return ihtFormType;
            }
        }
        return null;
    }

    public static class Constants {

        public static final String YES_SOME = "YesSome";

        public static final String YES_ALL = "YesAll";

        public static final String NO = "No";

        private Constants() {
        }
    }
}
