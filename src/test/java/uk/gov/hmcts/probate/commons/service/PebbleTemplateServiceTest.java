package uk.gov.hmcts.probate.commons.service;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.reform.probate.exception.PebbleTemplateException;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PebbleTemplateServiceTest {
    @Mock
    PebbleEngine pebbleEngine;

    @Mock
    ParamMapKeyService paramMapKeyService;

    @InjectMocks
    PebbleTemplateService pebbleTemplateService;

    AutoCloseable closeableMocks;

    private static final String TEMPLATE_NAME = "templateName";
    private static final Locale LOCALE = Locale.UK;
    private static final Map<String, Object> TWO_PARAMS = Map.of(
            "key_1", "value_1",
            "key_0", "value_0");

    @BeforeEach
    void setUp() {
        closeableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeableMocks.close();
    }

    @Test
    void shouldRenderPebbleTemplate() throws Exception {
        final PebbleTemplate template = mock();
        when(pebbleEngine.getTemplate(any()))
                .thenReturn(template);

        pebbleTemplateService.applyTemplate(TEMPLATE_NAME, LOCALE, TWO_PARAMS);

        verify(pebbleEngine).getTemplate(same(TEMPLATE_NAME));
        verify(template).evaluate(any(), same(TWO_PARAMS), same(LOCALE));
    }

    @Test
    void shouldThrowPebbleTemplateExceptionWhenTemplateLookupThrowsPebble() throws Exception {
        final PebbleException pebbleException = new PebbleException(new RuntimeException(), "");
        when(pebbleEngine.getTemplate(any()))
                .thenThrow(pebbleException);

        final PebbleTemplateException thrown = assertThrows(
                PebbleTemplateException.class,
                () -> pebbleTemplateService.applyTemplate(TEMPLATE_NAME, LOCALE, TWO_PARAMS));
        assertSame(TEMPLATE_NAME, thrown.getTemplateName());
        assertSame(pebbleException, thrown.getCause());
        assertTrue(thrown.getKeys().isEmpty());
    }

    @Test
    void shouldThrowPebbleTemplateExceptionWhenTemplateEvaluateThrowsPebble() throws Exception {
        final PebbleException pebbleException = new PebbleException(new RuntimeException(), "");
        final PebbleTemplate template = mock();
        final SortedSet<String> keys = mock(SortedSet.class);

        when(pebbleEngine.getTemplate(any()))
                .thenReturn(template);
        when(paramMapKeyService.getParams(any()))
                .thenReturn(keys);

        doThrow(pebbleException)
                .when(template).evaluate(any(), any(), any());

        final PebbleTemplateException thrown = assertThrows(
                PebbleTemplateException.class,
                () -> pebbleTemplateService.applyTemplate(TEMPLATE_NAME, LOCALE, TWO_PARAMS));

        assertSame(TEMPLATE_NAME, thrown.getTemplateName());
        assertSame(pebbleException, thrown.getCause());
        assertSame(keys, thrown.getKeys());
    }

    @Test
    void shouldThrowPebbleTemplateExceptionWhenTemplateEvalueateThrowsIO() throws Exception {
        final IOException ioException = mock();

        final PebbleTemplate template = mock();
        when(pebbleEngine.getTemplate(any()))
                .thenReturn(template);

        doThrow(ioException)
                .when(template).evaluate(any(), any(), any());

        final PebbleTemplateException thrown = assertThrows(
                PebbleTemplateException.class,
                () -> pebbleTemplateService.applyTemplate(TEMPLATE_NAME, LOCALE, TWO_PARAMS));

        assertSame(TEMPLATE_NAME, thrown.getTemplateName());
        assertSame(ioException, thrown.getCause());
        assertTrue(thrown.getKeys().isEmpty());
    }
}
