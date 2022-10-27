package uk.gov.hmcts.reform.probate.model.payments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @BeforeEach
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
        assertEquals(CARD_PAYMENT_CASE_REFERNCE, cardPaymentRequest.getCaseReference());
        assertEquals(CARD_PAYMENT_AMOUNT, cardPaymentRequest.getAmount());
        assertEquals(CARD_PAYMENT_CCD_CASE_NUMBER, cardPaymentRequest.getCcdCaseNumber());
        assertEquals(CARD_PAYMENT_DESCRIPTION, cardPaymentRequest.getDescription());
        assertEquals(CARD_PAYMENT_SERVICE, cardPaymentRequest.getService());
        assertEquals(CARD_PAYMENT_CURRENCY, cardPaymentRequest.getCurrency());
        assertEquals(CARD_PAYMENT_SITEID, cardPaymentRequest.getSiteId());
        assertThat(CARD_PAYMENT_FEES, is(equalTo(cardPaymentRequest.getFees())));
    }

    @Test
    public void shouldComparePaymentsToEnsureTheyMatch() throws IOException {
        assertThat(cardPaymentRequest, is(equalTo(cardPaymentRequestMatch)));
        assertTrue(cardPaymentRequest.equals(cardPaymentRequestMatch));
    }

    @Test
    public void shouldComparePaymentsToEnsureTheyDontMatch() throws IOException {
        cardPaymentRequestMatch.setAmount(CARD_PAYMENT_AMOUNT_2);
        assertThat(cardPaymentRequest, is(not(cardPaymentRequestMatch)));
        assertFalse(cardPaymentRequest.equals(cardPaymentRequestMatch));
    }
}
