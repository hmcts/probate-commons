package uk.gov.hmcts.reform.probate.model.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiClientException extends RuntimeException {

    private int status;

    private ErrorResponse errorResponse;

    public ApiClientException(int status, ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
