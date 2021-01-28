package com.chat.app.cores;

import com.chat.app.db.entities.UserEntity;

import java.util.List;

import kotlin.ParameterName;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("user?limit=100")
    Call<BaseData<List<UserEntity>>> getUsers();

    @GET("user/{userId}")
    Call<UserEntity> getUserDetail(@Path("userId") String id);
}

