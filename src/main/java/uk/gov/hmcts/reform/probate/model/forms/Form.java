package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.caveat.CaveatForm;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyForm;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Form", description = "Abstract base model for all form types", discriminator = Form.TYPE_FIELD,
        subTypes = {IntestacyForm.class, CaveatForm.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes(
        {@JsonSubTypes.Type(value = IntestacyForm.class, name = ProbateType.Constants.INTESTACY_NAME),
         @JsonSubTypes.Type(value = CaveatForm.class, name = ProbateType.Constants.CAVEAT_NAME)})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Form {

    public static final String TYPE_FIELD = "type";


}
