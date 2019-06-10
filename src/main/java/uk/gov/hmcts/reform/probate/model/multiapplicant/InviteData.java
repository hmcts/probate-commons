package uk.gov.hmcts.reform.probate.model.multiapplicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteData {

    @JsonProperty("id")
    private String id;

    @JsonProperty("formdataId")
    private String formdataId;

    @JsonProperty("mainExecutorName")
    private String mainExecutorName;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    public String getId() {
        return id;
    }
}
