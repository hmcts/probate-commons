package uk.gov.hmcts.reform.probate.model.cases.caveat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFeeNotIncludedReason.Constants.HELP_WITH_FEES_APPLIED_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFeeNotIncludedReason.Constants.HELP_WITH_FEES_APPLYING_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFeeNotIncludedReason.Constants.OTHER_VALUE;

@RequiredArgsConstructor
public enum ProbateFeeNotIncludedReason {

    @JsonProperty(HELP_WITH_FEES_APPLIED_VALUE) HELP_WITH_FEES_APPLIED(HELP_WITH_FEES_APPLIED_VALUE),
    @JsonProperty(HELP_WITH_FEES_APPLYING_VALUE) HELP_WITH_FEES_APPLYING(HELP_WITH_FEES_APPLYING_VALUE),
    @JsonProperty(OTHER_VALUE) OTHER(OTHER_VALUE);

    @Getter
    private final String description;

    public static ProbateFeeNotIncludedReason fromString(String text) {
        for (ProbateFeeNotIncludedReason ms : ProbateFeeNotIncludedReason.values()) {
            if (text != null && ms.description.equalsIgnoreCase(text)) {
                return ms;
            }
        }
        return null;
    }

    public static class Constants {
        public static final String HELP_WITH_FEES_APPLIED_VALUE = "helpWithFeesApplied";
        public static final String HELP_WITH_FEES_APPLYING_VALUE = "helpWithFeesApplying";
        public static final String OTHER_VALUE = "other";

        private Constants() {
        }
    }
}