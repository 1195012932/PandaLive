package com.example.panda.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.panda.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LivePandaActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    private VideoView live_panda;
    private Intent intent;
    private String liveurl;
    private String titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_panda);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = LivePandaActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //必须写这个，初始化加载库文件
        Vitamio.isInitialized(this);
        intent = getIntent();
        liveurl = intent.getStringExtra("liveurl");
        titles = intent.getStringExtra("titles");
        initView();
    }

    private void initView() {
        live_panda = (VideoView) findViewById(R.id.live_panda);
        live_panda.setVideoPath(liveurl);
        live_panda.setOnPreparedListener(this);
        live_panda.setMediaController(new MediaController(this));

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        live_panda.start();

    }


    @Override
    protected void onDestroy() {
        live_panda.setVideoPath("");
        live_panda.stopPlayback();
        super.onDestroy();
    }
}
