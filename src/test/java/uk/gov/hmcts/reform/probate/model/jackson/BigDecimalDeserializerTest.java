package uk.gov.hmcts.reform.probate.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BigDecimalDeserializerTest {

    BigDecimalDeserializer bigDecimalDeserializer = new BigDecimalDeserializer();
    @Mock
    JsonParser mockJsonParser;
    @Mock
    DeserializationContext mockDeserializarionContext;

    @Test
    public void shouldDeserializeBigDecimal() throws IOException {
        Mockito.when(mockJsonParser.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        Mockito.when(mockJsonParser.getText()).thenReturn("£100,000.00");
        BigDecimal result = bigDecimalDeserializer.deserialize(mockJsonParser, mockDeserializarionContext);

        assertEquals(new BigDecimal("100000.00"), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInvalidText() throws IOException {
        Mockito.when(mockJsonParser.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        Mockito.when(mockJsonParser.getText()).thenReturn("$r100,000.00");
        BigDecimal result = bigDecimalDeserializer.deserialize(mockJsonParser, mockDeserializarionContext);
    }
}
