package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.forms.Copies;

public final class CopiesBuilder {
    private Long uk;
    private Long overseas;

    private CopiesBuilder() {
    }

    public static CopiesBuilder createCopies() {
        return new CopiesBuilder();
    }

    public CopiesBuilder withUk(Long uk) {
        this.uk = uk;
        return this;
    }

    public CopiesBuilder withOverseas(Long overseas) {
        this.overseas = overseas;
        return this;
    }

    public Copies build() {
        Copies copies = new Copies();
        copies.setUk(uk);
        copies.setOverseas(overseas);
        return copies;
    }
}
