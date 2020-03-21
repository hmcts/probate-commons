package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ProbateDocument {

    public ProbateDocument() {
    }

    public ProbateDocument(ProbateDocumentLink documentLink, ProbateDocumentType documentType, String documentFileName) {
        this.documentLink = documentLink;
        this.documentType = documentType;
        this.documentFileName = documentFileName;
    }

    public ProbateDocument(ProbateDocumentLink documentLink, ProbateDocumentType documentType, String documentFileName, LocalDate documentDateAdded, String documentGeneratedBy) {
        this.documentLink = documentLink;
        this.documentType = documentType;
        this.documentFileName = documentFileName;
        this.documentDateAdded = documentDateAdded;
        this.documentGeneratedBy = documentGeneratedBy;
    }

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
