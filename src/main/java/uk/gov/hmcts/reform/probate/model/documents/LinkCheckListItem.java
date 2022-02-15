package uk.gov.hmcts.reform.probate.model.documents;

import lombok.Builder;

@Builder
public class LinkCheckListItem {
    private String beforeLinkText;
    private String afterLinkText;
    private String href;
    private String linkText;
}
