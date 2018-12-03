package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import uk.gov.hmcts.reform.probate.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyApplicant;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyDeceased;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyForm;

@ApiModel(value = "Form", description = "Abstract base model for all form types", discriminator = "type",
        subTypes = {IntestacyForm.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = IntestacyForm.class,
        name = ProbateType.Constants.INTESTACY_NAME)})
@Data
public abstract class Form {

    private ProbateType type;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes({@JsonSubTypes.Type(value = IntestacyDeceased.class, name = ProbateType.Constants.INTESTACY_NAME)})
    private Deceased deceased;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes({@JsonSubTypes.Type(value = IntestacyApplicant.class, name = ProbateType.Constants.INTESTACY_NAME)})
    private Applicant applicant;

    private String uploadDocumentUrl;
}
