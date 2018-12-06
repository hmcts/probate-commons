package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.forms.MaritalStatus.Constants.DIVORCED_DESC;
import static uk.gov.hmcts.reform.probate.model.forms.MaritalStatus.Constants.JUDICIALLY_SEPERATED_DESC;
import static uk.gov.hmcts.reform.probate.model.forms.MaritalStatus.Constants.MARRIED_DESC;
import static uk.gov.hmcts.reform.probate.model.forms.MaritalStatus.Constants.NEVER_MARRIED_DESC;
import static uk.gov.hmcts.reform.probate.model.forms.MaritalStatus.Constants.WIDOWED_DESC;

@RequiredArgsConstructor
public enum MaritalStatus {

    @JsonProperty(MARRIED_DESC) MARRIED(MARRIED_DESC),
    @JsonProperty(DIVORCED_DESC) DIVORCED(DIVORCED_DESC),
    @JsonProperty(WIDOWED_DESC) WIDOWED(WIDOWED_DESC),
    @JsonProperty(NEVER_MARRIED_DESC) NEVER_MARRIED(NEVER_MARRIED_DESC),
    @JsonProperty(JUDICIALLY_SEPERATED_DESC) JUDICIALLY_SEPERATED(JUDICIALLY_SEPERATED_DESC);

    @Getter
    private final String description;

    public static class Constants {

        public static final String MARRIED_DESC = "Married or in civil partnership";
        public static final String DIVORCED_DESC = "Divorced or civil partnership dissolved";
        public static final String WIDOWED_DESC = "Widowed";
        public static final String NEVER_MARRIED_DESC = "Never married";
        public static final String JUDICIALLY_SEPERATED_DESC = "Judicially separated";

        private Constants() {
        }
    }
}
