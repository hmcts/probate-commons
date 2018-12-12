package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uk.gov.hmcts.reform.probate.model.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;

@Data
public class Payment {

    private String reference;

    private String transactionId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_STR_ISO8601)
    private Date date;

    private BigDecimal amount;

    private String siteId;

    private PaymentStatus status;

    private String channel;

}
