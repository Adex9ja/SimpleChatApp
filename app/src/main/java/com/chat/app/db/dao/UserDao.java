package com.chat.app.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.chat.app.db.entities.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveUsers(List<UserEntity> data);

    @Query("select * from user_entity")
    LiveData<List<UserEntity>> getUsers();

    @Update
    void updateUser(UserEntity userEntity);

    @Query("select * from user_entity where id = :id")
    LiveData<UserEntity> getUserById(String id);
}
