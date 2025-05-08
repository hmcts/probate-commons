package uk.gov.hmcts.reform.probate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
@Slf4j
public class PdfTemplateService {
    private final PebbleTemplateService pebbleTemplateService;
    private final PdfRenderService pdfRenderService;

    public PdfTemplateService(
            final PebbleTemplateService pebbleTemplateService,
            final PdfRenderService pdfRenderService) {
        this.pebbleTemplateService = pebbleTemplateService;
        this.pdfRenderService = pdfRenderService;
    }

    public byte[] generate(
            final String templateString,
            final Locale locale,
            final Map<String, Object> params) {
        final String templatedHtml = pebbleTemplateService.applyTemplate(
                templateString,
                locale,
                params);
        return pdfRenderService.renderHtml(templatedHtml);
    }
}
