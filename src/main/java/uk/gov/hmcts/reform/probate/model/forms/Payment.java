package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    private String reference;

    private String transactionId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_STR_ISO8601)
    private Date date;

    private BigDecimal amount;

    //Remove this after refactoring FE, use amount instead
    private BigDecimal total;

    private String siteId;

    private PaymentStatus status;

    private String method;

    private PaymentCopies copies;

}
