package com.chat.app.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chat.app.MainApplication;
import com.chat.app.R;
import com.chat.app.cores.BaseData;
import com.chat.app.cores.RetrofitClientInstance;
import com.chat.app.db.AppDatabase;
import com.chat.app.db.entities.LocationEntity;
import com.chat.app.db.entities.UserEntity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

/**
 * 6/13/2017.
 */

public class Util {

    public static void loadPix(ImageView preview, String link) {
        try {
            if(preview != null && link != null && !link.isEmpty())
                Glide.with(MainApplication.getInstance())
                        .load(link)
                        .into(preview);
        }catch (Exception e){

        }
    }

    public static String getFullName(UserEntity userEntity) {
        try {
            String title = userEntity.title.substring(0, 1).toUpperCase() + userEntity.title.substring(1);
            return  title + ". " +  userEntity.lastName + " " + userEntity.lastName;
        }catch (Exception e){
            return null;
        }
    }

    public static void saveUsers(List<UserEntity> data) {
        MainApplication.getExecutorService().execute(() -> AppDatabase.getDatabase().userDao().saveUsers(data));
    }

    public static String formatAddress(LocationEntity locationEntity) {
        try {
            if(locationEntity != null){
                return String.format("%s, %s, %s, %s", locationEntity.street, locationEntity.city, locationEntity.state, locationEntity.country);
            }
            else
                return null;
        }catch (Exception e){
            return null;
        }
    }

    public static void updateUser(UserEntity userEntity) {
        MainApplication.getExecutorService().execute(() -> {
            AppDatabase.getDatabase().userDao().updateUser(userEntity);
        });
    }
}
