package uk.gov.hmcts.reform.probate.exception;

public class PdfRenderException extends ProbateRuntimeException {
    public PdfRenderException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
}
