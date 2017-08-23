package com.example.panda.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.panda.app.MyApp;

/**
 * Created by lenovo on 2017/8/23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        MyApp.activity=this;
        initView();
        initData();
        initListener();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApp.activity=this;
    }

    protected abstract void loadData();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();

}
