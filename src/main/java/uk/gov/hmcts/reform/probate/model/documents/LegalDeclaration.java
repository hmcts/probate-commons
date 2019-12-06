package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "legalDeclaration")
public class LegalDeclaration implements BusinessDocument {

    Language en;
    Language cy;

    public Language getEn() {
        return en;
    }

    public void setEn(Language en) {
        this.en = en;
    }

    public Language getCy() {
        return cy;
    }

    public void setCy(Language cy) {
        this.cy = cy;
    }


}
