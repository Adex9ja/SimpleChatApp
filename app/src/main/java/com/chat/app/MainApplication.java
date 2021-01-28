package com.chat.app;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by root on 9/24/17.
 */

@SuppressWarnings("ALL")
public class MainApplication extends Application {


    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static MainApplication getInstance() {
        return instance;
    }

}
