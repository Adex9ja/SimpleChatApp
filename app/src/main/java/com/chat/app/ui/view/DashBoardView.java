package com.chat.app.ui.view;


import com.chat.app.db.entities.UserEntity;

import java.util.List;

public interface DashBoardView extends BaseView {

    void onDataChanged(List<UserEntity> userEntities);
}
