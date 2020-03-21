package uk.gov.hmcts.reform.probate.model.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "applicationreceived")
public class ApplicationReceivedDetails {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String applicantEmail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deceasedName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String applicantName;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean bilingual;
}
