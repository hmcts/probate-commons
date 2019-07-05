package uk.gov.hmcts.reform.probate.model.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AssertFieldException extends RuntimeException {

    private ErrorResponse errorResponse;

    public AssertFieldException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
