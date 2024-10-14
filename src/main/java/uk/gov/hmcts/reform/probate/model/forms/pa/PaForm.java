package uk.gov.hmcts.reform.probate.model.forms.pa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
import uk.gov.hmcts.reform.probate.model.forms.Registry;
import uk.gov.hmcts.reform.probate.model.forms.Will;
import uk.gov.hmcts.reform.probate.model.jackson.BooleanAndNoneDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.BooleanAndNoneSerializer;

import java.time.LocalDate;
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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate applicationSubmittedDate;

    //submissionReference may need to be removed later on.
    private Long submissionReference;

    private Map<String, Object> legalDeclaration;

    private Map<String, Object> checkAnswersSummary;

    private DocumentUpload statementOfTruthDocument;

    private String caseType;

    @JsonProperty(value = "equality")
    private Equality equality;

    private String documentsReceivedNotificationSent;

    private String citizenResponse;

    private String citizenResponseSubmittedDate;

    @JsonDeserialize(using = BooleanAndNoneDeserializer.class)
    @JsonSerialize(using = BooleanAndNoneSerializer.class)
    private Boolean citizenResponseCheckbox;

    @JsonDeserialize(using = BooleanAndNoneDeserializer.class)
    @JsonSerialize(using = BooleanAndNoneSerializer.class)
    private Boolean documentUploadIssue;

    @Builder
    public PaForm(ProbateType type, String applicantEmail, PaDeceased deceased, PaApplicant applicant,
                  Declaration declaration, Registry registry,
                  CcdCase ccdCase, List<Payment> payments, Copies copies, PaAssets assets,
                  InheritanceTax iht, Will will, Summary summary, Executors executors,
                  LocalDate applicationSubmittedDate, Long submissionReference,
                  Map<String, Object> legalDeclaration, Map<String, Object> checkAnswersSummary, Payment payment,
                  Fees fees, Documents documents, DocumentUpload statementOfTruthDocument, String caseType,
                  Language language, Equality equality, String documentsReceivedNotificationSent,
                  String citizenResponse, String citizenResponseSubmittedDate, Boolean citizenResponseCheckbox,
                  Boolean documentUploadIssue, String eventDescription) {
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
        this.applicationSubmittedDate = applicationSubmittedDate;
        this.submissionReference = submissionReference;
        this.legalDeclaration = legalDeclaration;
        this.checkAnswersSummary = checkAnswersSummary;
        this.statementOfTruthDocument = statementOfTruthDocument;
        this.caseType = caseType;
        this.equality = equality;
        this.documentsReceivedNotificationSent = documentsReceivedNotificationSent;
        this.citizenResponse = citizenResponse;
        this.citizenResponseSubmittedDate = citizenResponseSubmittedDate;
        this.citizenResponseCheckbox = citizenResponseCheckbox;
        this.documentUploadIssue = documentUploadIssue;
    }

}
