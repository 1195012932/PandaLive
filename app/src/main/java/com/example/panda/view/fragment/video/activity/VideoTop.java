package com.example.panda.view.fragment.video.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.presenter.video.VideoTopPre;
import com.example.panda.presenter.video.VideoTopPreImpl;
import com.example.panda.utils.OkHttpsManner;
import com.example.panda.view.fragment.video.CustomMediaController;
import com.example.panda.view.fragment.video.VideoTopView;
import com.example.panda.view.fragment.video.entity.VideoTopBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class VideoTop extends AppCompatActivity implements VideoTopView, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    private static final String TAG = "VdieoTop";
    private AudioManager am;
    private boolean state_shouchang = true;
    private String title;

    private String urls;
    private VideoTopPre videoTop;
    private VideoView buffer;
    private ProgressBar probar;
    private TextView download_rate;
    private TextView load_rate;
    private VideoView mVideoView;
    private CustomMediaController mCustomMediaController;
    private ProgressBar pb;
    private TextView downloadRateView;
    private TextView loadRateView;
    private Uri uri;
    private String mapurl;
    private LinearLayout custom_listener;
    private VideoTopBean videoTopBean;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = VideoTop.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //必须写这个，初始化加载库文件
        Vitamio.isInitialized(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        urls = intent.getStringExtra("url_top");
        initView();
    }

    /**
     * 销毁播放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    private void initView() {
        videoTop = new VideoTopPreImpl(this);
        Map<String, String> map = new HashMap<>();
        map.put("param", "http://115.182.9.189/api/");
        map.put("pid", urls);
        mVideoView = (VideoView) findViewById(R.id.buffer);
        mCustomMediaController = new CustomMediaController(this, mVideoView, this);
        mCustomMediaController.setVideoName(title);
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
        custom_listener = (LinearLayout) findViewById(R.id.custom_listener2);
        videoTop.getData(map);
        Toast.makeText(VideoTop.this, urls, Toast.LENGTH_SHORT).show();
        System.out.println("12346789" + urls);

        String mapurl = "http://115.182.35.91/api/getVideoInfoForCBox.do?pid=" + urls;
        OkHttpsManner.getInstance().getNetData(mapurl, new OkHttpsManner.CallBacks() {
            @Override
            public void getString(String ss) {
                Gson gson = new Gson();
                videoTopBean = gson.fromJson(ss, VideoTopBean.class);
                videoTopBean.getTitle();
                url = videoTopBean.getVideo().getChapters().get(0).getUrl();
                Log.e(TAG, "getString:" + url);
                initData(url);
            }
        });
    }

    //初始化数据
    private void initData(String url) {
        String s="http://3811.liveplay.myqcloud.com/live/m3u8/3811_channel1519.m3u8?AUTH=6TNpqHqQWOfJtdaFb+d0QsBvUKA9Abr0ziu2MeW0w/Vs1Zx9v5egU6BxwKlyqnk+6m/63igekKQaEAiU8L5aQg==LIVE-FLV-CDN-FW";
        uri = Uri.parse(url);
        Log.e(TAG, "initData: " + uri);
        mVideoView.setVideoPath(url);//设置视频播放地址
        //mVideoView.setVideoURI(uri);
        mCustomMediaController.show(5000);
        mVideoView.setMediaController(mCustomMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        mVideoView.setOnCompletionListener(dismiss);
    }

    //注册在媒体文件播放完毕时调用的回调函数。
    private MediaPlayer.OnCompletionListener dismiss = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            custom_listener.setVisibility(View.VISIBLE);
            pb.setVisibility(View.GONE);
            if (mVideoView.isPlaying()) {
                custom_listener.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVideoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public void onShowTop3(List<VideoTopBean.VideoBean.ChaptersBean> been) {
      /*  String url = been.get(0).getUrl();
        Log.e(TAG, "视频地址1" + url);
        initData(url);*/
    }

    @Override
    public void onShowTop2(VideoTopBean.VideoBean been) {
        mapurl = been.getChapters()
                .get(0).getUrl();
        //Toast.makeText(this, mapurl, Toast.LENGTH_SHORT).show();
//        Log.e(TAG, "视频地址2" + mapurl);
    }

    @Override
    public void OnShow(VideoTopBean videoTopBean) {

        Log.e("hahahahha------", "OnShow: " + videoTopBean.getTitle());
    }

    @Override
    public void onError(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mVideoView != null) {
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }
}
