package com.example.retroapilusgson.Model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.format.DateTimeFormatter.ofPattern;

public class UTCDateTypeAdapter implements  JsonSerializer <LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

    //"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type scrType, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }

}

