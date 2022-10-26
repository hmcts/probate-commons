package uk.gov.hmcts.reform.probate.model.cases.caveat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFee.Constants.PROBATE_FEE_ACCOUNT_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFee.Constants.PROBATE_FEE_CHEQUE_OR_POSTAL_ORDER_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFee.Constants.PROBATE_FEE_IN_PERSON_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFee.Constants.PROBATE_FEE_NOT_INCLUDED_VALUE;

@RequiredArgsConstructor
public enum ProbateFee {

    @JsonProperty(PROBATE_FEE_NOT_INCLUDED_VALUE) PROBATE_FEE_NOT_INCLUDED(PROBATE_FEE_NOT_INCLUDED_VALUE),
    @JsonProperty(PROBATE_FEE_CHEQUE_OR_POSTAL_ORDER_VALUE)
        PROBATE_FEE_CHEQUE_OR_POSTAL_ORDER(PROBATE_FEE_CHEQUE_OR_POSTAL_ORDER_VALUE),
    @JsonProperty(PROBATE_FEE_IN_PERSON_VALUE) PROBATE_FEE_IN_PERSON(PROBATE_FEE_IN_PERSON_VALUE),
    @JsonProperty(PROBATE_FEE_ACCOUNT_VALUE) PROBATE_FEE_ACCOUNT(PROBATE_FEE_ACCOUNT_VALUE);

    @Getter
    private final String description;

    public static ProbateFee fromString(String text) {
        for (ProbateFee ms : ProbateFee.values()) {
            if (text != null && ms.description.equalsIgnoreCase(text)) {
                return ms;
            }
        }
        return null;
    }

    public static class Constants {

        public static final String PROBATE_FEE_NOT_INCLUDED_VALUE = "probateFeeNotIncluded";
        public static final String PROBATE_FEE_CHEQUE_OR_POSTAL_ORDER_VALUE = "probateFeeChequeOrPostalOrder";
        public static final String PROBATE_FEE_IN_PERSON_VALUE = "probateFeeInPerson";
        public static final String PROBATE_FEE_ACCOUNT_VALUE = "probateFeeAccount";

        private Constants() {
        }
    }
}

