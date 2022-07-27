package uk.gov.hmcts.reform.probate.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
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

        assertThat(result, is(new BigDecimal("100000.00")));
    }

    @Test
    public void shouldThrowExceptionWhenInvalidText() throws IOException {
        Mockito.when(mockJsonParser.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        Mockito.when(mockJsonParser.getText()).thenReturn("$r100,000.00");
        assertThrows(IllegalArgumentException.class, () -> {
            bigDecimalDeserializer.deserialize(mockJsonParser, mockDeserializarionContext);
        });
    }
}
