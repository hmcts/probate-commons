package uk.gov.hmcts.reform.probate.model.forms.pa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Summary {

    private boolean readyToDeclare;
}
