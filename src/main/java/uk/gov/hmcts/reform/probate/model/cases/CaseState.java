package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum CaseState {

    DRAFT("Draft"),
    PA_APP_CREATED("PAAppCreated"),
    CASE_PAYMENT_FAILED("CasePaymentFailed"),
    CASE_CREATED("CaseCreated"),
    CAVEAT_RAISED("CaveatRaised");

    @Getter
    private final String name;

    public static CaseState getState(String name) {
        return Arrays.stream(CaseState.values()).filter(caseState -> caseState.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find CaseState enum for name: " + name));
    }
}
