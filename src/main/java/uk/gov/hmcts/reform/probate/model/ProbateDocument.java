package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProbateDocument {

    @JsonProperty("DocumentLink")
    private ProbateDocumentLink documentLink;

    @JsonProperty("DocumentType")
    private ProbateDocumentType documentType;

    @JsonProperty("DocumentFileName")
    private String documentFileName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("DocumentDateAdded")
    private LocalDate documentDateAdded;

    @JsonProperty("DocumentGeneratedBy")
    private String documentGeneratedBy;
}
