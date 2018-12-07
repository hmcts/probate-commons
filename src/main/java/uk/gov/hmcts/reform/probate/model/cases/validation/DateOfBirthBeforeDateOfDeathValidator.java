package uk.gov.hmcts.reform.probate.model.cases.validation;

import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateOfBirthBeforeDateOfDeathValidator implements ConstraintValidator<DateOfDeathBeforeDateOfBirth, GrantOfRepresentation> {

    @Override
    public boolean isValid(GrantOfRepresentation grantOfRepresentation, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate dod = grantOfRepresentation.getDeceasedDateOfDeath();
        LocalDate dob = grantOfRepresentation.getDeceasedDateOfBirth();
        return !(dod.isBefore(dob));
    }

}
