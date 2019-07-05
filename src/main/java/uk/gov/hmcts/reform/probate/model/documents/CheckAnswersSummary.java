package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CheckAnswersSummary implements BusinessDocument {

    @NotBlank
    @JsonProperty("pageTitle")
    private String pageTitle;

    @NotBlank
    @JsonProperty("mainParagraph")
    private String mainParagraph;

    @NotEmpty
    @JsonProperty("sections")
    private List<Section> sections = new ArrayList<>();

    public String getMainParagraph() {
        return mainParagraph;
    }

    public void setMainParagraph(String mainParagraph) {
        this.mainParagraph = mainParagraph;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

}
