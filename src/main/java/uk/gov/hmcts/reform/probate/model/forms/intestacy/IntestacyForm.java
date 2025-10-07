package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Copies;
import uk.gov.hmcts.reform.probate.model.forms.Declaration;
import uk.gov.hmcts.reform.probate.model.forms.DocumentUpload;
import uk.gov.hmcts.reform.probate.model.forms.Documents;
import uk.gov.hmcts.reform.probate.model.forms.Equality;
import uk.gov.hmcts.reform.probate.model.forms.Fees;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Language;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.ProvideInformation;
import uk.gov.hmcts.reform.probate.model.forms.Registry;
import uk.gov.hmcts.reform.probate.model.forms.ReviewResponse;
import uk.gov.hmcts.reform.probate.model.forms.pa.PaAssets;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntestacyForm extends Form<IntestacyDeceased, IntestacyApplicant> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private Declaration declaration;

    @NotNull
    private String applicantEmail;

    private Documents documents;

    private DocumentUpload statementOfTruthDocument;

    private InheritanceTax iht;

    private Map<String, Object> legalDeclaration;

    private Map<String, Object> checkAnswersSummary;

    private String caseType;

    private CoApplicants coApplicants;

    @JsonProperty(value = "equality")
    private Equality equality;

    private String documentsReceivedNotificationSent;

    @JsonProperty(value = "provideinformation")
    private ProvideInformation provideinformation;

    @JsonProperty(value = "reviewresponse")
    private ReviewResponse reviewresponse;

    private String expectedResponseDate;

    private String informationNeeded;

    private String informationNeededByPost;

    private FamilyDetails details;

    private PaAssets assets;

    @Builder
    public IntestacyForm(ProbateType type, IntestacyDeceased deceased, IntestacyApplicant applicant,
                         Declaration declaration, Documents documents, Registry registry,
                         CcdCase ccdCase, List<Payment> payments, Copies copies, PaAssets assets,
                         InheritanceTax iht, Fees fees, Payment payment, Map<String, Object> legalDeclaration,
                         Map<String, Object> checkAnswersSummary,
                         String applicantEmail, DocumentUpload statementOfTruthDocument, String caseType,
                         Language language, Equality equality, String documentsReceivedNotificationSent,
                         ProvideInformation provideinformation, ReviewResponse reviewresponse,
                         String expectedResponseDate, String informationNeeded, String informationNeededByPost,
                         FamilyDetails details, String eventDescription,CoApplicants coApplicants) {

        super(type, deceased, applicant, registry, ccdCase, payments, fees, copies, payment, language,
            eventDescription);
        this.declaration = declaration;
        this.documents = documents;
        this.iht = iht;
        this.legalDeclaration = legalDeclaration;
        this.checkAnswersSummary = checkAnswersSummary;
        this.applicantEmail = applicantEmail;
        this.statementOfTruthDocument = statementOfTruthDocument;
        this.caseType = caseType;
        this.equality = equality;
        this.documentsReceivedNotificationSent = documentsReceivedNotificationSent;
        this.provideinformation = provideinformation;
        this.reviewresponse = reviewresponse;
        this.expectedResponseDate = expectedResponseDate;
        this.informationNeeded = informationNeeded;
        this.informationNeededByPost = informationNeededByPost;
        this.details = details;
        this.assets = assets;
        this.coApplicants = coApplicants;
    }

}
