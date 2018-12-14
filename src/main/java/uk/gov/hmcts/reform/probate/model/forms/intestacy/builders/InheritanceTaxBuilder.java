package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.forms.IhtMethod;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;

import java.math.BigDecimal;

public final class InheritanceTaxBuilder {
    private IhtMethod method;
    private IhtFormType form;
    private String identifier;
    private BigDecimal grossValue;
    private BigDecimal netValue;

    private InheritanceTaxBuilder() {
    }

    public static InheritanceTaxBuilder anInheritanceTax() {
        return new InheritanceTaxBuilder();
    }

    public InheritanceTaxBuilder withMethod(IhtMethod method) {
        this.method = method;
        return this;
    }

    public InheritanceTaxBuilder withForm(IhtFormType form) {
        this.form = form;
        return this;
    }

    public InheritanceTaxBuilder withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public InheritanceTaxBuilder withGrossValue(BigDecimal grossValue) {
        this.grossValue = grossValue;
        return this;
    }

    public InheritanceTaxBuilder withNetValue(BigDecimal netValue) {
        this.netValue = netValue;
        return this;
    }

    public InheritanceTax build() {
        InheritanceTax inheritanceTax = new InheritanceTax();
        inheritanceTax.setMethod(method);
        inheritanceTax.setForm(form);
        inheritanceTax.setIdentifier(identifier);
        inheritanceTax.setGrossValue(grossValue);
        inheritanceTax.setNetValue(netValue);
        return inheritanceTax;
    }
}
