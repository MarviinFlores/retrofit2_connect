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

public class UTCDateTypeAdapter implements  JsonSerializer <Date>,JsonDeserializer<Date> {
       private final DateFormat dateFormat;
       public UTCDateTypeAdapter(){
           dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.US);
       }
       //"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

     @Override
     public  synchronized  JsonElement serialize (Date date, Type type, JsonSerializationContext jsonSerializationContext){
           synchronized  (dateFormat) {
               String dateFormatAsString =  dateFormat.format(date);
               return  new JsonPrimitive (dateFormatAsString);
           }
     }

    @Override
    public  synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext ) throws JsonParseException {

        try {
            synchronized (dateFormat){
                return dateFormat.parse(jsonElement.getAsString());
            }
            } catch (ParseException e){
            throw  new JsonParseException(jsonElement.getAsString(),e);
        }
    }
}


