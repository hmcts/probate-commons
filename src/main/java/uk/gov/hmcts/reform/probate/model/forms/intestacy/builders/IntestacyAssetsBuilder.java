package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyAssets;

import java.math.BigDecimal;

public final class IntestacyAssetsBuilder {
    protected Boolean assetsOverseas;
    private BigDecimal assetsOverseasNetValue;

    private IntestacyAssetsBuilder() {
    }

    public static IntestacyAssetsBuilder anIntestacyAssets() {
        return new IntestacyAssetsBuilder();
    }

    public IntestacyAssetsBuilder withAssetsOverseasNetValue(BigDecimal assetsOverseasNetValue) {
        this.assetsOverseasNetValue = assetsOverseasNetValue;
        return this;
    }

    public IntestacyAssetsBuilder withAssetsOverseas(Boolean assetsOverseas) {
        this.assetsOverseas = assetsOverseas;
        return this;
    }

    public IntestacyAssets build() {
        IntestacyAssets intestacyAssets = new IntestacyAssets();
        intestacyAssets.setAssetsOverseasNetValue(assetsOverseasNetValue);
        intestacyAssets.setAssetsOverseas(assetsOverseas);
        return intestacyAssets;
    }
}
