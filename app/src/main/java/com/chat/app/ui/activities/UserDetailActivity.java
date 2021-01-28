package com.chat.app.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.chat.app.db.entities.UserEntity;
import com.chat.app.ui.bases.BaseActivity;
import com.chat.app.ui.viewmodel.UserDetailViewModel;
import com.chat.app.ui.view.UserDetailView;
import com.chat.app.util.Util;

import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

import com.chat.app.R;

import butterknife.BindView;
import butterknife.OnClick;

public class UserDetailActivity extends BaseActivity implements UserDetailView {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageViewBg)
    ImageView imageViewBg;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtDob)
    TextView txtDob;
    @BindView(R.id.txtPhone)
    TextView txtPhone;
    @BindView(R.id.txtFullName)
    TextView txtFullName;
    @BindView(R.id.txtEmail)
    TextView txtEmail;
    private UserDetailViewModel presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        String id = getIntent().getStringExtra(getString(R.string.data));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setToolbar(toolbar, getString(R.string.full_detail));
        presenter = new UserDetailViewModel(this, this, id);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.fab)
    public void fabClicked(){
        super.toastInfo(getString(R.string.chat_inactive));
    }

    private void populateData(UserEntity userEntity) {
        try {
            Util.loadPix(imageView, userEntity.picture);
            Util.loadPix(imageViewBg, userEntity.picture);
            txtFullName.setText(Util.getFullName(userEntity));
            txtAddress.setText(Util.formatAddress(userEntity.location));
            txtPhone.setText(userEntity.phone);
            txtEmail.setText(userEntity.email);
            txtDob.setText(userEntity.dateOfBirth);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void loadingStart() {
        super.startLoading();
    }

    @Override
    public void loadingFailed(String msg) {
        super.loadingFailed(msg);
    }

    @Override
    public void loadingSuccessful(String msg) {
        super.loadingSuccessful(msg);
    }

    @Override
    public void userDetail(UserEntity userEntity) {
        populateData(userEntity);
    }
}