package uk.gov.hmcts.reform.probate.model.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckListItem {
    private CheckListItemType type;
    private String text;
    private String url;
    private String beforeLinkText;
    private String afterLinkText;
}
