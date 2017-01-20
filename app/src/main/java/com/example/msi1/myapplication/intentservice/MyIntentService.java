package com.example.msi1.myapplication.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.socks.library.KLog;

/**
 * IntentServiceDemo
 * Created by soyoungboy on 2017/1/20.
 */

public class MyIntentService extends IntentService {
    MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        KLog.i("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.i("onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        KLog.i("onStart");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        KLog.i("onBind");
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        KLog.i("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        KLog.i("onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        KLog.i("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        KLog.i("onHandleIntent");
        String param = intent.getStringExtra("param");
        switch (param){
            case "oper1":
                KLog.i("param = oper1");
                break;
            default:
                break;
        }
    }
}
