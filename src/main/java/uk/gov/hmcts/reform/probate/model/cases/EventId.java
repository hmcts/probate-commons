package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventId {

    GOP_CREATE_DRAFT("createDraft"),
    GOP_UPDATE_DRAFT("updateDraft"),
    GOP_CREATE_APPLICATION("createApplication"),
    GOP_UPDATE_APPLICATION("updateApplication"),
    GOP_CREATE_CASE("createCase"),
    GOP_PAYMENT_FAILED("createCasePaymentFailed"),
    GOP_PAYMENT_FAILED_TO_SUCCESS("createCasePaymentSuccess"),
    GOP_PAYMENT_FAILED_AGAIN("createCasePaymentFailedMultiple"),
    UPDATE_GOP_PAYMENT_FAILED("updateCasePaymentFailed"),
    GOP_CREATE_CASE_WITHOUT_PAYMENT("createCaseWithoutPayment"),
    GOP_CITIZEN_HUB_RESPONSE("citizenHubResponse"),
    GOP_CITIZEN_HUB_RESPONSE_DRAFT("citizenHubResponseDraft"),

    CAVEAT_APPLY_FOR_CAVEAT("applyForCaveat"),
    CAVEAT_EXPIRED_FOR_AWAITING_RESOLUTION("caveatExpiredForAwaitingCaveatResolution"),
    CAVEAT_EXPIRED_FOR_CAVEAT_NOT_MATCHED("caveatExpiredForCaveaNotMatched"),
    CAVEAT_APPLY_FOR_WARNNG_VALIDATION("caveatExpiredForWarningValidation"),
    CAVEAT_APPLY_FOR_AWAITING_WARNING_RESPONSE("caveatExpiredForAwaitingWarningResponse");

    @Getter
    private final String name;

}
