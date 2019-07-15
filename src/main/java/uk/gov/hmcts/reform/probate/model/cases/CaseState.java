package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CASE_CREATED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CASE_PAYMENT_FAILED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CAVEAT_RAISED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.DRAFT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.PA_APP_CREATED_NAME;

@RequiredArgsConstructor
public enum CaseState {

    @JsonProperty(DRAFT_NAME) DRAFT(DRAFT_NAME),
    @JsonProperty(PA_APP_CREATED_NAME) PA_APP_CREATED(PA_APP_CREATED_NAME),
    @JsonProperty(CASE_PAYMENT_FAILED_NAME) CASE_PAYMENT_FAILED(CASE_PAYMENT_FAILED_NAME),
    @JsonProperty(CASE_CREATED_NAME) CASE_CREATED(CASE_CREATED_NAME),
    @JsonProperty(CAVEAT_RAISED_NAME) CAVEAT_RAISED(CAVEAT_RAISED_NAME);

    @Getter
    private final String name;

    public static CaseState getState(String name) {
        return Arrays.stream(CaseState.values()).filter(caseState -> caseState.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find CaseState enum for name: " + name));
    }

    public static class Constants {

        public static final String DRAFT_NAME = "Draft";

        public static final String PA_APP_CREATED_NAME = "PAAppCreated";

        public static final String CASE_PAYMENT_FAILED_NAME = "CasePaymentFailed";

        public static final String CASE_CREATED_NAME = "CaseCreated";

        public static final String CAVEAT_RAISED_NAME = "CaveatRaised";

        private Constants() {
        }
    }
}
