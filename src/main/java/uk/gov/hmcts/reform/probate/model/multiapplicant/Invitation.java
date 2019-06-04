package uk.gov.hmcts.reform.probate.model.multiapplicant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.io.Serializable;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Invitation implements Serializable {

    @Size(min = 2, message = "fieldMinSize")
    private String firstName;

    @Size(min = 2, message = "fieldMinSize")
    private String lastName;

    @Size(min = 5, message = "fieldMinSize")
    private String email;

    @JsonProperty("formdataId")
    private String formdataId;

    @JsonProperty("executorName")
    private String executorName;

    @JsonProperty("leadExecutorName")
    private String leadExecutorName;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("inviteId")
    private String inviteId;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean agreed;

}
