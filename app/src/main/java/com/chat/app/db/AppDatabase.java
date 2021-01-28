package com.chat.app.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.chat.app.MainApplication;
import com.chat.app.db.dao.UserDao;
import com.chat.app.db.entities.UserEntity;

@Database(entities = {UserEntity.class}, version = 8, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;


    public static AppDatabase getDatabase() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(MainApplication.getInstance()
                    .getApplicationContext(), AppDatabase.class, "ChatApp.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }


    public abstract UserDao userDao();
}
