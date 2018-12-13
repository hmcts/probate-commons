package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.forms.intestacy.SpouseNotApplyingReason.Constants.MENTALLY_INCAPABLE_DESC;
import static uk.gov.hmcts.reform.probate.model.forms.intestacy.SpouseNotApplyingReason.Constants.OTHER_DESC;
import static uk.gov.hmcts.reform.probate.model.forms.intestacy.SpouseNotApplyingReason.Constants.RENUNCIATED_DESC;

@RequiredArgsConstructor
public enum SpouseNotApplyingReason {

    @JsonProperty(RENUNCIATED_DESC) RENUNCIATED(RENUNCIATED_DESC),
    @JsonProperty(MENTALLY_INCAPABLE_DESC) MENTALLY_INCAPABLE(MENTALLY_INCAPABLE_DESC),
    @JsonProperty(OTHER_DESC) OTHER(OTHER_DESC);

    @Getter
    private final String description;

    public static class Constants {

        public static final String RENUNCIATED_DESC = "renunciated";
        public static final String MENTALLY_INCAPABLE_DESC = "mentallyIncapable";
        public static final String OTHER_DESC = "other";

        private Constants() {
        }
    }
}
