package com.chat.app.ui.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.chat.app.cores.RetrofitClientInstance;
import com.chat.app.db.AppDatabase;
import com.chat.app.db.entities.UserEntity;
import com.chat.app.ui.view.UserDetailView;
import com.chat.app.util.Debug;
import com.chat.app.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailViewModel implements Callback<UserEntity>, Observer<UserEntity> {
    private final UserDetailView listener;

    public UserDetailViewModel(LifecycleOwner owner, UserDetailView listener, String id) {
        this.listener = listener;
        AppDatabase.getDatabase().userDao().getUserById(id).observe(owner, this);
        loadUserDetail(id);
    }

    private void loadUserDetail(String id) {
        listener.loadingStart();
        RetrofitClientInstance.getInstance().getDataService().getUserDetail(id).enqueue(this);
    }

    @Override
    public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                listener.loadingSuccessful(null);
                Util.updateUser(response.body());
            } else {
                listener.loadingFailed("Empty response body!");
            }
        } else {
            listener.loadingFailed(response.message());
        }
    }

    @Override
    public void onFailure(Call<UserEntity> call, Throwable t) {
        Debug.Log(t.getMessage());
        listener.loadingFailed("Connection failed!");
    }

    @Override
    public void onChanged(UserEntity userEntity) {
        listener.userDetail(userEntity);
    }
}
