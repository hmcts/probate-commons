package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason.Constants.MENTALLY_INCAPABLE_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason.Constants.OTHER_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason.Constants.RENUNCIATED_VALUE;

@RequiredArgsConstructor
public enum SpouseNotApplyingReason {

    @JsonProperty(RENUNCIATED_VALUE) RENUNCIATED("Renunciated"),
    @JsonProperty(MENTALLY_INCAPABLE_VALUE) MENTALLY_INCAPABLE("Mentally incapable"),
    @JsonProperty(OTHER_VALUE) OTHER("Other");

    @Getter
    private final String description;

    public static SpouseNotApplyingReason fromString(String text) {
        for (SpouseNotApplyingReason ms : SpouseNotApplyingReason.values()) {
            if (text != null && ms.description.equalsIgnoreCase(text)) {
                return ms;
            }
        }
        return null;
    }


    public static class Constants {

        public static final String RENUNCIATED_VALUE = "renunciated";
        public static final String MENTALLY_INCAPABLE_VALUE = "mentallyIncapable";
        public static final String OTHER_VALUE = "other";

        private Constants() {
        }
    }
}
