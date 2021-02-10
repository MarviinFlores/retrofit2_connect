package com.example.retroapilusgson.Model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
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

 public class UTCDateTypeAdapter implements JsonSerializer<LocalDateTime>,JsonDeserializer<LocalDateTime>{
     private  final  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.US);

     @Override
     public  JsonElement serialize(LocalDateTime localDateTime , Type type, JsonSerializationContext jsonSerializationContext) {

         return new JsonPrimitive(formatter.format(localDateTime));
     }

     @Override
     public synchronized  LocalDateTime deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext jsonDeserializationContext)
             throws JsonParseException {
         return LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.US));

     }
 }