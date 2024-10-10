package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.DocumentLink;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitizenDocument {

    @JsonProperty("DocumentLink")
    private DocumentLink documentLink;
}