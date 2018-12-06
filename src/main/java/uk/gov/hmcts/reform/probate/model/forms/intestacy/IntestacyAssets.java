package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.reform.probate.model.forms.Assets;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntestacyAssets extends Assets {

    private BigDecimal assetsOverseasNetValue;
}
