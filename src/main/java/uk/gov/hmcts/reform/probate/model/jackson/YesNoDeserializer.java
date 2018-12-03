package uk.gov.hmcts.reform.probate.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import static uk.gov.hmcts.reform.probate.model.YesNo.NO;
import static uk.gov.hmcts.reform.probate.model.YesNo.YES;

public class YesNoDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken.equals(JsonToken.VALUE_STRING)) {
            String text = jsonParser.getText().trim();
            if (YES.getDescription().equals(text)) {
                return Boolean.TRUE;
            } else if (NO.getDescription().equalsIgnoreCase(text)) {
                return Boolean.FALSE;
            } else if (text == null) {
                return null;
            }
            throw context.mappingException("String value needs to be 'yes' or 'no'", text);
        }
        throw context.mappingException("Cannot deserialize for non string value", currentToken);
    }
}

