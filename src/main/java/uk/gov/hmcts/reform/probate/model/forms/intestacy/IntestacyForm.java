package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.reform.probate.model.forms.Copies;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntestacyForm extends Form {

    private Copies copies;

    private IntestacyAssets assets;

    private InheritanceTax iht;
}
