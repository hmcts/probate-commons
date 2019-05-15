package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProbateCalculatedFees {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long applicationFee;

    private String applicationFeeCode;

    private String applicationFeeVersion;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ukCopiesFee;

    private String ukCopiesFeeCode;

    private String ukCopiesFeeVersion;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long overseasCopiesFee;

    private String overseasCopiesFeeCode;

    private String overseasCopiesFeeVersion;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long total;

}
