package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.AliasReason.Constants.DEED_POLL_VALUE;
import static uk.gov.hmcts.reform.probate.model.AliasReason.Constants.DIVORCE_VALUE;
import static uk.gov.hmcts.reform.probate.model.AliasReason.Constants.MARRIAGE_VALUE;
import static uk.gov.hmcts.reform.probate.model.AliasReason.Constants.DIFFERENT_SPELLING_VALUE;
import static uk.gov.hmcts.reform.probate.model.AliasReason.Constants.PART_OF_NAME_NOT_INCLUDED_VALUE;
import static uk.gov.hmcts.reform.probate.model.AliasReason.Constants.OTHER_VALUE;

@RequiredArgsConstructor
public enum AliasReason {

    @JsonProperty(MARRIAGE_VALUE) MARRIAGE("optionMarriage"),
    @JsonProperty(DIVORCE_VALUE) DIVORCE("optionDivorce"),
    @JsonProperty(DEED_POLL_VALUE) DEED_POLL("optionDeedPoll"),
    @JsonProperty(DIFFERENT_SPELLING_VALUE) DIFFERENT_SPELLING("optionDifferentSpelling"),
    @JsonProperty(PART_OF_NAME_NOT_INCLUDED_VALUE) PART_OF_NAME_NOT_INCLUDED("optionPartOfNameNotIncluded"),
    @JsonProperty(OTHER_VALUE) OTHER("optionOther");

    @Getter
    private final String description;

    public static AliasReason fromString(String text) {
        for (AliasReason aliasReason : AliasReason.values()) {
            if (aliasReason.description.equalsIgnoreCase(text)) {
                return aliasReason;
            }
        }
        return null;
    }

    public static class Constants {

        public static final String MARRIAGE_VALUE = "Marriage or civil partnership";
        public static final String DIVORCE_VALUE = "Divorce or dissolution";
        public static final String DEED_POLL_VALUE = "Change by deed poll";
        public static final String DIFFERENT_SPELLING_VALUE = "Different Spelling";
        public static final String PART_OF_NAME_NOT_INCLUDED_VALUE = "Part of their name was not included";
        public static final String OTHER_VALUE = "Other";

        private Constants() {
        }
    }
}
