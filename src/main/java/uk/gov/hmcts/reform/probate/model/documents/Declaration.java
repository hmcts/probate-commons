package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class Declaration implements Serializable {

    @NotEmpty
    @JsonProperty("headers")
    private List<String> headers;

    @NotEmpty
    @Valid
    @JsonProperty("sections")
    private List<DeclarationSection> sections = new ArrayList<>();

    @JsonProperty("lastDeclaration")
    private boolean lastDeclaration;

    @JsonProperty("welshLanguage")
    private boolean welshLanguage;

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<DeclarationSection> getSections() {
        return sections;
    }

    public void setSections(List<DeclarationSection> sections) {
        this.sections = sections;
    }

    public boolean isLastDeclaration() {
        return lastDeclaration;
    }

    public void setLastDeclaration(boolean lastDeclaration) {
        this.lastDeclaration = lastDeclaration;
    }

    public boolean isWelshLanguage() {
        return welshLanguage;
    }

    public void setWelshLanguage(boolean welshLanguage) {
        this.welshLanguage = welshLanguage;
    }


}
