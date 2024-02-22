package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeclarationSection implements Serializable {

    @NotBlank
    @JsonProperty("title")
    private String title;

    @NotBlank
    @JsonProperty("headingType")
    private String headingType;

    @NotEmpty
    @Valid
    @JsonProperty("declarationItems")
    private List<DeclarationItem> declarationItems = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DeclarationItem> getDeclarationItems() {
        return declarationItems;
    }

    public void setDeclarationItems(List<DeclarationItem> declarationItems) {
        this.declarationItems = declarationItems;
    }

    public String getHeadingType() {
        return headingType;
    }

    public void setHeadingType(String headingType) {
        this.headingType = headingType;
    }
}
