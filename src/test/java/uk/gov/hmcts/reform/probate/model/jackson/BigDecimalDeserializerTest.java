package uk.gov.hmcts.reform.probate.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BigDecimalDeserializerTest {

    private final BigDecimalDeserializer bigDecimalDeserializer = new BigDecimalDeserializer();

    @Mock
    private JsonParser mockJsonParser;

    @Mock
    private DeserializationContext mockDeserializationContext;

    @Test
    void shouldDeserializeBigDecimal() throws IOException {
        when(mockJsonParser.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(mockJsonParser.getText()).thenReturn("£100,000.00");

        BigDecimal result = bigDecimalDeserializer.deserialize(mockJsonParser, mockDeserializationContext);

        assertEquals(new BigDecimal("100000.00"), result);
    }

    @Test
    void shouldThrowExceptionWhenInvalidText() throws IOException {
        when(mockJsonParser.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(mockJsonParser.getText()).thenReturn("$r100,000.00");

        assertThrows(IllegalArgumentException.class, () ->
            bigDecimalDeserializer.deserialize(mockJsonParser, mockDeserializationContext));
    }
}
