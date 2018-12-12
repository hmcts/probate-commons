package uk.gov.hmcts.reform.probate.model.cases.validation;

import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.springframework.util.CollectionUtils.isEmpty;

public class AliasNamesMandatoryValidator implements ConstraintValidator<AliasNamesMandatory, GrantOfRepresentation> {

    @Override
    public boolean isValid(GrantOfRepresentation grantOfRepresentation,
                           ConstraintValidatorContext constraintValidatorContext) {
        return !(isTrue(grantOfRepresentation.getDeceasedOtherNames())
                && isEmpty(grantOfRepresentation.getDeceasedAliasNameList()));
    }
}
