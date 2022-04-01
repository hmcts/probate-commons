package uk.gov.hmcts.reform.probate.model.payments;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CardPaymentRequestTest {

    private static final BigDecimal CARD_PAYMENT_AMOUNT = new BigDecimal(120.00);
    private static final BigDecimal CARD_PAYMENT_AMOUNT_2 = new BigDecimal(90.00);
    private static final String CARD_PAYMENT_DESCRIPTION = "Debit card";
    private static final String CARD_PAYMENT_CCD_CASE_NUMBER = "1234-5678-9012-3456";
    private static final String CARD_PAYMENT_CASE_REFERNCE = "12345";
    private static final String CARD_PAYMENT_SERVICE = "GovPay";
    private static final String CARD_PAYMENT_CURRENCY = "Pounds";
    private static final String CARD_PAYMENT_SITEID = "Probate";

    private static List<FeeDto> CARD_PAYMENT_FEES = new ArrayList();

    CardPaymentRequest cardPaymentRequest;

    CardPaymentRequest cardPaymentRequestMatch;

    @Before
    public void setUp() throws Exception {
        CARD_PAYMENT_FEES.add(FeeDto.builder()
                .calculatedAmount(CARD_PAYMENT_AMOUNT)
                .ccdCaseNumber(CARD_PAYMENT_CCD_CASE_NUMBER)
                .code("121")
                .reference("Payment")
                .build());

        cardPaymentRequest = CardPaymentRequest.builder()
                .caseReference(CARD_PAYMENT_CASE_REFERNCE)
                .amount(CARD_PAYMENT_AMOUNT)
                .ccdCaseNumber(CARD_PAYMENT_CCD_CASE_NUMBER)
                .description(CARD_PAYMENT_DESCRIPTION)
                .service(CARD_PAYMENT_SERVICE)
                .currency(CARD_PAYMENT_CURRENCY)
                .siteId(CARD_PAYMENT_SITEID)
                .fees(CARD_PAYMENT_FEES)
                .build();

        cardPaymentRequestMatch = CardPaymentRequest.builder()
                .caseReference(CARD_PAYMENT_CASE_REFERNCE)
                .amount(CARD_PAYMENT_AMOUNT)
                .ccdCaseNumber(CARD_PAYMENT_CCD_CASE_NUMBER)
                .description(CARD_PAYMENT_DESCRIPTION)
                .service(CARD_PAYMENT_SERVICE)
                .currency(CARD_PAYMENT_CURRENCY)
                .siteId(CARD_PAYMENT_SITEID)
                .fees(CARD_PAYMENT_FEES)
                .build();
    }

    @Test
    public void shouldSetValuesCorrectlyForPayment() throws IOException {
        assertEquals(cardPaymentRequest.getCaseReference(), CARD_PAYMENT_CASE_REFERNCE);
        assertEquals(cardPaymentRequest.getAmount(), CARD_PAYMENT_AMOUNT);
        assertEquals(cardPaymentRequest.getCcdCaseNumber(), CARD_PAYMENT_CCD_CASE_NUMBER);
        assertEquals(cardPaymentRequest.getDescription(), CARD_PAYMENT_DESCRIPTION);
        assertEquals(cardPaymentRequest.getService(), CARD_PAYMENT_SERVICE);
        assertEquals(cardPaymentRequest.getCurrency(), CARD_PAYMENT_CURRENCY);
        assertEquals(cardPaymentRequest.getSiteId(), CARD_PAYMENT_SITEID);
        assertEquals(cardPaymentRequest.getFees(), CARD_PAYMENT_FEES);
    }

    @Test
    public void shouldComparePaymentsToEnsureTheyMatch() throws IOException {
        assertEquals(cardPaymentRequestMatch, cardPaymentRequest);
    }

    @Test
    public void shouldComparePaymentsToEnsureTheyDontMatch() throws IOException {
        cardPaymentRequestMatch.setAmount(CARD_PAYMENT_AMOUNT_2);
        assertNotEquals(cardPaymentRequestMatch, cardPaymentRequest);
    }
}
