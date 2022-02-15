package uk.gov.hmcts.reform.probate.model.documents;

import lombok.Builder;

@Builder
public class LabelCheckListItem implements CheckListItem {
    private String text;
    private boolean isLabel = true;
}
