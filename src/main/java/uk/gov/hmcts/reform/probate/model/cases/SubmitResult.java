package uk.gov.hmcts.reform.probate.model.cases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmitResult {

    private ProbateCaseDetails probateCaseDetails;

    private ValidatorResults validatorResults;

    public Optional<ValidatorResults> getValidatorResults() {
        return Optional.ofNullable(validatorResults);
    }

    public boolean isValid() {

        if (getValidatorResults().isPresent()) {
            return getValidatorResults().get().getValidationMessages().isEmpty();
        }
        return true;
    }
}
