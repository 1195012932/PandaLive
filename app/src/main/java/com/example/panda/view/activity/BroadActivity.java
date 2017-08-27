package com.example.panda.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.R;

public class BroadActivity extends AppCompatActivity {

    private WebView broadcast_web;
    private ImageView broadcast_sc;
    private TextView broadcast_fx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        initView();
        initweb();
    }

    private void initweb() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        WebSettings settings = broadcast_web.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        broadcast_web.setWebChromeClient(new WebChromeClient());
        broadcast_web.loadUrl(name);

    }

    private void initView() {
        broadcast_web = (WebView) findViewById(R.id.broadcast_web);
        broadcast_sc = (ImageView) findViewById(R.id.broadcast_sc);
        broadcast_fx = (TextView) findViewById(R.id.broadcast_fx);
    }
}
