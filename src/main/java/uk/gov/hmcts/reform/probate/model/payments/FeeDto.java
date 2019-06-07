package uk.gov.hmcts.reform.probate.model.payments;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.utils.NumberUtils;

import java.math.BigDecimal;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeeDto {

    private String code;

    private String version;

    private Integer volume;

    private BigDecimal calculatedAmount;

    private String memoLine;

    private String ccdCaseNumber;

    private String reference;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeeDto)) return false;
        FeeDto feeDto = (FeeDto) o;
        return Objects.equals(code, feeDto.code) &&
                Objects.equals(version, feeDto.version) &&
                Objects.equals(volume, feeDto.volume) &&
                NumberUtils.equals(calculatedAmount, feeDto.calculatedAmount) &&
                Objects.equals(memoLine, feeDto.memoLine) &&
                Objects.equals(ccdCaseNumber, feeDto.ccdCaseNumber) &&
                Objects.equals(reference, feeDto.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, version, volume, calculatedAmount, memoLine, ccdCaseNumber, reference);
    }
}
