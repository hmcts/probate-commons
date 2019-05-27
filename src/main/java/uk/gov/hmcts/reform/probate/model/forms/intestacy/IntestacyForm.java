package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Copies;
import uk.gov.hmcts.reform.probate.model.forms.Fees;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class IntestacyForm extends Form<IntestacyDeceased, IntestacyApplicant> {

    private IntestacyDeclaration declaration;

    private String uploadDocumentUrl;

    private IntestacyAssets assets;

    private InheritanceTax iht;

    @Builder
    public IntestacyForm(ProbateType type, IntestacyDeceased deceased, IntestacyApplicant applicant,
                         IntestacyDeclaration declaration, String uploadDocumentUrl, Registry registry,
                         CcdCase ccdCase, List<Payment> payments, Copies copies, IntestacyAssets assets,
                         InheritanceTax iht, Fees fees, Payment payment) {

        super(type, deceased, applicant, registry, ccdCase, payments, fees, copies, payment);
        this.declaration = declaration;
        this.uploadDocumentUrl = uploadDocumentUrl;
        this.assets = assets;
        this.iht = iht;
    }

}
