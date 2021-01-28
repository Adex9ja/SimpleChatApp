package com.chat.app;

import com.chat.app.db.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
    public static List<UserEntity> createUser(int size) {
        List<UserEntity> temp = new ArrayList<>();
        for (int i = 0; i < size; i ++ ){
            UserEntity userEntity = new UserEntity();
            userEntity.id = String.valueOf(i);
            userEntity.firstName = "Anonymous";
            userEntity.lastName = "Tester " + i;
            userEntity.email = "anonymous_tester@gmail.com";
            userEntity.phone = "08166767271";
            temp.add(userEntity);
        }
        return temp;
    }
}
