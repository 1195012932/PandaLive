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
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.model.entity.home.KanDian;
import com.example.panda.model.entity.home.KanDianDao;
import com.example.panda.view.home.KanDianUtils;

import java.util.List;

public class BroadActivity extends AppCompatActivity {

    private WebView broadcast_web;
    private ImageView broadcast_sc;
    private TextView broadcast_fx;
    private KanDianDao look;
    private String name;
    private String titles;
    private String data;
    private String intExtra;
    boolean flag=true;
    private List<KanDian> list;
    private KanDian unique;
    private String titles1;
    private String time;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        initView();
        initweb();
    }
    private void initweb() {
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
    int count=0;
    int k=0;
    private void initView() {
        KanDianUtils ss = KanDianUtils.ss();
        look = ss.look(BroadActivity.this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        titles = intent.getStringExtra("title");
        data = intent.getStringExtra("data");
        intExtra = intent.getStringExtra("img");
        time = intent.getStringExtra("time");
        id = intent.getStringExtra("id");
        broadcast_web = (WebView) findViewById(R.id.broadcast_web);
        broadcast_sc = (ImageView) findViewById(R.id.broadcast_sc);
        broadcast_fx = (TextView) findViewById(R.id.broadcast_fx);
        broadcast_fx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent it=new Intent(BroadActivity.this,FenXiangActivity.class);

                startActivity(it);
            }
        });
        final List<KanDian> list1 = look.queryBuilder().build().list();
        for (int i = 0; i < list1.size(); i++) {
            if(titles.equals(list1.get(i).getTitle())){
                    k++;
            }else {
                count++;
            }
        }
        if(count== list1.size()){
            System.out.println("没有这个内容，可以增加");
            flag=true;
        }else{
            flag=false;
            broadcast_sc.setImageResource(R.drawable.collect_yes);
            System.out.println("有这个内容");
        }

        broadcast_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==true){
                    broadcast_sc.setImageResource(R.drawable.collect_yes);
                    look.insert(new KanDian(null,titles,data,intExtra,name,null));
                    Toast.makeText(BroadActivity.this, "已添加请到【我的收藏】中查看", Toast.LENGTH_SHORT).show();
                    flag=false;
                }else {
                    list = look.queryBuilder().build().list();
                    KanDian unique = look.queryBuilder().where(KanDianDao.Properties.Id.eq(list.get(0).getId())).build().unique();
                    look.delete(unique);
                    Toast.makeText(BroadActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                    broadcast_sc.setImageResource(R.drawable.collect_no);
                    flag=true;


                }

            }
        });
    }
}
