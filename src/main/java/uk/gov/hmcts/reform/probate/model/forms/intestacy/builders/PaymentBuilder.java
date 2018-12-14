package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.PaymentStatus;
import uk.gov.hmcts.reform.probate.model.forms.Payment;

import java.math.BigDecimal;
import java.util.Date;

public final class PaymentBuilder {
    private String reference;
    private String transactionId;
    private Date date;
    private BigDecimal amount;
    private String siteId;
    private PaymentStatus status;
    private String channel;

    private PaymentBuilder() {
    }

    public static PaymentBuilder createPayment() {
        return new PaymentBuilder();
    }

    public PaymentBuilder withReference(String reference) {
        this.reference = reference;
        return this;
    }

    public PaymentBuilder withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public PaymentBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public PaymentBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public PaymentBuilder withSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }

    public PaymentBuilder withStatus(PaymentStatus status) {
        this.status = status;
        return this;
    }

    public PaymentBuilder withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public Payment build() {
        Payment payment = new Payment();
        payment.setReference(reference);
        payment.setTransactionId(transactionId);
        payment.setDate(date);
        payment.setAmount(amount);
        payment.setSiteId(siteId);
        payment.setStatus(status);
        payment.setChannel(channel);
        return payment;
    }
}
