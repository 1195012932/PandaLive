package com.example.panda.view.activity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.zhy.android.percent.support.PercentLinearLayout;
import com.zhy.android.percent.support.PercentRelativeLayout;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoview_top;
    private ImageView image_fanhui;
    private TextView tv_broadtop_title;
    private ImageView image_broadtop_shouchang;
    private ImageView image_broadtop_fenxiang;
    private PercentRelativeLayout top;
    private ImageView image_broadtop_startAndstop;
    private TextView tv_timerplayer_timer;
    private SeekBar progredd_player_jindu;
    private TextView tv_player_timers;
    private Button btn_player_fenbianlv;
    private ImageView image_player_yinliang;
    private SeekBar progress_player_yinliang;
    private PercentLinearLayout down;
    private AudioManager am;
    private boolean state_shouchang = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //初始化Vitamio
        Vitamio.isInitialized(this);

        setContentView(R.layout.activity_play);
        initView();
        initListener();
    }

    private void initListener() {
        image_fanhui.setOnClickListener(this);
        image_broadtop_shouchang.setOnClickListener(this);
        image_broadtop_fenxiang.setOnClickListener(this);
        image_broadtop_startAndstop.setOnClickListener(this);
        image_player_yinliang.setOnClickListener(this);
    }

    private void initView() {
        videoview_top = (VideoView) findViewById(R.id.videoview_top);
        image_fanhui = (ImageView) findViewById(R.id.image_fanhui);
        tv_broadtop_title = (TextView) findViewById(R.id.tv_broadtop_title);
        image_broadtop_shouchang = (ImageView) findViewById(R.id.image_broadtop_shouchang);
        image_broadtop_fenxiang = (ImageView) findViewById(R.id.image_broadtop_fenxiang);
        top = (PercentRelativeLayout) findViewById(R.id.top);
        image_broadtop_startAndstop = (ImageView) findViewById(R.id.image_broadtop_startAndstop);
        tv_timerplayer_timer = (TextView) findViewById(R.id.tv_timerplayer_timer);
        progredd_player_jindu = (SeekBar) findViewById(R.id.progredd_player_jindu);
        tv_player_timers = (TextView) findViewById(R.id.tv_player_timers);
        btn_player_fenbianlv = (Button) findViewById(R.id.btn_player_fenbianlv);
        image_player_yinliang = (ImageView) findViewById(R.id.image_player_yinliang);
        progress_player_yinliang = (SeekBar) findViewById(R.id.progress_player_yinliang);
        down = (PercentLinearLayout) findViewById(R.id.down);

        progressBar_yinliang();
    }


    //通过SeekBar设置系统音量
    private void progressBar_yinliang() {

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //获取系统最大音量
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        progress_player_yinliang.setMax(maxVolume);
        //获取当前音量
        int currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        progress_player_yinliang.setProgress(currentVolume);
        //Seekbar的拖动监听
        progress_player_yinliang.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    //设置系统音量
                    am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    int currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                    seekBar.setProgress(currentVolume);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_fanhui:
                finish();
                break;
            case R.id.image_broadtop_shouchang:
                shouCang();
                break;
            case R.id.image_broadtop_fenxiang:
                share();
                break;
            case R.id.image_broadtop_startAndstop:

                break;
            case R.id.image_player_yinliang:
                if (progress_player_yinliang.getProgress() > 0){
                    progress_player_yinliang.setProgress(0);
                    image_player_yinliang.setImageResource(R.drawable.ic_volume_off_white_36dp);
                    am.setStreamVolume(AudioManager.STREAM_MUSIC,0,0);
                }
                break;
        }
    }

    private void share() {

    }

    private void shouCang() {
        if (state_shouchang) {
            image_broadtop_shouchang.setImageResource(R.drawable.play_fullsrcee_collect_true);
            state_shouchang = false;
//                    Toast.makeText(BroadDetail_TopActivity.this, "已收藏，请到我的收藏查看", Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(PlayActivity.this, "已收藏，请到我的收藏查看", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            image_broadtop_shouchang.setImageResource(R.drawable.play_fullsrcee_collect);
            state_shouchang = true;
            Toast toast = Toast.makeText(PlayActivity.this, "已取消收藏", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
