package uk.gov.hmcts.reform.probate.model.forms.pa;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.Assets;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;
import uk.gov.hmcts.reform.probate.model.forms.Will;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaForm extends Form<PaDeceased, PaApplicant> {

    private PaDeclaration declaration;

    private Executors executors;

    private String uploadDocumentUrl;

    private Map<String, PaCopies> copies;

    private Assets assets;

    private InheritanceTax iht;

    private Will will;

    private Summary summary;

    //Do we need this?
    private Boolean paymentPending;

    //Do we need this?
    private Boolean creatingPayment;

    @Builder
    public PaForm(ProbateType type, PaDeceased deceased, PaApplicant applicant,
                  PaDeclaration declaration, String uploadDocumentUrl, Registry registry,
                  CcdCase ccdCase, List<Payment> payments, Map<String, PaCopies> copies, Assets assets,
                  InheritanceTax iht, Will will, Summary summary, Executors executors, Boolean paymentPending,
                  Boolean creatingPayment) {
        super(type, deceased, applicant, registry, ccdCase, payments);
        this.declaration = declaration;
        this.uploadDocumentUrl = uploadDocumentUrl;
        this.copies = copies;
        this.assets = assets;
        this.iht = iht;
        this.will = will;
        this.summary = summary;
        this.executors = executors;
        this.paymentPending = paymentPending;
        this.creatingPayment = creatingPayment;
    }
}
