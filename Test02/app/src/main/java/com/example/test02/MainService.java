package com.example.test02;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class MainService extends Service {
    @Override
    public ComponentName startService(Intent service) {
        System.out.println("************************");
        System.out.println("startService");
        System.out.println("************************");
        return super.startService(service);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("************************");
        System.out.println("onCreate");
        System.out.println("************************");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("************************");
        System.out.println("onStartCommand");
        System.out.println("************************");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("************************");
        System.out.println("onDestroy");
        System.out.println("************************");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("************************");
        System.out.println("onBind");
        System.out.println("************************");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("************************");
        System.out.println("onUnbind");
        System.out.println("************************");
        return super.onUnbind(intent);
    }

}
