package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventId {

    GOP_CREATE_DRAFT("createDraft"),
    GOP_UPDATE_DRAFT("updateDraft"),
    GOP_CREATE_APPLICATION("createApplication"),
    GOP_CREATE_CASE("createCase"),
    GOP_PAYMENT_FAILED("createCasePaymentFailed"),
    GOP_PAYMENT_FAILED_TO_SUCCESS("createCasePaymentSuccess"),
    GOP_PAYMENT_FAILED_AGAIN("createCasePaymentFailedMultiple");

    @Getter
    private final String name;

}