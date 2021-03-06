package com.chat.app.util;

import android.util.Log;

/**
 * Created by root on 9/25/17.
 */

@SuppressWarnings("ALL")
public class Debug {

    private static final String TAG = "Tag";

    public static void Log(String data, Throwable throwable) {
        Log(data + "_", throwable);
    }
    public static void Log(Throwable throwable) {
        Log("ERROR", throwable);
    }
    public static void Log(String data) {
        Log.d(TAG, data + "_");
    }
}
