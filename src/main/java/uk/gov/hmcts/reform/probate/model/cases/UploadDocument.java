package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadDocument {

    @JsonProperty("DocumentLink")
    private DocumentLink documentLink;

    @JsonProperty("DocumentType")
    private DocumentType documentType;

    @JsonProperty("Comment")
    private String comment;
}
