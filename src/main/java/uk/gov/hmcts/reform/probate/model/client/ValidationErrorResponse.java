package uk.gov.hmcts.reform.probate.model.client;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ValidationErrorResponse extends ErrorResponse {

    private List<ValidationError> errors;

    @Builder
    public ValidationErrorResponse(List<ValidationError> errors) {
        super(ErrorType.VALIDATION);
        this.errors = errors;
    }
}
