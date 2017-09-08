package com.example.panda.app;

import android.app.Application;

import com.example.panda.base.BaseActivity;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import io.vov.vitamio.Vitamio;

/**
 * Created by lenovo on 2017/8/23.
 */

public class MyApp extends Application {
    public static BaseActivity activity;


    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wxd930ea5d5a258f4f","fce891bb7d181766f92172395802a21f24de93e4");

        Vitamio.isInitialized(getApplicationContext());
    }
}
