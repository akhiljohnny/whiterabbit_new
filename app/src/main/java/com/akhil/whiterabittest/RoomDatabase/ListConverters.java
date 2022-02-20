/*
 * Created by Novigado Technologies
 * Developer Name: Hareesh, Jojo, Aravind
 * File Name: FoodItemConverters
 * Copyright (c). All rights reserved.
 */
package com.akhil.whiterabittest.RoomDatabase;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListConverters {
    @TypeConverter
    public static List<String> stringToAnswer(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
        // return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static String answerToString(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
        // return date == null ? null : date.getTime();
    }
}
