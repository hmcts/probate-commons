package uk.gov.hmcts.reform.probate.exception;

public class ProbateRuntimeException extends RuntimeException {
    public ProbateRuntimeException(String message) {
        super(message);
    }

    public ProbateRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
