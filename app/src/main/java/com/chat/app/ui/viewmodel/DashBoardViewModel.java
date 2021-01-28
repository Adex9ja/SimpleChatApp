package com.chat.app.ui.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.chat.app.cores.BaseData;
import com.chat.app.cores.RetrofitClientInstance;
import com.chat.app.db.AppDatabase;
import com.chat.app.db.entities.UserEntity;
import com.chat.app.ui.view.DashBoardView;
import com.chat.app.util.Debug;
import com.chat.app.util.Util;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardViewModel implements Callback<BaseData<List<UserEntity>>>, Observer<List<UserEntity>> {
    private final DashBoardView listener;

    public DashBoardViewModel(LifecycleOwner activity, DashBoardView viewCallback) {
        listener = viewCallback;
        fetchDataFromApi();
        AppDatabase.getDatabase().userDao().getUsers().observe(activity, this);
    }

    @Override
    public void onResponse(@NotNull Call<BaseData<List<UserEntity>>> call, @NotNull Response<BaseData<List<UserEntity>>> response) {
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getData() != null) {
                listener.loadingSuccessful(null);
                Util.saveUsers(response.body().getData());
            } else {
                listener.loadingFailed("Empty response body!");
            }
        } else {
            listener.loadingFailed(response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<BaseData<List<UserEntity>>> call, Throwable t) {
        Debug.Log(t.getMessage());
        listener.loadingFailed("Connection failed!");
    }

    public void fetchDataFromApi() {
        listener.loadingStart();
        RetrofitClientInstance.getInstance().getDataService().getUsers().enqueue(this);
    }

    @Override
    public void onChanged(List<UserEntity> userEntities) {
        listener.onDataChanged(userEntities);
    }
}