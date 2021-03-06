package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.MaritalStatus.Constants.DIVORCED_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.MaritalStatus.Constants.JUDICIALLY_SEPARATED_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.MaritalStatus.Constants.MARRIED_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.MaritalStatus.Constants.NEVER_MARRIED_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.MaritalStatus.Constants.WIDOWED_VALUE;


@RequiredArgsConstructor
public enum MaritalStatus {

    @JsonProperty(MARRIED_VALUE) MARRIED("optionMarried"),
    @JsonProperty(DIVORCED_VALUE) DIVORCED("optionDivorced"),
    @JsonProperty(WIDOWED_VALUE) WIDOWED("optionWidowed"),
    @JsonProperty(NEVER_MARRIED_VALUE) NEVER_MARRIED("optionNotMarried"),
    @JsonProperty(JUDICIALLY_SEPARATED_VALUE) JUDICIALLY_SEPARATED("optionSeparated");

    @Getter
    private final String description;


    public static MaritalStatus fromString(String text) {
        for (MaritalStatus ms : MaritalStatus.values()) {
            if (text != null && ms.description.equalsIgnoreCase(text)) {
                return ms;
            }
        }
        return null;
    }

    public static class Constants {

        public static final String MARRIED_VALUE = "marriedCivilPartnership";
        public static final String DIVORCED_VALUE = "divorcedCivilPartnership";
        public static final String WIDOWED_VALUE = "widowed";
        public static final String NEVER_MARRIED_VALUE = "neverMarried";
        public static final String JUDICIALLY_SEPARATED_VALUE = "judicially";

        private Constants() {
        }
    }
}
