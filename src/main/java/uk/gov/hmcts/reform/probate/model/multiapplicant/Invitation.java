package uk.gov.hmcts.reform.probate.model.multiapplicant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@JsonRootName(value = "invitation")
public class Invitation implements Serializable {

    @JsonProperty("id")
    private String id;

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

    private Boolean agreed;

}
