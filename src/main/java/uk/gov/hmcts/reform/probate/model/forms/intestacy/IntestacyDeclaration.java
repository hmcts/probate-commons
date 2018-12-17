package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.Declaration;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IntestacyDeclaration extends Declaration {

    @Builder
    public IntestacyDeclaration(Boolean declarationAgreement) {
        super(declarationAgreement);
    }
}
