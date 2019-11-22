package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.caveat.CaveatApplicant;
import uk.gov.hmcts.reform.probate.model.forms.caveat.CaveatDeceased;
import uk.gov.hmcts.reform.probate.model.forms.caveat.CaveatForm;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyApplicant;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyDeceased;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyForm;
import uk.gov.hmcts.reform.probate.model.forms.pa.PaApplicant;
import uk.gov.hmcts.reform.probate.model.forms.pa.PaDeceased;
import uk.gov.hmcts.reform.probate.model.forms.pa.PaForm;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Form", description = "Abstract base model for all form types", discriminator = Form.TYPE_FIELD,
    subTypes = {IntestacyForm.class, CaveatForm.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes(
    {@JsonSubTypes.Type(value = IntestacyForm.class, name = ProbateType.Constants.INTESTACY_NAME),
        @JsonSubTypes.Type(value = CaveatForm.class, name = ProbateType.Constants.CAVEAT_NAME),
        @JsonSubTypes.Type(value = PaForm.class, name = ProbateType.Constants.PA_NAME)})
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Form<D extends Deceased, A extends Applicant> {

    public static final String TYPE_FIELD = "type";

    @JsonTypeId
    private ProbateType type;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = TYPE_FIELD)
    @JsonSubTypes({
        @JsonSubTypes.Type(value = IntestacyDeceased.class, name = ProbateType.Constants.INTESTACY_NAME),
        @JsonSubTypes.Type(value = CaveatDeceased.class, name = ProbateType.Constants.CAVEAT_NAME),
        @JsonSubTypes.Type(value = PaDeceased.class, name = ProbateType.Constants.PA_NAME)
    })
    private D deceased;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = TYPE_FIELD)
    @JsonSubTypes({
        @JsonSubTypes.Type(value = IntestacyApplicant.class, name = ProbateType.Constants.INTESTACY_NAME),
        @JsonSubTypes.Type(value = CaveatApplicant.class, name = ProbateType.Constants.CAVEAT_NAME),
        @JsonSubTypes.Type(value = PaApplicant.class, name = ProbateType.Constants.PA_NAME)
    })
    private A applicant;

    private Registry registry;

    private CcdCase ccdCase;

    private List<Payment> payments;

    private Fees fees;

    private Copies copies;

    private Payment payment;

    private Boolean languagePreferenceWelsh;

}
