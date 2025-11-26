package uk.gov.hmcts.probate.commons.service;


import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.probate.exception.PebbleTemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PebbleTemplateService {
    private final PebbleEngine pebbleEngine;
    private final ParamMapKeyService paramMapKeyService;

    public PebbleTemplateService(
            final PebbleEngine pebbleEngine,
            final ParamMapKeyService paramMapKeyService) {
        this.pebbleEngine = pebbleEngine;
        this.paramMapKeyService = paramMapKeyService;
    }

    /**
     * Loads the named template from the classpath, then applies the parameters to it in the provided locale.
     *
     * @param templateName The name of the template to loaded and then applied
     * @param locale The locale to use for internationalization
     * @param params The map of parameters to be used to template
     * @return The resulting templated output
     * @throws PebbleTemplateException if any exception is caught within the templating process.
     */
    public String applyTemplate(
            final String templateName,
            final Locale locale,
            final Map<String, Object> params) {
        StringWriter writer = new StringWriter();
        try {
            log.info("applying template [{}]", templateName);
            final PebbleTemplate template = pebbleEngine.getTemplate(templateName);

            template.evaluate(writer, params, locale);
            log.info("applying template [{}] completed", templateName);
        } catch (PebbleException e) {
            final Set<String> keys = paramMapKeyService.getParams(params);
            final String keysStr = keys.stream().collect(Collectors.joining(","));
            final String errorMessage = MessageFormat.format(
                    "PebbleException: [{0}] with parameters [{1}]",
                    e.getMessage(),
                    keysStr);
            log.error(errorMessage);
            throw new PebbleTemplateException(errorMessage, e, templateName, keys);
        } catch (IOException e) {
            final String errorMessage = MessageFormat.format(
                    "IOException while applying template [{0}]",
                    templateName);
            log.error(errorMessage, e);
            throw new PebbleTemplateException(errorMessage, e, templateName);
        }
        return writer.toString();
    }
}
