package uk.gov.hmcts.reform.probate.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PdfTemplateServiceTest {
    @Mock
    PebbleTemplateService pebbleTemplateService;
    @Mock
    PdfRenderService pdfRenderService;

    @InjectMocks
    PdfTemplateService pdfTemplateService;

    AutoCloseable closeableMocks;

    private static final String TEMPLATE_NAME = "templateName";
    private static final Locale LOCALE = Locale.UK;
    private static final Map<String, Object> PARAMS = Map.of("a", "a", "b", "b");

    @BeforeEach
    public void setUp() {
        closeableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeableMocks.close();
    }

    @Test
    public void shouldGenerateFiles() {
        final String templated = "TEMPLATED";
        when(pebbleTemplateService.applyTemplate(any(), any(), any()))
                .thenReturn(templated);

        pdfTemplateService.generate(
                TEMPLATE_NAME,
                LOCALE,
                PARAMS);

        verify(pebbleTemplateService)
                .applyTemplate(TEMPLATE_NAME, LOCALE, PARAMS);
        verify(pdfRenderService).
                renderHtml(templated);
    }


}
