package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeclarationItem implements Serializable {

    @NotBlank
    @JsonProperty("title")
    private String title;

    @JsonProperty("values")
    private List<String> values = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
