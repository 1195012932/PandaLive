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
        WebSettings settings = webView.getSettings();
        //wv必须设置支持JavaScript
        settings.setJavaScriptEnabled(true);
        //设置自适应平面，两者合用
        settings.setUseWideViewPort(true);//图片调整到适应wv大小
        settings.setLoadWithOverviewMode(true);//缩放至屏幕大小
        //缩放操作
        settings.setSupportZoom(true);//支持缩放，是下面那个的前提
        settings.setBuiltInZoomControls(true);//设置内置的缩放控件
        settings.setDisplayZoomControls(false);//隐藏原生的缩放控件
        //其他细节操作
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//关闭wv缓存
        settings.setAllowFileAccess(true);//设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过Js打开新窗口
        settings.setLoadsImagesAutomatically(true);//支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //选择加载方式
        //1.加载一个网页
        webView.loadUrl("http://webapp.cctv.com/h5/travel/U80531QU7SY7.html");
        //2.加载apk中的HTML页面
        //wv.loadUrl("file:///android_assets/text.html");
        //3.加载手机本地的html页面
        //wv.loadUrl("content://"com.android.htmlfileprovider/sdcard/text.html");
        // 步骤3   调用shouldOverrideUrlLoading方法，使打开网页不用系统浏览器，在本页面显示
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


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
