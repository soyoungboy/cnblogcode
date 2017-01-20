package com.example.msi1.myapplication;

import android.app.Application;

import com.socks.library.KLog;

/**
 * Application基础库
 * Created by soyoungboy on 2017/1/20.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.LOG_DEBUG, "soyoungboy");
    }
}
