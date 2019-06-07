package uk.gov.hmcts.reform.probate.model.payments;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.utils.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPaymentRequest {

    private BigDecimal amount;

    private String description;

    private String ccdCaseNumber;

    private String caseReference;

    private String service;

    private String currency;

    @JsonProperty("site_id")
    private String siteId;

    private List<FeeDto> fees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardPaymentRequest)) return false;
        CardPaymentRequest that = (CardPaymentRequest) o;
        return NumberUtils.equals(amount, that.amount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(ccdCaseNumber, that.ccdCaseNumber) &&
                Objects.equals(caseReference, that.caseReference) &&
                Objects.equals(service, that.service) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(fees, that.fees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, ccdCaseNumber, caseReference, service, currency, siteId, fees);
    }
}
