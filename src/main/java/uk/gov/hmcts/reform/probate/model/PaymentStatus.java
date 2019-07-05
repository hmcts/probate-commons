package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static uk.gov.hmcts.reform.probate.model.PaymentStatus.Constants.FAILED_NAME;
import static uk.gov.hmcts.reform.probate.model.PaymentStatus.Constants.INITIATED_NAME;
import static uk.gov.hmcts.reform.probate.model.PaymentStatus.Constants.NOT_REQUIRED_NAME;
import static uk.gov.hmcts.reform.probate.model.PaymentStatus.Constants.SUCCESS_NAME;

@RequiredArgsConstructor
public enum PaymentStatus {

    @JsonProperty(SUCCESS_NAME) SUCCESS(SUCCESS_NAME),
    @JsonProperty(FAILED_NAME) FAILED(FAILED_NAME),
    @JsonProperty(INITIATED_NAME) INITIATED(INITIATED_NAME),
    @JsonProperty(NOT_REQUIRED_NAME) NOT_REQUIRED(NOT_REQUIRED_NAME);

    @Getter
    private final String name;

    public static PaymentStatus getPaymentStatusByName(String name) {
        return Arrays.stream(values())
                .filter(paymentStatus -> paymentStatus.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static class Constants {

        public static final String SUCCESS_NAME = "Success";
        public static final String FAILED_NAME = "Failed";
        public static final String INITIATED_NAME = "Initiated";
        public static final String NOT_REQUIRED_NAME = "not_required";

        private Constants() {
        }
    }
}
