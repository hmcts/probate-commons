package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.Assets;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IntestacyAssets extends Assets {

    private BigDecimal assetsOverseasNetValue;

    @Builder
    public IntestacyAssets(Boolean assetsOverseas, BigDecimal assetsOverseasNetValue) {
        super(assetsOverseas);
        this.assetsOverseasNetValue = assetsOverseasNetValue;
    }
}
