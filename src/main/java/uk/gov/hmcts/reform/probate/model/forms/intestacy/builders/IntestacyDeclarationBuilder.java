package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyDeclaration;

public final class IntestacyDeclarationBuilder {
    private Boolean declarationAgreement;

    private IntestacyDeclarationBuilder() {
    }

    public static IntestacyDeclarationBuilder anIntestacyDeclaration() {
        return new IntestacyDeclarationBuilder();
    }

    public IntestacyDeclarationBuilder withDeclarationAgreement(Boolean declarationAgreement) {
        this.declarationAgreement = declarationAgreement;
        return this;
    }

    public IntestacyDeclaration build() {
        IntestacyDeclaration intestacyDeclaration = new IntestacyDeclaration();
        intestacyDeclaration.setDeclarationAgreement(declarationAgreement);
        return intestacyDeclaration;
    }
}
