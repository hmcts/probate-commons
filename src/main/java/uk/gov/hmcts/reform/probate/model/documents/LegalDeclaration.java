package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@JsonRootName(value = "legalDeclaration")
public class LegalDeclaration implements BusinessDocument {

    @JsonProperty("declarations")
    @Valid
    private List<Declaration> declarations;

    @NotBlank
    @JsonProperty("dateCreated")
    private String dateCreated;

    @NotBlank
    @JsonProperty("deceased")
    private String deceased;

    @JsonProperty("bilingual")
    private boolean bilingual;

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    public boolean isBilingual() {
        return bilingual;
    }

    public void setBilingual(boolean bilingual) {
        this.bilingual = bilingual;
    }


}
