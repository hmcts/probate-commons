package uk.gov.hmcts.reform.probate.model.forms.pa;

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
import uk.gov.hmcts.reform.probate.model.forms.Will;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaForm extends Form<PaDeceased, PaApplicant> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private Declaration declaration;

    @NotNull
    private String applicantEmail;

    private Executors executors;

    private Documents documents;

    private PaAssets assets;

    private InheritanceTax iht;

    private Will will;

    private Summary summary;

    //submissionReference may need to be removed later on.
    private Long submissionReference;

    private Map<String, Object> legalDeclaration;

    private Map<String, Object> checkAnswersSummary;

    private DocumentUpload statementOfTruthDocument;

    private String caseType;

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


    @Builder
    public PaForm(ProbateType type, String applicantEmail, PaDeceased deceased, PaApplicant applicant,
                  Declaration declaration, Registry registry,
                  CcdCase ccdCase, List<Payment> payments, Copies copies, PaAssets assets,
                  InheritanceTax iht, Will will, Summary summary, Executors executors, Long submissionReference,
                  Map<String, Object> legalDeclaration, Map<String, Object> checkAnswersSummary, Payment payment,
                  Fees fees, Documents documents, DocumentUpload statementOfTruthDocument, String caseType,
                  Language language, Equality equality, String documentsReceivedNotificationSent,
                  ProvideInformation provideinformation, ReviewResponse reviewresponse,
                  String expectedResponseDate, String informationNeeded, String informationNeededByPost,
                  String eventDescription) {
        super(type, deceased, applicant, registry, ccdCase, payments, fees, copies, payment, language,
            eventDescription);
        this.applicantEmail = applicantEmail;
        this.declaration = declaration;
        this.documents = documents;
        this.assets = assets;
        this.iht = iht;
        this.will = will;
        this.summary = summary;
        this.executors = executors;
        this.submissionReference = submissionReference;
        this.legalDeclaration = legalDeclaration;
        this.checkAnswersSummary = checkAnswersSummary;
        this.statementOfTruthDocument = statementOfTruthDocument;
        this.caseType = caseType;
        this.equality = equality;
        this.documentsReceivedNotificationSent = documentsReceivedNotificationSent;
        this.provideinformation = provideinformation;
        this.reviewresponse = reviewresponse;
        this.expectedResponseDate = expectedResponseDate;
        this.informationNeeded = informationNeeded;
        this.informationNeededByPost = informationNeededByPost;
    }

}
