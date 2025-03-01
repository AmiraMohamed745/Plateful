package com.example.plateful.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {

    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromObject(Object object) {
        return object == null ? null : gson.toJson(object);
    }

    @TypeConverter
    public static Object toObject(String value) {
        if (value == null) {
            return null;
        }
        Type type = new TypeToken<Object>() {
        }.getType();
        return gson.fromJson(value, type);
    }
}
