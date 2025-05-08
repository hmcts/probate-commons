package uk.gov.hmcts.reform.probate.exception;

import java.util.Collections;
import java.util.Set;

public class PebbleTemplateException extends ProbateRuntimeException {
    private final String templateName;
    private final Set<String> keys;

    public PebbleTemplateException(
            final String message,
            final Throwable cause,
            final String templateName) {
        this(message, cause, templateName, Collections.emptySet());
    }

    public PebbleTemplateException(
            final String message,
            final Throwable cause,
            final String templateName,
            final Set<String> keys) {
        super(message, cause);
        this.templateName = templateName;
        this.keys = keys;
    }

    public String getTemplateName() {
        return templateName;
    }

    public Set<String> getKeys() {
        return keys;
    }
}
