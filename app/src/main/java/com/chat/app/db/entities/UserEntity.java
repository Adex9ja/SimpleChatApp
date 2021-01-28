package com.chat.app.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.chat.app.db.converters.LocationConverter;

import java.io.Serializable;

/**
 * Created by root on 9/25/17.
 */

@SuppressWarnings("ALL")

@Entity(tableName = "user_entity")
public class UserEntity implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    public String id;
    public String lastName;
    public String firstName;
    public String email;
    public String title;
    public String picture;
    public String phone;
    @TypeConverters(LocationConverter.class)
    public LocationEntity location;
    public String gender;
    public String dateOfBirth;
    public String registerDate;

}
