package com.example.panda.view.activity.home;

import android.webkit.WebView;

import com.example.panda.R;
import com.example.panda.base.BaseActivity;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class Interaction extends BaseActivity {

    private WebView interaction_web;

    @Override
    protected int getLayout() {
        return R.layout.activity_interaction;
    }


    @Override
    protected void initView() {
        interaction_web = (WebView) findViewById(R.id.interaction_web);

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


}
