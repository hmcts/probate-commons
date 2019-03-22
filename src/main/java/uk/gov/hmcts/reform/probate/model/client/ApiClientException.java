package uk.gov.hmcts.reform.probate.model.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiClientException extends RuntimeException {

    private int status;
    private ApiClientErrorResponse errorReponse;

    public ApiClientException(int status, ApiClientErrorResponse errorReponse) {
        this.errorReponse = errorReponse;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public ApiClientErrorResponse getErrorReponse() {
        return errorReponse;
    }
}
