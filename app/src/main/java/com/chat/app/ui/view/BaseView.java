package com.chat.app.ui.view;

public interface BaseView {

    void loadingStart();

    void loadingFailed(String msg);

    void loadingSuccessful(String msg);
}
