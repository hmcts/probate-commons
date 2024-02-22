package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Fees {

    @JsonProperty(value = "applicationfee")
    private BigDecimal applicationFee;

    private String applicationFeeCode;

    private String applicationFeeVersion;

    @JsonProperty(value = "ukcopiesfee")
    private BigDecimal ukCopiesFee;

    private String ukCopiesFeeCode;

    private String ukCopiesFeeVersion;

    @JsonProperty(value = "overseascopiesfee")
    private BigDecimal overseasCopiesFee;

    private String overseasCopiesFeeCode;

    private String overseasCopiesFeeVersion;

    private BigDecimal total;
}
