package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.forms.CcdCase;

public final class CcdCaseBuilder {
    private Long id;
    private String state;

    private CcdCaseBuilder() {
    }

    public static CcdCaseBuilder createCcdCase() {
        return new CcdCaseBuilder();
    }

    public CcdCaseBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CcdCaseBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public CcdCase build() {
        CcdCase ccdCase = new CcdCase();
        ccdCase.setId(id);
        ccdCase.setState(state);
        return ccdCase;
    }
}
