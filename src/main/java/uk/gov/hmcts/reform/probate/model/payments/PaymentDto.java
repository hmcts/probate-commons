package uk.gov.hmcts.reform.probate.model.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.utils.NumberUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonNaming(SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"service_name", "payment_group_reference"})
@JsonInclude(NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {

    private String id;

    private BigDecimal amount;

    private String description;

    private String reference;

    @JsonProperty("date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT")
    private Date dateCreated;

    @JsonProperty("date_updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT")
    private Date dateUpdated;

    private String currency;

    @JsonProperty("ccd_case_number")
    private String ccdCaseNumber;

    private String channel;

    private String method;

    private String paymentReference;

    @JsonProperty("external_provider")
    private String externalProvider;

    @JsonProperty("external_reference")
    private String externalReference;
    
    private String siteId;
    
    private String status;

    private List<FeeDto> fees;
    
    @JsonProperty("_links")
    private LinksDto links;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDto)) {
            return false;
        }
        PaymentDto that = (PaymentDto) o;
        return Objects.equals(id, that.id)
                && NumberUtils.equals(amount, that.amount)
                && Objects.equals(description, that.description)
                && Objects.equals(reference, that.reference)
                && Objects.equals(dateCreated, that.dateCreated)
                && Objects.equals(dateUpdated, that.dateUpdated)
                && Objects.equals(currency, that.currency)
                && Objects.equals(ccdCaseNumber, that.ccdCaseNumber)
                && Objects.equals(channel, that.channel)
                && Objects.equals(method, that.method)
                && Objects.equals(paymentReference, that.paymentReference)
                && Objects.equals(externalProvider, that.externalProvider)
                && Objects.equals(externalReference, that.externalReference)
                && Objects.equals(siteId, that.siteId)
                && Objects.equals(status, that.status)
                && Objects.equals(fees, that.fees)
                && Objects.equals(links, that.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, reference, dateCreated, dateUpdated, currency,
                ccdCaseNumber, channel, method, paymentReference, externalProvider, externalReference,
                siteId, status, fees, links);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(SnakeCaseStrategy.class)
    @JsonInclude(NON_NULL)
    @EqualsAndHashCode
    public static class LinksDto {
        private LinkDto nextUrl;
        private LinkDto self;
        private LinkDto cancel;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(NON_NULL)
    @EqualsAndHashCode
    public static class LinkDto {
        private String href;
        private String method;
    }
}
