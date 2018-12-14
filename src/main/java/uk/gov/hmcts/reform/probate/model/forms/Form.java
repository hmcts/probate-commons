package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyApplicant;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyDeceased;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyDeclaration;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyForm;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Form", description = "Abstract base model for all form types", discriminator = Form.TYPE_FIELD,
    subTypes = {IntestacyForm.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = IntestacyForm.class,
    name = ProbateType.Constants.INTESTACY_NAME)})
@Data
public abstract class Form<D extends Deceased, A extends Applicant, E extends Declaration> {

    public static final String TYPE_FIELD = "type";

    @JsonTypeId
    private ProbateType type;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = TYPE_FIELD)
    @JsonSubTypes({@JsonSubTypes.Type(value = IntestacyDeceased.class, name = ProbateType.Constants.INTESTACY_NAME)})
    private D deceased;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = TYPE_FIELD)
    @JsonSubTypes({@JsonSubTypes.Type(value = IntestacyApplicant.class, name = ProbateType.Constants.INTESTACY_NAME)})
    private A applicant;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = TYPE_FIELD)
    @JsonSubTypes({@JsonSubTypes.Type(value = IntestacyDeclaration.class, name = ProbateType.Constants.INTESTACY_NAME)})
    private E declaration;

    private String uploadDocumentUrl;

    private Registry registry;

    private CcdCase ccdCase;

    private Payment payment;
}
