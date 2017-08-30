package com.example.panda.view.activity.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.base.BaseActivity;

/**
 * Created by XXASUS on 2017/8/30.
 */

public class InteracationWeb extends BaseActivity {

    private WebView webView;
    private ImageView finshimage;


    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        Log.e("TAG", "传过来的HTML: " + url);

        webView = (WebView) findViewById(R.id.web_webview);
        finshimage = (ImageView) findViewById(R.id.web_fish);
        TextView titletext = (TextView) findViewById(R.id.web_title);
        titletext.setText(title);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = webView.getSettings();
        //设置自适应屏幕
        webSettings.setUseWideViewPort(true);//将图片设置到合适webview的大小
        webSettings.setLoadWithOverviewMode(true);//缩放至屏幕的大小

        finshimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
