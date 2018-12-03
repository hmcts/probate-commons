package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import lombok.Data;
import uk.gov.hmcts.reform.probate.model.forms.Deceased;

@Data
public class IntestacyDeceased extends Deceased {

    private String deceasedMaritalStatus;

    private Boolean divorcedInEnglandOrWales;
}
