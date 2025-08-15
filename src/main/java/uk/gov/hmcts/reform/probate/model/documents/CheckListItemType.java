package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CheckListItemType {
    @JsonProperty("textOnly")
    TEXT_ONLY,
    @JsonProperty("textWithLink")
    TEXT_WITH_LINK,
    @JsonProperty("textWithMultipleLinks")
    TEXT_WITH_MULTIPLE_LINKS

}
