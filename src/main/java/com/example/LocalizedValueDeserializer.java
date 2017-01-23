package com.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
public class LocalizedValueDeserializer extends JsonDeserializer<LocalizedValue> {

    @Override
    public LocalizedValue deserialize(JsonParser jp, DeserializationContext ctxt, LocalizedValue intoValue) throws IOException, JsonProcessingException {
        jp.nextToken(); // skip over { to the locale
        jp.nextToken(); // skip over to value
        String localizedValue = jp.getText();   // skip over to localizedValue
        intoValue.setValue(localizedValue);
        jp.nextToken(); //skip to the }
        return intoValue;
    }

    @Override
    public LocalizedValue deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        jp.nextToken(); // skip over { to the locale
        jp.nextToken(); // skip over to value
        String localizedValue = jp.getText();   // skip over to localizedValue
        LocalizedValue result = new LocalizedValue();
        result.setValue(localizedValue);
        jp.nextToken(); //skip to the }
        return result;
    }
}

