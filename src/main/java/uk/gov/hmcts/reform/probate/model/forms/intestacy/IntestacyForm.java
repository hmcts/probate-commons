package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Copies;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonTypeName(ProbateType.Constants.INTESTACY_NAME)
public class IntestacyForm extends Form {

    private IntestacyDeceased deceased;

    private IntestacyApplicant applicant;

    private IntestacyDeclaration declaration;

    private String uploadDocumentUrl;

    private List<Payment> payments;

    private Copies copies;

    private IntestacyAssets assets;

    private InheritanceTax iht;

    private Registry registry;

    private CcdCase ccdCase;

    @Builder
    public IntestacyForm(IntestacyDeceased deceased, IntestacyApplicant applicant,
                         IntestacyDeclaration declaration, String uploadDocumentUrl, Registry registry,
                         CcdCase ccdCase, List<Payment> payments, Copies copies, IntestacyAssets assets,
                         InheritanceTax iht) {
        this.deceased = deceased;
        this.applicant = applicant;
        this.declaration = declaration;
        this.uploadDocumentUrl = uploadDocumentUrl;
        this.registry = registry;
        this.ccdCase = ccdCase;
        this.payments = payments;
        this.copies = copies;
        this.assets = assets;
        this.iht = iht;
    }
}
