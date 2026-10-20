package uk.gov.hmcts.reform.probate.model.payments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardPaymentRequestTest {

    private static final BigDecimal CARD_PAYMENT_AMOUNT = new BigDecimal("120.00");
    private static final BigDecimal CARD_PAYMENT_AMOUNT_2 = new BigDecimal("90.00");
    private static final String CARD_PAYMENT_DESCRIPTION = "Debit card";
    private static final String CARD_PAYMENT_CCD_CASE_NUMBER = "1234-5678-9012-3456";
    private static final String CARD_PAYMENT_CASE_REFERENCE = "12345";
    private static final String CARD_PAYMENT_SERVICE = "GovPay";
    private static final String CARD_PAYMENT_CURRENCY = "Pounds";
    private static final String CARD_PAYMENT_SITEID = "Probate";

    private List<FeeDto> cardPaymentFees;

    private CardPaymentRequest cardPaymentRequest;
    private CardPaymentRequest cardPaymentRequestMatch;

    @BeforeEach
    void setUp() {
        cardPaymentFees = List.of(FeeDto.builder()
                .calculatedAmount(CARD_PAYMENT_AMOUNT)
                .ccdCaseNumber(CARD_PAYMENT_CCD_CASE_NUMBER)
                .code("121")
                .reference("Payment")
                .build());

        cardPaymentRequest = CardPaymentRequest.builder()
                .caseReference(CARD_PAYMENT_CASE_REFERENCE)
                .amount(CARD_PAYMENT_AMOUNT)
                .ccdCaseNumber(CARD_PAYMENT_CCD_CASE_NUMBER)
                .description(CARD_PAYMENT_DESCRIPTION)
                .service(CARD_PAYMENT_SERVICE)
                .currency(CARD_PAYMENT_CURRENCY)
                .siteId(CARD_PAYMENT_SITEID)
                .fees(cardPaymentFees)
                .build();

        cardPaymentRequestMatch = CardPaymentRequest.builder()
                .caseReference(CARD_PAYMENT_CASE_REFERENCE)
                .amount(CARD_PAYMENT_AMOUNT)
                .ccdCaseNumber(CARD_PAYMENT_CCD_CASE_NUMBER)
                .description(CARD_PAYMENT_DESCRIPTION)
                .service(CARD_PAYMENT_SERVICE)
                .currency(CARD_PAYMENT_CURRENCY)
                .siteId(CARD_PAYMENT_SITEID)
                .fees(cardPaymentFees)
                .build();
    }

    @Test
    void shouldSetValuesCorrectlyForPayment() {
        assertEquals(CARD_PAYMENT_CASE_REFERENCE, cardPaymentRequest.getCaseReference());
        assertEquals(CARD_PAYMENT_AMOUNT, cardPaymentRequest.getAmount());
        assertEquals(CARD_PAYMENT_CCD_CASE_NUMBER, cardPaymentRequest.getCcdCaseNumber());
        assertEquals(CARD_PAYMENT_DESCRIPTION, cardPaymentRequest.getDescription());
        assertEquals(CARD_PAYMENT_SERVICE, cardPaymentRequest.getService());
        assertEquals(CARD_PAYMENT_CURRENCY, cardPaymentRequest.getCurrency());
        assertEquals(CARD_PAYMENT_SITEID, cardPaymentRequest.getSiteId());
        assertEquals(cardPaymentFees, cardPaymentRequest.getFees());
    }

    @Test
    void shouldComparePaymentsToEnsureTheyMatch() {
        assertEquals(cardPaymentRequest, cardPaymentRequestMatch);
        assertEquals(cardPaymentRequest.hashCode(), cardPaymentRequestMatch.hashCode());
    }

    @Test
    void shouldComparePaymentsToEnsureTheyDontMatch() {
        cardPaymentRequestMatch.setAmount(CARD_PAYMENT_AMOUNT_2);
        assertNotEquals(cardPaymentRequest, cardPaymentRequestMatch);
    }
}
