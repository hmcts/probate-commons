package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;

public final class AliasOtherNamesBuilder {
    private String firstName;
    private String lastName;

    private AliasOtherNamesBuilder() {
    }

    public static AliasOtherNamesBuilder anAliasOtherNames() {
        return new AliasOtherNamesBuilder();
    }

    public AliasOtherNamesBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AliasOtherNamesBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AliasOtherNames build() {
        AliasOtherNames aliasOtherNames = new AliasOtherNames();
        aliasOtherNames.setFirstName(firstName);
        aliasOtherNames.setLastName(lastName);
        return aliasOtherNames;
    }
}
