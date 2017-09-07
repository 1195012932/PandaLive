package com.example.panda.app;

import android.app.Application;

import com.example.panda.base.BaseActivity;

import io.vov.vitamio.Vitamio;

/**
 * Created by lenovo on 2017/8/23.
 */

public class MyApp extends Application {
    public static BaseActivity activity;


    @Override
    public void onCreate() {
        super.onCreate();
        Vitamio.isInitialized(getApplicationContext());
    }
}
