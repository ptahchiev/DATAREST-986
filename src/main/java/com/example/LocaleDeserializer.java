package com.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Locale;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
public class LocaleDeserializer extends JsonDeserializer<Locale> {
    @Override
    public Locale deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return Locale.ENGLISH;
    }
}

