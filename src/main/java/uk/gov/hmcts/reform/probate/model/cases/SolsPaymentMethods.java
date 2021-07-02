package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.SolsPaymentMethods.Constants.FEE_ACCOUNT_VALUE;

@RequiredArgsConstructor
public enum SolsPaymentMethods {

    @JsonProperty(FEE_ACCOUNT_VALUE) FEE_ACCOUNT(FEE_ACCOUNT_VALUE);

    @Getter
    private final String description;

    public static class Constants {

        public static final String FEE_ACCOUNT_VALUE = "fee account";

        public static final String ALLOWABLE_VALUES = FEE_ACCOUNT_VALUE;

        private Constants() {
        }
    }
}