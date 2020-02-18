package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BulkPrint {

    @JsonProperty("templateName")
    private final String templateName;

    @JsonProperty("sendLetterId")
    private final String sendLetterId;

}
