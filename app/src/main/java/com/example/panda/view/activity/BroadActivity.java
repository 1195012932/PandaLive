package com.example.panda.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.R;
import com.example.panda.model.entity.home.KanDian;
import com.example.panda.model.entity.home.KanDianDao;
import com.example.panda.view.home.KanDianUtils;

public class BroadActivity extends AppCompatActivity {

    private WebView broadcast_web;
    private ImageView broadcast_sc;
    private TextView broadcast_fx;
    private KanDianDao look;
    private String name;
    private String title;
    private int data;
    private String intExtra;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        initView();
        initweb();
    }

    private void initweb() {
        KanDianUtils ss = KanDianUtils.ss();
        look = ss.look(BroadActivity.this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        title = intent.getStringExtra("title");
        data = intent.getIntExtra("data", 1);
        intExtra = intent.getStringExtra("img");
        WebSettings settings = broadcast_web.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        // 设置出现缩放工具
        broadcast_web.getSettings().setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setLoadWithOverviewMode(true);

        broadcast_web.setWebChromeClient(new WebChromeClient());
        broadcast_web.loadUrl(name);


    }

    private void initView() {
        broadcast_web = (WebView) findViewById(R.id.broadcast_web);
        broadcast_sc = (ImageView) findViewById(R.id.broadcast_sc);
        broadcast_fx = (TextView) findViewById(R.id.broadcast_fx);
        broadcast_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==true){
                    broadcast_sc.setImageResource(R.drawable.collect_yes);
                    look.insert(new KanDian(null,title,data,intExtra,name));
                    flag=false;
                }

            }
        });
    }
}
