package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Copies;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyApplicant;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyAssets;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyDeceased;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyDeclaration;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyForm;

public final class IntestacyFormBuilder {
    private Copies copies;
    private IntestacyAssets assets;
    private InheritanceTax iht;
    private ProbateType type;
    private IntestacyDeceased deceased;
    private IntestacyApplicant applicant;
    private IntestacyDeclaration declaration;
    private String uploadDocumentUrl;
    private Registry registry;
    private CcdCase ccdCase;
    private Payment payment;

    private IntestacyFormBuilder() {
    }

    public static IntestacyFormBuilder anIntestacyForm() {
        return new IntestacyFormBuilder();
    }

    public IntestacyFormBuilder withCopies(Copies copies) {
        this.copies = copies;
        return this;
    }

    public IntestacyFormBuilder withAssets(IntestacyAssets assets) {
        this.assets = assets;
        return this;
    }

    public IntestacyFormBuilder withIht(InheritanceTax iht) {
        this.iht = iht;
        return this;
    }

    public IntestacyFormBuilder withType(ProbateType type) {
        this.type = type;
        return this;
    }

    public IntestacyFormBuilder withDeceased(IntestacyDeceased deceased) {
        this.deceased = deceased;
        return this;
    }

    public IntestacyFormBuilder withApplicant(IntestacyApplicant applicant) {
        this.applicant = applicant;
        return this;
    }

    public IntestacyFormBuilder withDeclaration(IntestacyDeclaration declaration) {
        this.declaration = declaration;
        return this;
    }

    public IntestacyFormBuilder withUploadDocumentUrl(String uploadDocumentUrl) {
        this.uploadDocumentUrl = uploadDocumentUrl;
        return this;
    }

    public IntestacyFormBuilder withRegistry(Registry registry) {
        this.registry = registry;
        return this;
    }

    public IntestacyFormBuilder withCcdCase(CcdCase ccdCase) {
        this.ccdCase = ccdCase;
        return this;
    }

    public IntestacyFormBuilder withPayment(Payment payment) {
        this.payment = payment;
        return this;
    }

    public IntestacyForm build() {
        IntestacyForm intestacyForm = new IntestacyForm();
        intestacyForm.setCopies(copies);
        intestacyForm.setAssets(assets);
        intestacyForm.setIht(iht);
        intestacyForm.setType(type);
        intestacyForm.setDeceased(deceased);
        intestacyForm.setApplicant(applicant);
        intestacyForm.setDeclaration(declaration);
        intestacyForm.setUploadDocumentUrl(uploadDocumentUrl);
        intestacyForm.setRegistry(registry);
        intestacyForm.setCcdCase(ccdCase);
        intestacyForm.setPayment(payment);
        return intestacyForm;
    }
}
