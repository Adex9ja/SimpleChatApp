package com.chat.app;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.chat.app.db.AppDatabase;
import com.chat.app.db.dao.UserDao;
import com.chat.app.db.entities.UserEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Objects;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoInstrumentedTest {

    private AppDatabase db;

    @Before
    public void initDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();

    }

    @After
    public void closeDB(){
        db.close();
    }

    @Test
    public void insertUserTest() {
        List<UserEntity> userEntityList = TestUtil.createUser(5);
        db.userDao().saveUsers(userEntityList);
        List<UserEntity> userEntities = db.userDao().getUsers().getValue();
        assert(!userEntities.isEmpty());

    }

    @Test
    public void updateUserTest() {
        List<UserEntity> userEntities = TestUtil.createUser(2);
        UserEntity userEntity = userEntities.get(0);

        db.userDao().saveUsers(userEntities);
        userEntity.lastName  = "Another Name";
        db.userDao().updateUser(userEntity);

        UserEntity userToCompare = db.userDao().getUserById(userEntity.id).getValue();
        assert (userEntity.lastName.equals(userToCompare.lastName));
    }



}
