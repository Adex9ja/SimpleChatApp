package com.chat.app.db.converters;

import androidx.room.TypeConverter;

import com.chat.app.db.entities.LocationEntity;
import com.google.gson.Gson;

public class LocationConverter {
    @TypeConverter
    public String toString(LocationEntity locationEntity){
        try {
            return new Gson().toJson(locationEntity);
        }catch (Exception e){
            return null;
        }
    }

    @TypeConverter
    public LocationEntity fromJson(String str){
        try {
            return  new Gson().fromJson(str, LocationEntity.class);
        }catch (Exception e){
            return null;
        }
    }
}
