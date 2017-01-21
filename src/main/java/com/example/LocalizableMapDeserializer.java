package com.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
public class LocalizableMapDeserializer extends JsonDeserializer<Map<Locale, LocalizedValue>> {


    @Override
    public Map<Locale, LocalizedValue> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        throw new IOException("IOEX");
    }
}
