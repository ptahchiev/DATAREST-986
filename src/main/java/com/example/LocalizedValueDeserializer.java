package com.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
public class LocalizedValueDeserializer extends JsonDeserializer<LocalizedValue> {

    @Override
    public LocalizedValue deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return new LocalizedValue();
    }
}

