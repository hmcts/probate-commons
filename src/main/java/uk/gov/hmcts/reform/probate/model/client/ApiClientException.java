package uk.gov.hmcts.reform.probate.model.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ApiClientException extends RuntimeException {

    private int status;
    private ApiClientErrorResponse errorReponse;

    public ApiClientException(int status, ApiClientErrorResponse errorReponse){
        this.errorReponse = errorReponse;
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public ApiClientErrorResponse getErrorReponse() {
        return errorReponse;
    }
}
