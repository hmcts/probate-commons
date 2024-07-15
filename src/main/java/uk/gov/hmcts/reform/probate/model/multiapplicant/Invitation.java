package uk.gov.hmcts.reform.probate.model.multiapplicant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

import java.io.Serializable;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "invitation")
public class Invitation implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Size(min = 2, message = "fieldMinSize")
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Size(min = 2, message = "fieldMinSize")
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Size(min = 5, message = "fieldMinSize")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("formdataId")
    private String formdataId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("executorName")
    private String executorName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("leadExecutorName")
    private String leadExecutorName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("inviteId")
    private String inviteId;

    @JsonProperty("agreed")
    private Boolean agreed;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean bilingual;
}
