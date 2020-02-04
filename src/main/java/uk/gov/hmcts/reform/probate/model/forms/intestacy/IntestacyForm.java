package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
import uk.gov.hmcts.reform.probate.model.forms.Fees;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Language;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;

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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate applicationSubmittedDate;

    private Map<String, Object> legalDeclaration;

    private Map<String, Object> checkAnswersSummary;

    private String caseType;


    @Builder
    public IntestacyForm(ProbateType type, IntestacyDeceased deceased, IntestacyApplicant applicant,
                         Declaration declaration, Documents documents, Registry registry,
                         CcdCase ccdCase, List<Payment> payments, Copies copies,
                         InheritanceTax iht, Fees fees, Payment payment, LocalDate applicationSubmittedDate,
                         Map<String, Object> legalDeclaration, Map<String, Object> checkAnswersSummary,
                         String applicantEmail, DocumentUpload statementOfTruthDocument, String caseType,
                         Language language) {

        super(type, deceased, applicant, registry, ccdCase, payments, fees, copies, payment, language);
        this.declaration = declaration;
        this.documents = documents;
        this.iht = iht;
        this.applicationSubmittedDate = applicationSubmittedDate;
        this.legalDeclaration = legalDeclaration;
        this.checkAnswersSummary = checkAnswersSummary;
        this.applicantEmail = applicantEmail;
        this.statementOfTruthDocument = statementOfTruthDocument;
        this.caseType = caseType;

    }

}
