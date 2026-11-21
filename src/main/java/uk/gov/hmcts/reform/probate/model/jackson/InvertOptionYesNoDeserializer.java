package uk.gov.hmcts.reform.probate.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import static uk.gov.hmcts.reform.probate.model.OptionYesNo.NO;
import static uk.gov.hmcts.reform.probate.model.OptionYesNo.YES;

public class InvertOptionYesNoDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken.equals(JsonToken.VALUE_STRING)) {
            String text = jsonParser.getText().trim();
            if (NO.getDescription().equals(text)) {
                return Boolean.TRUE;
            } else if (YES.getDescription().equalsIgnoreCase(text)) {
                return Boolean.FALSE;
            } else if (text == null) {
                return null;
            }
            throw context.weirdStringException(text, Boolean.class,
                    "String value needs to be 'optionYes' or 'optionNo'");
        }
        throw new IllegalArgumentException("Cannot deserialize for non string value");
    }
}
