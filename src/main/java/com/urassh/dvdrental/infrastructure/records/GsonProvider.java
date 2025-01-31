package com.urassh.dvdrental.infrastructure.records;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GsonProvider {
    public static Gson getGsonForRecord() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .setPrettyPrinting()
                .create();
    }

    private static class DateSerializer implements JsonSerializer<Date> {
        private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        @Override
        public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(date));
        }
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        @Override
        public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            try {
                return formatter.parse(json.getAsString());
            } catch (Exception e) {
                throw new JsonParseException("Failed to parse Date: " + json.getAsString(), e);
            }
        }
    }
}