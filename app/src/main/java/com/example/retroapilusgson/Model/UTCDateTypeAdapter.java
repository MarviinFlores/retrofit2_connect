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

 class LocalDateSerializer  implements JsonSerializer<LocalDate> {
    private  final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

     @Override
     public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
         return new JsonPrimitive(formatter.format(localDate));
     }
     class LocalDateTimeSerializer  implements  JsonSerializer <LocalDateTime>{
         private final  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu  HH::mm::ss");

         @Override
         public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
             return  new JsonPrimitive(formatter.format(localDateTime));
         }
     }

     class LocalDateDeserializer implements  JsonDeserializer <LocalDate> {
         @Override
         public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                 throws JsonParseException {
                 return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("d-MM-yyyy").withLocale(Locale.ENGLISH));
             }

         }
     }
    class LocalDateTimeDeserializer implements JsonDeserializer <LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(),DateTimeFormatter.ofPattern("d::MMMM::uuuu HH::mm:ss").withLocale(Locale.ENGLISH));
        }
    }
 }