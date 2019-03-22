package uk.gov.hmcts.reform.probate.model.forms.pa;

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
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;
import uk.gov.hmcts.reform.probate.model.forms.Will;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaForm extends Form<PaDeceased, PaApplicant> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private PaDeclaration declaration;

    @NotNull
    private String applicantEmail;

    private Executors executors;

    private String uploadDocumentUrl;

    private Copies copies;

    private PaAssets assets;

    private InheritanceTax iht;

    private Will will;

    private Summary summary;

    //Do we need this?
    private Boolean paymentPending;

    //Do we need this?
    private Boolean creatingPayment;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate applicationSubmittedDate;

    //submissionReference may need to be removed later on.
    private Long submissionReference;

    @Builder
    public PaForm(ProbateType type, String applicantEmail, PaDeceased deceased, PaApplicant applicant,
                  PaDeclaration declaration, String uploadDocumentUrl, Registry registry,
                  CcdCase ccdCase, List<Payment> payments, Copies copies, PaAssets assets,
                  InheritanceTax iht, Will will, Summary summary, Executors executors, Boolean paymentPending,
                  Boolean creatingPayment, LocalDate applicationSubmittedDate, Long submissionReference) {
        super(type, deceased, applicant, registry, ccdCase, payments);
        this.applicantEmail = applicantEmail;
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
        this.applicationSubmittedDate = applicationSubmittedDate;
        this.submissionReference = submissionReference;
    }
}
