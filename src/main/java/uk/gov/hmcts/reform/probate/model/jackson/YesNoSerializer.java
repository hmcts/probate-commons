package uk.gov.hmcts.reform.probate.model.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import static uk.gov.hmcts.reform.probate.model.YesNo.NO;
import static uk.gov.hmcts.reform.probate.model.YesNo.YES;

public class YesNoSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        }
        gen.writeString(value ? YES.getDescription() : NO.getDescription());
    }
}

