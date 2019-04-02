package uk.gov.hmcts.reform.probate.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;


public class BooleanAndNoneDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken.equals(JsonToken.VALUE_STRING)) {
            String text = jsonParser.getText().trim();
            if ((Boolean.TRUE.toString()).equalsIgnoreCase(text)) {
                return Boolean.TRUE;
            } else if (Boolean.FALSE.toString().equalsIgnoreCase(text) || (text == null || text.equalsIgnoreCase(
                "none"))) {
                return Boolean.FALSE;
            }
            throw context.weirdStringException(text, Boolean.class, "String value needs to be 'True' or 'False' or "
                + "'none'");
        }
        throw new IllegalArgumentException("Cannot deserialize for non string value");
    }
}
