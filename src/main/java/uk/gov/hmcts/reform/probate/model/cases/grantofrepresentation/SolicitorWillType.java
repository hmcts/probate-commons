package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SolicitorWillType.Constants.GRANT_TYPE_ADMON_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SolicitorWillType.Constants.GRANT_TYPE_PROBATE_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SolicitorWillType.Constants.GRANT_TYPE_INTESTACY_VALUE;

@RequiredArgsConstructor
public enum SolicitorWillType {

    @JsonProperty(GRANT_TYPE_ADMON_VALUE) GRANT_TYPE_ADMON(GRANT_TYPE_ADMON_VALUE),
    @JsonProperty(GRANT_TYPE_PROBATE_VALUE) GRANT_TYPE_PROBATE(GRANT_TYPE_ADMON_VALUE),
    @JsonProperty(GRANT_TYPE_INTESTACY_VALUE) GRANT_TYPE_INTESTACY(GRANT_TYPE_INTESTACY_VALUE);

    @Getter
    private final String name;

    public static class Constants {

        public static final String GRANT_TYPE_ADMON_VALUE = "WillLeftAnnexed";
        public static final String GRANT_TYPE_PROBATE_VALUE = "WillLeft";
        public static final String GRANT_TYPE_INTESTACY_VALUE = "NoWill";

        private Constants() {
        }
    }
}
