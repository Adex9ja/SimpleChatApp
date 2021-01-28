package com.chat.app.ui.view;


import com.chat.app.db.entities.UserEntity;

import java.util.List;

public interface UserDetailView extends BaseView {

    void userDetail(UserEntity body);
}
