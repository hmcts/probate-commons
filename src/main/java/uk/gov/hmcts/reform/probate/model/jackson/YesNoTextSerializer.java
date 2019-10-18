package uk.gov.hmcts.reform.probate.model.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import static uk.gov.hmcts.reform.probate.model.YesNo.NO;
import static uk.gov.hmcts.reform.probate.model.YesNo.YES;

public class YesNoTextSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        System.out.println("YesNoTextSerializer start");
        if (value == null) {
            System.out.println("YesNoTextSerializer null");
            gen.writeNull();
        } else if (value instanceof Boolean) {
            System.out.println("YesNoTextSerializer bool");
            gen.writeString((Boolean)value ? YES.getDescription() : NO.getDescription());
        } else {
            System.out.println("YesNoTextSerializer string?");
            gen.writeString(value.toString());
        }

        System.out.println("YesNoTextSerializer done");
    }
}

