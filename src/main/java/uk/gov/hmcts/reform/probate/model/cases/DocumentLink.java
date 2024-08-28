package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentLink {

    @JsonProperty(value = "document_url")
    private String documentUrl;

    @JsonProperty(value = "document_binary_url")
    private String documentBinaryUrl;

    @JsonProperty(value = "document_filename")
    private String documentFilename;

    @JsonProperty(value = "upload_timestamp")
    private LocalDateTime uploadTimestamp;

}
