package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "AliasName", description = "Represents alias name in CCD")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliasName {

    @JsonProperty(value = "Forenames")
    private String forenames;

    @JsonProperty(value = "LastName")
    private String lastName;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    @JsonProperty(value = "AppearOnGrant")
    private Boolean appearOnGrant;

}
