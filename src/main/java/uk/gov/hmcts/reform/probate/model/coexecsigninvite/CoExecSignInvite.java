package uk.gov.hmcts.reform.probate.model.coexecsigninvite;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "coExecSignInvite")
public class CoExecSignInvite implements Serializable {

    @JsonProperty("ccdReference")
    private String ccdReference;

    @JsonProperty("executorName")
    private String executorName;

    @JsonProperty("applicantName")
    private String applicantName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("deceasedDod")
    private String deceasedDod;

}
